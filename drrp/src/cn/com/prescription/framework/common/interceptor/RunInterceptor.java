/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.common.interceptor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.com.prescription.framework.StandardConstantsIF;
import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.framework.action.form.AbstractForm;
import cn.com.prescription.framework.action.form.StandartForm;
import cn.com.prescription.framework.message.MessageUtils;
import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.framework.util.LogUtils;
import cn.com.prescription.framework.util.ReflectUtils;
import cn.com.prescription.framework.util.ServiceUtils;
import cn.com.prescription.framework.util.StringUtils;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.config.entities.ActionConfig;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 実行制御インターセプター.
 * 
 * @author tyc
 */
public class RunInterceptor extends AbstractInterceptor {

    /** 串行版本号 */
    private static final long serialVersionUID = 1L;

    /**
     * インターセプト処理.
     * 
     * @param _invocation action呼出
     * @return 処理結果
     * @throws Exception 予期せぬ错误発生時
     */
    @Override
    public String intercept(ActionInvocation _invocation) throws Exception {

        // プロキシ
        ActionProxy proxy_ = _invocation.getProxy();
        // 実行元情報
        ActionConfig config_ = proxy_.getConfig();

        // tメソッド名
        String methodName = proxy_.getMethod();

        // 処理開始時間
        long startMillis_ = System.currentTimeMillis();
        // 処理開始ログ
        LogUtils.start(config_.getClassName(), methodName);

        try {

            // 実行
            return _invocation.invoke();

        } catch (Exception e) {
            // ログ出力
            LogUtils.error(e.getMessage(), e);
            // 再スロー
            throw e;

        } finally {
            // 処理終了ログ
            LogUtils.end(config_.getClassName(), methodName);
            // 処理終了
            LogUtils.process(config_.getClassName(), methodName, startMillis_, System.currentTimeMillis());
        }

    }

    /**
     * データソースの再設定処理
     * 
     * @param invocation action呼出
     * @throws Exception 予期せぬ错误発生時
     */
    protected void doResetDataSource(ActionInvocation invocation) throws Exception {

        HttpServletRequest request = ServletActionContext.getRequest();
        ActionProxy proxy = invocation.getProxy();
        StandartForm form = null;
        String formName = "";
        Field[] fieldItems = ServletActionContext.getValueStack(request).getRoot().get(0).getClass()
            .getDeclaredFields();

        for (Field fieldItem : fieldItems) {
            Object formObject = request.getAttribute(fieldItem.getName());
            if (formObject instanceof AbstractForm) {
                formName = fieldItem.getName();
                form = (StandartForm) request.getAttribute(formName);

                doInitForm(form, proxy.getConfig().getClassName(), request, proxy.getMethod().toLowerCase());
            }
        }

        AbstractForm resultForm = ServiceUtils.getSession(invocation.getProxy().getConfig().getClassName());

        if (resultForm != null) {
            resultForm.setPdfFilePath(null);
            resultForm.getPdfFilePathList().clear();
            ServiceUtils.putSession(invocation.getProxy().getConfig().getClassName(), resultForm);
        }
    }

    /**
     * formの準備処理
     * 
     * @param _formObject 対象form
     * @param _sessionKey セッションキー
     * @param _request
     * @param _methodName
     * @throws Exception
     */

    protected void doInitForm(StandartForm _formObject, String _sessionKey, HttpServletRequest _request,
                    String _methodName) throws Exception {

        List<String> attributeNames = new ArrayList<String>();
        Method[] declaredMethods = _formObject.getClass().getDeclaredMethods();
        for (int i = 0; i < declaredMethods.length; i++) {
            attributeNames.add(declaredMethods[i].getName());
        }

        // セッションからform的取得。
        StandartForm sesionForm = ServiceUtils.getSession(_sessionKey);
        if (sesionForm != null) {
            Field[] fieldItems = _formObject.getClass().getDeclaredFields();
            for (Field fieldItem : fieldItems) {

                String fieldName = fieldItem.getName();
                char firstChar = fieldName.charAt(0);
                String uFirstChar = String.valueOf(Character.toUpperCase(firstChar));
                String getMethod = "get" + fieldName.replaceFirst(String.valueOf(firstChar), uFirstChar);
                String setterMethod = "set" + fieldName.replaceFirst(String.valueOf(firstChar), uFirstChar);

                if (attributeNames.contains(getMethod) && attributeNames.contains(setterMethod)) {
                    Object sessionFieldObject = ReflectUtils.getFieldValueByName(sesionForm, fieldItem.getName());
                    if (sessionFieldObject instanceof Iterable<?>
                                    && ReflectUtils.isRestore(sesionForm, fieldItem.getName())) {
                        ReflectUtils.setFieldValueByName(_formObject, fieldItem.getName(), sessionFieldObject);
                    }
                }
            }

            // _formObject.setInitSerialForm(sesionForm.getInitSerialForm());

            // if (CheckUtils.isEqual("dobeforeback", _methodName)) {
            // String newSerialForm = JSONUtil.serialize((_formObject)).replaceAll("null", "\"\"");
            // _formObject.setBackFlag(true);
            // if (CheckUtils.isEqual(_formObject.getInitSerialForm(), newSerialForm)) {
            // _formObject.setBackConfirmMessageFlag(false);
            // } else {
            // _formObject.setBackConfirmMessageFlag(true);
            // }
            // } else {
            // _formObject.setBackFlag(false);
            // }
        }
    }
}

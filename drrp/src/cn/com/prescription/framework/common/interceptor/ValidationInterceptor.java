/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.common.interceptor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.config.entities.ActionConfig;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.framework.action.ActionMessages;
import cn.com.prescription.framework.action.form.AbstractForm;
import cn.com.prescription.framework.action.form.StandartForm;
import cn.com.prescription.framework.exception.SystemException;
import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.framework.util.LogUtils;
import cn.com.prescription.framework.util.ReflectUtils;
import cn.com.prescription.framework.util.ServiceUtils;
import cn.com.prescription.framework.util.ValidateCheckUtils;

/**
 * actionインターセプター.
 * 
 * @author nttdc
 */
public class ValidationInterceptor extends AbstractInterceptor {

    /** 串行版本号 */
    private static final long serialVersionUID = 1L;

    /** 行削除tag key */
    public static final String TAG_DELROW_KEY = "tag_delRow_filter_subFormNULL_key";

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
        char firstChar = methodName.charAt(0);
        char uFirstChar = Character.toUpperCase(firstChar);
        String validateMethodName = "validate"
                        + methodName.replaceFirst(String.valueOf(firstChar), String.valueOf(uFirstChar));

        // 処理開始時間
        long startMillis_ = System.currentTimeMillis();
        // 処理開始ログ
        LogUtils.start(config_.getClassName(), validateMethodName);

        try {
            filterSubFormNULL();
            doBeforeInvocation(_invocation);
            // 実行
            return _invocation.invoke();

        } catch (Exception e) {
            // ログ出力
            LogUtils.error(e.getMessage(), e);
            // 再スロー
            throw e;

        } finally {
            // 処理終了ログ
            LogUtils.end(config_.getClassName(), validateMethodName);
            // 処理終了
            LogUtils.process(config_.getClassName(), validateMethodName, startMillis_, System.currentTimeMillis());
        }

    }

    /**
     * バリデーション処理
     * 
     * @param invocation action呼出
     * @throws Exception 予期せぬ错误発生時
     */
    protected void doBeforeInvocation(ActionInvocation invocation) throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        ActionProxy proxy = invocation.getProxy();
        String methodName = proxy.getActionName() + "#" + proxy.getMethod();

        StandartForm form = null;
        String formName = "";
        Field[] fieldItems = ServletActionContext.getValueStack(request).getRoot().get(0).getClass()
            .getDeclaredFields();
        for (Field fieldItem : fieldItems) {
            Object formObject = request.getAttribute(fieldItem.getName());
            if (formObject instanceof AbstractForm) {
                formName = fieldItem.getName();
                form = (StandartForm) request.getAttribute(formName);

                doInitForm(form, formName, proxy.getConfig().getClassName(), request, proxy.getMethod().toLowerCase());

                String[] actionName = (proxy.getConfig().getClassName()).split("\\.");

                StringBuffer buffer_ = new StringBuffer();
                String validationName = "";
                for (int i = 0; i < actionName.length - 2; i++) {

                    buffer_.append(validationName);
                    buffer_.append(actionName[i]);
                    buffer_.append("/");

                }
                validationName = buffer_.toString();
                buffer_ = new StringBuffer();
                buffer_.append(validationName);
                buffer_.append("config/validation-config-");
                buffer_.append(actionName[actionName.length - 3]);
                buffer_.append("_zh_CN.xml");

                validationName = buffer_.toString();
                editFieldError(invocation, ValidateCheckUtils.check(validationName, methodName, form), formName);
                break;

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
     * @param formObject 対象form
     * @throws Exception
     */
    protected void doInitForm(StandartForm formObject, String formName, String sessionKey, HttpServletRequest request,
                    String _methodName) throws Exception {

        List<String> attributeNames = new ArrayList<String>();
        Method[] declaredMethods = formObject.getClass().getDeclaredMethods();
        for (int i = 0; i < declaredMethods.length; i++) {
            attributeNames.add(declaredMethods[i].getName());
        }

        // セッションからform的取得。
        StandartForm sesionForm = ServiceUtils.getSession(sessionKey);
        if (sesionForm != null) {
            Field[] fieldItems = formObject.getClass().getDeclaredFields();
            for (Field fieldItem : fieldItems) {

                String fieldName = fieldItem.getName();
                char firstChar = fieldName.charAt(0);
                String uFirstChar = String.valueOf(Character.toUpperCase(firstChar));
                String getMethod = "get" + fieldName.replaceFirst(String.valueOf(firstChar), uFirstChar);
                String setterMethod = "set" + fieldName.replaceFirst(String.valueOf(firstChar), uFirstChar);

                if (attributeNames.contains(getMethod) && attributeNames.contains(setterMethod)) {
                    Object sessionFieldObject = ReflectUtils.getFieldValueByName(sesionForm, fieldName);
                    if (sessionFieldObject instanceof Iterable<?>
                                    && ReflectUtils.isRestore(sesionForm, fieldItem.getName())) {
                        ReflectUtils.setFieldValueByName(formObject, fieldItem.getName(), sessionFieldObject);
                    }
                }
            }

            // formObject.setInitSerialForm(sesionForm.getInitSerialForm());
            // if (CheckUtils.isEqual("dobeforeback", _methodName)) {
            // String newSerialForm = JSONUtil.serialize((formObject)).replaceAll("null", "\"\"");
            // formObject.setBackFlag(true);
            // if (CheckUtils.isEqual(formObject.getInitSerialForm(), newSerialForm)) {
            // formObject.setBackConfirmMessageFlag(false);
            // } else {
            // formObject.setBackConfirmMessageFlag(true);
            // }
            // } else {
            // formObject.setBackFlag(false);
            // }
        }
    }

    /**
     * フィールド错误编辑
     * 
     * @param _errorMessages action错误メッセージ
     * @param _formName form名
     */
    private void editFieldError(ActionInvocation invocation, ActionMessages _errorMessages, String _formName) {
        ActionProxy proxy = invocation.getProxy();
        AbstractAction abstractAction = (AbstractAction) proxy.getAction();
        // int i = 0;
        for (String key : _errorMessages.getMessages().keySet()) {
            // 指定フィールドのみ、最大表示件数まで
            ListIterator<?> interator = _errorMessages.getMessages().get(key).listIterator();
            while (interator.hasNext()) {
                String message = (String) interator.next();
                if (CheckUtils.isEmpty(_formName)) {
                    abstractAction.addFieldError(key, message);
                } else {
                    abstractAction.addFieldError(_formName + "." + key, message);
                }
                // i++;
            }
        }
    }

    /**
     * subForm编辑
     * 
     * @throws SystemException
     */
    private void filterSubFormNULL() throws SystemException {
        HttpServletRequest request = ServletActionContext.getRequest();
        // formName = fieldItem.getName();
        String objKeyStr = (String) request.getParameter(TAG_DELROW_KEY);
        if (!CheckUtils.isEmpty(objKeyStr)) {
            String[] objKeys = objKeyStr.split(",");
            for (int i = 0; i < objKeys.length; i++) {
                String objKey = objKeys[i];
                if (!CheckUtils.isEmpty(objKey)) {
                    filterSubFormNULL(request, objKey);
                }
            }

        }
    }

    /**
     * subForm编辑
     * 
     * @param request
     * @param objKey
     * @throws SystemException
     */
    private void filterSubFormNULL(HttpServletRequest request, String objKey) throws SystemException {
        String[] names = objKey.split("\\.");
        String formName = names[0];
        Object formObj = request.getAttribute(formName);
        Object subFormObj = null;
        for (int i = 1; i < names.length; i++) {
            String subFormName = names[i];
            if (formObj == null) {
                break;
            } else {
                subFormObj = ReflectUtils.getProperty(formObj, subFormName);
                formObj = subFormObj;
            }
        }
        if (subFormObj instanceof List) {
            List<?> subForm = (List<?>) subFormObj;
            if (subFormObj != null) {
                for (int j = subForm.size() - 1; j >= 0; j--) {
                    if (subForm.get(j) == null) {
                        subForm.remove(j);
                    }
                }
            }
        }
    }

}

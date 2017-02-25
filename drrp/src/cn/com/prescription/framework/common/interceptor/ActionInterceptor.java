/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.common.interceptor;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.config.entities.ActionConfig;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import cn.com.prescription.framework.CodeValueRecord;
import cn.com.prescription.framework.StandardConstantsIF;
import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.framework.action.form.AbstractForm;
import cn.com.prescription.framework.action.form.StandartForm;
import cn.com.prescription.framework.action.form.UploadFileDto;
import cn.com.prescription.framework.common.component.dialog.DialogAction;
import cn.com.prescription.framework.common.session.UserSessionInfo;
import cn.com.prescription.framework.common.session.UserSessionUtils;
import cn.com.prescription.framework.event.exception.EventAjaxApplicationException;
import cn.com.prescription.framework.event.exception.EventValidateException;
import cn.com.prescription.framework.exception.ApplicationException;
import cn.com.prescription.framework.exception.SystemException;
import cn.com.prescription.framework.message.MessageUtils;
import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.framework.util.LogUtils;
import cn.com.prescription.framework.util.ReflectUtils;
import cn.com.prescription.framework.util.ServiceUtils;
import cn.com.prescription.framework.util.StringUtils;

/**
 * actionインターセプター.
 * 
 * @author nttdc
 */
public class ActionInterceptor extends AbstractInterceptor {

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

        // 処理開始時間
        long startMillis_ = System.currentTimeMillis();
        // 処理開始ログ
        LogUtils.start(config_.getClassName(), proxy_.getMethod());

        // セッション用户情報を取得
        UserSessionInfo userInfo_ = UserSessionUtils.getUserSessionInfo();

        // 画面ID
        String gamenID = "";
        String methodName = "";

        // 実行を指示した画面の画面情報をログファイルに出力する
        Matcher matcher_ = Pattern.compile("([a-z]{3})+(\\d{5,})").matcher(config_.getName());
        if (userInfo_ != null && matcher_.find()) {
            // 画面ID
            gamenID = matcher_.group(1) + matcher_.group(2);

            if (isEnableOut(gamenID)) {
                List<String> infoList = new ArrayList<String>();
                infoList.add(gamenID.toUpperCase());
                // 画面名
                infoList.add(StringUtils.defaultString(MessageUtils.getSimpleMessage("gamen_name_" + gamenID)));
                // メッソド名
                String methodCode = "execute";
                if (methodCode.equals(proxy_.getMethod())) {
                    methodCode = "doInit";
                } else {
                    methodCode = proxy_.getMethod();
                }
                methodName = MessageUtils.getSimpleMessage("accessLog.event." + methodCode);

                // メッソド名存在の場合
                if (!CheckUtils.isEmpty(methodName)) {
                    infoList.add(methodName);
                    // 操作ログ空行を入れる
                    LogUtils.debugOperate("", false);
                    // マーク
                    LogUtils.debugOperate("********************", false);
                    // 画面ID、画面名、メッソド名を出力する
                    LogUtils.debugOperate(StringUtils.join(infoList.toArray(), ','), true);
                }
            }

        }

        try {

            doBeforeInvocation(_invocation);

            // ＵＲＬチェック
            HttpServletRequest request = ServletActionContext.getRequest();

            List<String> urlList = new ArrayList<String>();
            // 無視URLメソッドリスト
            if (!CheckUtils.isEmpty(StandardConstantsIF.KYOTU_IGNORE_URLMETHOD)) {
                urlList = Arrays.asList(StandardConstantsIF.KYOTU_IGNORE_URLMETHOD.split(","));
            }
            // GETメソッド
            if ("GET".equals(request.getMethod().toUpperCase())) {

                for (String url : urlList) {

                    // チェック対象
                    if (request.getRequestURI().contains(url)) {
                        boolean errFlg = false;

                        // パラメータ無しの場合
                        Enumeration names = request.getParameterNames();

                        while (names.hasMoreElements()) {
                            // パラメータ名
                            String name = (String) names.nextElement();

                            if (!CheckUtils.isEmpty(request.getParameter(name))) {
                                errFlg = false;
                                break;
                            } else {
                                errFlg = true;
                            }
                        }

                        if (errFlg) {
                            return Action.SUCCESS;
                        }
                    }
                }

            }
            // 実行
            String result = _invocation.invoke();

            StandartForm sessionForm = getSessionForm(proxy_);
            if (sessionForm != null) {
                ServiceUtils.putSession(config_.getClassName(), sessionForm);

                // 画面情報操作ログを出力する
                if (!CheckUtils.isEmpty(methodName) && isEnableOut(gamenID)) {
                    try {
                        outFormInfo(sessionForm, gamenID, true);
                    } catch (Exception e) {
                        LogUtils.error(e.getMessage(), e);
                    }
                }
            }

            return result;

        } catch (EventAjaxApplicationException e) {
            // AJAX错误
            applicationExceptionSetting(_invocation, e, false);
            // 終わらせる
            return Action.SUCCESS;
        } catch (EventValidateException e) {
            // ログ出力
            LogUtils.debug(e.getMessage(), e);
            // 错误設定
            eventValidateExceptionSetting(_invocation);
            return Action.ERROR;
        } catch (ApplicationException e) {
            // ログ出力
            LogUtils.warn(e.getMessage(), e);
            // 错误設定
            applicationExceptionSetting(_invocation, e, true);
            // 错误終了
            return Action.ERROR;

        } catch (Exception e) {
            StandartForm sessionForm = getSessionForm(proxy_);
            if (sessionForm != null) {
                // 画面情報出力
                try {
                    outFormInfo(sessionForm, gamenID, true);
                } catch (Exception e1) {
                    LogUtils.error(e1.getMessage(), e1);
                }
            }
            // ログ出力
            LogUtils.error(e.getMessage(), e);
            // 再スロー
            throw e;

        } finally {

            // 処理終了ログ
            LogUtils.end(config_.getClassName(), proxy_.getMethod());
            // 処理終了
            LogUtils.process(config_.getClassName(), proxy_.getMethod(), startMillis_, System.currentTimeMillis());
        }

    }

    /**
     * アプリケーション错误処理
     * 
     * @param _invocation action呼出
     * @param _exception アプリケーション例外
     * @param _isExclamation 業務错误标识設定
     */
    private void applicationExceptionSetting(ActionInvocation _invocation, ApplicationException _exception,
                    boolean _isExclamation) {

        // action取得
        AbstractAction action_ = (AbstractAction) _invocation.getAction();

        // 错误标识設定
        action_.setIsExclamation(_isExclamation);

        // 错误メッセージ設定（s:actionerror で出力されないようフィールドを分けた）
        action_.setMessages(_exception.getErrorMessages());

        // コールバックファンクション（PopUpError.javaで取り出す）
        ServiceUtils.putSession(DialogAction.CALLBACK_FUNCTION_SESSION_KEY, _exception.getCallBackFunction());

        // 「OK」「はい」action（dialog.jspで取り出す）
        ServiceUtils.putSession(DialogAction.OK_ACTION_SESSION_KEY, _exception.getOkAction());

        // 「いいえ」action（dialog.jspで取り出す）
        ServiceUtils.putSession(DialogAction.CANCEL_ACTION_SESSION_KEY, _exception.getCancelAction());

    }

    /**
     * eventバリデーション错误処理
     * 
     * @param _invocation action呼出
     */
    private void eventValidateExceptionSetting(ActionInvocation _invocation) {

        // action取得
        AbstractAction action_ = (AbstractAction) _invocation.getAction();

        action_.addActionError(action_.getActionMessage());

    }

    /**
     * form取得処理
     * 
     * @param _gamenID 画面ID
     * @return form
     */
    private StandartForm getSessionForm(ActionProxy _proxy) throws Exception {
        ActionConfig _config = _proxy.getConfig();
        String _gamenID = _config.getName();
        String actionID = _gamenID.split("Action")[0].toLowerCase();
        StandartForm resultForm = null;
        HttpServletRequest request = ServletActionContext.getRequest();
        Class<? extends Object> className = ServletActionContext.getValueStack(request).getRoot().get(0).getClass();
        Field[] fieldItems = className.getDeclaredFields();
        for (Field fieldItem : fieldItems) {
            Object formObject = request.getAttribute(fieldItem.getName());
            if (formObject instanceof AbstractForm) {
                StandartForm currentForm = (StandartForm) request.getAttribute(fieldItem.getName());

                // 画面ID
                String formID = currentForm.getClass().getSimpleName().toString().split("Form")[0].toLowerCase();
                if (actionID.equals(formID)) {
                    resultForm = currentForm;
                    break;
                }
            }
        }
        return resultForm;
    }

    /**
     * 画面情報ログ出力
     * 
     * @param _form シリアルform
     * @param _gamenId 画面ID
     * @param _flg 标识
     * @throws SystemException
     */
    private void outFormInfo(StandartForm _form, String _gamenId, boolean _flg) throws SystemException {

        List<Field> fieldList = new ArrayList<Field>(); // 画面項目リスト
        List<Field> subFormList = new ArrayList<Field>(); // 画面明細項目リスト
        List<String> logList = new ArrayList<String>(); // ログリスト

        // 抽象formのフィールド的取得
        Field[] fields = _form.getClass().getFields();
        for (Field field : fields) {
            fieldList.add(field);
        }
        // フィールド的取得
        fields = _form.getClass().getDeclaredFields();
        for (Field field : fields) {
            // formに明細存在チェック
            if (!field.getName().contains("subForm")) {
                fieldList.add(field);
            } else {
                subFormList.add(field);
            }
        }

        // ログ内容の编辑
        for (Field field : fieldList) {
            StringBuffer logBuf = new StringBuffer();
            StringBuffer idBuf = new StringBuffer();

            // キー
            idBuf.append(_gamenId);
            idBuf.append("_");
            idBuf.append(field.getName());

            // 項目漢字名的取得
            String name = MessageUtils.getSimpleMessage(idBuf.toString());

            // データソース以外 かつ 漢字名存在の場合、項目内容を出力する
            if (!CheckUtils.isEmpty(name) && !field.getName().contains("DataSource")) {
                // 項目漢字名的取得
                logBuf.append(name);
                logBuf.append("=\"");
                logBuf.append(getValue(_form, field.getName(), null, idBuf.toString()));
                logBuf.append("\"");
                // ログ
                logList.add(logBuf.toString());
            }
        }
        // 操作ログ出力の場合
        if (_flg == true) {
            // ログ内容を出力する
            for (int i = 0; i < logList.size(); i = i + 10) {
                // １０項目で改行する。
                if ((logList.size() - i) > 10) {
                    LogUtils.debugOperate(StringUtils.join(logList.subList(i, i + 10).toArray(), ','), false);
                } else {
                    LogUtils.debugOperate(StringUtils.join(logList.subList(i, logList.size()).toArray(), ','), false);
                }
            }
        } else {
            // 系统错误の場合
            for (int i = 0; i < logList.size(); i = i + 10) {
                // １０項目で改行する。
                if ((logList.size() - i) > 10) {
                    LogUtils.error(StringUtils.join(logList.subList(i, i + 10).toArray(), ','), null);
                } else {
                    LogUtils.error(StringUtils.join(logList.subList(i, logList.size()).toArray(), ','), null);
                }
            }
        }

        // 明細ログ内容の编辑
        for (Field lstField : subFormList) {
            // 明細リスト的取得
            List<Object> subForm = ReflectUtils.getProperty(_form, lstField.getName());

            if (subForm != null) {
                for (Object dto : subForm) {
                    logList = new ArrayList<String>();
                    fieldList = new ArrayList<Field>();

                    // 抽象DTOのフィールド的取得
                    Field[] dtoFields = dto.getClass().getFields();
                    for (Field field : dtoFields) {
                        fieldList.add(field);
                    }
                    // DTOのフィールド的取得
                    dtoFields = dto.getClass().getDeclaredFields();
                    for (Field field : dtoFields) {
                        fieldList.add(field);
                    }

                    // DTOログ内容の编辑
                    for (Field field : fieldList) {
                        StringBuffer logBuf = new StringBuffer();
                        StringBuffer idBuf = new StringBuffer();

                        // キー
                        idBuf.append(_gamenId);
                        idBuf.append("_");
                        idBuf.append(lstField.getName());
                        idBuf.append("_");
                        idBuf.append(field.getName());

                        // 項目漢字名
                        String name = MessageUtils.getSimpleMessage(idBuf.toString());

                        // 項目漢字名存在の場合
                        if (!CheckUtils.isEmpty(name) && !field.getName().contains("DataSource")) {
                            // ログ内容の编辑
                            logBuf.append(name);
                            logBuf.append("=\"");
                            logBuf.append(getValue(_form, field.getName(), dto, idBuf.toString()));
                            logBuf.append("\"");
                            // ログ
                            logList.add(logBuf.toString());
                        }
                    }

                    // 操作ログ出力の場合
                    if (_flg == true) {
                        // 明細行項目ログ出力
                        LogUtils.debugOperate(StringUtils.join(logList.toArray(), ','), false);
                    } else {
                        // 明細行項目ログ出力
                        LogUtils.error(StringUtils.join(logList.toArray(), ','), null);
                    }

                }
            }
        }
    }

    /**
     * 画面項目値取得
     * 
     * @param _form 画面form
     * @param _name 項目名
     * @param _dto 明細データ
     * @param _key データソースキー
     * @return 値
     * @throws SystemException
     */
    private static String getValue(Object _form, String _name, Object _dto, String _key) throws SystemException {

        Object value = null; // 値
        String result = "";

        if (_dto == null) {
            // formのプロパティー的取得
            value = ReflectUtils.getProperty(_form, _name);
        } else {
            // DTOのプロパティー的取得
            value = ReflectUtils.getProperty(_dto, _name);
        }

        // プロパティー存在の場合
        if (value != null) {
            // キー
            StringBuffer tmpBuf = new StringBuffer();
            tmpBuf.append(_key);
            tmpBuf.append("DataSource");

            // データソース名的取得
            String dataSource = MessageUtils.getSimpleMessage(tmpBuf.toString());

            // データソース存在チェック
            if (!CheckUtils.isEmpty(dataSource)) {
                List<CodeValueRecord> records = null;
                try {
                    records = ReflectUtils.getProperty(_form, dataSource);
                } catch (Exception e) {
                }
                if (_dto != null) {
                    try {
                        records = ReflectUtils.getProperty(_dto, dataSource);
                    } catch (Exception e) {
                    }
                }
                if (records != null) {
                    result = getCdName(value, records);
                } else {
                    result = StringUtils.objectToString(value);
                }
            } else {
                // ファイル上传の場合
                if (value instanceof UploadFileDto) {
                    UploadFileDto dto = (UploadFileDto) value;
                    result = dto.getFileDisPath();
                } else {
                    result = StringUtils.objectToString(value);
                }
            }
        }

        return result;
    }

    /**
     * 操作ログ許可チェック.
     * 
     * @param _gamenId 画面ID
     * @return true:許可画面ID
     */
    private boolean isEnableOut(String _gamenId) {

        // 画面ID
        for (String disEnableGamenId : StringUtils.split(StandardConstantsIF.KYOTU_SOSA_LOG_SHUTSURYOKU_TAISHO_GAI_LIST,
            "|")) {
            // チェック
            if (CheckUtils.isEqual(disEnableGamenId, _gamenId)) {
                return false;
            }
        }

        // 許可画面ID
        return true;
    }

    /**
     * コード名称取得
     * 
     * @param _cd コード
     * @param _records データソース
     * @return コード名称
     */
    private static String getCdName(Object _cd, List<CodeValueRecord> _records) {

        List<String> result = new ArrayList<String>();
        // コードの存在チェック
        if (_cd != null) {
            // データソース存在チェック
            if (_records != null) {
                if (_cd.getClass().isArray()) {
                    for (int i = 0; i < Array.getLength(_cd); i++) {
                        // コード名称的取得
                        for (CodeValueRecord record : _records) {
                            if (CheckUtils.isEqual(record.getRecordCode(),
                                StringUtils.defaultString(Array.get(_cd, i)))) {
                                result.add(record.getRecordValue());
                                break;
                            }
                        }
                    }
                } else {
                    // コード名称的取得
                    for (CodeValueRecord record : _records) {
                        if (CheckUtils.isEqual(record.getRecordCode(), _cd.toString())) {
                            result.add(record.getRecordValue());
                            break;
                        }
                    }
                }

            } else {
                result.add(_cd.toString());
            }
        }
        return StringUtils.join(result.toArray(), ',');
    }

    /**
     * インターセプト前処理
     * 
     * @param _invocation action呼出
     * @throws Exception 予期せぬ错误発生時
     */
    protected void doBeforeInvocation(ActionInvocation _invocation) throws Exception {
        // プロキシ
        ActionProxy proxy_ = _invocation.getProxy();
        // メッソド名
        String methodCode = proxy_.getMethod();
        if ("doSearch".equals(methodCode)) {
            HttpServletRequest request = ServletActionContext.getRequest();

            Field[] fieldItems = ServletActionContext.getValueStack(request).getRoot().get(0).getClass()
                .getDeclaredFields();
            for (Field fieldItem : fieldItems) {
                Object formObject = request.getAttribute(fieldItem.getName());
                if (formObject instanceof AbstractForm) {
                    StandartForm form = (StandartForm) request.getAttribute(fieldItem.getName());
                    ReflectUtils.setFieldValueByName(form, "pageStartRowNo", 0);
                    break;
                }
            }
        }

    }
}

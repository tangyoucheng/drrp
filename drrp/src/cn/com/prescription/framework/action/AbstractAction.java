/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.action;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.prescription.framework.action.form.AbstractForm;
import cn.com.prescription.framework.action.form.StandartForm;
import cn.com.prescription.framework.biz.DbExclusiveChainInfo;
import cn.com.prescription.framework.common.component.dialog.DialogAction;
import cn.com.prescription.framework.common.component.download.DownloadAction;
import cn.com.prescription.framework.common.session.UserSessionUtils;
import cn.com.prescription.framework.event.StandardEvent;
import cn.com.prescription.framework.event.StandardEventResult;
import cn.com.prescription.framework.event.exception.EventApplicationException;
import cn.com.prescription.framework.exception.ApplicationException;
import cn.com.prescription.framework.exception.SystemException;
import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.framework.util.ReflectUtils;
import cn.com.prescription.framework.util.ServiceUtils;
import cn.com.prescription.framework.util.StringUtils;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.intra_mart.framework.base.event.EventResult;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 標準の抽象action.
 * 
 * @author nttdc
 */
public abstract class AbstractAction extends ActionSupport implements ServletRequestAware, ServletResponseAware,
                SessionAware {

    /** 串行版本号 */
    private static final long serialVersionUID = -6474525967036477217L;

    /** action错误メッセージ */
    private List<String> actionMessage = null;

    /** リクエスト */
    protected HttpServletRequest request = null;

    /** レスポンス */
    protected HttpServletResponse response = null;

    /** セッション */
    protected Map<String, Object> session = null;

    /** event結果 */
    private EventResult eventResult = null;

    /** 「！」标识 */
    private Boolean isExclamation = false;

    /** ダウンロード标识 */
    private Boolean isDownload = false;

    /** 登録完了ダイアログ标识 */
    private Boolean isCompleteDialog = false;

    /** メッセージ */
    private Collection<String> messages = null;

    /**
     * 「登録・変更画面」の戻るチェックメソッド.
     * 
     * @return 処理結果
     * @throws Exception 予期せぬ错误発生時
     */
    public String doBeforeBack() throws Exception {
        return SUCCESS;
    }

    /**
     * 標準の実行メソッド.
     * 
     * @return 処理結果
     * @throws Exception 予期せぬ错误発生時
     */
    @Override
    public String execute() throws Exception {
        return doInit();
    }

    /**
     * 初期化処理
     * 
     * @return 処理結果
     * @throws Exception 予期せぬ错误発生時
     */
    public abstract String doInit() throws Exception;

    /**
     * 指定されたアプリケーションID、eventキーからeventオブジェクトを生成する.
     * <p>
     * StandardEventを拡張する場合に使用する。
     * </p>
     * 
     * @param _application アプリケーションID
     * @param _key eventキー
     * @return event
     * @throws SystemException 予期せぬ错误発生時
     */
    private StandardEvent doCreateEvent(String _application, String _key) throws SystemException {
        // event生成
        return ServiceUtils.createEvent(_application, _key);
    }

    // /**
    // * 指定されたアプリケーションID、eventキーから指定されたeventオブジェクトを生成しeventを実行する。
    // * <p>
    // * StandardEventを利用する場合に使用する。<br>
    // * eventオブジェクトへのパラメータ設定は、保持されている入力情報、ページング検索条件が設定される。 入力情報の変更、検索条件の生成が必要な場合はevent実行前に行っておくこと。
    // * </p>
    // *
    // * @param _application アプリケーションID
    // * @param _key eventキー
    // * @param _form form
    // * @throws ApplicationException 应用程序产生异常的情况スローされる 系统例外发生的情况スローされる
    // * @throws SystemException 予期せぬ错误発生時
    // */
    // protected void doDispatchEvent(String _application, String _key, AbstractForm _form) throws ApplicationException,
    // SystemException {
    //
    // // event生成
    // StandardEvent event_ = doCreateEvent(_application, _key);
    //
    // // パラメータ設定
    // event_.setForm(_form);
    //
    // // event実行
    // doDispatchEvent(event_);
    //
    // }

    /**
     * 指定されたアプリケーションID、eventキーから指定されたeventオブジェクトを生成しeventを実行する。
     * <p>
     * StandardEventを利用する場合に使用する。<br>
     * eventオブジェクトへのパラメータ設定は、保持されている入力情報、ページング検索条件が設定される。 入力情報の変更、検索条件の生成が必要な場合はevent実行前に行っておくこと。
     * </p>
     * 
     * @param _form form
     * @param _class Logcクラス
     * @throws ApplicationException 应用程序产生异常的情况スローされる 系统例外发生的情况スローされる
     * @throws SystemException 予期せぬ错误発生時
     * @throws LoginErrorException 登录错误発生時
     */
    protected void doDispatchEvent(AbstractForm _form, Class<?> _class) throws ApplicationException, SystemException {
        doDispatchEvent(_form, _class, false);
    }

    /**
     * 指定されたアプリケーションID、eventキーから指定されたeventオブジェクトを生成しeventを実行する。
     * <p>
     * StandardEventを利用する場合に使用する。<br>
     * eventオブジェクトへのパラメータ設定は、保持されている入力情報、ページング検索条件が設定される。 入力情報の変更、検索条件の生成が必要な場合はevent実行前に行っておくこと。
     * </p>
     * 
     * @param _form form
     * @param _class Logcクラス
     * @param _isFindDepartmentLogic 組織個別ロジック検索
     * @throws ApplicationException 应用程序产生异常的情况スローされる 系统例外发生的情况スローされる
     * @throws SystemException 予期せぬ错误発生時
     * @throws LoginErrorException 登录错误発生時
     */
    protected void doDispatchEvent(AbstractForm _form, Class<?> _class, Boolean _isFindDepartmentLogic)
                    throws ApplicationException, SystemException {

        // event生成
        StandardEvent event_ = doCreateEvent("cn.com.prescription.framework.event.common", "standard");

        // 組織個別ロジック
        if (_isFindDepartmentLogic && UserSessionUtils.getUserSessionInfo() != null) {

            // クラス名パターン
            Matcher matcher_ = Pattern.compile("^([A-Z]+[0-9]+)(.+)$").matcher(_class.getSimpleName());

            // 検索
            if (matcher_.matches()) {

                // 組織ID付のクラス名
                String deptClass_ = new StringBuffer(_class.getPackage().getName()).append(".")
//                    .append(matcher_.group(1)).append(UserSessionUtils.getShozokuCd().toUpperCase())
                    .append(matcher_.group(1))
                    .append(matcher_.group(2)).toString();

                // クラスを生成
                _class = ReflectUtils.newClass(deptClass_);

            }

        }

        // パラメータ設定
        event_.setForm(_form);
        event_.setObject(_class);

        // event実行
        doDispatchEvent(event_);

    }

    /**
     * event実行。
     * 
     * @param _event event処理に引き渡すBeanクラス
     * @throws ApplicationException 应用程序产生异常的情况スローされる
     * @throws SystemException 系统例外发生的情况スローされる
     * @throws LoginErrorException 登录错误発生時
     */
    private void doDispatchEvent(StandardEvent _event) throws ApplicationException, SystemException {

        try {

            // event実行
            StandardEventResult result_ = ServiceUtils.dispatchEvent(_event);

            // ダウロード継続标识
            if (result_.getIsDownload()) {
                // ダウンロード标识を設定
                this.isDownload = true;
                // URLにパスを見せないよう、セッションを経由してダウンロード処理に渡す
                this.session.put(DownloadAction.SESSION_KEY_DOWNLOAD_INFO, result_);
            }

            // 登録完了ダイアログ标识
            if (result_.getIsCompleteDialog()) {
                // 登録完了ダイアログ标识を設定
                this.isCompleteDialog = true;
                // 「OK」押下時action
                this.session.put(DialogAction.OK_ACTION_SESSION_KEY, result_.getActionInfo());
            }

            // メッセージ
            this.messages = result_.getMessages();

            // 排他错误が設定されたが errorEnd が呼ばれなかった場合に備えてセッションクリア（通常はバグだが・・）
            if (!isCallAjaxAction()) {
                ServiceUtils.removeSession(DbExclusiveChainInfo.SESSION_KEY);
            }

            // event実行
            setEventResult(result_);

        } catch (EventApplicationException e) {
            // errorEnd()での例外はそのままthrow
            throw e;
        } catch (ApplicationException e) {
            // その他のアプリケーション错误はメッセージを設定
            this.setActionMessage(e.getErrorMessages());
            // 再スロー
            throw e;
        }

    }

    /**
     * @param _actionError 追加する错误メッセージ
     */
    public void addActionError(List<String> _actionError) {
        for (String error : _actionError) {
            this.addActionError(error);
        }
    }

    /**
     * @param _actionMessage 設定するメッセージ
     */
    public void setActionMessage(List<String> _actionMessage) {
        this.actionMessage = _actionMessage;
    }

    /**
     * @param _actionMessage 追加するメッセージ
     */
    public void addActionMessage(List<String> _actionMessage) {
        for (String message : _actionMessage) {
            this.addActionMessage(message);
        }
    }

    /**
     * @return actionメッセージ
     */
    public List<String> getActionMessage() {
        return actionMessage;
    }

    /**
     * 項目错误（複数項目指定）
     * 
     * @param _fieldNames 错误項目名リスト
     * @param _message 错误メッセージ
     */
    public void addFieldError(Collection<String> _fieldNames, String _message) {
        // 全ての項目でメッセージ追加
        for (String fieldName_ : _fieldNames) {
            // 通常の項目错误追加
            this.addFieldError(fieldName_, _message);
            // 错误メッセージは初回のみ設定
            _message = null;
        }
    }

    /**
     * @param _request リクエスト
     */
    public void setServletRequest(HttpServletRequest _request) {
        this.request = _request;
    }

    /**
     * @param _response レスポンス
     */
    public void setServletResponse(HttpServletResponse _response) {
        this.response = _response;
    }

    /**
     * @param _session セッション
     */
    public void setSession(Map<String, Object> _session) {
        this.session = _session;
    }

    /**
     * formをセッションへ保存する。
     * 
     * @param _form 出力情報
     */
    protected void saveFormObject(StandartForm _form) {
        session.put(this.getClassName(), _form);
    }

    /**
     * セッションへ保存されたform的取得。
     * 
     * @return セッションへ保存されたコントローラオブジェクト
     */
    protected StandartForm getSaveFormObject() {
        return (StandartForm) session.get(this.getClassName());
    }

    /**
     * @param _eventResult event処理結果
     */
    public void setEventResult(EventResult _eventResult) {
        this.eventResult = _eventResult;
    }

    /**
     * @return event処理結果
     */
    public EventResult getEventResult() {
        return eventResult;
    }

    /**
     * @return 実行クラス名
     */
    private String getClassName() {
        return this.getClass().getName();
    }

    /**
     * @return 「！」标识
     */
    public final Boolean getIsExclamation() {
        return isExclamation;
    }

    /**
     * @param _isExclamation 「！」标识
     */
    public final void setIsExclamation(Boolean _isExclamation) {
        isExclamation = _isExclamation;
    }

    /**
     * @return メッセージ
     */
    public final Collection<String> getMessages() {
        return messages;
    }

    /**
     * @param _messages メッセージ
     */
    public final void setMessages(Collection<String> _messages) {
        messages = _messages;
    }

    /**
     * @return メッセージ
     */
    public String getMessage() {
        return messages == null ? "" : StringUtils.join(messages.toArray(), '\n');
    }

    /**
     * @return ダウンロード開始标识
     */
    public final Boolean getIsDownload() {
        return isDownload;
    }

    /**
     * @param _isDownload ダウンロード開始标识
     */
    public final void setIsDownload(Boolean _isDownload) {
        isDownload = _isDownload;
    }

    /**
     * @return 登録完了ダイアログ标识.
     */
    public final Boolean getIsCompleteDialog() {
        return isCompleteDialog;
    }

    /**
     * @param _isCompleteDialog 登録完了ダイアログ标识.
     */
    public final void setIsCompleteDialog(Boolean _isCompleteDialog) {
        isCompleteDialog = _isCompleteDialog;
    }

    /**
     * @param _errorMessages action错误メッセージ
     * @param _formName form名
     */
    protected void convertMessageToFieldError(ActionMessages _errorMessages, String _formName) {
        // int i = 0;
        for (String key : _errorMessages.getMessages().keySet()) {
            // 指定フィールドのみ、最大表示件数まで
            ListIterator<?> interator = _errorMessages.getMessages().get(key).listIterator();
            while (interator.hasNext()) {
                String message = (String) interator.next();
                if (CheckUtils.isEmpty(_formName)) {
                    addFieldError(key, message);
                } else {
                    addFieldError(_formName + "." + key, message);
                }
                // i++;
            }
        }
    }

    /**
     * AJAX処理判定.
     * 
     * @return TRUE:当処理はAJAX呼出
     */
    private Boolean isCallAjaxAction() {
        // action名により判定
        return ActionContext.getContext().getName().toLowerCase().endsWith("json");
    }

}

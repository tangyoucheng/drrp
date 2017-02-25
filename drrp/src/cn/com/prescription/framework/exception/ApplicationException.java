/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.exception;

import java.util.ArrayList;
import java.util.List;

import cn.com.prescription.framework.biz.ActionInfo;

/**
 * サービスフレームワークアプリケーション例外。
 * <p>
 * サービスフレームワーク内にてアプリケーション例外を生成する場合はこの例外クラスをスローする。
 * </p>
 *
 * @author t.d.m
 */
public class ApplicationException extends org.intra_mart.framework.system.exception.ApplicationException {

    /**
     * 串行版本号
     */
    private static final long serialVersionUID = 5120912413704313431L;

    /**
     * 例外の発生原因となったメッセージリスト。
     */
    private List<String> errorMessages = new ArrayList<String>();

    /**
     * ダイアログボタン押下時に実行するコールバックファンクション名
     */
    private String callBackFunction = null;

    /**
     * 「OK」または「はい」押下時に実行するaction
     */
    private ActionInfo okAction = null;

    /**
     * 「いいえ」押下時に実行するaction
     */
    private ActionInfo cancelAction = null;

    /**
     * ApplicationException を構築します。
     */
    public ApplicationException() {
        super();
    }

    /**
     * 指定された詳細メッセージを示す ApplicationException を構築します。
     *
     * @param _message 詳細メッセージ
     */
    public ApplicationException(String _message) {
        super(_message);
    }

    /**
     * 指定された原因を持つ ApplicationException を構築します。
     *
     * @param _throwable 原因
     */
    public ApplicationException(Throwable _throwable) {
        super(_throwable);
    }

    /**
     * 指定された詳細メッセージと原因を持つ ApplicationException を構築します.
     *
     * @param _message 詳細メッセージ
     * @param _throwable 原因
     */
    public ApplicationException(String _message, Throwable _throwable) {
        super(_message, _throwable);
    }

    /**
     * 例外の発生原因となったメッセージリストを取得します。
     *
     * @return メッセージリスト
     */
    public List<String> getErrorMessages() {
        return errorMessages;
    }

    /**
     * 例外の発生原因となったメッセージリストを設定します。
     *
     * @param _errorMessages メッセージリスト
     */
    public void setErrorMessages(List<String> _errorMessages) {
        errorMessages = _errorMessages;
    }

    /**
     * @return ダイアログボタン押下時に実行するコールバックファンクション名
     */
    public final String getCallBackFunction() {
        return callBackFunction;
    }

    /**
     * @param _callBackFunction ダイアログボタン押下時に実行するコールバックファンクション名
     */
    public final void setCallBackFunction(String _callBackFunction) {
        callBackFunction = _callBackFunction;
    }

    /**
     * @return 「OK」または「はい」押下時に実行するaction
     */
    public final ActionInfo getOkAction() {
        return okAction;
    }

    /**
     * @param _okAction 「OK」または「はい」押下時に実行するaction
     */
    public final void setOkAction(ActionInfo _okAction) {
        okAction = _okAction;
    }

    /**
     * @return 「いいえ」押下時に実行するaction
     */
    public final ActionInfo getCancelAction() {
        return cancelAction;
    }

    /**
     * @param _cancelAction 「いいえ」押下時に実行するaction
     */
    public final void setCancelAction(ActionInfo _cancelAction) {
        cancelAction = _cancelAction;
    }
}

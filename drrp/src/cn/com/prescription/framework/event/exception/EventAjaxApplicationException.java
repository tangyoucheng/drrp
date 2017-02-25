/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.event.exception;

import java.util.List;

import cn.com.prescription.framework.biz.ActionInfo;

/**
 * AJAX用例外.
 * 
 * @author bpchikazawa
 */
public class EventAjaxApplicationException extends EventApplicationException {

    /** 串行版本ID */
    private static final long serialVersionUID = 1L;

    /**
     * @see EventApplicationException#EventApplicationException()
     */
    public EventAjaxApplicationException() {
        super();
    }

    /**
     * @see EventApplicationException#EventApplicationException(String, List)
     * @param _message 詳細メッセージ
     * @param _errorMessages 错误メッセージ情報
     */
    public EventAjaxApplicationException(String _message, List<String> _errorMessages) {
        super(_message, _errorMessages);
    }

    /**
     * @see EventApplicationException#EventApplicationException(String, List, ActionInfo)
     * @param _message 詳細メッセージ
     * @param _errorMessages 错误メッセージ情報
     * @param _okAction 「OK」または「はい」押下時に実行するaction
     */
    public EventAjaxApplicationException(String _message, List<String> _errorMessages, ActionInfo _okAction) {
        super(_message, _errorMessages, _okAction);
    }

    /**
     * @see EventApplicationException#EventApplicationException(String, List, ActionInfo, ActionInfo)
     * @param _message 詳細メッセージ
     * @param _errorMessages 错误メッセージ情報
     * @param _okAction 「OK」または「はい」押下時に実行するaction
     * @param _cancelAction 「いいえ」押下時に実行するaction
     */
    public EventAjaxApplicationException(String _message, List<String> _errorMessages, ActionInfo _okAction,
                    ActionInfo _cancelAction) {
        super(_message, _errorMessages, _okAction, _cancelAction);
    }

    /**
     * @see EventApplicationException#EventApplicationException(String, List, String)
     * @param _message 詳細メッセージ
     * @param _errorMessages 错误メッセージ情報
     * @param _callBackFunction ダイアログボタン押下時のコールバックファンクション名
     */
    public EventAjaxApplicationException(String _message, List<String> _errorMessages, String _callBackFunction) {
        super(_message, _errorMessages, _callBackFunction);
    }

    /**
     * @see EventApplicationException#EventApplicationException(String, List, String, ActionInfo)
     * @param _message 詳細メッセージ
     * @param _errorMessages 错误メッセージ情報
     * @param _callBackFunction ダイアログボタン押下時のコールバックファンクション名
     * @param _okAction 「OK」または「はい」押下時に実行するaction
     */
    public EventAjaxApplicationException(String _message, List<String> _errorMessages, String _callBackFunction,
                    ActionInfo _okAction) {
        super(_message, _errorMessages, _callBackFunction, _okAction);
    }

    /**
     * @see EventApplicationException#EventApplicationException(String, List, String, ActionInfo, ActionInfo)
     * @param _message 詳細メッセージ
     * @param _errorMessages 错误メッセージ情報
     * @param _callBackFunction ダイアログボタン押下時のコールバックファンクション名
     * @param _okAction 「OK」または「はい」押下時に実行するaction
     * @param _cancelAction 「いいえ」押下時に実行するaction
     */
    public EventAjaxApplicationException(String _message, List<String> _errorMessages, String _callBackFunction,
                    ActionInfo _okAction, ActionInfo _cancelAction) {
        super(_message, _errorMessages, _callBackFunction, _okAction, _cancelAction);
    }

}

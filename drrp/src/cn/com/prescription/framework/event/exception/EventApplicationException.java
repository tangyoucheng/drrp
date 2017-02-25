/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.event.exception;

import java.util.List;

import cn.com.prescription.framework.biz.ActionInfo;
import cn.com.prescription.framework.exception.ApplicationException;

/**
 * eventフレームワークアプリケーション例外。
 * <p>
 * eventフレームワーク内にてアプリケーション例外を生成する場合はこの例外クラスをスローする。
 * </p>
 * 
 * @author t.d.m
 */
public class EventApplicationException extends ApplicationException {

    /** 串行版本号 */
    private static final long serialVersionUID = -5257939607470825922L;

    /**
     * EventApplicationException を構築します。
     */
    public EventApplicationException() {
        super();
    }

    /**
     * 指定された詳細メッセージを示す EventApplicationException を構築します。
     * 
     * @param _message 詳細メッセージ
     * @param _errorMessages 错误メッセージ情報
     */
    public EventApplicationException(String _message, List<String> _errorMessages) {
        this(_message, _errorMessages, "", (ActionInfo) null, (ActionInfo) null);
    }

    /**
     * 指定された詳細メッセージを示す EventApplicationException を構築します。
     * 
     * @param _message 詳細メッセージ
     * @param _errorMessages 错误メッセージ情報
     * @param _okAction 「OK」または「はい」押下時に実行するaction
     */
    public EventApplicationException(String _message, List<String> _errorMessages, ActionInfo _okAction) {
        this(_message, _errorMessages, "", _okAction, (ActionInfo) null);
    }

    /**
     * 指定された詳細メッセージを示す EventApplicationException を構築します。
     * 
     * @param _message 詳細メッセージ
     * @param _errorMessages 错误メッセージ情報
     * @param _okAction 「OK」または「はい」押下時に実行するaction
     * @param _cancelAction 「いいえ」押下時に実行するaction
     */
    public EventApplicationException(String _message, List<String> _errorMessages, ActionInfo _okAction,
                    ActionInfo _cancelAction) {
        this(_message, _errorMessages, "", _okAction, _cancelAction);
    }

    /**
     * 指定された詳細メッセージを示す EventApplicationException を構築します。
     * 
     * @param _message 詳細メッセージ
     * @param _errorMessages 错误メッセージ情報
     * @param _callBackFunction ダイアログボタン押下時のコールバックファンクション名
     */
    public EventApplicationException(String _message, List<String> _errorMessages, String _callBackFunction) {
        this(_message, _errorMessages, _callBackFunction, (ActionInfo) null, (ActionInfo) null);
    }

    /**
     * 指定された詳細メッセージを示す EventApplicationException を構築します。
     * 
     * @param _message 詳細メッセージ
     * @param _errorMessages 错误メッセージ情報
     * @param _callBackFunction ダイアログボタン押下時のコールバックファンクション名
     * @param _okAction 「OK」または「はい」押下時に実行するaction
     */
    public EventApplicationException(String _message, List<String> _errorMessages, String _callBackFunction,
                    ActionInfo _okAction) {
        this(_message, _errorMessages, _callBackFunction, _okAction, (ActionInfo) null);
    }

    /**
     * 指定された詳細メッセージを示す EventApplicationException を構築します。
     * 
     * @param _message 詳細メッセージ
     * @param _errorMessages 错误メッセージ情報
     * @param _callBackFunction ダイアログボタン押下時のコールバックファンクション名
     * @param _okAction 「OK」または「はい」押下時に実行するaction
     * @param _cancelAction 「いいえ」押下時に実行するaction
     */
    public EventApplicationException(String _message, List<String> _errorMessages, String _callBackFunction,
                    ActionInfo _okAction, ActionInfo _cancelAction) {
        super(_message);
        setErrorMessages(_errorMessages);
        setCallBackFunction(_callBackFunction);
        setOkAction(_okAction);
        setCancelAction(_cancelAction);
    }

}

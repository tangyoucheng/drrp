/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.event.exception;

import java.util.ArrayList;

import cn.com.prescription.framework.exception.ApplicationException;

/**
 * バリデーション错误例外.
 *
 * @author nttdc
 */
public class EventValidateException extends ApplicationException {

    /** 串行版本号 */
    private static final long serialVersionUID = 1L;

    /**
     * EventApplicationException を構築します。
     */
    public EventValidateException() {
        super();
    }

    /**
     * 指定された詳細メッセージを示す EventValidatorException を構築します。
     *
     * @param _message 詳細メッセージ
     * @param _errorMessages 错误メッセージ情報
     */
    public EventValidateException(String _message, ArrayList<String> _errorMessages) {
        super(_message);
        setErrorMessages(_errorMessages);
    }

    /**
     * 指定された詳細メッセージと原因を持つ EventValidatorException を構築します。
     *
     * @param _message 詳細メッセージ
     * @param _errorMessages 错误メッセージ情報
     * @param _throwable 原因
     */
    public EventValidateException(String _message, ArrayList<String> _errorMessages, Throwable _throwable) {
        super(_message, _throwable);
        setErrorMessages(_errorMessages);
    }

}

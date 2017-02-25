/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.event.exception;

import cn.com.prescription.framework.exception.SystemException;

/**
 * eventフレームワーク系统例外。
 * <p>
 * eventフレームワーク内にて系统例外を生成する場合はこの例外クラスをスローする。
 * </p>
 * 
 * @author t.d.m
 */
public class EventSystemException extends SystemException {

    /**
     * 串行版本号
     * 
     * @since 1.0
     */
    private static final long serialVersionUID = 1111802912683240757L;

    /**
     * EventSystemException を構築します。
     */
    public EventSystemException() {
        super();
    }

    /**
     * 指定された詳細メッセージを示す EventSystemException を構築します.
     * 
     * @param _message 詳細メッセージ
     */
    public EventSystemException(String _message) {
        super(_message);
    }

    /**
     * 指定された原因を持つ EventSystemException を構築します。
     * 
     * @param _throwable 原因
     */
    public EventSystemException(Throwable _throwable) {
        super(_throwable);
    }

    /**
     * 指定された詳細メッセージと原因を持つ EventSystemException を構築します。
     * 
     * @param _message 詳細メッセージ
     * @param _throwable 原因
     */
    public EventSystemException(String _message, Throwable _throwable) {
        super(_message, _throwable);
    }

}

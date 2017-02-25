/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.action.validator;

import cn.com.prescription.framework.exception.SystemException;

/**
 * バリデータマネージャ内で処理が失敗した場合にこの例外をスローします。
 * 
 * @author t.d.m
 */
public class ValidatorManagerException extends SystemException {

    /**
     * 串行版本号
     */
    private static final long serialVersionUID = -5096505333457532153L;

    /**
     * 詳細メッセージを指定しないで <code>ValidatorManagerException</code> を構築します。
     */
    public ValidatorManagerException() {
        super();
    }

    /**
     * 指定された詳細メッセージを持つ <code>ValidatorManagerException</code> を構築します。
     * 
     * @param message 詳細メッセージ
     */
    public ValidatorManagerException(String message) {
        super(message);
    }

    /**
     * この例外の発生原因となる例外を持つ <code>ValidatorManagerException</code> を構築します。<BR>
     * 原因が存在する場合は、原因の詳細メッセージを詳細メッセージに引き継ぎます。
     * 
     * @param cause 原因
     */
    public ValidatorManagerException(Throwable cause) {
        super(cause == null ? null : cause.getMessage(), cause);
    }

    /**
     * 指定された詳細メッセージとこの例外の発生原因となる例外を持つ<br>
     * <code>ValidatorManagerException</code> を構築します。
     * 
     * @param message 詳細メッセージ
     * @param cause 原因
     */
    public ValidatorManagerException(String message, Throwable cause) {
        super(message, cause);
    }

}

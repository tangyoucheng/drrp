/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.exception;

import java.util.ArrayList;
import java.util.Arrays;

import cn.com.prescription.framework.biz.ActionInfo;
import cn.com.prescription.framework.event.exception.EventApplicationException;

/**
 * データベース排他错误例外.
 * 
 * @author bpchikazawa
 */
public class DbExclusiveException extends EventApplicationException {

    /**
     * 串行版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * DbExclusiveException を構築します。
     */
    public DbExclusiveException() {
        super();
    }

    /**
     * 指定された詳細メッセージを示す DbExclusiveException を構築します。
     * 
     * @param _message 詳細メッセージ
     * @param _errorMessage 错误メッセージ情報
     */
    public DbExclusiveException(String _message, String _errorMessage) {
        super(_message, Arrays.asList(_errorMessage));
    }

    /**
     * 指定された詳細メッセージを示す DbExclusiveException を構築します。
     * 
     * @param _message 詳細メッセージ
     * @param _errorMessages 错误メッセージ情報
     */
    public DbExclusiveException(String _message, ArrayList<String> _errorMessages) {
        super(_message, _errorMessages);
    }

    /**
     * 指定された詳細メッセージを示す DbExclusiveException を構築します。
     * 
     * @param _message 詳細メッセージ
     * @param _errorMessages 错误メッセージ情報
     * @param _okAction 「OK」または「はい」押下時に実行するaction
     */
    public DbExclusiveException(String _message, ArrayList<String> _errorMessages, ActionInfo _okAction) {
        super(_message, _errorMessages, _okAction);
    }

    /**
     * 指定された詳細メッセージを示す DbExclusiveException を構築します。
     * 
     * @param _message 詳細メッセージ
     * @param _errorMessages 错误メッセージ情報
     * @param _okAction 「OK」または「はい」押下時に実行するaction
     * @param _cancelAction 「いいえ」押下時に実行するaction
     */
    public DbExclusiveException(String _message, ArrayList<String> _errorMessages, ActionInfo _okAction,
                    ActionInfo _cancelAction) {
        super(_message, _errorMessages, _okAction, _cancelAction);
    }

}

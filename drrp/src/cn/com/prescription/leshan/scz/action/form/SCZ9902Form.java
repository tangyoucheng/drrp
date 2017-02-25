/*
 * Copyright(c) 2011 
 */

package cn.com.prescription.leshan.scz.action.form;

import cn.com.prescription.framework.action.form.AbstractForm;

/**
 * 画面ロック機能 form。
 * 
 * @author t.d.m
 */
/*
 * 新規作成 DATE: 2010/05/26 NAME: t.d.m
 */
public class SCZ9902Form extends AbstractForm {

    /**
     * 串行版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String userName = null;

    /**
     * 密码
     */
    private String password = null;

    /**
     * メッセージ
     */
    private String message = null;

    /**
     * 画面ロック機能 コントローラオブジェクト的构造。
     */
    public SCZ9902Form() {
        super();
    }

    /**
     * 用户名を取得します。
     * 
     * @return 用户名
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * 用户名を設定します。
     * 
     * @param _userName 用户名
     */
    public void setUserName(String _userName) {
        this.userName = _userName;
    }

    /**
     * 密码を取得します。
     * 
     * @return 密码
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * 密码を設定します。
     * 
     * @param _password 密码
     */
    public void setPassword(String _password) {
        this.password = _password;
    }

    /**
     * メッセージを取得します。
     * 
     * @return メッセージ
     */
    public String getMessage() {
        return message;
    }

    /**
     * メッセージを設定します。
     * 
     * @param message メッセージ
     */
    public void setMessage(String message) {
        this.message = message;
    }
}

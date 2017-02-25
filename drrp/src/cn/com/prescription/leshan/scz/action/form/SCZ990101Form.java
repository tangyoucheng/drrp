/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.leshan.scz.action.form;

import java.util.ArrayList;
import java.util.List;

import cn.com.prescription.framework.action.form.AbstractForm;

/**
 * SCZ990101 门户画面 form。
 * 
 * @author kourei
 */
/*
 * 新規作成 DATE: 2010.03.10 NAME: kourei
 */
public class SCZ990101Form extends AbstractForm {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private String userId = null;

    /**
     * 用户名
     */
    private String userName = null;

    /**
     * クッキー読みFLG
     */
    private String cookieFlg = null;

    /**
     * 一覧データ
     */
    private List<List<SCZ990101Dto>> subForm = new ArrayList<List<SCZ990101Dto>>();

    /**
     * クライアントIPアドレス
     */
    private String ipAddr = null;

    /**
     * 登录form 的构造。
     */
    public SCZ990101Form() {
        super();
    }

    /**
     * 用户ID的取得。
     * @return 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 用户ID的设定。
     * @param userId 用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 用户名的取得。
     * @return 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 用户名的设定。
     * @param _userName 用户名
     */
    public void setUserName(String _userName) {
        this.userName = _userName;
    }

    /**
     * クッキー読みFLG的取得。
     * @return クッキー読みFLG
     */
    public String getCookieFlg() {
        return cookieFlg;
    }

    /**
     * クッキー読みFLG的设定。
     * @param cookieFlg クッキー読みFLG
     */
    public void setCookieFlg(String cookieFlg) {
        this.cookieFlg = cookieFlg;
    }

    /**
     * 一覧データ的取得。
     * @return 一覧データ
     */
    public List<List<SCZ990101Dto>> getSubForm() {
        return subForm;
    }

    /**
     * 一覧データ的设定。
     * @param subForm 一覧データ
     */
    public void setSubForm(List<List<SCZ990101Dto>> subForm) {
        this.subForm = subForm;
    }

    /**
     * クライアントIPアドレス的取得。
     * @return クライアントIPアドレス
     */
    public String getIpAddr() {
        return ipAddr;
    }

    /**
     * クライアントIPアドレス的设定。
     * @param ipAddr クライアントIPアドレス
     */
    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

}

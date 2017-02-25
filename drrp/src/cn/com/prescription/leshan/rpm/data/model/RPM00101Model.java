/*
 * Copyright(c) 2016 
 */

package cn.com.prescription.leshan.rpm.data.model;

import cn.com.prescription.leshan.common.data.model.RpmUserModel;

/**
 * 登录画面のモードル
 * 
 * @author mhy
 */
/*
 * 新規作成
 * DATE: 2016.03.20 NAME: tyc
 */
public class RPM00101Model extends RpmUserModel {

    /**
     * 串行版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String userName = null;

    /**
     * 身份ID
     */
    private String role_id = null;


    /**
     * 登录画面的构造。
     */
    public RPM00101Model() {
        super();
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
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 身份ID的取得。
     * @return 身份ID
     */
    public String getRoleId() {
        return role_id;
    }

    /**
     * 身份ID的设定。
     * @param role_id 身份ID
     */
    public void setRoleId(String role_id) {
        this.role_id = role_id;
    }
}

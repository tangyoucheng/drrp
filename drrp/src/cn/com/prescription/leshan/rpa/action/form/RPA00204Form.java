/*
 * Copyright(c) 2016 
 */

package cn.com.prescription.leshan.rpa.action.form;

import java.util.ArrayList;
import java.util.List;

import cn.com.prescription.framework.action.form.AbstractForm;

/**
 * 患者一览 form。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.09 NAME: fsb
 */
public class RPA00204Form extends AbstractForm {

    /**
     * 串行版本UID
     */
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
     * 生日
     */
    private String birthday = null;

    /**
     * 性别ID
     */
    private String sexId = null;

    /**
     * 邮政番号
     */
    private String postNumber = null;

    /**
     * 地址
     */
    private String addr = null;

    /**
     * 座机
     */
    private String phoneNumber = null;

    /**
     * 手机
     */
    private String ceelNumber = null;

    /**
     * 电子邮箱
     */
    private String email = null;

    /**
     * 页面类型
     */
    private String pageType = null;

    /**
     * subForm1
     */
    private List<RPA0020401Dto> subForm1 = new ArrayList<RPA0020401Dto>();

    /**
     * 用户ID的取得
     * 
     * @return 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 用户ID的设定
     * 
     * @param _userId 用户ID
     */
    public void setUserId(String _userId) {
        this.userId = _userId;
    }

    /**
     * 用户名的取得
     * 
     * @return 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 用户名的设定
     * 
     * @param _userName 用户名
     */
    public void setUserName(String _userName) {
        this.userName = _userName;
    }

    /**
     * 生日的取得
     * 
     * @return 生日
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * 生日的设定
     * 
     * @param _birthday 生日
     */
    public void setBirthday(String _birthday) {
        this.birthday = _birthday;
    }

    /**
     * 性别ID的取得
     * 
     * @return 性别ID
     */
    public String getSexId() {
        return sexId;
    }

    /**
     * 性别ID的设定
     * 
     * @param _sexId 性别ID
     */
    public void setSexId(String _sexId) {
        this.sexId = _sexId;
    }

    /**
     * 邮政番号的取得
     * 
     * @return 邮政番号
     */
    public String getPostNumber() {
        return postNumber;
    }

    /**
     * 邮政番号的设定
     * 
     * @param _postNumber 邮政番号
     */
    public void setPostNumber(String _postNumber) {
        this.postNumber = _postNumber;
    }

    /**
     * 地址的取得
     * 
     * @return 地址
     */
    public String getAddr() {
        return addr;
    }

    /**
     * 地址的设定
     * 
     * @param _addr 地址
     */
    public void setAddr(String _addr) {
        this.addr = _addr;
    }

    /**
     * 座机的取得
     * 
     * @return 座机
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 座机的设定
     * 
     * @param _phoneNumber 座机
     */
    public void setPhoneNumber(String _phoneNumber) {
        this.phoneNumber = _phoneNumber;
    }

    /**
     * 手机的取得
     * 
     * @return 手机
     */
    public String getCeelNumber() {
        return ceelNumber;
    }

    /**
     * 手机的设定
     * 
     * @param _ceelNumber 手机
     */
    public void setCeelNumber(String _ceelNumber) {
        this.ceelNumber = _ceelNumber;
    }

    /**
     * 电子邮箱的取得
     * 
     * @return 电子邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 电子邮箱的设定
     * 
     * @param _email 电子邮箱
     */
    public void setEmail(String _email) {
        this.email = _email;
    }

    /**
     * 页面类型的取得
     * 
     * @return 页面类型
     */
    public String getPageType() {
        return pageType;
    }

    /**
     * 页面类型的设定
     * 
     * @param _pageType 页面类型
     */
    public void setPageType(String _pageType) {
        this.pageType = _pageType;
    }

    /**
     * subForm1的取得
     * 
     * @return subForm1
     */
    public List<RPA0020401Dto> getSubForm1() {
        return subForm1;
    }

    /**
     * subForm1的设定
     * 
     * @param _subForm1 subForm1
     */
    public void setSubForm1(List<RPA0020401Dto> _subForm1) {
        this.subForm1 = _subForm1;
    }

}

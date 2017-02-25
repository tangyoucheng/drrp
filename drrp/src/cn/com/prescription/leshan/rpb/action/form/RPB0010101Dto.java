/*
 * Copyright(c) 2016 
 */

package cn.com.prescription.leshan.rpb.action.form;

import cn.com.prescription.framework.action.form.AbstractForm;

/**
 * 处方一览dto。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.09 NAME: fsb
 */
public class RPB0010101Dto extends AbstractForm {

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
     * 处方ID
     */
    private String prescriptionId = null;

    /**
     * 处方创建日
     */
    private String prescriptionCreateDate = null;

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
     * 处方ID的取得
     * 
     * @return 处方ID
     */
    public String getPrescriptionId() {
        return prescriptionId;
    }

    /**
     * 处方ID的设定
     * 
     * @param _prescriptionId 处方ID
     */
    public void setPrescriptionId(String _prescriptionId) {
        this.prescriptionId = _prescriptionId;
    }

    /**
     * 处方创建日的取得
     * 
     * @return 处方创建日
     */
    public String getPrescriptionCreateDate() {
        return prescriptionCreateDate;
    }

    /**
     * 处方创建日的设定
     * 
     * @param _prescriptionCreateDate 处方创建日
     */
    public void setPrescriptionCreateDate(String _prescriptionCreateDate) {
        this.prescriptionCreateDate = _prescriptionCreateDate;
    }

}

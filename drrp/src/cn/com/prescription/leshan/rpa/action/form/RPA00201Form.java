/*
 * Copyright(c) 2016 
 */

package cn.com.prescription.leshan.rpa.action.form;

import java.util.List;

import cn.com.prescription.framework.CodeValueRecord;
import cn.com.prescription.framework.action.form.AbstractForm;

/**
 * 患者信息 form。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.09 NAME: fsb
 */
public class RPA00201Form extends AbstractForm {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;
    /**
     * 生日
     */
    private String birthday;
    /**
     * 年龄
     */
    private String age;
    /**
     * 邮政编码
     */
    private String postNumber;
    /**
     * 住所
     */
    private String addr;
    /**
     * 座机
     */
    private String phoneNumber;
    /**
     * 手机
     */
    private String ceelNumber;
    /**
     * 身份证号码
     */
    private String idNumber;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 性别ID
     */
    private String sexId;
    /**
     * 性别
     */
    private List<CodeValueRecord> sexDataSource;

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
     * 年龄的取得
     * 
     * @return 年龄
     */
    public String getAge() {
        return age;
    }

    /**
     * 年龄的设定
     * 
     * @param _age 年龄
     */
    public void setAge(String _age) {
        this.age = _age;
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
     * 邮政编码的取得
     * 
     * @return 邮政编码
     */
    public String getPostNumber() {
        return postNumber;
    }

    /**
     * 邮政编码的设定
     * 
     * @param _postNumber 邮政编码
     */
    public void setPostNumber(String _postNumber) {
        this.postNumber = _postNumber;
    }

    /**
     * 住所的取得
     * 
     * @return 住所
     */
    public String getAddr() {
        return addr;
    }

    /**
     * 住所的设定
     * 
     * @param _addr 住所
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
     * 身份证号码的取得
     * 
     * @return 身份证号码
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * 身份证号码的设定
     * 
     * @param _idNumber 身份证号码
     */
    public void setIdNumber(String _idNumber) {
        this.idNumber = _idNumber;
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
     * 性别的取得
     * 
     * @return 性别
     */
    public List<CodeValueRecord> getSexDataSource() {
        return sexDataSource;
    }

    /**
     * 性别的设定
     * 
     * @param __sexDataSource 性别
     */
    public void setSexDataSource(List<CodeValueRecord> _sexDataSource) {
        this.sexDataSource = _sexDataSource;
    }
}

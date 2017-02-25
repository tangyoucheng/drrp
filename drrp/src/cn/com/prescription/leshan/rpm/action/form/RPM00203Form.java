/*
 * Copyright(c) 2016 
 */

package cn.com.prescription.leshan.rpm.action.form;

import java.util.List;

import cn.com.prescription.framework.CodeValueRecord;
import cn.com.prescription.framework.action.form.AbstractForm;

/**
 * 编辑用户form。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.09 NAME: fsb
 */
/**
 * @author admin
 */
public class RPM00203Form extends AbstractForm {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 开始日
     */
    private String startDate;
    /**
     * 终了日
     */
    private String endDate;
    /**
     * 密码
     */
    private String password;
    /**
     * 备注
     */
    private String notes;

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
     * 性别ID
     */
    private String sexId;
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
     * 电子邮箱
     */
    private String email;
    /**
     * 性别
     */
    private String sex;
    /**
     * 性别
     */
    private List<CodeValueRecord> sexDataSource;

    /**
     * 角色ID
     */
    private String roleId;
    /**
     * 性别
     */
    private List<CodeValueRecord> roleDataSource;

    /**
     * 最终更新日期
     */
    private String lastUpdateDateUser;

    /**
     * 最终更新日期
     */
    private String lastUpdateDateUserProfile;

    /**
     * 最终更新日期
     */
    private String lastUpdateDateUserRole;

    /**
     * 证件图片变更前
     */
    private String oldIdentityImage;

    /**
     * 证件图片变更前
     */
    private String newIdentityImage;

    /**
     * 证件图片变更后session
     */
    private List<CodeValueRecord> sessionImageDataSource;

    /**
     * ajaxメッセージ情報
     */
    private String ajaxMessageInfo;

    /** 削除URL */
    private String delete;

    /** キー */
    private String upload_key;

    /**
     * 构造添加用户的form
     */
    public RPM00203Form() {
        super();
    }

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
     * @param __userId 用户ID
     */
    public void setUserId(String _userId) {
        this.userId = _userId;
    }

    /**
     * 开始日的取得
     * 
     * @return 开始日
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * 开始日的设定
     * 
     * @param __statrtDate 开始日
     */
    public void setStartDate(String _statrtDate) {
        this.startDate = _statrtDate;
    }

    /**
     * 终了日的取得
     * 
     * @return 终了日
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * 终了日的设定
     * 
     * @param __endDate 终了日
     */
    public void setEndDate(String _endDate) {
        this.endDate = _endDate;
    }

    /**
     * 密码的取得
     * 
     * @return 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码的设定
     * 
     * @param __password 密码
     */
    public void setPassword(String _password) {
        this.password = _password;
    }

    /**
     * 备注的取得
     * 
     * @return 备注
     */
    public String getNotes() {
        return notes;
    }

    /**
     * 备注的设定
     * 
     * @param __notes 备注
     */
    public void setNotes(String _notes) {
        this.notes = _notes;
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
     * @param __userName 用户名
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
     * @param __birthday 生日
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
     * @param __age 年龄
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
     * @param __sexId 性别ID
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
     * @param __postNumber 邮政编码
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
     * @param __addr 住所
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
     * @param __phoneNumber 座机
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
     * @param __ceelNumber 手机
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
     * @param __email 电子邮箱
     */
    public void setEmail(String _email) {
        this.email = _email;
    }

    /**
     * 性别的取得
     * 
     * @return 性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * 性别的设定
     * 
     * @param __sex 性别
     */
    public void setSex(String _sex) {
        this.sex = _sex;
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

    /**
     * 角色ID的取得
     * 
     * @return 角色ID
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 角色ID的设定
     * 
     * @param __roleId 角色ID
     */
    public void setRoleId(String _roleId) {
        this.roleId = _roleId;
    }

    /**
     * 性别的取得
     * 
     * @return 性别
     */
    public List<CodeValueRecord> getRoleDataSource() {
        return roleDataSource;
    }

    /**
     * 性别的设定
     * 
     * @param __roleDataSource 性别
     */
    public void setRoleDataSource(List<CodeValueRecord> _roleDataSource) {
        this.roleDataSource = _roleDataSource;
    }

    /**
     * 最终更新日期的取得
     * 
     * @return 最终更新日期
     */
    public String getLastUpdateDateUser() {
        return lastUpdateDateUser;
    }

    /**
     * 最终更新日期的设定
     * 
     * @param __lastUpdateDateUser 最终更新日期
     */
    public void setLastUpdateDateUser(String _lastUpdateDateUser) {
        this.lastUpdateDateUser = _lastUpdateDateUser;
    }

    /**
     * 最终更新日期的取得
     * 
     * @return 最终更新日期
     */
    public String getLastUpdateDateUserProfile() {
        return lastUpdateDateUserProfile;
    }

    /**
     * 最终更新日期的设定
     * 
     * @param __lastUpdateDateUserProfile 最终更新日期
     */
    public void setLastUpdateDateUserProfile(String _lastUpdateDateUserProfile) {
        this.lastUpdateDateUserProfile = _lastUpdateDateUserProfile;
    }

    /**
     * 最终更新日期的取得
     * 
     * @return 最终更新日期
     */
    public String getLastUpdateDateUserRole() {
        return lastUpdateDateUserRole;
    }

    /**
     * 最终更新日期的设定
     * 
     * @param __lastUpdateDateUserRole 最终更新日期
     */
    public void setLastUpdateDateUserRole(String _lastUpdateDateUserRole) {
        this.lastUpdateDateUserRole = _lastUpdateDateUserRole;
    }

    /**
     * 证件图片变更前的取得
     * 
     * @return 证件图片变更前
     */
    public String getOldIdentityImage() {
        return oldIdentityImage;
    }

    /**
     * 证件图片变更前的设定
     * 
     * @param _oldIdentityImage 证件图片变更前
     */
    public void setOldIdentityImage(String _oldIdentityImage) {
        this.oldIdentityImage = _oldIdentityImage;
    }

    /**
     * 证件图片变更前的取得
     * @return 证件图片变更前
     */
    public String getNewIdentityImage() {
        return newIdentityImage;
    }

    /**
     * 证件图片变更前的设定
     * @param _newIdentityImage 证件图片变更前
     */
    public void setNewIdentityImage(String _newIdentityImage) {
        this.newIdentityImage = _newIdentityImage;
    }

    /**
     * 证件图片变更后的取得
     * 
     * @return 证件图片变更后
     */
    public List<CodeValueRecord> getSessionImageDataSource() {
        return sessionImageDataSource;
    }

    /**
     * 证件图片变更后的设定
     * 
     * @param _sessionImageDataSource 证件图片变更后
     */
    public void setSessionImageDataSource(List<CodeValueRecord> _sessionImageDataSource) {
        this.sessionImageDataSource = _sessionImageDataSource;
    }

    /**
     * ajaxメッセージ情報的取得
     * 
     * @return ajaxメッセージ情報
     */
    public String getAjaxMessageInfo() {
        return ajaxMessageInfo;
    }

    /**
     * ajaxメッセージ情報的设定
     * 
     * @param __ajaxMessageInfo ajaxメッセージ情報
     */
    public void setAjaxMessageInfo(String _ajaxMessageInfo) {
        this.ajaxMessageInfo = _ajaxMessageInfo;
    }

    /**
     * 削除URL的取得
     * 
     * @return 削除URL
     */
    public String getDelete() {
        return delete;
    }

    /**
     * 削除URL的设定
     * 
     * @param __delete 削除URL
     */
    public void setDelete(String _delete) {
        this.delete = _delete;
    }

    /**
     * キー的取得
     * 
     * @return キー
     */
    public String getUpload_key() {
        return upload_key;
    }

    /**
     * キー的设定
     * 
     * @param __upload_key キー
     */
    public void setUpload_key(String _upload_key) {
        this.upload_key = _upload_key;
    }

}

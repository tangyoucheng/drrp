/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpm.data.model;

import java.sql.Timestamp;

import cn.com.prescription.leshan.common.data.model.RpmUserProfileModel;

/**
 * 用户情報設定画面のモードル
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPM00202Model extends RpmUserProfileModel {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 开始日
     */
    private String startDate = null;

    /**
     * 终了日
     */
    private String endDate = null;

    /**
     * 密码
     */
    private String password = null;

    /**
     * 备注
     */
    private String notes = null;

    /**
     * 最终更新日期
     */
    private Timestamp lastUpdateDateUser = null;

    /**
     * 最终更新日期
     */
    private Timestamp lastUpdateDateUserProfile = null;

    /**
     * 最终更新日期
     */
    private Timestamp lastUpdateDateUserRole = null;

    /**
     * 角色ID
     */
    private String roleId = null;

    /**
     * ログイン画面モデル 的构造。
     */
    public RPM00202Model() {
        super();
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
     * @param __startDate 开始日
     */
    public void setStartDate(String _startDate) {
        this.startDate = _startDate;
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
     * 最终更新日期的取得
     * 
     * @return 最终更新日期
     */
    public Timestamp getLastUpdateDateUser() {
        return lastUpdateDateUser;
    }

    /**
     * 最终更新日期的设定
     * 
     * @param __lastUpdateDateUser 最终更新日期
     */
    public void setLastUpdateDateUser(Timestamp _lastUpdateDateUser) {
        this.lastUpdateDateUser = _lastUpdateDateUser;
    }

    /**
     * 最终更新日期的取得
     * 
     * @return 最终更新日期
     */
    public Timestamp getLastUpdateDateUserProfile() {
        return lastUpdateDateUserProfile;
    }

    /**
     * 最终更新日期的设定
     * 
     * @param __lastUpdateDateUserProfile 最终更新日期
     */
    public void setLastUpdateDateUserProfile(Timestamp _lastUpdateDateUserProfile) {
        this.lastUpdateDateUserProfile = _lastUpdateDateUserProfile;
    }

    /**
     * 最终更新日期的取得
     * 
     * @return 最终更新日期
     */
    public Timestamp getLastUpdateDateUserRole() {
        return lastUpdateDateUserRole;
    }

    /**
     * 最终更新日期的设定
     * 
     * @param _lastUpdateDateUserRole 最终更新日期
     */
    public void setLastUpdateDateUserRole(Timestamp _lastUpdateDateUserRole) {
        this.lastUpdateDateUserRole = _lastUpdateDateUserRole;
    }

    /**
     * 角色ID的取得
     * @return 角色ID
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 角色ID的设定
     * @param _roleId 角色ID
     */
    public void setRoleId(String _roleId) {
        this.roleId = _roleId;
    }

}

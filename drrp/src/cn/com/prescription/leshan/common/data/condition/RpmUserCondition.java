/*
 * Copyright(c) 2016
 */

package cn.com.prescription.leshan.common.data.condition;

import java.sql.Timestamp;
import cn.com.prescription.framework.data.condition.BaseCondition;

/**
 * 用?基本信息表Condition。
 *
 * @author gl
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */
public class RpmUserCondition extends BaseCondition {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用?ID
     */
    private String userId = null;

    /**
     * ?始日
     */
    private String startDate = null;

    /**
     * ?了日
     */
    private String endDate = null;

    /**
     * 密?
     */
    private String password = null;

    /**
     * 最后登???
     */
    private Timestamp lastLoginDate = null;

    /**
     * ?注
     */
    private String notes = null;

    /**
     * ?除??
     */
    private String deleteFlag = null;

    /**
     * ?建者
     */
    private String createUserId = null;

    /**
     * ?建日期
     */
    private Timestamp createDate = null;

    /**
     * 最?更新者
     */
    private String lastUpdateUserId = null;

    /**
     * 最?更新者名
     */
    private String lastUpdateUserName = null;

    /**
     * 最?更新日期
     */
    private Timestamp lastUpdateDate = null;

    /**
     * 用?基本信息表Condition 的构造。
     */
    public RpmUserCondition() {
        super();
    }

    /**
     * 用?IDを取得する。
     *
     * @return 用?ID
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * 用?IDを設定する。
     *
     * @param _userId 用?ID
     */
    public void setUserId(String _userId) {
        this.userId = _userId;
    }

    /**
     * ?始日を取得する。
     *
     * @return ?始日
     */
    public String getStartDate() {
        return this.startDate;
    }

    /**
     * ?始日を設定する。
     *
     * @param _startDate ?始日
     */
    public void setStartDate(String _startDate) {
        this.startDate = _startDate;
    }

    /**
     * ?了日を取得する。
     *
     * @return ?了日
     */
    public String getEndDate() {
        return this.endDate;
    }

    /**
     * ?了日を設定する。
     *
     * @param _endDate ?了日
     */
    public void setEndDate(String _endDate) {
        this.endDate = _endDate;
    }

    /**
     * 密?を取得する。
     *
     * @return 密?
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * 密?を設定する。
     *
     * @param _password 密?
     */
    public void setPassword(String _password) {
        this.password = _password;
    }

    /**
     * 最后登???を取得する。
     *
     * @return 最后登???
     */
    public Timestamp getLastLoginDate() {
        return this.lastLoginDate;
    }

    /**
     * 最后登???を設定する。
     *
     * @param _lastLoginDate 最后登???
     */
    public void setLastLoginDate(Timestamp _lastLoginDate) {
        this.lastLoginDate = _lastLoginDate;
    }

    /**
     * ?注を取得する。
     *
     * @return ?注
     */
    public String getNotes() {
        return this.notes;
    }

    /**
     * ?注を設定する。
     *
     * @param _notes ?注
     */
    public void setNotes(String _notes) {
        this.notes = _notes;
    }

    /**
     * ?除??を取得する。
     *
     * @return ?除??
     */
    public String getDeleteFlag() {
        return this.deleteFlag;
    }

    /**
     * ?除??を設定する。
     *
     * @param _deleteFlag ?除??
     */
    public void setDeleteFlag(String _deleteFlag) {
        this.deleteFlag = _deleteFlag;
    }

    /**
     * ?建者を取得する。
     *
     * @return ?建者
     */
    public String getCreateUserId() {
        return this.createUserId;
    }

    /**
     * ?建者を設定する。
     *
     * @param _createUserId ?建者
     */
    public void setCreateUserId(String _createUserId) {
        this.createUserId = _createUserId;
    }

    /**
     * ?建日期を取得する。
     *
     * @return ?建日期
     */
    public Timestamp getCreateDate() {
        return this.createDate;
    }

    /**
     * ?建日期を設定する。
     *
     * @param _createDate ?建日期
     */
    public void setCreateDate(Timestamp _createDate) {
        this.createDate = _createDate;
    }

    /**
     * 最?更新者を取得する。
     *
     * @return 最?更新者
     */
    public String getLastUpdateUserId() {
        return this.lastUpdateUserId;
    }

    /**
     * 最?更新者を設定する。
     *
     * @param _lastUpdateUserId 最?更新者
     */
    public void setLastUpdateUserId(String _lastUpdateUserId) {
        this.lastUpdateUserId = _lastUpdateUserId;
    }

    /**
     * 最?更新者名を取得する。
     *
     * @return 最?更新者名
     */
    public String getLastUpdateUserName() {
        return this.lastUpdateUserName;
    }

    /**
     * 最?更新者名を設定する。
     *
     * @param _lastUpdateUserName 最?更新者名
     */
    public void setLastUpdateUserName(String _lastUpdateUserName) {
        this.lastUpdateUserName = _lastUpdateUserName;
    }

    /**
     * 最?更新日期を取得する。
     *
     * @return 最?更新日期
     */
    public Timestamp getLastUpdateDate() {
        return this.lastUpdateDate;
    }

    /**
     * 最?更新日期を設定する。
     *
     * @param _lastUpdateDate 最?更新日期
     */
    public void setLastUpdateDate(Timestamp _lastUpdateDate) {
        this.lastUpdateDate = _lastUpdateDate;
    }

}

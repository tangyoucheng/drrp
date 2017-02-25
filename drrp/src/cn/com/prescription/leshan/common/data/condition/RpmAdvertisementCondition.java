/*
 * Copyright(c) 2016
 */

package cn.com.prescription.leshan.common.data.condition;

import java.sql.Timestamp;
import cn.com.prescription.framework.data.condition.BaseCondition;

/**
 * 推广信息表Condition。
 *
 * @author gl
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */
public class RpmAdvertisementCondition extends BaseCondition {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 推广信息ID
     */
    private String adId = null;

    /**
     * 推广信息名
     */
    private String adName = null;

    /**
     * 推广信息URL
     */
    private String adUrl = null;

    /**
     * 推广信息状?
     */
    private String adStatus = null;

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
     * 推广信息表Condition 的构造。
     */
    public RpmAdvertisementCondition() {
        super();
    }

    /**
     * 推广信息IDを取得する。
     *
     * @return 推广信息ID
     */
    public String getAdId() {
        return this.adId;
    }

    /**
     * 推广信息IDを設定する。
     *
     * @param _adId 推广信息ID
     */
    public void setAdId(String _adId) {
        this.adId = _adId;
    }

    /**
     * 推广信息名を取得する。
     *
     * @return 推广信息名
     */
    public String getAdName() {
        return this.adName;
    }

    /**
     * 推广信息名を設定する。
     *
     * @param _adName 推广信息名
     */
    public void setAdName(String _adName) {
        this.adName = _adName;
    }

    /**
     * 推广信息URLを取得する。
     *
     * @return 推广信息URL
     */
    public String getAdUrl() {
        return this.adUrl;
    }

    /**
     * 推广信息URLを設定する。
     *
     * @param _adUrl 推广信息URL
     */
    public void setAdUrl(String _adUrl) {
        this.adUrl = _adUrl;
    }

    /**
     * 推广信息状?を取得する。
     *
     * @return 推广信息状?
     */
    public String getAdStatus() {
        return this.adStatus;
    }

    /**
     * 推广信息状?を設定する。
     *
     * @param _adStatus 推广信息状?
     */
    public void setAdStatus(String _adStatus) {
        this.adStatus = _adStatus;
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

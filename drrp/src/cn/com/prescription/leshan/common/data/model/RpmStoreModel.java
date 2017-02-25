/*
 * Copyright(c) 2016
 */

package cn.com.prescription.leshan.common.data.model;

import java.sql.Timestamp;
import cn.com.prescription.framework.data.model.StandardModel;

/**
 * ?店信息表 モデルオブジェクト。
 *
 * @author gl
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */
public class RpmStoreModel extends StandardModel {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * テーブル定義
     */
    public static final String TABLE = "RPM_STORE";

    /**
     * ?店ID
     */
    private String storeId = null;

    /**
     * ?店名
     */
    private String storeName = null;

    /**
     * ?店?方???前?
     */
    private String rpCodePrefix = null;

    /**
     * ?店二??
     */
    private String qrCode = null;

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
     * ?店信息表モデルBean 的构造。
     */
    public RpmStoreModel() {
        super();
    }

    /**
     * ?店IDを取得する。
     *
     * @return ?店ID
     */
    public String getStoreId() {
        return this.storeId;
    }

    /**
     * ?店IDを設定する。
     *
     * @param _storeId ?店ID
     */
    public void setStoreId(String _storeId) {
        this.storeId = _storeId;
    }

    /**
     * ?店名を取得する。
     *
     * @return ?店名
     */
    public String getStoreName() {
        return this.storeName;
    }

    /**
     * ?店名を設定する。
     *
     * @param _storeName ?店名
     */
    public void setStoreName(String _storeName) {
        this.storeName = _storeName;
    }

    /**
     * ?店?方???前?を取得する。
     *
     * @return ?店?方???前?
     */
    public String getRpCodePrefix() {
        return this.rpCodePrefix;
    }

    /**
     * ?店?方???前?を設定する。
     *
     * @param _rpCodePrefix ?店?方???前?
     */
    public void setRpCodePrefix(String _rpCodePrefix) {
        this.rpCodePrefix = _rpCodePrefix;
    }

    /**
     * ?店二??を取得する。
     *
     * @return ?店二??
     */
    public String getQrCode() {
        return this.qrCode;
    }

    /**
     * ?店二??を設定する。
     *
     * @param _qrCode ?店二??
     */
    public void setQrCode(String _qrCode) {
        this.qrCode = _qrCode;
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

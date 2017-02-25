/*
 * Copyright(c) 2016
 */

package cn.com.prescription.leshan.common.data.condition;

import java.sql.Timestamp;
import cn.com.prescription.framework.data.condition.BaseCondition;

/**
 * ?品信息表Condition。
 *
 * @author gl
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */
public class RpmDrugCondition extends BaseCondition {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * ?品ID
     */
    private String drugId = null;

    /**
     * ?品名称
     */
    private String drugName = null;

    /**
     * 厂商名称
     */
    private String manufacturerName = null;

    /**
     * 价格
     */
    private String price = null;

    /**
     * ?位
     */
    private String unit = null;

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
     * ?品信息表Condition 的构造。
     */
    public RpmDrugCondition() {
        super();
    }

    /**
     * ?品IDを取得する。
     *
     * @return ?品ID
     */
    public String getDrugId() {
        return this.drugId;
    }

    /**
     * ?品IDを設定する。
     *
     * @param _drugId ?品ID
     */
    public void setDrugId(String _drugId) {
        this.drugId = _drugId;
    }

    /**
     * ?品名称を取得する。
     *
     * @return ?品名称
     */
    public String getDrugName() {
        return this.drugName;
    }

    /**
     * ?品名称を設定する。
     *
     * @param _drugName ?品名称
     */
    public void setDrugName(String _drugName) {
        this.drugName = _drugName;
    }

    /**
     * 厂商名称を取得する。
     *
     * @return 厂商名称
     */
    public String getManufacturerName() {
        return this.manufacturerName;
    }

    /**
     * 厂商名称を設定する。
     *
     * @param _manufacturerName 厂商名称
     */
    public void setManufacturerName(String _manufacturerName) {
        this.manufacturerName = _manufacturerName;
    }

    /**
     * 价格を取得する。
     *
     * @return 价格
     */
    public String getPrice() {
        return this.price;
    }

    /**
     * 价格を設定する。
     *
     * @param _price 价格
     */
    public void setPrice(String _price) {
        this.price = _price;
    }

    /**
     * ?位を取得する。
     *
     * @return ?位
     */
    public String getUnit() {
        return this.unit;
    }

    /**
     * ?位を設定する。
     *
     * @param _unit ?位
     */
    public void setUnit(String _unit) {
        this.unit = _unit;
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

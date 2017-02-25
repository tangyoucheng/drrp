/*
 * Copyright(c) 2016
 */

package cn.com.prescription.leshan.common.data.condition;

import java.math.BigDecimal;
import java.sql.Timestamp;
import cn.com.prescription.framework.data.condition.BaseCondition;

/**
 * ?方?品一?信息表Condition。
 *
 * @author gl
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */
public class RpmPrescriptionDrugCondition extends BaseCondition {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * ?方ID
     */
    private String prescriptionId = null;

    /**
     * ?品ID
     */
    private String drugId = null;

    /**
     * 数量
     */
    private String quantity = null;

    /**
     * 排序?
     */
    private BigDecimal sortKey = null;

    /**
     * ?注
     */
    private String notes = null;

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
     * ?方?品一?信息表Condition 的构造。
     */
    public RpmPrescriptionDrugCondition() {
        super();
    }

    /**
     * ?方IDを取得する。
     *
     * @return ?方ID
     */
    public String getPrescriptionId() {
        return this.prescriptionId;
    }

    /**
     * ?方IDを設定する。
     *
     * @param _prescriptionId ?方ID
     */
    public void setPrescriptionId(String _prescriptionId) {
        this.prescriptionId = _prescriptionId;
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
     * 数量を取得する。
     *
     * @return 数量
     */
    public String getQuantity() {
        return this.quantity;
    }

    /**
     * 数量を設定する。
     *
     * @param _quantity 数量
     */
    public void setQuantity(String _quantity) {
        this.quantity = _quantity;
    }

    /**
     * 排序?を取得する。
     *
     * @return 排序?
     */
    public BigDecimal getSortKey() {
        return this.sortKey;
    }

    /**
     * 排序?を設定する。
     *
     * @param _sortKey 排序?
     */
    public void setSortKey(BigDecimal _sortKey) {
        this.sortKey = _sortKey;
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

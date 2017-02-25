/*
 * Copyright(c) 2016
 */

package cn.com.prescription.leshan.common.data.condition;

import java.sql.Timestamp;
import cn.com.prescription.framework.data.condition.BaseCondition;

/**
 * 患者基本信息表Condition。
 *
 * @author gl
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */
public class RpmPrescriptionCondition extends BaseCondition {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * ?方ID
     */
    private String prescriptionId = null;

    /**
     * 患者ID
     */
    private String patientId = null;

    /**
     * ?方状?
     */
    private String prescriptionStatus = null;

    /**
     * ?方??
     */
    private String prescriptionType = null;

    /**
     * ?方内容
     */
    private String contents = null;

    /**
     * ?方?片信息
     */
    private String fileContents = null;

    /**
     * ?方做成者
     */
    private String prescriptionCreateUserId = null;

    /**
     * ?方做成者名
     */
    private String prescriptionCreateUserName = null;

    /**
     * ?方配?者
     */
    private String dispenseUserId = null;

    /**
     * ?方配?者名
     */
    private String dispenseUserName = null;

    /**
     * ?方?核者
     */
    private String confirmUserId = null;

    /**
     * ?方?核者名
     */
    private String confirmUserName = null;

    /**
     * ??/住院号
     */
    private String inpatientNumber = null;

    /**
     * 科室
     */
    private String department = null;

    /**
     * 床号
     */
    private String bedNo = null;

    /**
     * ?敏史
     */
    private String allergyHistory = null;

    /**
     * ?床?断
     */
    private String diagnosis = null;

    /**
     * 价格
     */
    private String price = null;

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
     * 患者基本信息表Condition 的构造。
     */
    public RpmPrescriptionCondition() {
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
     * 患者IDを取得する。
     *
     * @return 患者ID
     */
    public String getPatientId() {
        return this.patientId;
    }

    /**
     * 患者IDを設定する。
     *
     * @param _patientId 患者ID
     */
    public void setPatientId(String _patientId) {
        this.patientId = _patientId;
    }

    /**
     * ?方状?を取得する。
     *
     * @return ?方状?
     */
    public String getPrescriptionStatus() {
        return this.prescriptionStatus;
    }

    /**
     * ?方状?を設定する。
     *
     * @param _prescriptionStatus ?方状?
     */
    public void setPrescriptionStatus(String _prescriptionStatus) {
        this.prescriptionStatus = _prescriptionStatus;
    }

    /**
     * ?方??を取得する。
     *
     * @return ?方??
     */
    public String getPrescriptionType() {
        return this.prescriptionType;
    }

    /**
     * ?方??を設定する。
     *
     * @param _prescriptionType ?方??
     */
    public void setPrescriptionType(String _prescriptionType) {
        this.prescriptionType = _prescriptionType;
    }

    /**
     * ?方内容を取得する。
     *
     * @return ?方内容
     */
    public String getContents() {
        return this.contents;
    }

    /**
     * ?方内容を設定する。
     *
     * @param _contents ?方内容
     */
    public void setContents(String _contents) {
        this.contents = _contents;
    }

    /**
     * ?方?片信息を取得する。
     *
     * @return ?方?片信息
     */
    public String getFileContents() {
        return this.fileContents;
    }

    /**
     * ?方?片信息を設定する。
     *
     * @param _fileContents ?方?片信息
     */
    public void setFileContents(String _fileContents) {
        this.fileContents = _fileContents;
    }

    /**
     * ?方做成者を取得する。
     *
     * @return ?方做成者
     */
    public String getPrescriptionCreateUserId() {
        return this.prescriptionCreateUserId;
    }

    /**
     * ?方做成者を設定する。
     *
     * @param _prescriptionCreateUserId ?方做成者
     */
    public void setPrescriptionCreateUserId(String _prescriptionCreateUserId) {
        this.prescriptionCreateUserId = _prescriptionCreateUserId;
    }

    /**
     * ?方做成者名を取得する。
     *
     * @return ?方做成者名
     */
    public String getPrescriptionCreateUserName() {
        return this.prescriptionCreateUserName;
    }

    /**
     * ?方做成者名を設定する。
     *
     * @param _prescriptionCreateUserName ?方做成者名
     */
    public void setPrescriptionCreateUserName(String _prescriptionCreateUserName) {
        this.prescriptionCreateUserName = _prescriptionCreateUserName;
    }

    /**
     * ?方配?者を取得する。
     *
     * @return ?方配?者
     */
    public String getDispenseUserId() {
        return this.dispenseUserId;
    }

    /**
     * ?方配?者を設定する。
     *
     * @param _dispenseUserId ?方配?者
     */
    public void setDispenseUserId(String _dispenseUserId) {
        this.dispenseUserId = _dispenseUserId;
    }

    /**
     * ?方配?者名を取得する。
     *
     * @return ?方配?者名
     */
    public String getDispenseUserName() {
        return this.dispenseUserName;
    }

    /**
     * ?方配?者名を設定する。
     *
     * @param _dispenseUserName ?方配?者名
     */
    public void setDispenseUserName(String _dispenseUserName) {
        this.dispenseUserName = _dispenseUserName;
    }

    /**
     * ?方?核者を取得する。
     *
     * @return ?方?核者
     */
    public String getConfirmUserId() {
        return this.confirmUserId;
    }

    /**
     * ?方?核者を設定する。
     *
     * @param _confirmUserId ?方?核者
     */
    public void setConfirmUserId(String _confirmUserId) {
        this.confirmUserId = _confirmUserId;
    }

    /**
     * ?方?核者名を取得する。
     *
     * @return ?方?核者名
     */
    public String getConfirmUserName() {
        return this.confirmUserName;
    }

    /**
     * ?方?核者名を設定する。
     *
     * @param _confirmUserName ?方?核者名
     */
    public void setConfirmUserName(String _confirmUserName) {
        this.confirmUserName = _confirmUserName;
    }

    /**
     * ??/住院号を取得する。
     *
     * @return ??/住院号
     */
    public String getInpatientNumber() {
        return this.inpatientNumber;
    }

    /**
     * ??/住院号を設定する。
     *
     * @param _inpatientNumber ??/住院号
     */
    public void setInpatientNumber(String _inpatientNumber) {
        this.inpatientNumber = _inpatientNumber;
    }

    /**
     * 科室を取得する。
     *
     * @return 科室
     */
    public String getDepartment() {
        return this.department;
    }

    /**
     * 科室を設定する。
     *
     * @param _department 科室
     */
    public void setDepartment(String _department) {
        this.department = _department;
    }

    /**
     * 床号を取得する。
     *
     * @return 床号
     */
    public String getBedNo() {
        return this.bedNo;
    }

    /**
     * 床号を設定する。
     *
     * @param _bedNo 床号
     */
    public void setBedNo(String _bedNo) {
        this.bedNo = _bedNo;
    }

    /**
     * ?敏史を取得する。
     *
     * @return ?敏史
     */
    public String getAllergyHistory() {
        return this.allergyHistory;
    }

    /**
     * ?敏史を設定する。
     *
     * @param _allergyHistory ?敏史
     */
    public void setAllergyHistory(String _allergyHistory) {
        this.allergyHistory = _allergyHistory;
    }

    /**
     * ?床?断を取得する。
     *
     * @return ?床?断
     */
    public String getDiagnosis() {
        return this.diagnosis;
    }

    /**
     * ?床?断を設定する。
     *
     * @param _diagnosis ?床?断
     */
    public void setDiagnosis(String _diagnosis) {
        this.diagnosis = _diagnosis;
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

/*
 * Copyright(c) 2016 NTT DATA KANSAI CORPORATION
 */

package cn.com.prescription.leshan.common.data.model;

import java.sql.Timestamp;

/**
 * 患者基本信息表 モデルオブジェクト。
 *
 * @author gl
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */
public class RpmPatient01Model extends RpmPatientModel {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 处方ID
     */
    private String prescriptionId = null;

    /**
     * 患者ID
     */
    private String patientId = null;

    /**
     * 处方状态
     */
    private String prescriptionStatus = null;

    /**
     * 处方类型
     */
    private String prescriptionType = null;

    /**
     * 处方内容
     */
    private String contents = null;

    /**
     * 处方图片信息
     */
    private String fileContents = null;

    /**
     * 处方创建日
     */
    private Timestamp createDatePrescription = null;

    /**
     * 处方做成者
     */
    private String prescriptionCreateUserId = null;

    /**
     * 处方做成者名
     */
    private String prescriptionCreateUserName = null;

    /**
     * 处方配药者
     */
    private String dispenseUserId = null;

    /**
     * 处方配药者名
     */
    private String dispenseUserName = null;

    /**
     * 处方审核者
     */
    private String confirmUserId = null;

    /**
     * 处方审核者名
     */
    private String confirmUserName = null;

    /**
     * 门诊/住院号
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
     * 过敏史
     */
    private String allergyHistory = null;

    /**
     * 临床诊断
     */
    private String diagnosis = null;

    /**
     * 备注
     */
    private String notes = null;

    /**
     * 最终更新日期
     */
    private Timestamp lastUpdateDatePrescription = null;

    /**
     * 价格
     */
    private String price = null;

    /**
     * 患者基本信息表モデルBean 的构造。
     */
    public RpmPatient01Model() {
        super();
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
     * @param __prescriptionId 处方ID
     */
    public void setPrescriptionId(String _prescriptionId) {
        this.prescriptionId = _prescriptionId;
    }

    /**
     * 患者ID的取得
     * 
     * @return 患者ID
     */
    public String getPatientId() {
        return patientId;
    }

    /**
     * 患者ID的设定
     * 
     * @param __patientId 患者ID
     */
    public void setPatientId(String _patientId) {
        this.patientId = _patientId;
    }

    /**
     * 处方状态的取得
     * 
     * @return 处方状态
     */
    public String getPrescriptionStatus() {
        return prescriptionStatus;
    }

    /**
     * 处方状态的设定
     * 
     * @param __prescriptionStatus 处方状态
     */
    public void setPrescriptionStatus(String _prescriptionStatus) {
        this.prescriptionStatus = _prescriptionStatus;
    }

    /**
     * 处方类型的取得
     * 
     * @return 处方类型
     */
    public String getPrescriptionType() {
        return prescriptionType;
    }

    /**
     * 处方类型的设定
     * 
     * @param __prescriptionType 处方类型
     */
    public void setPrescriptionType(String _prescriptionType) {
        this.prescriptionType = _prescriptionType;
    }

    /**
     * 处方内容的取得
     * 
     * @return 处方内容
     */
    public String getContents() {
        return contents;
    }

    /**
     * 处方内容的设定
     * 
     * @param __contents 处方内容
     */
    public void setContents(String _contents) {
        this.contents = _contents;
    }

    /**
     * 处方图片信息的取得
     * 
     * @return 处方图片信息
     */
    public String getFileContents() {
        return fileContents;
    }

    /**
     * 处方图片信息的设定
     * 
     * @param _fileContents 处方图片信息
     */
    public void setFileContents(String _fileContents) {
        this.fileContents = _fileContents;
    }

    /**
     * 处方创建日的取得
     * 
     * @return 处方创建日
     */
    public Timestamp getCreateDatePrescription() {
        return createDatePrescription;
    }

    /**
     * 处方创建日的设定
     * 
     * @param _createDatePrescription 处方创建日
     */
    public void setCreateDatePrescription(Timestamp _createDatePrescription) {
        this.createDatePrescription = _createDatePrescription;
    }

    /**
     * 处方做成者的取得
     * 
     * @return 处方做成者
     */
    public String getPrescriptionCreateUserId() {
        return prescriptionCreateUserId;
    }

    /**
     * 处方做成者的设定
     * 
     * @param __prescriptionCreateUserId 处方做成者
     */
    public void setPrescriptionCreateUserId(String _prescriptionCreateUserId) {
        this.prescriptionCreateUserId = _prescriptionCreateUserId;
    }

    /**
     * 处方做成者名的取得
     * 
     * @return 处方做成者名
     */
    public String getPrescriptionCreateUserName() {
        return prescriptionCreateUserName;
    }

    /**
     * 处方做成者名的设定
     * 
     * @param __prescriptionCreateUserName 处方做成者名
     */
    public void setPrescriptionCreateUserName(String _prescriptionCreateUserName) {
        this.prescriptionCreateUserName = _prescriptionCreateUserName;
    }

    /**
     * 处方配药者的取得
     * 
     * @return 处方配药者
     */
    public String getDispenseUserId() {
        return dispenseUserId;
    }

    /**
     * 处方配药者的设定
     * 
     * @param __dispenseUserId 处方配药者
     */
    public void setDispenseUserId(String _dispenseUserId) {
        this.dispenseUserId = _dispenseUserId;
    }

    /**
     * 处方配药者名的取得
     * 
     * @return 处方配药者名
     */
    public String getDispenseUserName() {
        return dispenseUserName;
    }

    /**
     * 处方配药者名的设定
     * 
     * @param __dispenseUserName 处方配药者名
     */
    public void setDispenseUserName(String _dispenseUserName) {
        this.dispenseUserName = _dispenseUserName;
    }

    /**
     * 处方审核者的取得
     * 
     * @return 处方审核者
     */
    public String getConfirmUserId() {
        return confirmUserId;
    }

    /**
     * 处方审核者的设定
     * 
     * @param __confirmUserId 处方审核者
     */
    public void setConfirmUserId(String _confirmUserId) {
        this.confirmUserId = _confirmUserId;
    }

    /**
     * 处方审核者名的取得
     * 
     * @return 处方审核者名
     */
    public String getConfirmUserName() {
        return confirmUserName;
    }

    /**
     * 处方审核者名的设定
     * 
     * @param __confirmUserName 处方审核者名
     */
    public void setConfirmUserName(String _confirmUserName) {
        this.confirmUserName = _confirmUserName;
    }

    /**
     * 门诊/住院号的取得
     * 
     * @return 门诊/住院号
     */
    public String getInpatientNumber() {
        return inpatientNumber;
    }

    /**
     * 门诊/住院号的设定
     * 
     * @param __inpatientNumber 门诊/住院号
     */
    public void setInpatientNumber(String _inpatientNumber) {
        this.inpatientNumber = _inpatientNumber;
    }

    /**
     * 科室的取得
     * 
     * @return 科室
     */
    public String getDepartment() {
        return department;
    }

    /**
     * 科室的设定
     * 
     * @param __department 科室
     */
    public void setDepartment(String _department) {
        this.department = _department;
    }

    /**
     * 床号的取得
     * 
     * @return 床号
     */
    public String getBedNo() {
        return bedNo;
    }

    /**
     * 床号的设定
     * 
     * @param __bedNo 床号
     */
    public void setBedNo(String _bedNo) {
        this.bedNo = _bedNo;
    }

    /**
     * 过敏史的取得
     * 
     * @return 过敏史
     */
    public String getAllergyHistory() {
        return allergyHistory;
    }

    /**
     * 过敏史的设定
     * 
     * @param __allergyHistory 过敏史
     */
    public void setAllergyHistory(String _allergyHistory) {
        this.allergyHistory = _allergyHistory;
    }

    /**
     * 临床诊断的取得
     * 
     * @return 临床诊断
     */
    public String getDiagnosis() {
        return diagnosis;
    }

    /**
     * 临床诊断的设定
     * 
     * @param __diagnosis 临床诊断
     */
    public void setDiagnosis(String _diagnosis) {
        this.diagnosis = _diagnosis;
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
    public Timestamp getLastUpdateDatePrescription() {
        return lastUpdateDatePrescription;
    }

    /**
     * 最终更新日期的设定
     * 
     * @param __lastUpdateDatePrescription 最终更新日期
     */
    public void setLastUpdateDatePrescription(Timestamp _lastUpdateDatePrescription) {
        this.lastUpdateDatePrescription = _lastUpdateDatePrescription;
    }

    /**
     * 价格的取得
     * @return 价格
     */
    public String getPrice() {
        return price;
    }

    /**
     * 价格的设定
     * @param _price 价格
     */
    public void setPrice(String _price) {
        this.price = _price;
    }

}

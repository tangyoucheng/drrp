/*
 * Copyright(c) 2016 
 */

package cn.com.prescription.leshan.rpa.action.form;

import java.util.ArrayList;
import java.util.List;

import cn.com.prescription.framework.CodeValueRecord;
import cn.com.prescription.framework.action.form.AbstractForm;

/**
 * 处方添加 form。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.09 NAME: fsb
 */
public class RPA00101Form extends AbstractForm {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;
    /**
     * 患者ID
     */
    private String patientId;
    /**
     * 患者姓名
     */
    private String patientName;
    /**
     * 出生日期
     */
    private String birthday;
    /**
     * 门诊/住院号
     */
    private String inpatientNumber;
    /**
     * 科室
     */
    private String department;
    /**
     * 床号
     */
    private String bedNo;
    /**
     * 过敏史
     */
    private String allergyHistory;
    /**
     * 临床诊断
     */
    private String diagnosis;
    /**
     * 身份证号码
     */
    private String idNumber;
    /**
     * 座机号码
     */
    private String phoneNumber;
    /**
     * 手机号码
     */
    private String ceelNumber;
    /**
     * 居住地
     */
    private String addr;
    /**
     * 处方类型
     */
    private String prescriptionType;
    /**
     * 处方类型
     */
    private List<CodeValueRecord> prescriptionTypeDataSource;
    /**
     * 处方内容
     */
    private String prescriptionContent;

    /**
     * 处方图片信息
     */
    private String fileContents = null;
    /**
     * 处方做成者
     */
    private String prescriptionCreateUserId;
    /**
     * 处方做成者名
     */
    private String prescriptionCreateUserName;
    /**
     * 处方做成日
     */
    private String prescriptionCreateDate;
    /**
     * 性别ID
     */
    private String sexId;
    /**
     * 性别
     */
    private List<CodeValueRecord> sexDataSource;

    /**
     * 价格
     */
    private String price = null;

    /**
     * subForm1
     */
    private List<RPA0010101Dto> subForm1 = new ArrayList<RPA0010101Dto>();

    /**
     * 患者ID的取得
     * @return 患者ID
     */
    public String getPatientId() {
        return patientId;
    }

    /**
     * 患者ID的设定
     * @param __patientId 患者ID
     */
    public void setPatientId(String _patientId) {
        this.patientId = _patientId;
    }

    /**
     * 患者姓名的取得
     * @return 患者姓名
     */
    public String getPatientName() {
        return patientName;
    }

    /**
     * 患者姓名的设定
     * @param __patientName 患者姓名
     */
    public void setPatientName(String _patientName) {
        this.patientName = _patientName;
    }

    /**
     * 出生日期的取得
     * @return 出生日期
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * 出生日期的设定
     * @param __birthday 出生日期
     */
    public void setBirthday(String _birthday) {
        this.birthday = _birthday;
    }

    /**
     * 门诊/住院号的取得
     * @return 门诊/住院号
     */
    public String getInpatientNumber() {
        return inpatientNumber;
    }

    /**
     * 门诊/住院号的设定
     * @param __inpatientNumber 门诊/住院号
     */
    public void setInpatientNumber(String _inpatientNumber) {
        this.inpatientNumber = _inpatientNumber;
    }

    /**
     * 科室的取得
     * @return 科室
     */
    public String getDepartment() {
        return department;
    }

    /**
     * 科室的设定
     * @param __department 科室
     */
    public void setDepartment(String _department) {
        this.department = _department;
    }

    /**
     * 床号的取得
     * @return 床号
     */
    public String getBedNo() {
        return bedNo;
    }

    /**
     * 床号的设定
     * @param __bedNo 床号
     */
    public void setBedNo(String _bedNo) {
        this.bedNo = _bedNo;
    }

    /**
     * 过敏史的取得
     * @return 过敏史
     */
    public String getAllergyHistory() {
        return allergyHistory;
    }

    /**
     * 过敏史的设定
     * @param __allergyHistory 过敏史
     */
    public void setAllergyHistory(String _allergyHistory) {
        this.allergyHistory = _allergyHistory;
    }

    /**
     * 临床诊断的取得
     * @return 临床诊断
     */
    public String getDiagnosis() {
        return diagnosis;
    }

    /**
     * 临床诊断的设定
     * @param __diagnosis 临床诊断
     */
    public void setDiagnosis(String _diagnosis) {
        this.diagnosis = _diagnosis;
    }

    /**
     * 身份证号码的取得
     * @return 身份证号码
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * 身份证号码的设定
     * @param __idNumber 身份证号码
     */
    public void setIdNumber(String _idNumber) {
        this.idNumber = _idNumber;
    }

    /**
     * 座机号码的取得
     * @return 座机号码
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 座机号码的设定
     * @param __phoneNumber 座机号码
     */
    public void setPhoneNumber(String _phoneNumber) {
        this.phoneNumber = _phoneNumber;
    }

    /**
     * 手机号码的取得
     * @return 手机号码
     */
    public String getCeelNumber() {
        return ceelNumber;
    }

    /**
     * 手机号码的设定
     * @param __ceelNumber 手机号码
     */
    public void setCeelNumber(String _ceelNumber) {
        this.ceelNumber = _ceelNumber;
    }

    /**
     * 居住地的取得
     * @return 居住地
     */
    public String getAddr() {
        return addr;
    }

    /**
     * 居住地的设定
     * @param __addr 居住地
     */
    public void setAddr(String _addr) {
        this.addr = _addr;
    }

    /**
     * 处方类型的取得
     * @return 处方类型
     */
    public String getPrescriptionType() {
        return prescriptionType;
    }

    /**
     * 处方类型的设定
     * @param __prescriptionType 处方类型
     */
    public void setPrescriptionType(String _prescriptionType) {
        this.prescriptionType = _prescriptionType;
    }

    /**
     * 处方类型的取得
     * @return 处方类型
     */
    public List<CodeValueRecord> getPrescriptionTypeDataSource() {
        return prescriptionTypeDataSource;
    }

    /**
     * 处方类型的设定
     * @param __prescriptionTypeDataSource 处方类型
     */
    public void setPrescriptionTypeDataSource(List<CodeValueRecord> _prescriptionTypeDataSource) {
        this.prescriptionTypeDataSource = _prescriptionTypeDataSource;
    }

    /**
     * 处方内容的取得
     * @return 处方内容
     */
    public String getPrescriptionContent() {
        return prescriptionContent;
    }

    /**
     * 处方内容的设定
     * @param __prescriptionContent 处方内容
     */
    public void setPrescriptionContent(String _prescriptionContent) {
        this.prescriptionContent = _prescriptionContent;
    }

    /**
     * 处方图片信息的取得
     * @return 处方图片信息
     */
    public String getFileContents() {
        return fileContents;
    }

    /**
     * 处方图片信息的设定
     * @param __fileContents 处方图片信息
     */
    public void setFileContents(String _fileContents) {
        this.fileContents = _fileContents;
    }

    /**
     * 处方做成者的取得
     * @return 处方做成者
     */
    public String getPrescriptionCreateUserId() {
        return prescriptionCreateUserId;
    }

    /**
     * 处方做成者的设定
     * @param __prescriptionCreateUserId 处方做成者
     */
    public void setPrescriptionCreateUserId(String _prescriptionCreateUserId) {
        this.prescriptionCreateUserId = _prescriptionCreateUserId;
    }

    /**
     * 处方做成者名的取得
     * @return 处方做成者名
     */
    public String getPrescriptionCreateUserName() {
        return prescriptionCreateUserName;
    }

    /**
     * 处方做成者名的设定
     * @param __prescriptionCreateUserName 处方做成者名
     */
    public void setPrescriptionCreateUserName(String _prescriptionCreateUserName) {
        this.prescriptionCreateUserName = _prescriptionCreateUserName;
    }

    /**
     * 处方做成日的取得
     * @return 处方做成日
     */
    public String getPrescriptionCreateDate() {
        return prescriptionCreateDate;
    }

    /**
     * 处方做成日的设定
     * @param __prescriptionCreateDate 处方做成日
     */
    public void setPrescriptionCreateDate(String _prescriptionCreateDate) {
        this.prescriptionCreateDate = _prescriptionCreateDate;
    }

    /**
     * 性别ID的取得
     * @return 性别ID
     */
    public String getSexId() {
        return sexId;
    }

    /**
     * 性别ID的设定
     * @param __sexId 性别ID
     */
    public void setSexId(String _sexId) {
        this.sexId = _sexId;
    }

    /**
     * 性别的取得
     * @return 性别
     */
    public List<CodeValueRecord> getSexDataSource() {
        return sexDataSource;
    }

    /**
     * 性别的设定
     * @param __sexDataSource 性别
     */
    public void setSexDataSource(List<CodeValueRecord> _sexDataSource) {
        this.sexDataSource = _sexDataSource;
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
     * @param __price 价格
     */
    public void setPrice(String _price) {
        this.price = _price;
    }

    /**
     * subForm1的取得
     * @return subForm1
     */
    public List<RPA0010101Dto> getSubForm1() {
        return subForm1;
    }

    /**
     * subForm1的设定
     * @param _subForm1 subForm1
     */
    public void setSubForm1(List<RPA0010101Dto> _subForm1) {
        this.subForm1 = _subForm1;
    }

}

/*
 * Copyright(c) 
 */

package cn.com.prescription.leshan.common.data.condition;

import java.sql.Timestamp;

import cn.com.prescription.framework.data.condition.BaseCondition;

/**
 * 门诊信息ondition。
 *
 * @author gl
 */
/*
 * ﾐﾂﾒ邪ﾉ DATE: 2012.02.13 NAME: gl
 */
public class RpmOutpatientCondition extends BaseCondition {

	/**
	 * シリアルバージョンUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 门诊ID
	 */
	private String recordId = null;
	
	/**
	 * 患者ID
	 */
	private String patientId = null;

	/**
	 * 就诊时间
	 */
	private String medicalDate = null;

	/**
	 * 主诉
	 */
	private String chiefComplaint = null;

	/**
	 * 发病节气
	 */
	private String onsetSolarTerms = null;

	/**
	 * 现病史
	 */
	private String presentHistory = null;

	/**
	 * 既往史
	 */
	private String previousHistory = null;

	/**
	 * 家族史
	 */
	private String familyHistory = null;

	/**
	 * 过敏史
	 */
	private String allergyHistory = null;

	/**
	 * 其他情况
	 */
	private String otherCases = null;
	
	/**
	 * 四诊摘要
	 */
	private String fourDiagnosis = null;

	/**
	 * 舌诊
	 */
	private String tongueInspection = null;

	/**
	 * 脉诊
	 */
	private String pulseTaking = null;

	/**
	 * 体温
	 */
	private String temperature = null;

	/**
	 * 脉搏
	 */
	private String pulse = null;

	/**
	 * 呼吸
	 */
	private String breath = null;

	/**
	 * 血压
	 */
	private String bloodPressure = null;

	/**
	 * 身高
	 */
	private String height = null;

	/**
	 * 体重
	 */
	private String weight = null;

	/**
	 * 辅助检查
	 */
	private String assistantExamination = null;

	/**
	 * 诊断
	 */
	private String diagnosis = null;

	/**
	 * 处置
	 */
	private String disposition = null;

	/**
	 * 医师
	 */
	private String physician = null;

	/**
	 * 备注
	 */
	private String notes = null;

	/**
	 * 病例记录人
	 */
	private String caseRecorder = null;

	/**
	 * 主诊助理
	 */
	private String attendingAssistant = null;

	/**
	 * 初诊标识
	 */
	private String firstVisitFlag = null;

	/**
	 * 回访记录
	 */
	private String returnVisit = null;

	/**
	 * 疗效
	 */
	private String effect = null;

	/**
	 * 删除标识
	 */
	private String deleteFlag = null;

	/**
	 * 创建者
	 */
	private String createUserId = null;

	/**
	 * 创建日期
	 */
	private Timestamp createDate = null;

	/**
	 * 更新着
	 */
	private String lastUpdateUserId = null;

	/**
	 * 更新者明名
	 */
	private String lastUpdateUserName = null;

	/**
	 * 更新日期
	 */
	private Timestamp lastUpdateDate = null;

	/**
	 * 门诊信息ondition を構築する。
	 */
	public RpmOutpatientCondition() {
		super();
	}

	/**
	 * 门诊ID的取得
	 * @return 门诊ID
	 */
	public String getRecordId() {
		return this.recordId;
	}

	/**
	 * 门诊ID的设定
	 * @param __recordId 门诊ID
	 */
	public void setRecordId(String _recordId) {
		this.recordId = _recordId;
	}

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
	 * 就诊时间的取得
	 * @return 就诊时间
	 */
	public String getMedicalDate() {
		return this.medicalDate;
	}

	/**
	 * 就诊时间的设定
	 * @param __medicalDate 就诊时间
	 */
	public void setMedicalDate(String _medicalDate) {
		this.medicalDate = _medicalDate;
	}

	/**
	 * 主诉的取得
	 * @return 主诉
	 */
	public String getChiefComplaint() {
		return this.chiefComplaint;
	}

	/**
	 * 主诉的设定
	 * @param __chiefComplaint 主诉
	 */
	public void setChiefComplaint(String _chiefComplaint) {
		this.chiefComplaint = _chiefComplaint;
	}

	/**
	 * 发病节气的取得
	 * @return 发病节气
	 */
	public String getOnsetSolarTerms() {
		return this.onsetSolarTerms;
	}

	/**
	 * 发病节气的设定
	 * @param __onsetSolarTerms 发病节气
	 */
	public void setOnsetSolarTerms(String _onsetSolarTerms) {
		this.onsetSolarTerms = _onsetSolarTerms;
	}

	/**
	 * 现病史的取得
	 * @return 现病史
	 */
	public String getPresentHistory() {
		return this.presentHistory;
	}

	/**
	 * 现病史的设定
	 * @param __presentHistory 现病史
	 */
	public void setPresentHistory(String _presentHistory) {
		this.presentHistory = _presentHistory;
	}

	/**
	 * 既往史的取得
	 * @return 既往史
	 */
	public String getPreviousHistory() {
		return this.previousHistory;
	}

	/**
	 * 既往史的设定
	 * @param __previousHistory 既往史
	 */
	public void setPreviousHistory(String _previousHistory) {
		this.previousHistory = _previousHistory;
	}

	/**
	 * 家族史的取得
	 * @return 家族史
	 */
	public String getFamilyHistory() {
		return this.familyHistory;
	}

	/**
	 * 家族史的设定
	 * @param __familyHistory 家族史
	 */
	public void setFamilyHistory(String _familyHistory) {
		this.familyHistory = _familyHistory;
	}

	/**
	 * 过敏史的取得
	 * @return 过敏史
	 */
	public String getAllergyHistory() {
		return this.allergyHistory;
	}

	/**
	 * 过敏史的设定
	 * @param __allergyHistory 过敏史
	 */
	public void setAllergyHistory(String _allergyHistory) {
		this.allergyHistory = _allergyHistory;
	}

	/**
	 * 其他情况的取得
	 * @return 其他情况
	 */
	public String getOtherCases() {
		return this.otherCases;
	}

	/**
	 * 其他情况的设定
	 * @param __otherCases 其他情况
	 */
	public void setOtherCases(String _otherCases) {
		this.otherCases = _otherCases;
	}

	/**
	 * 四诊摘要的取得
	 * @return 四诊摘要
	 */
	public String getFourDiagnosis() {
	    return fourDiagnosis;
	}

	/**
	 * 四诊摘要的设定
	 * @param _fourDiagnosis 四诊摘要
	 */
	public void setFourDiagnosis(String _fourDiagnosis) {
	    this.fourDiagnosis = _fourDiagnosis;
	}

	/**
	 * 舌诊的取得
	 * @return 舌诊
	 */
	public String getTongueInspection() {
		return this.tongueInspection;
	}

	/**
	 * 舌诊的设定
	 * @param __tongueInspection 舌诊
	 */
	public void setTongueInspection(String _tongueInspection) {
		this.tongueInspection = _tongueInspection;
	}

	/**
	 * 脉诊的取得
	 * @return 脉诊
	 */
	public String getPulseTaking() {
		return this.pulseTaking;
	}

	/**
	 * 脉诊的设定
	 * @param __pulseTaking 脉诊
	 */
	public void setPulseTaking(String _pulseTaking) {
		this.pulseTaking = _pulseTaking;
	}

	/**
	 * 体温的取得
	 * @return 体温
	 */
	public String getTemperature() {
		return this.temperature;
	}

	/**
	 * 体温的设定
	 * @param __temperature 体温
	 */
	public void setTemperature(String _temperature) {
		this.temperature = _temperature;
	}

	/**
	 * 脉搏的取得
	 * @return 脉搏
	 */
	public String getPulse() {
		return this.pulse;
	}

	/**
	 * 脉搏的设定
	 * @param __pulse 脉搏
	 */
	public void setPulse(String _pulse) {
		this.pulse = _pulse;
	}

	/**
	 * 呼吸的取得
	 * @return 呼吸
	 */
	public String getBreath() {
		return this.breath;
	}

	/**
	 * 呼吸的设定
	 * @param __breath 呼吸
	 */
	public void setBreath(String _breath) {
		this.breath = _breath;
	}

	/**
	 * 血压的取得
	 * @return 血压
	 */
	public String getBloodPressure() {
		return this.bloodPressure;
	}

	/**
	 * 血压的设定
	 * @param __bloodPressure 血压
	 */
	public void setBloodPressure(String _bloodPressure) {
		this.bloodPressure = _bloodPressure;
	}

	/**
	 * 身高的取得
	 * @return 身高
	 */
	public String getHeight() {
		return this.height;
	}

	/**
	 * 身高的设定
	 * @param __height 身高
	 */
	public void setHeight(String _height) {
		this.height = _height;
	}

	/**
	 * 体重的取得
	 * @return 体重
	 */
	public String getWeight() {
		return this.weight;
	}

	/**
	 * 体重的设定
	 * @param __weight 体重
	 */
	public void setWeight(String _weight) {
		this.weight = _weight;
	}

	/**
	 * 辅助检查的取得
	 * @return 辅助检查
	 */
	public String getAssistantExamination() {
		return this.assistantExamination;
	}

	/**
	 * 辅助检查的设定
	 * @param __assistantExamination 辅助检查
	 */
	public void setAssistantExamination(String _assistantExamination) {
		this.assistantExamination = _assistantExamination;
	}

	/**
	 * 诊断的取得
	 * @return 诊断
	 */
	public String getDiagnosis() {
		return this.diagnosis;
	}

	/**
	 * 诊断的设定
	 * @param __diagnosis 诊断
	 */
	public void setDiagnosis(String _diagnosis) {
		this.diagnosis = _diagnosis;
	}

	/**
	 * 处置的取得
	 * @return 处置
	 */
	public String getDisposition() {
		return this.disposition;
	}

	/**
	 * 处置的设定
	 * @param __disposition 处置
	 */
	public void setDisposition(String _disposition) {
		this.disposition = _disposition;
	}

	/**
	 * 医师的取得
	 * @return 医师
	 */
	public String getPhysician() {
		return this.physician;
	}

	/**
	 * 医师的设定
	 * @param __physician 医师
	 */
	public void setPhysician(String _physician) {
		this.physician = _physician;
	}

	/**
	 * 备注的取得
	 * @return 备注
	 */
	public String getNotes() {
		return this.notes;
	}

	/**
	 * 备注的设定
	 * @param __notes 备注
	 */
	public void setNotes(String _notes) {
		this.notes = _notes;
	}

	/**
	 * 病例记录人的取得
	 * @return 病例记录人
	 */
	public String getCaseRecorder() {
		return this.caseRecorder;
	}

	/**
	 * 病例记录人的设定
	 * @param __caseRecorder 病例记录人
	 */
	public void setCaseRecorder(String _caseRecorder) {
		this.caseRecorder = _caseRecorder;
	}

	/**
	 * 主诊助理的取得
	 * @return 主诊助理
	 */
	public String getAttendingAssistant() {
		return this.attendingAssistant;
	}

	/**
	 * 主诊助理的设定
	 * @param __attendingAssistant 主诊助理
	 */
	public void setAttendingAssistant(String _attendingAssistant) {
		this.attendingAssistant = _attendingAssistant;
	}

	/**
	 * 初诊标识的取得
	 * @return 初诊标识
	 */
	public String getFirstVisitFlag() {
		return this.firstVisitFlag;
	}

	/**
	 * 初诊标识的设定
	 * @param __firstVisitFlag 初诊标识
	 */
	public void setFirstVisitFlag(String _firstVisitFlag) {
		this.firstVisitFlag = _firstVisitFlag;
	}

	/**
	 * 回访记录的取得
	 * @return 回访记录
	 */
	public String getReturnVisit() {
		return this.returnVisit;
	}

	/**
	 * 回访记录的设定
	 * @param __returnVisit 回访记录
	 */
	public void setReturnVisit(String _returnVisit) {
		this.returnVisit = _returnVisit;
	}

	/**
	 * 疗效的取得
	 * @return 疗效
	 */
	public String getEffect() {
		return this.effect;
	}

	/**
	 * 疗效的设定
	 * @param __effect 疗效
	 */
	public void setEffect(String _effect) {
		this.effect = _effect;
	}

	/**
	 * 删除标识的取得
	 * @return 删除标识
	 */
	public String getDeleteFlag() {
		return this.deleteFlag;
	}

	/**
	 * 删除标识的设定
	 * @param __deleteFlag 删除标识
	 */
	public void setDeleteFlag(String _deleteFlag) {
		this.deleteFlag = _deleteFlag;
	}

	/**
	 * 创建者的取得
	 * @return 创建者
	 */
	public String getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * 创建者的设定
	 * @param __createUserId 创建者
	 */
	public void setCreateUserId(String _createUserId) {
		this.createUserId = _createUserId;
	}

	/**
	 * 创建日期的取得
	 * @return 创建日期
	 */
	public Timestamp getCreateDate() {
		return this.createDate;
	}

	/**
	 * 创建日期的设定
	 * @param __createDate 创建日期
	 */
	public void setCreateDate(Timestamp _createDate) {
		this.createDate = _createDate;
	}

	/**
	 * 更新着的取得
	 * @return 更新着
	 */
	public String getLastUpdateUserId() {
		return this.lastUpdateUserId;
	}

	/**
	 * 更新着的设定
	 * @param __lastUpdateUserId 更新着
	 */
	public void setLastUpdateUserId(String _lastUpdateUserId) {
		this.lastUpdateUserId = _lastUpdateUserId;
	}

	/**
	 * 更新者明名的取得
	 * @return 更新者明名
	 */
	public String getLastUpdateUserName() {
		return this.lastUpdateUserName;
	}

	/**
	 * 更新者明名的设定
	 * @param __lastUpdateUserName 更新者明名
	 */
	public void setLastUpdateUserName(String _lastUpdateUserName) {
		this.lastUpdateUserName = _lastUpdateUserName;
	}

	/**
	 * 更新日期的取得
	 * @return 更新日期
	 */
	public Timestamp getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	/**
	 * 更新日期的设定
	 * @param __lastUpdateDate 更新日期
	 */
	public void setLastUpdateDate(Timestamp _lastUpdateDate) {
		this.lastUpdateDate = _lastUpdateDate;
	}

}

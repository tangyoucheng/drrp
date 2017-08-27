/*
 * Copyright(c) 2016 
 */

package cn.com.prescription.leshan.rpc.action.form;

import java.util.List;

import cn.com.prescription.framework.CodeValueRecord;
import cn.com.prescription.framework.action.form.AbstractForm;

/**
 * 初诊信息 form。
 * 
 * @author fsb
 */
/*
 * 新規作成 DATE: 2012.11.09 NAME: fsb
 */
public class RPC00101Form extends AbstractForm {

	/**
	 * 串行版本UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 病例号码
	 */
	private String caseNumber;
	/**
	 * 就诊日期
	 */
	private String medicalDate;
	/**
	 * 患者姓名
	 */
	private String patientName;
	/**
	 * 性别ID
	 */
	private String sexId;
	/**
	 * 性别
	 */
	private List<CodeValueRecord> sexDataSource;
	/**
	 * 年龄
	 */
	private String age;
	/**
	 * 生日
	 */
	private String birthday;
	/**
	 * 住所
	 */
	private String addr;
	/**
	 * 手机
	 */
	private String ceelNumber;

	/**
	 * 民族
	 */
	private String nation = null;

	/**
	 * 出生地
	 */
	private String placeOfBirth = null;

	/**
	 * 婚况
	 */
	private String maritalStatus = null;

	/**
	 * 农历
	 */
	private String lunarBirthday = null;

	/**
	 * 出生时间
	 */
	private String timeOfBirth = null;

	/**
	 * 属相
	 */
	private String zodiac = null;

	/**
	 * 单位
	 */
	private String company = null;

	/**
	 * 职业
	 */
	private String profession = null;

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
	 * 主诉的取得
	 * @return 主诉
	 */
	public String getChiefComplaint() {
		return chiefComplaint;
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
		return onsetSolarTerms;
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
		return presentHistory;
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
		return previousHistory;
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
		return familyHistory;
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
	 * 其他情况的取得
	 * @return 其他情况
	 */
	public String getOtherCases() {
		return otherCases;
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
		return tongueInspection;
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
		return pulseTaking;
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
		return temperature;
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
		return pulse;
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
		return breath;
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
		return bloodPressure;
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
		return height;
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
		return weight;
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
		return assistantExamination;
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
		return diagnosis;
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
		return disposition;
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
		return physician;
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
		return notes;
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
		return caseRecorder;
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
		return attendingAssistant;
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
		return firstVisitFlag;
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
		return returnVisit;
	}

	/**
	 * 回访记录的设定
	 * @param __returnVisit 回访记录
	 */
	public void setReturnVisit(String _returnVisit) {
		this.returnVisit = _returnVisit;
	}

	/**
	 * 年龄的取得
	 * @return 年龄
	 */
	public String getAge() {
		return age;
	}

	/**
	 * 年龄的设定
	 * @param __age 年龄
	 */
	public void setAge(String _age) {
		this.age = _age;
	}

	/**
	 * 生日的取得
	 * @return 生日
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * 生日的设定
	 * @param __birthday 生日
	 */
	public void setBirthday(String _birthday) {
		this.birthday = _birthday;
	}

	/**
	 * 病例号码的取得
	 * @return 病例号码
	 */
	public String getCaseNumber() {
		return caseNumber;
	}

	/**
	 * 病例号码的设定
	 * @param __caseNumber 病例号码
	 */
	public void setCaseNumber(String _caseNumber) {
		this.caseNumber = _caseNumber;
	}

	/**
	 * 就诊日期的取得
	 * @return 就诊日期
	 */
	public String getMedicalDate() {
		return medicalDate;
	}

	/**
	 * 就诊日期的设定
	 * @param __medicalDate 就诊日期
	 */
	public void setMedicalDate(String _medicalDate) {
		this.medicalDate = _medicalDate;
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
	 * 
	 * @return 性别
	 */
	public List<CodeValueRecord> getSexDataSource() {
		return sexDataSource;
	}

	/**
	 * 性别的设定
	 * 
	 * @param __sexDataSource
	 *            性别
	 */
	public void setSexDataSource(List<CodeValueRecord> _sexDataSource) {
		this.sexDataSource = _sexDataSource;
	}

	/**
	 * 住所的取得
	 * @return 住所
	 */
	public String getAddr() {
		return addr;
	}

	/**
	 * 住所的设定
	 * @param __addr 住所
	 */
	public void setAddr(String _addr) {
		this.addr = _addr;
	}

	/**
	 * 手机的取得
	 * @return 手机
	 */
	public String getCeelNumber() {
		return ceelNumber;
	}

	/**
	 * 手机的设定
	 * @param __ceelNumber 手机
	 */
	public void setCeelNumber(String _ceelNumber) {
		this.ceelNumber = _ceelNumber;
	}

	/**
	 * 民族的取得
	 * @return 民族
	 */
	public String getNation() {
		return nation;
	}

	/**
	 * 民族的设定
	 * @param __nation 民族
	 */
	public void setNation(String _nation) {
		this.nation = _nation;
	}

	/**
	 * 出生地的取得
	 * @return 出生地
	 */
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	/**
	 * 出生地的设定
	 * @param __placeOfBirth 出生地
	 */
	public void setPlaceOfBirth(String _placeOfBirth) {
		this.placeOfBirth = _placeOfBirth;
	}

	/**
	 * 婚况的取得
	 * @return 婚况
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}

	/**
	 * 婚况的设定
	 * @param __maritalStatus 婚况
	 */
	public void setMaritalStatus(String _maritalStatus) {
		this.maritalStatus = _maritalStatus;
	}

	/**
	 * 农历的取得
	 * @return 农历
	 */
	public String getLunarBirthday() {
		return lunarBirthday;
	}

	/**
	 * 农历的设定
	 * @param __lunarBirthday 农历
	 */
	public void setLunarBirthday(String _lunarBirthday) {
		this.lunarBirthday = _lunarBirthday;
	}

	/**
	 * 出生时间的取得
	 * @return 出生时间
	 */
	public String getTimeOfBirth() {
		return timeOfBirth;
	}

	/**
	 * 出生时间的设定
	 * @param __timeOfBirth 出生时间
	 */
	public void setTimeOfBirth(String _timeOfBirth) {
		this.timeOfBirth = _timeOfBirth;
	}

	/**
	 * 属相的取得
	 * @return 属相
	 */
	public String getZodiac() {
		return zodiac;
	}

	/**
	 * 属相的设定
	 * @param __zodiac 属相
	 */
	public void setZodiac(String _zodiac) {
		this.zodiac = _zodiac;
	}

	/**
	 * 单位的取得
	 * @return 单位
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * 单位的设定
	 * @param __company 单位
	 */
	public void setCompany(String _company) {
		this.company = _company;
	}

	/**
	 * 职业的取得
	 * 
	 * @return 职业
	 */
	public String getProfession() {
		return profession;
	}

	/**
	 * 职业的设定
	 * 
	 * @param __profession
	 *            职业
	 */
	public void setProfession(String _profession) {
		this.profession = _profession;
	}

	/**
	 * 疗效的取得
	 * @return 疗效
	 */
	public String getEffect() {
		return effect;
	}

	/**
	 * 疗效的设定
	 * @param __effect 疗效
	 */
	public void setEffect(String _effect) {
		this.effect = _effect;
	}
}

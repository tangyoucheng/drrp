/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpc.data.model;

import cn.com.prescription.leshan.common.data.model.RpmOutpatientModel;

/**
 * 复诊编辑画面のモードル
 * 
 * @author fsb
 */
/*
 * 新規作成 DATE: 2012.11.07 NAME: fsb
 */
public class RPC00105Model extends RpmOutpatientModel {

	/**
	 * 串行版本UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 性别
	 */
	private String sex = null;

	/**
	 * 用?ID
	 */
	private String userId = null;

	/**
	 * 用?名
	 */
	private String userName = null;

	/**
	 * 生日
	 */
	private String birthday = null;

	/**
	 * 性?ID
	 */
	private String sexId = null;

	/**
	 * ?政番号
	 */
	private String postNumber = null;

	/**
	 * 地址
	 */
	private String addr = null;

	/**
	 * 座机
	 */
	private String phoneNumber = null;

	/**
	 * 手机
	 */
	private String ceelNumber = null;

	/**
	 * 身??号?
	 */
	private String idNumber = null;

	/**
	 * ?子?箱
	 */
	private String email = null;

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
	 * ログイン画面モデル 的构造。
	 */
	public RPC00105Model() {
		super();
	}

	/**
	 * 性别的取得
	 * @return 性别
	 */
	public String getSex() {
	    return sex;
	}

	/**
	 * 性别的设定
	 * @param _sex 性别
	 */
	public void setSex(String _sex) {
	    this.sex = _sex;
	}

	/**
	 * 用?ID的取得
	 * @return 用?ID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 用?ID的设定
	 * @param __userId 用?ID
	 */
	public void setUserId(String _userId) {
		this.userId = _userId;
	}

	/**
	 * 用?名的取得
	 * @return 用?名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 用?名的设定
	 * @param __userName 用?名
	 */
	public void setUserName(String _userName) {
		this.userName = _userName;
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
	 * 性?ID的取得
	 * @return 性?ID
	 */
	public String getSexId() {
		return sexId;
	}

	/**
	 * 性?ID的设定
	 * @param __sexId 性?ID
	 */
	public void setSexId(String _sexId) {
		this.sexId = _sexId;
	}

	/**
	 * ?政番号的取得
	 * @return ?政番号
	 */
	public String getPostNumber() {
		return postNumber;
	}

	/**
	 * ?政番号的设定
	 * @param __postNumber ?政番号
	 */
	public void setPostNumber(String _postNumber) {
		this.postNumber = _postNumber;
	}

	/**
	 * 地址的取得
	 * @return 地址
	 */
	public String getAddr() {
		return addr;
	}

	/**
	 * 地址的设定
	 * @param __addr 地址
	 */
	public void setAddr(String _addr) {
		this.addr = _addr;
	}

	/**
	 * 座机的取得
	 * @return 座机
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * 座机的设定
	 * @param __phoneNumber 座机
	 */
	public void setPhoneNumber(String _phoneNumber) {
		this.phoneNumber = _phoneNumber;
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
	 * 身??号?的取得
	 * @return 身??号?
	 */
	public String getIdNumber() {
		return idNumber;
	}

	/**
	 * 身??号?的设定
	 * @param __idNumber 身??号?
	 */
	public void setIdNumber(String _idNumber) {
		this.idNumber = _idNumber;
	}

	/**
	 * ?子?箱的取得
	 * @return ?子?箱
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * ?子?箱的设定
	 * @param __email ?子?箱
	 */
	public void setEmail(String _email) {
		this.email = _email;
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
	 * @return 职业
	 */
	public String getProfession() {
		return profession;
	}

	/**
	 * 职业的设定
	 * @param __profession 职业
	 */
	public void setProfession(String _profession) {
		this.profession = _profession;
	}

}

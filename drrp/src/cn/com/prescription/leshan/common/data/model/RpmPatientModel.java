/*
 * Copyright(c) 2016
 */

package cn.com.prescription.leshan.common.data.model;

import java.sql.Timestamp;
import cn.com.prescription.framework.data.model.StandardModel;

/**
 * 患者基本信息表 モデルオブジェクト。
 *
 * @author gl
 */
/*
 * 新規作成 DATE: 2012.02.13 NAME: gl
 */
public class RpmPatientModel extends StandardModel {

	/**
	 * 串行版本UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * テーブル定義
	 */
	public static final String TABLE = "RPM_PATIENT";

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
	 * 患者基本信息表モデルBean 的构造。
	 */
	public RpmPatientModel() {
		super();
	}

	/**
	 * 用?ID的取得
	 * 
	 * @return 用?ID
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * 用?ID的设定
	 * 
	 * @param __userId
	 *            用?ID
	 */
	public void setUserId(String _userId) {
		this.userId = _userId;
	}

	/**
	 * 用?名的取得
	 * 
	 * @return 用?名
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * 用?名的设定
	 * 
	 * @param __userName
	 *            用?名
	 */
	public void setUserName(String _userName) {
		this.userName = _userName;
	}

	/**
	 * 生日的取得
	 * 
	 * @return 生日
	 */
	public String getBirthday() {
		return this.birthday;
	}

	/**
	 * 生日的设定
	 * 
	 * @param __birthday
	 *            生日
	 */
	public void setBirthday(String _birthday) {
		this.birthday = _birthday;
	}

	/**
	 * 性?ID的取得
	 * 
	 * @return 性?ID
	 */
	public String getSexId() {
		return this.sexId;
	}

	/**
	 * 性?ID的设定
	 * 
	 * @param __sexId
	 *            性?ID
	 */
	public void setSexId(String _sexId) {
		this.sexId = _sexId;
	}

	/**
	 * ?政番号的取得
	 * 
	 * @return ?政番号
	 */
	public String getPostNumber() {
		return this.postNumber;
	}

	/**
	 * ?政番号的设定
	 * 
	 * @param __postNumber
	 *            ?政番号
	 */
	public void setPostNumber(String _postNumber) {
		this.postNumber = _postNumber;
	}

	/**
	 * 地址的取得
	 * 
	 * @return 地址
	 */
	public String getAddr() {
		return this.addr;
	}

	/**
	 * 地址的设定
	 * 
	 * @param __addr
	 *            地址
	 */
	public void setAddr(String _addr) {
		this.addr = _addr;
	}

	/**
	 * 座机的取得
	 * 
	 * @return 座机
	 */
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * 座机的设定
	 * 
	 * @param __phoneNumber
	 *            座机
	 */
	public void setPhoneNumber(String _phoneNumber) {
		this.phoneNumber = _phoneNumber;
	}

	/**
	 * 手机的取得
	 * 
	 * @return 手机
	 */
	public String getCeelNumber() {
		return this.ceelNumber;
	}

	/**
	 * 手机的设定
	 * 
	 * @param __ceelNumber
	 *            手机
	 */
	public void setCeelNumber(String _ceelNumber) {
		this.ceelNumber = _ceelNumber;
	}

	/**
	 * 身??号?的取得
	 * 
	 * @return 身??号?
	 */
	public String getIdNumber() {
		return this.idNumber;
	}

	/**
	 * 身??号?的设定
	 * 
	 * @param __idNumber
	 *            身??号?
	 */
	public void setIdNumber(String _idNumber) {
		this.idNumber = _idNumber;
	}

	/**
	 * ?子?箱的取得
	 * 
	 * @return ?子?箱
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * ?子?箱的设定
	 * 
	 * @param __email
	 *            ?子?箱
	 */
	public void setEmail(String _email) {
		this.email = _email;
	}

	/**
	 * 民族的取得
	 * 
	 * @return 民族
	 */
	public String getNation() {
		return nation;
	}

	/**
	 * 民族的设定
	 * 
	 * @param _nation
	 *            民族
	 */
	public void setNation(String _nation) {
		this.nation = _nation;
	}

	/**
	 * 出生地的取得
	 * 
	 * @return 出生地
	 */
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	/**
	 * 出生地的设定
	 * 
	 * @param _placeOfBirth
	 *            出生地
	 */
	public void setPlaceOfBirth(String _placeOfBirth) {
		this.placeOfBirth = _placeOfBirth;
	}

	/**
	 * 婚况的取得
	 * 
	 * @return 婚况
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}

	/**
	 * 婚况的设定
	 * 
	 * @param _maritalStatus
	 *            婚况
	 */
	public void setMaritalStatus(String _maritalStatus) {
		this.maritalStatus = _maritalStatus;
	}

	/**
	 * 农历的取得
	 * 
	 * @return 农历
	 */
	public String getLunarBirthday() {
		return lunarBirthday;
	}

	/**
	 * 农历的设定
	 * 
	 * @param _lunarBirthday
	 *            农历
	 */
	public void setLunarBirthday(String _lunarBirthday) {
		this.lunarBirthday = _lunarBirthday;
	}

	/**
	 * 出生时间的取得
	 * 
	 * @return 出生时间
	 */
	public String getTimeOfBirth() {
		return timeOfBirth;
	}

	/**
	 * 出生时间的设定
	 * 
	 * @param _timeOfBirth
	 *            出生时间
	 */
	public void setTimeOfBirth(String _timeOfBirth) {
		this.timeOfBirth = _timeOfBirth;
	}

	/**
	 * 属相的取得
	 * 
	 * @return 属相
	 */
	public String getZodiac() {
		return zodiac;
	}

	/**
	 * 属相的设定
	 * 
	 * @param _zodiac
	 *            属相
	 */
	public void setZodiac(String _zodiac) {
		this.zodiac = _zodiac;
	}

	/**
	 * 单位的取得
	 * 
	 * @return 单位
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * 单位的设定
	 * 
	 * @param _company
	 *            单位
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
	 * @param _profession
	 *            职业
	 */
	public void setProfession(String _profession) {
		this.profession = _profession;
	}

	/**
	 * ?除??的取得
	 * 
	 * @return ?除??
	 */
	public String getDeleteFlag() {
		return this.deleteFlag;
	}

	/**
	 * ?除??的设定
	 * 
	 * @param __deleteFlag
	 *            ?除??
	 */
	public void setDeleteFlag(String _deleteFlag) {
		this.deleteFlag = _deleteFlag;
	}

	/**
	 * ?建者的取得
	 * 
	 * @return ?建者
	 */
	public String getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * ?建者的设定
	 * 
	 * @param __createUserId
	 *            ?建者
	 */
	public void setCreateUserId(String _createUserId) {
		this.createUserId = _createUserId;
	}

	/**
	 * ?建日期的取得
	 * 
	 * @return ?建日期
	 */
	public Timestamp getCreateDate() {
		return this.createDate;
	}

	/**
	 * ?建日期的设定
	 * 
	 * @param __createDate
	 *            ?建日期
	 */
	public void setCreateDate(Timestamp _createDate) {
		this.createDate = _createDate;
	}

	/**
	 * 最?更新者的取得
	 * 
	 * @return 最?更新者
	 */
	public String getLastUpdateUserId() {
		return this.lastUpdateUserId;
	}

	/**
	 * 最?更新者的设定
	 * 
	 * @param __lastUpdateUserId
	 *            最?更新者
	 */
	public void setLastUpdateUserId(String _lastUpdateUserId) {
		this.lastUpdateUserId = _lastUpdateUserId;
	}

	/**
	 * 最?更新者名的取得
	 * 
	 * @return 最?更新者名
	 */
	public String getLastUpdateUserName() {
		return this.lastUpdateUserName;
	}

	/**
	 * 最?更新者名的设定
	 * 
	 * @param __lastUpdateUserName
	 *            最?更新者名
	 */
	public void setLastUpdateUserName(String _lastUpdateUserName) {
		this.lastUpdateUserName = _lastUpdateUserName;
	}

	/**
	 * 最?更新日期的取得
	 * 
	 * @return 最?更新日期
	 */
	public Timestamp getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	/**
	 * 最?更新日期的设定
	 * 
	 * @param __lastUpdateDate
	 *            最?更新日期
	 */
	public void setLastUpdateDate(Timestamp _lastUpdateDate) {
		this.lastUpdateDate = _lastUpdateDate;
	}

}

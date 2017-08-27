/*
 * Copyright(c) 2016 
 */

package cn.com.prescription.leshan.rpa.action.form;

import cn.com.prescription.framework.action.form.AbstractForm;

/**
 * 患者一览dto。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.09 NAME: fsb
 */
public class RPA0020401Dto extends AbstractForm {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private String userId = null;

    /**
     * 用户名
     */
    private String userName = null;

    /**
     * 生日
     */
    private String birthday = null;

    /**
     * 性别ID
     */
    private String sexId = null;

    /**
     * 邮政番号
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
     * 电子邮箱
     */
    private String email = null;

    /**
     * 身份证号码
     */
    private String idNumber = null;

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
     * 处方创建日
     */
    private String prescriptionCreateDate = null;

    /**
     * 处方种类
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
     * 处方金额
     */
    private String price = null;

    /**
	 * 用户ID的取得
	 * @return 用户ID
	 */
	public String getUserId() {
	    return userId;
	}

	/**
	 * 用户ID的设定
	 * @param __userId 用户ID
	 */
	public void setUserId(String _userId) {
	    this.userId = _userId;
	}

	/**
	 * 用户名的取得
	 * @return 用户名
	 */
	public String getUserName() {
	    return userName;
	}

	/**
	 * 用户名的设定
	 * @param __userName 用户名
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
	 * 邮政番号的取得
	 * @return 邮政番号
	 */
	public String getPostNumber() {
	    return postNumber;
	}

	/**
	 * 邮政番号的设定
	 * @param __postNumber 邮政番号
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
	 * 电子邮箱的取得
	 * @return 电子邮箱
	 */
	public String getEmail() {
	    return email;
	}

	/**
	 * 电子邮箱的设定
	 * @param __email 电子邮箱
	 */
	public void setEmail(String _email) {
	    this.email = _email;
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
	 * 民族的取得
	 * @return 民族
	 */
	public String getNation() {
	    return nation;
	}

	/**
	 * 民族的设定
	 * @param _nation 民族
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
	 * @param _placeOfBirth 出生地
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
	 * @param _maritalStatus 婚况
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
	 * @param _lunarBirthday 农历
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
	 * @param _timeOfBirth 出生时间
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
	 * @param _zodiac 属相
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
	 * @param _company 单位
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
	 * @param _profession 职业
	 */
	public void setProfession(String _profession) {
	    this.profession = _profession;
	}

	/**
	 * 处方创建日的取得
	 * @return 处方创建日
	 */
	public String getPrescriptionCreateDate() {
	    return prescriptionCreateDate;
	}

	/**
	 * 处方创建日的设定
	 * @param __prescriptionCreateDate 处方创建日
	 */
	public void setPrescriptionCreateDate(String _prescriptionCreateDate) {
	    this.prescriptionCreateDate = _prescriptionCreateDate;
	}

	/**
	 * 处方种类的取得
	 * @return 处方种类
	 */
	public String getPrescriptionType() {
	    return prescriptionType;
	}

	/**
	 * 处方种类的设定
	 * @param __prescriptionType 处方种类
	 */
	public void setPrescriptionType(String _prescriptionType) {
	    this.prescriptionType = _prescriptionType;
	}

	/**
	 * 处方内容的取得
	 * @return 处方内容
	 */
	public String getContents() {
	    return contents;
	}

	/**
	 * 处方内容的设定
	 * @param __contents 处方内容
	 */
	public void setContents(String _contents) {
	    this.contents = _contents;
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
     * 处方金额的取得
     * @return 处方金额
     */
    public String getPrice() {
        return price;
    }

    /**
	 * 处方金额的设定
	 * @param __price 处方金额
	 */
	public void setPrice(String _price) {
	    this.price = _price;
	}

}

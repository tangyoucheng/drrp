/*
 * Copyright(c) 2016 
 */

package cn.com.prescription.leshan.rpa.action.form;

import java.util.List;

import cn.com.prescription.framework.CodeValueRecord;
import cn.com.prescription.framework.action.form.AbstractForm;

/**
 * 患者信息 form。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.09 NAME: fsb
 */
public class RPA00203Form extends AbstractForm {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;
    /**
     * 生日
     */
    private String birthday;
    /**
     * 年龄
     */
    private String age;
    /**
     * 邮政编码
     */
    private String postNumber;
    /**
     * 住所
     */
    private String addr;
    /**
     * 座机
     */
    private String phoneNumber;
    /**
     * 手机
     */
    private String ceelNumber;
    /**
     * 身份证号码
     */
    private String idNumber;
    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 最终更新者
     */
    private String lastUpdateUserId = null;

    /**
     * 最终更新日期
     */
    private String lastUpdateDate = null;
    /**
     * 性别ID
     */
    private String sexId;
    /**
     * 性别
     */
    private List<CodeValueRecord> sexDataSource;

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
	 * 用户IDを取得します。
	 * @return 用户ID
	 */
	public String getUserId() {
	    return userId;
	}

	/**
	 * 用户IDを設定します。
	 * @param __userId 用户ID
	 */
	public void setUserId(String _userId) {
	    this.userId = _userId;
	}

	/**
	 * 用户名を取得します。
	 * @return 用户名
	 */
	public String getUserName() {
	    return userName;
	}

	/**
	 * 用户名を設定します。
	 * @param __userName 用户名
	 */
	public void setUserName(String _userName) {
	    this.userName = _userName;
	}

	/**
	 * 最终更新者を取得します。
	 * @return 最终更新者
	 */
	public String getLastUpdateUserId() {
	    return lastUpdateUserId;
	}

	/**
	 * 最终更新者を設定します。
	 * @param __lastUpdateUserId 最终更新者
	 */
	public void setLastUpdateUserId(String _lastUpdateUserId) {
	    this.lastUpdateUserId = _lastUpdateUserId;
	}

	/**
	 * 生日を取得します。
	 * @return 生日
	 */
	public String getBirthday() {
	    return birthday;
	}

	/**
	 * 生日を設定します。
	 * @param __birthday 生日
	 */
	public void setBirthday(String _birthday) {
	    this.birthday = _birthday;
	}

	/**
	 * 年龄を取得します。
	 * @return 年龄
	 */
	public String getAge() {
	    return age;
	}

	/**
	 * 年龄を設定します。
	 * @param __age 年龄
	 */
	public void setAge(String _age) {
	    this.age = _age;
	}

	/**
	 * 邮政编码を取得します。
	 * @return 邮政编码
	 */
	public String getPostNumber() {
	    return postNumber;
	}

	/**
	 * 邮政编码を設定します。
	 * @param __postNumber 邮政编码
	 */
	public void setPostNumber(String _postNumber) {
	    this.postNumber = _postNumber;
	}

	/**
	 * 住所を取得します。
	 * @return 住所
	 */
	public String getAddr() {
	    return addr;
	}

	/**
	 * 住所を設定します。
	 * @param __addr 住所
	 */
	public void setAddr(String _addr) {
	    this.addr = _addr;
	}

	/**
	 * 座机を取得します。
	 * @return 座机
	 */
	public String getPhoneNumber() {
	    return phoneNumber;
	}

	/**
	 * 座机を設定します。
	 * @param __phoneNumber 座机
	 */
	public void setPhoneNumber(String _phoneNumber) {
	    this.phoneNumber = _phoneNumber;
	}

	/**
	 * 手机を取得します。
	 * @return 手机
	 */
	public String getCeelNumber() {
	    return ceelNumber;
	}

	/**
	 * 手机を設定します。
	 * @param __ceelNumber 手机
	 */
	public void setCeelNumber(String _ceelNumber) {
	    this.ceelNumber = _ceelNumber;
	}

	/**
	 * 身份证号码を取得します。
	 * @return 身份证号码
	 */
	public String getIdNumber() {
	    return idNumber;
	}

	/**
	 * 身份证号码を設定します。
	 * @param __idNumber 身份证号码
	 */
	public void setIdNumber(String _idNumber) {
	    this.idNumber = _idNumber;
	}

	/**
     * 电子邮箱的取得
     * 
     * @return 电子邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 电子邮箱的设定
     * 
     * @param _email 电子邮箱
     */
    public void setEmail(String _email) {
        this.email = _email;
    }

    /**
	 * 最终更新日期を取得します。
	 * @return 最终更新日期
	 */
	public String getLastUpdateDate() {
	    return lastUpdateDate;
	}

	/**
	 * 最终更新日期を設定します。
	 * @param __lastUpdateDate 最终更新日期
	 */
	public void setLastUpdateDate(String _lastUpdateDate) {
	    this.lastUpdateDate = _lastUpdateDate;
	}

	/**
	 * 性别IDを取得します。
	 * @return 性别ID
	 */
	public String getSexId() {
	    return sexId;
	}

	/**
	 * 性别IDを設定します。
	 * @param __sexId 性别ID
	 */
	public void setSexId(String _sexId) {
	    this.sexId = _sexId;
	}

	/**
	 * 性别を取得します。
	 * @return 性别
	 */
	public List<CodeValueRecord> getSexDataSource() {
	    return sexDataSource;
	}

	/**
	 * 性别を設定します。
	 * @param __sexDataSource 性别
	 */
	public void setSexDataSource(List<CodeValueRecord> _sexDataSource) {
	    this.sexDataSource = _sexDataSource;
	}

	/**
	 * 民族を取得します。
	 * @return 民族
	 */
	public String getNation() {
	    return nation;
	}

	/**
	 * 民族を設定します。
	 * @param _nation 民族
	 */
	public void setNation(String _nation) {
	    this.nation = _nation;
	}

	/**
	 * 出生地を取得します。
	 * @return 出生地
	 */
	public String getPlaceOfBirth() {
	    return placeOfBirth;
	}

	/**
	 * 出生地を設定します。
	 * @param __placeOfBirth 出生地
	 */
	public void setPlaceOfBirth(String _placeOfBirth) {
	    this.placeOfBirth = _placeOfBirth;
	}

	/**
	 * 婚况を取得します。
	 * @return 婚况
	 */
	public String getMaritalStatus() {
	    return maritalStatus;
	}

	/**
	 * 婚况を設定します。
	 * @param _maritalStatus 婚况
	 */
	public void setMaritalStatus(String _maritalStatus) {
	    this.maritalStatus = _maritalStatus;
	}

	/**
	 * 农历を取得します。
	 * @return 农历
	 */
	public String getLunarBirthday() {
	    return lunarBirthday;
	}

	/**
	 * 农历を設定します。
	 * @param _lunarBirthday 农历
	 */
	public void setLunarBirthday(String _lunarBirthday) {
	    this.lunarBirthday = _lunarBirthday;
	}

	/**
	 * 出生时间を取得します。
	 * @return 出生时间
	 */
	public String getTimeOfBirth() {
	    return timeOfBirth;
	}

	/**
	 * 出生时间を設定します。
	 * @param _timeOfBirth 出生时间
	 */
	public void setTimeOfBirth(String _timeOfBirth) {
	    this.timeOfBirth = _timeOfBirth;
	}

	/**
	 * 属相を取得します。
	 * @return 属相
	 */
	public String getZodiac() {
	    return zodiac;
	}

	/**
	 * 属相を設定します。
	 * @param _zodiac 属相
	 */
	public void setZodiac(String _zodiac) {
	    this.zodiac = _zodiac;
	}

	/**
	 * 单位を取得します。
	 * @return 单位
	 */
	public String getCompany() {
	    return company;
	}

	/**
	 * 单位を設定します。
	 * @param _company 单位
	 */
	public void setCompany(String _company) {
	    this.company = _company;
	}

	/**
	 * 职业を取得します。
	 * @return 职业
	 */
	public String getProfession() {
	    return profession;
	}

	/**
	 * 职业を設定します。
	 * @param _profession 职业
	 */
	public void setProfession(String _profession) {
	    this.profession = _profession;
	}
}

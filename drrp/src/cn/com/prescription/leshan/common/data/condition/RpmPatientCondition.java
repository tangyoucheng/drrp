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
public class RpmPatientCondition extends BaseCondition {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

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
	 * @return the nation
	 */
	public String getNation() {
		return nation;
	}

	/**
	 * @param nation the nation to set
	 */
	public void setNation(String nation) {
		this.nation = nation;
	}

	/**
	 * @return the placeOfBirth
	 */
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	/**
	 * @param placeOfBirth the placeOfBirth to set
	 */
	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	/**
	 * @return the maritalStatus
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}

	/**
	 * @param maritalStatus the maritalStatus to set
	 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	/**
	 * @return the lunarBirthday
	 */
	public String getLunarBirthday() {
		return lunarBirthday;
	}

	/**
	 * @param lunarBirthday the lunarBirthday to set
	 */
	public void setLunarBirthday(String lunarBirthday) {
		this.lunarBirthday = lunarBirthday;
	}

	/**
	 * @return the timeOfBirth
	 */
	public String getTimeOfBirth() {
		return timeOfBirth;
	}

	/**
	 * @param timeOfBirth the timeOfBirth to set
	 */
	public void setTimeOfBirth(String timeOfBirth) {
		this.timeOfBirth = timeOfBirth;
	}

	/**
	 * @return the zodiac
	 */
	public String getZodiac() {
		return zodiac;
	}

	/**
	 * @param zodiac the zodiac to set
	 */
	public void setZodiac(String zodiac) {
		this.zodiac = zodiac;
	}

	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * @return the profession
	 */
	public String getProfession() {
		return profession;
	}

	/**
	 * @param profession the profession to set
	 */
	public void setProfession(String profession) {
		this.profession = profession;
	}

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
     * 患者基本信息表Condition 的构造。
     */
    public RpmPatientCondition() {
        super();
    }

    /**
     * 用?IDを取得する。
     *
     * @return 用?ID
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * 用?IDを設定する。
     *
     * @param _userId 用?ID
     */
    public void setUserId(String _userId) {
        this.userId = _userId;
    }

    /**
     * 用?名を取得する。
     *
     * @return 用?名
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * 用?名を設定する。
     *
     * @param _userName 用?名
     */
    public void setUserName(String _userName) {
        this.userName = _userName;
    }

    /**
     * 生日を取得する。
     *
     * @return 生日
     */
    public String getBirthday() {
        return this.birthday;
    }

    /**
     * 生日を設定する。
     *
     * @param _birthday 生日
     */
    public void setBirthday(String _birthday) {
        this.birthday = _birthday;
    }

    /**
     * 性?IDを取得する。
     *
     * @return 性?ID
     */
    public String getSexId() {
        return this.sexId;
    }

    /**
     * 性?IDを設定する。
     *
     * @param _sexId 性?ID
     */
    public void setSexId(String _sexId) {
        this.sexId = _sexId;
    }

    /**
     * ?政番号を取得する。
     *
     * @return ?政番号
     */
    public String getPostNumber() {
        return this.postNumber;
    }

    /**
     * ?政番号を設定する。
     *
     * @param _postNumber ?政番号
     */
    public void setPostNumber(String _postNumber) {
        this.postNumber = _postNumber;
    }

    /**
     * 地址を取得する。
     *
     * @return 地址
     */
    public String getAddr() {
        return this.addr;
    }

    /**
     * 地址を設定する。
     *
     * @param _addr 地址
     */
    public void setAddr(String _addr) {
        this.addr = _addr;
    }

    /**
     * 座机を取得する。
     *
     * @return 座机
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * 座机を設定する。
     *
     * @param _phoneNumber 座机
     */
    public void setPhoneNumber(String _phoneNumber) {
        this.phoneNumber = _phoneNumber;
    }

    /**
     * 手机を取得する。
     *
     * @return 手机
     */
    public String getCeelNumber() {
        return this.ceelNumber;
    }

    /**
     * 手机を設定する。
     *
     * @param _ceelNumber 手机
     */
    public void setCeelNumber(String _ceelNumber) {
        this.ceelNumber = _ceelNumber;
    }

    /**
     * 身??号?を取得する。
     *
     * @return 身??号?
     */
    public String getIdNumber() {
        return this.idNumber;
    }

    /**
     * 身??号?を設定する。
     *
     * @param _idNumber 身??号?
     */
    public void setIdNumber(String _idNumber) {
        this.idNumber = _idNumber;
    }

    /**
     * ?子?箱を取得する。
     *
     * @return ?子?箱
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * ?子?箱を設定する。
     *
     * @param _email ?子?箱
     */
    public void setEmail(String _email) {
        this.email = _email;
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

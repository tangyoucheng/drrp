/*
 * Copyright(c) 2016 
 */

package cn.com.prescription.leshan.rpc.action.form;

import cn.com.prescription.framework.action.form.AbstractForm;

/**
 * 门诊一览dto。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.09 NAME: fsb
 */
public class RPC0010201Dto extends AbstractForm {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 门诊ID
     */
    private String recordId = null;

    /**
     * 用户名
     */
    private String userName = null;

    /**
     * 手机
     */
    private String ceelNumber = null;

    /**
     * 门诊创建日期
     */
    private String medicalDate = null;
	/**
	 * 初诊标识
	 */
	private String firstVisitFlag = null;

    /**
     * 门诊类型
     */
    private String outpatientTypeName = null;

	/**
	 * 门诊ID的取得
	 * @return 门诊ID
	 */
	public String getRecordId() {
	    return recordId;
	}

	/**
	 * 门诊ID的设定
	 * @param __recordId 门诊ID
	 */
	public void setRecordId(String _recordId) {
	    this.recordId = _recordId;
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
	 * 门诊创建日期的取得
	 * @return 门诊创建日期
	 */
	public String getMedicalDate() {
	    return medicalDate;
	}

	/**
	 * 门诊创建日期的设定
	 * @param __medicalDate 门诊创建日期
	 */
	public void setMedicalDate(String _medicalDate) {
	    this.medicalDate = _medicalDate;
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
	 * @param _firstVisitFlag 初诊标识
	 */
	public void setFirstVisitFlag(String _firstVisitFlag) {
	    this.firstVisitFlag = _firstVisitFlag;
	}

	/**
	 * 门诊类型的取得
	 * @return 门诊类型
	 */
	public String getOutpatientTypeName() {
	    return outpatientTypeName;
	}

	/**
	 * 门诊类型的设定
	 * @param __outpatientTypeName 门诊类型
	 */
	public void setOutpatientTypeName(String _outpatientTypeName) {
	    this.outpatientTypeName = _outpatientTypeName;
	}



}

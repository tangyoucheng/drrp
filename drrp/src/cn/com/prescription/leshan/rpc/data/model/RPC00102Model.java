/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpc.data.model;


import cn.com.prescription.leshan.common.data.model.RpmOutpatientModel;

/**
 * 门诊情报检索画面のモードル
 * 
 * @author fsb
 */
/*
 * 新規作成 DATE: 2012.11.07 NAME: fsb
 */
public class RPC00102Model extends RpmOutpatientModel {

	/**
	 * 串行版本UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 患者名
	 */
	private String userName = null;

	/**
	 * 手机
	 */
	private String ceelNumber = null;
	/**
	 * 初诊标识
	 */
	private String firstVisitFlag = null;
	/**
	 * 门诊类型名
	 */
	private String outpatientTypeName = null;

	/**
	 * ログイン画面モデル 的构造。
	 */
	public RPC00102Model() {
		super();
	}

	/**
	 * 患者名的取得
	 * @return 患者名
	 */
	public String getUserName() {
	    return userName;
	}

	/**
	 * 患者名的设定
	 * @param __userName 患者名
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
	 * 门诊类型名的取得
	 * @return 门诊类型名
	 */
	public String getOutpatientTypeName() {
	    return outpatientTypeName;
	}

	/**
	 * 门诊类型名的设定
	 * @param __outpatientTypeName 门诊类型名
	 */
	public void setOutpatientTypeName(String _outpatientTypeName) {
	    this.outpatientTypeName = _outpatientTypeName;
	}

}

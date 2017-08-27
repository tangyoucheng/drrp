/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpc.data.condition;

import cn.com.prescription.leshan.common.data.condition.RpmOutpatientCondition;

/**
 * 门诊情报检索画面のコンディション
 * 
 * @author fsb
 */
/*
 * 新規作成 DATE: 2012.11.07 NAME: fsb
 */
public class RPC00102Condition extends RpmOutpatientCondition {

	/**
	 * 串行版本UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 患者名
	 */
	private String userName = null;

	/**
	 * 门诊创建者
	 */
	private String outpatientCreateUserId = null;

	/**
	 * 用户情報設定画面Condition的构造
	 */
	public RPC00102Condition() {
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
	 * @param _userName 患者名
	 */
	public void setUserName(String _userName) {
	    this.userName = _userName;
	}

	/**
	 * 门诊创建者的取得
	 * @return 门诊创建者
	 */
	public String getOutpatientCreateUserId() {
		return outpatientCreateUserId;
	}

	/**
	 * 门诊创建者的设定
	 * @param __outpatientCreateUserId 门诊创建者
	 */
	public void setOutpatientCreateUserId(String _outpatientCreateUserId) {
		this.outpatientCreateUserId = _outpatientCreateUserId;
	}

}
/*
 * Copyright(c) 2016 
 */

package cn.com.prescription.leshan.rpc.action.form;

import java.util.ArrayList;
import java.util.List;

import cn.com.prescription.framework.CodeValueRecord;
import cn.com.prescription.framework.action.form.AbstractForm;

/**
 * 门诊一览 form。
 * 
 * @author fsb
 */
/*
 * 新規作成 DATE: 2012.11.09 NAME: fsb
 */
public class RPC00102Form extends AbstractForm {

	/**
	 * 串行版本UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 患者姓名
	 */
	private String patientName;

	/**
	 * 门诊类型ID
	 */
	private String outpatientType;
	/**
	 * 门诊类型
	 */
	private List<CodeValueRecord> outpatientDataSource;

	/**
	 * subForm1
	 */
	private List<RPC0010201Dto> subForm1 = new ArrayList<RPC0010201Dto>();

	/**
	 * 页面类型
	 */
	private String pageType = null;

	/**
	 * 患者姓名的取得
	 * 
	 * @return 患者姓名
	 */
	public String getPatientName() {
		return patientName;
	}

	/**
	 * 患者姓名的设定
	 * 
	 * @param __patientName
	 *            患者姓名
	 */
	public void setPatientName(String _patientName) {
		this.patientName = _patientName;
	}

	/**
	 * 门诊类型ID的取得
	 * 
	 * @return 门诊类型ID
	 */
	public String getOutpatientType() {
		return outpatientType;
	}

	/**
	 * 门诊类型ID的设定
	 * 
	 * @param __outpatientType
	 *            门诊类型ID
	 */
	public void setOutpatientType(String _outpatientType) {
		this.outpatientType = _outpatientType;
	}

	/**
	 * 门诊类型的取得
	 * 
	 * @return 门诊类型
	 */
	public List<CodeValueRecord> getOutpatientDataSource() {
		return outpatientDataSource;
	}

	/**
	 * 门诊类型的设定
	 * 
	 * @param __outpatientDataSource
	 *            门诊类型
	 */
	public void setOutpatientDataSource(List<CodeValueRecord> _outpatientDataSource) {
		this.outpatientDataSource = _outpatientDataSource;
	}

	/**
	 * subForm1的取得
	 * 
	 * @return subForm1
	 */
	public List<RPC0010201Dto> getSubForm1() {
		return subForm1;
	}

	/**
	 * subForm1的设定
	 * 
	 * @param __subForm1
	 *            subForm1
	 */
	public void setSubForm1(List<RPC0010201Dto> _subForm1) {
		this.subForm1 = _subForm1;
	}

	/**
	 * 页面类型的取得
	 * 
	 * @return 页面类型
	 */
	public String getPageType() {
		return pageType;
	}

	/**
	 * 页面类型的设定
	 * 
	 * @param __pageType
	 *            页面类型
	 */
	public void setPageType(String _pageType) {
		this.pageType = _pageType;
	}

}

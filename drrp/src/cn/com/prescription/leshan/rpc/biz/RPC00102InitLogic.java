/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpc.biz;

import java.util.ArrayList;
import java.util.List;

import cn.com.prescription.framework.CodeValueRecord;
import cn.com.prescription.framework.biz.StandardBiz;
import cn.com.prescription.framework.biz.StandardLogic;
import cn.com.prescription.framework.event.StandardEvent;
import cn.com.prescription.framework.event.StandardEventResult;
import cn.com.prescription.framework.exception.ApplicationException;
import cn.com.prescription.framework.exception.SystemException;
import cn.com.prescription.leshan.rpc.action.form.RPC00102Form;

/**
 * 门诊一览初期化处理。 新規作成 DATE: 2016.03.24 NAME: tyc
 */
public class RPC00102InitLogic extends StandardBiz implements StandardLogic {

	/**
	 * 门诊一览的构造。
	 */
	public RPC00102InitLogic() {
		super();
	}

	/**
	 * event処理を行う。
	 * 
	 * @return event処理結果
	 * @param _event
	 *            event
	 * @throws ApplicationException
	 *             event処理里应用程序例外发生的情况
	 * @throws SystemException
	 *             event処理里系统例外发生的情况
	 */
	public StandardEventResult service(StandardEvent _event) throws ApplicationException, SystemException {

		// event処理結果
		RPC00102Form inForm_ = (RPC00102Form) _event.getForm();

		// 门诊类型
		CodeValueRecord record_ = null;
		List<CodeValueRecord> outpatient_ = new ArrayList<CodeValueRecord>();
		record_ = new CodeValueRecord();
		record_.setRecordCode("2");
		record_.setRecordValue("全部");
		outpatient_.add(record_);
		record_ = new CodeValueRecord();
		record_.setRecordCode("1");
		record_.setRecordValue("初诊");
		outpatient_.add(record_);
		record_ = new CodeValueRecord();
		record_.setRecordCode("0");
		record_.setRecordValue("复诊");
		outpatient_.add(record_);
		inForm_.setOutpatientDataSource(outpatient_);
		inForm_.setOutpatientType("2");

		// 出力情報設定
		return this.getEventResult(inForm_);
	}

}

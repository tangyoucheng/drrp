/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpc.biz;

import java.util.List;

import cn.com.prescription.framework.biz.StandardBiz;
import cn.com.prescription.framework.biz.StandardLogic;
import cn.com.prescription.framework.event.StandardEvent;
import cn.com.prescription.framework.event.StandardEventResult;
import cn.com.prescription.framework.exception.ApplicationException;
import cn.com.prescription.framework.exception.SystemException;
import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.framework.util.DateUtils;
import cn.com.prescription.framework.util.StringUtils;
import cn.com.prescription.leshan.common.LeshanConstantsIF;
import cn.com.prescription.leshan.rpc.action.form.RPC0010201Dto;
import cn.com.prescription.leshan.rpc.action.form.RPC00102Form;
import cn.com.prescription.leshan.rpc.data.RPC00102Dao;
import cn.com.prescription.leshan.rpc.data.condition.RPC00102Condition;
import cn.com.prescription.leshan.rpc.data.model.RPC00102Model;

/**
 * 门诊一览检索处理。 新規作成 DATE: 2016.03.24 NAME: tyc
 */
public class RPC00102SearchLogic extends StandardBiz implements StandardLogic {

	/**
	 * 门诊DAO
	 */
	RPC00102Dao rpc00102Dao = null;

	/**
	 * 用户情報設定画面（一般用户用） 初期化業務クラス的构造。
	 */
	public RPC00102SearchLogic() {
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

		// 门诊信息取得
		RPC00102Condition rpc00102Condition = new RPC00102Condition();
		// 用户名
		rpc00102Condition.setUserName(StringUtils.getLikeParameter(inForm_.getPatientName()));
		// 门诊类型
		if (!"2".equals(inForm_.getOutpatientType())) {
			rpc00102Condition.setFirstVisitFlag(inForm_.getOutpatientType());
		}
		// 消除标识
		rpc00102Condition.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);

		long outpatientCount_ = rpc00102Dao.selectOutpatientListCount(rpc00102Condition);
		// 前回検索結果クリア
		inForm_.getSubForm1().clear();
		// 件数チェック処理
		this.checkCount(inForm_, outpatientCount_);
		// ソートキー ASC
		// roleCondition.addPageSortField("RPM_ROLE.SORT_KEY",
		// LeshanConstantsIF.SORT_SEQ_ASC);
		rpc00102Condition.setPageStartRowNo(inForm_.getPageStartRowNo() + 1);
		List<RPC00102Model> resultList_ = rpc00102Dao.selectOutpatientList(rpc00102Condition);
		for (RPC00102Model model : resultList_) {
			RPC0010201Dto rpc0020201Dto = new RPC0010201Dto();
			rpc0020201Dto.setRecordId(model.getRecordId());
			rpc0020201Dto.setUserName(model.getUserName());
			rpc0020201Dto.setCeelNumber(model.getCeelNumber());
			rpc0020201Dto.setMedicalDate(DateUtils.format(model.getMedicalDate(),
					LeshanConstantsIF.DATE_FORMAT_YYYYMMDD, LeshanConstantsIF.DATE_FORMAT_YYYY_YEAR_MM_MONTH_DD_DATE));
			rpc0020201Dto.setOutpatientTypeName(model.getOutpatientTypeName());
			rpc0020201Dto.setFirstVisitFlag(model.getFirstVisitFlag());
			inForm_.getSubForm1().add(rpc0020201Dto);
		}

		// 出力情報設定
		return this.getEventResult(inForm_);
	}

	/**
	 * 门诊DAO的设定
	 * 
	 * @param _rpc00102Dao
	 *            门诊DAO
	 */
	public void setRpc00102Dao(RPC00102Dao _rpc00102Dao) {
		this.rpc00102Dao = _rpc00102Dao;
	}

}

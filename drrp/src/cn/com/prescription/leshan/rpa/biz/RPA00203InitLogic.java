/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpa.biz;

import java.util.ArrayList;
import java.util.List;

import cn.com.prescription.framework.CodeValueRecord;
import cn.com.prescription.framework.biz.ActionInfo;
import cn.com.prescription.framework.biz.StandardBiz;
import cn.com.prescription.framework.biz.StandardLogic;
import cn.com.prescription.framework.event.StandardEvent;
import cn.com.prescription.framework.event.StandardEventResult;
import cn.com.prescription.framework.exception.ApplicationException;
import cn.com.prescription.framework.exception.SystemException;
import cn.com.prescription.framework.message.MessageUtils;
import cn.com.prescription.framework.util.DateUtils;
import cn.com.prescription.framework.util.TimestampUtils;
import cn.com.prescription.leshan.common.LeshanConstantsIF;
import cn.com.prescription.leshan.common.data.RpmPatientDao;
import cn.com.prescription.leshan.common.data.condition.RpmPatientCondition;
import cn.com.prescription.leshan.common.data.model.RpmPatientModel;
import cn.com.prescription.leshan.rpa.action.RPA00202Action;
import cn.com.prescription.leshan.rpa.action.form.RPA00203Form;

/**
 * 患者信息添加初期化处理。 新規作成 DATE: 2016.03.24 NAME: tyc
 */
public class RPA00203InitLogic extends StandardBiz implements StandardLogic {
	/**
	 * 用户基本テーブル DAO
	 */
	private RpmPatientDao rpmPatientDao = null;

	/**
	 * 患者信息設定画面 初期化業務クラス的构造。
	 */
	public RPA00203InitLogic() {
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
		RPA00203Form inForm_ = (RPA00203Form) _event.getForm();

		// 性別リストを取得する
		CodeValueRecord record_ = null;
		List<CodeValueRecord> sexList_ = new ArrayList<CodeValueRecord>();
		record_ = new CodeValueRecord();
		record_.setRecordCode("1");
		record_.setRecordValue("男");
		sexList_.add(record_);
		record_ = new CodeValueRecord();
		record_.setRecordCode("2");
		record_.setRecordValue("女");
		sexList_.add(record_);
		inForm_.setSexDataSource(sexList_);
		inForm_.setSexId("1");

		RpmPatientCondition patientCondition_ = new RpmPatientCondition();
		// 用户ID
		patientCondition_.setUserId(inForm_.getUserId());
		// 消除标识＝ [定数：消除标识．有効レコード]
		patientCondition_.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
		// [排他情報：用户プロファイル情報]
		RpmPatientModel patientModel_ = rpmPatientDao.select(patientCondition_);
		if (patientModel_ != null) {

			// 用户ID
			inForm_.setUserId(patientModel_.getUserId());
			// 用户名
			inForm_.setUserName(patientModel_.getUserName());
			// 生日
			inForm_.setBirthday(DateUtils.format(patientModel_.getBirthday(), LeshanConstantsIF.DATE_FORMAT_YYYYMMDD,
					LeshanConstantsIF.DATE_FORMAT_YYYY_YEAR_MM_MONTH_DD_DATE));
			// 性别ID
			inForm_.setSexId(patientModel_.getSexId());
			// 邮政番号
			inForm_.setPostNumber(patientModel_.getPostNumber());
			// 住所
			inForm_.setAddr(patientModel_.getAddr());
			// 座机
			inForm_.setPhoneNumber(patientModel_.getPhoneNumber());
			// 手机
			inForm_.setCeelNumber(patientModel_.getCeelNumber());
			// 身份证号码
			inForm_.setIdNumber(patientModel_.getIdNumber());
			// 电子邮箱
			inForm_.setEmail(patientModel_.getEmail());

			// 民族
			inForm_.setNation(patientModel_.getNation());
			// 出生地
			inForm_.setPlaceOfBirth(patientModel_.getPlaceOfBirth());
			// 婚况
			inForm_.setMaritalStatus(patientModel_.getMaritalStatus());
			// 农历生日
			inForm_.setLunarBirthday(DateUtils.format(patientModel_.getLunarBirthday(),
					LeshanConstantsIF.DATE_FORMAT_YYYYMMDD, LeshanConstantsIF.DATE_FORMAT_YYYY_YEAR_MM_MONTH_DD_DATE));
			// 出生时间
			inForm_.setTimeOfBirth(DateUtils.format(patientModel_.getTimeOfBirth(), LeshanConstantsIF.DATE_FORMAT_HHMM,
					LeshanConstantsIF.DATE_FORMAT_HH_HOUR_MM_MINUTE));
			// 属相
			inForm_.setZodiac(patientModel_.getZodiac());
			// 单位
			inForm_.setCompany(patientModel_.getCompany());
			// 职业
			inForm_.setProfession(patientModel_.getProfession());

			// 最終更新日
			inForm_.setLastUpdateDate(TimestampUtils.formatUpd(patientModel_.getLastUpdateDate()));
		} else {
			// this.connectCompleteDialog(MessageUtils.getMessage("E00009"),
			// new ActionInfo(RPA00202Action.class, "doInit"));
			this.addErrorMessage(MessageUtils.getMessage("E00004", "削除", "表示"));
			this.errorEnd(new ActionInfo(RPA00202Action.class, "doBack"));
		}

		// 出力情報設定
		return this.getEventResult(inForm_);
	}

	/**
	 * 用户基本テーブル DAO的设定
	 * 
	 * @param _rpmPatientDao
	 *            用户基本テーブル DAO
	 */
	public void setRpmPatientDao(RpmPatientDao _rpmPatientDao) {
		this.rpmPatientDao = _rpmPatientDao;
	}

}

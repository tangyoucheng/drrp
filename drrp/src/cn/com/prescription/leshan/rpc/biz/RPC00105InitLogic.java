/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpc.biz;


import java.util.ArrayList;
import java.util.List;

import cn.com.prescription.framework.CodeValueRecord;
import cn.com.prescription.framework.biz.ActionInfo;
import cn.com.prescription.framework.biz.StandardBiz;
import cn.com.prescription.framework.biz.StandardLogic;
import cn.com.prescription.framework.common.session.UserSessionUtils;
import cn.com.prescription.framework.event.StandardEvent;
import cn.com.prescription.framework.event.StandardEventResult;
import cn.com.prescription.framework.exception.ApplicationException;
import cn.com.prescription.framework.exception.SystemException;
import cn.com.prescription.framework.message.MessageUtils;
import cn.com.prescription.framework.util.DateUtils;
import cn.com.prescription.framework.util.TimestampUtils;
import cn.com.prescription.leshan.common.LeshanConstantsIF;
import cn.com.prescription.leshan.rpc.action.RPC00102Action;
import cn.com.prescription.leshan.rpc.action.form.RPC00105Form;
import cn.com.prescription.leshan.rpc.data.RPC00105Dao;
import cn.com.prescription.leshan.rpc.data.condition.RPC00105Condition;
import cn.com.prescription.leshan.rpc.data.model.RPC00105Model;

/**
 * 复诊信息添加初期化处理。
 * 新規作成
 * DATE: 2016.03.24 NAME: tyc
 */
public class RPC00105InitLogic extends StandardBiz implements StandardLogic {
	/**
	 * 复诊更新 DAO
	 */
	private RPC00105Dao rpc00105Dao = null;

	/**
	 * 门诊信息設定画面 初期化業務クラス的构造。
	 */
	public RPC00105InitLogic() {
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
		RPC00105Form inForm_ = (RPC00105Form) _event.getForm();

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

		RPC00105Condition condition_ = new RPC00105Condition();
		// 用户ID
		condition_.setRecordId(inForm_.getRecordId());
		// 消除标识＝ [定数：消除标识．有効レコード]
		condition_.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
		// [排他情報：用户プロファイル情報]
		RPC00105Model model_ = rpc00105Dao.selectOutpatientInfo(condition_);
		if (model_ != null) {

			// 用户ID
			inForm_.setCaseNumber(model_.getUserId());
			// 就诊日期
			inForm_.setMedicalDate(DateUtils.format(model_.getMedicalDate(), LeshanConstantsIF.DATE_FORMAT_YYYYMMDD,
					LeshanConstantsIF.DATE_FORMAT_YYYY_YEAR_MM_MONTH_DD_DATE));
			// 用户名
			inForm_.setPatientName(model_.getUserName());
			// 生日
			inForm_.setBirthday(DateUtils.format(model_.getBirthday(), LeshanConstantsIF.DATE_FORMAT_YYYYMMDD,
					LeshanConstantsIF.DATE_FORMAT_YYYY_YEAR_MM_MONTH_DD_DATE));
			// 性别ID
			inForm_.setSexId(model_.getSexId());
			// 性别
			inForm_.setSex(model_.getSex());
			// 住所
			inForm_.setAddr(model_.getAddr());
			// 手机
			inForm_.setCeelNumber(model_.getCeelNumber());

			// 民族
			inForm_.setNation(model_.getNation());
			// 出生地
			inForm_.setPlaceOfBirth(model_.getPlaceOfBirth());
			// 婚况
			inForm_.setMaritalStatus(model_.getMaritalStatus());
			// 农历生日
			inForm_.setLunarBirthday(DateUtils.format(model_.getLunarBirthday(),
					LeshanConstantsIF.DATE_FORMAT_YYYYMMDD, LeshanConstantsIF.DATE_FORMAT_YYYY_YEAR_MM_MONTH_DD_DATE));
			// 出生时间
			inForm_.setTimeOfBirth(DateUtils.format(model_.getTimeOfBirth(), LeshanConstantsIF.DATE_FORMAT_HHMM,
					LeshanConstantsIF.DATE_FORMAT_HH_HOUR_MM_MINUTE));
			// 属相
			inForm_.setZodiac(model_.getZodiac());
			// 单位
			inForm_.setCompany(model_.getCompany());
			// 职业
			inForm_.setProfession(model_.getProfession());
			
			// 主诉
			inForm_.setChiefComplaint(model_.getChiefComplaint());
			// 发病节气
			inForm_.setOnsetSolarTerms(model_.getOnsetSolarTerms());
			// 现病史
			inForm_.setPresentHistory(model_.getPresentHistory());
			// 既往史
			inForm_.setPreviousHistory(model_.getPreviousHistory());
			// 家族史
			inForm_.setFamilyHistory(model_.getFamilyHistory());
			// 过敏史
			inForm_.setAllergyHistory(model_.getAllergyHistory());
			// 其他情况
			inForm_.setOtherCases(model_.getOtherCases());
			// 四诊摘要
			inForm_.setFourDiagnosis(model_.getFourDiagnosis());
			// 舌诊
			inForm_.setTongueInspection(model_.getTongueInspection());
			// 脉诊
			inForm_.setPulseTaking(model_.getPulseTaking());
			// 体温
			inForm_.setTemperature(model_.getTemperature());
			// 脉搏
			inForm_.setPulse(model_.getPulse());
			// 呼吸
			inForm_.setBreath(model_.getBreath());
			// 血压
			inForm_.setBloodPressure(model_.getBloodPressure());
			// 身高
			inForm_.setHeight(model_.getHeight());
			// 体重
			inForm_.setWeight(model_.getWeight());
			// 辅助检查
			inForm_.setAssistantExamination(model_.getAssistantExamination());
			// 诊断
			inForm_.setDiagnosis(model_.getDiagnosis());
			// 处置
			inForm_.setDisposition(model_.getDisposition());
			// 医师
			inForm_.setPhysician(model_.getPhysician());
			// 备注
			inForm_.setNotes(model_.getNotes());
			// 病例记录人
			inForm_.setCaseRecorder(model_.getCaseRecorder());
			// 主诊助理
			inForm_.setAttendingAssistant(model_.getAttendingAssistant());
			// 复诊标识
			inForm_.setFirstVisitFlag(LeshanConstantsIF.KBN_KYOTU_YES);
			// 回访记录
			inForm_.setReturnVisit(model_.getReturnVisit());
			// 疗效
			inForm_.setEffect(model_.getEffect());

			// 最終更新日
			inForm_.setLastUpdateDate(TimestampUtils.formatUpd(model_.getLastUpdateDate()));
			// 所有权
			if (UserSessionUtils.getUserId().equals(model_.getCreateUserId())) {
				inForm_.setOwnershipFlag("T");
			}
		} else {
			// this.connectCompleteDialog(MessageUtils.getMessage("E00009"),
			// new ActionInfo(RPC00102Action.class, "doInit"));
			this.addErrorMessage(MessageUtils.getMessage("E00004", "削除", "表示"));
			this.errorEnd(new ActionInfo(RPC00102Action.class, "doBack"));
		}

		// 出力情報設定
		return this.getEventResult(inForm_);
	}

	/**
	 * 复诊更新 DAO的设定
	 * @param _rpc00105Dao 复诊更新 DAO
	 */
	public void setRpc00105Dao(RPC00105Dao _rpc00105Dao) {
	    this.rpc00105Dao = _rpc00105Dao;
	}

}

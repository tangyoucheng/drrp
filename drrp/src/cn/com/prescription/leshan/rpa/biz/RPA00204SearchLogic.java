/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpa.biz;

import java.util.List;

import cn.com.prescription.framework.biz.StandardBiz;
import cn.com.prescription.framework.biz.StandardLogic;
import cn.com.prescription.framework.event.StandardEvent;
import cn.com.prescription.framework.event.StandardEventResult;
import cn.com.prescription.framework.exception.ApplicationException;
import cn.com.prescription.framework.exception.SystemException;
import cn.com.prescription.framework.util.DateUtils;
import cn.com.prescription.framework.util.StringUtils;
import cn.com.prescription.framework.util.TimestampUtils;
import cn.com.prescription.leshan.common.LeshanConstantsIF;
import cn.com.prescription.leshan.rpa.action.form.RPA0020401Dto;
import cn.com.prescription.leshan.rpa.action.form.RPA00204Form;
import cn.com.prescription.leshan.rpa.data.RPA00204Dao;
import cn.com.prescription.leshan.rpa.data.condition.RPA00204Condition;
import cn.com.prescription.leshan.rpa.data.model.RPA00204Model;

/**
 * 患者一览检索处理。 新規作成 DATE: 2016.03.24 NAME: tyc
 */
public class RPA00204SearchLogic extends StandardBiz implements StandardLogic {

	/**
	 * 患者DAO
	 */
	RPA00204Dao rpa00204Dao = null;

	/**
	 * 用户情報設定画面（一般用户用） 初期化業務クラス的构造。
	 */
	public RPA00204SearchLogic() {
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
		RPA00204Form inForm_ = (RPA00204Form) _event.getForm();

		// (2)役割リストを取得する
		RPA00204Condition patientCondition = new RPA00204Condition();
		// 用户名
		patientCondition.setUserName(StringUtils.getLikeParameter(inForm_.getUserName()));
		// 手机
		patientCondition.setCeelNumber(StringUtils.getLikeParameter(inForm_.getCeelNumber()));
		// 用户ID
		patientCondition.setUserId(inForm_.getUserId());
		// 消除标识
		patientCondition.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);

		long patientcount_ = rpa00204Dao.selectPatientListCount(patientCondition);
		// 前回検索結果クリア
		inForm_.getSubForm1().clear();
		// 件数チェック処理
		this.checkCount(inForm_, patientcount_);
		// ソートキー ASC
		// roleCondition.addPageSortField("RPM_ROLE.SORT_KEY",
		// LeshanConstantsIF.SORT_SEQ_ASC);
		patientCondition.setPageStartRowNo(inForm_.getPageStartRowNo() + 1);
		List<RPA00204Model> roleModelList_ = rpa00204Dao.selectPatientList(patientCondition);
		for (RPA00204Model rpmPatientModel : roleModelList_) {
			RPA0020401Dto rpa0020401Dto = new RPA0020401Dto();
			rpa0020401Dto.setUserId(rpmPatientModel.getUserId());
			rpa0020401Dto.setUserName(rpmPatientModel.getUserName());
			rpa0020401Dto.setSexId(rpmPatientModel.getSexId());
			rpa0020401Dto.setBirthday(DateUtils.format(rpmPatientModel.getBirthday(),
					LeshanConstantsIF.DATE_FORMAT_YYYYMMDD, LeshanConstantsIF.DATE_FORMAT_YYYY_YEAR_MM_MONTH_DD_DATE));
			rpa0020401Dto.setPhoneNumber(rpmPatientModel.getPhoneNumber());
			rpa0020401Dto.setCeelNumber(rpmPatientModel.getCeelNumber());
			rpa0020401Dto.setIdNumber(rpmPatientModel.getIdNumber());
			rpa0020401Dto.setAddr(rpmPatientModel.getAddr());
			if (rpmPatientModel.getCreateDatePrescription() != null) {
				rpa0020401Dto
						.setPrescriptionCreateDate(TimestampUtils.format(rpmPatientModel.getCreateDatePrescription(),
								LeshanConstantsIF.DATE_FORMAT_YYYY_YEAR_MM_MONTH_DD_DATE + " HH点mm分"));
			}
			rpa0020401Dto.setPrescriptionType(rpmPatientModel.getPrescriptionType());
			rpa0020401Dto.setContents(rpmPatientModel.getContents());
			rpa0020401Dto.setFileContents(rpmPatientModel.getFileContents());
			rpa0020401Dto.setPrice(rpmPatientModel.getPrice());

			// 民族
			rpa0020401Dto.setNation(rpmPatientModel.getNation());
			// 出生地
			rpa0020401Dto.setPlaceOfBirth(rpmPatientModel.getPlaceOfBirth());
			// 婚况
			rpa0020401Dto.setMaritalStatus(rpmPatientModel.getMaritalStatus());
			// 农历
			rpa0020401Dto.setLunarBirthday(DateUtils.format(rpmPatientModel.getLunarBirthday(),
					LeshanConstantsIF.DATE_FORMAT_YYYYMMDD, LeshanConstantsIF.DATE_FORMAT_YYYY_YEAR_MM_MONTH_DD_DATE));
			// 出生时间
			rpa0020401Dto.setTimeOfBirth(DateUtils.format(rpmPatientModel.getTimeOfBirth(),
					LeshanConstantsIF.DATE_FORMAT_HHMM, LeshanConstantsIF.DATE_FORMAT_HH_HOUR_MM_MINUTE));
			// 属相
			rpa0020401Dto.setZodiac(rpmPatientModel.getZodiac());
			// 单位
			rpa0020401Dto.setCompany(rpmPatientModel.getCompany());
			// 职业
			rpa0020401Dto.setProfession(rpmPatientModel.getProfession());

			inForm_.getSubForm1().add(rpa0020401Dto);
		}

		// 出力情報設定
		return this.getEventResult(inForm_);
	}

	/**
	 * 患者DAO的设定
	 * 
	 * @param _rpa00204Dao
	 *            患者DAO
	 */
	public void setRpa00204Dao(RPA00204Dao _rpa00204Dao) {
		this.rpa00204Dao = _rpa00204Dao;
	}

}

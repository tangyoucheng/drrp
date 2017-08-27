/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpc.biz;

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
import cn.com.prescription.framework.util.ReflectUtils;
import cn.com.prescription.framework.util.StringUtils;
import cn.com.prescription.framework.util.TimestampUtils;
import cn.com.prescription.leshan.common.LeshanConstantsIF;
import cn.com.prescription.leshan.common.data.RpmOutpatientDao;
import cn.com.prescription.leshan.common.data.RpmPatientDao;
import cn.com.prescription.leshan.common.data.condition.RpmPatientCondition;
import cn.com.prescription.leshan.common.data.model.RpmOutpatientModel;
import cn.com.prescription.leshan.common.data.model.RpmPatientModel;
import cn.com.prescription.leshan.rpc.action.RPC00101Action;
import cn.com.prescription.leshan.rpc.action.form.RPC00101Form;

/**
 * 门诊情報設定 登录処理 ロジック
 * 
 * @author fsb
 */
/*
 * 新規作成 DATE: 2012.11. NAME: fsb
 */
public class RPC00101EntryLogic extends StandardBiz implements StandardLogic {

	/**
	 * 患者基本テーブル DAO
	 */
	private RpmPatientDao rpmPatientDao = null;

	/**
	 * 门诊基本テーブル DAO
	 */
	private RpmOutpatientDao rpmOutpatientDao = null;

	/**
	 * 门诊情報設定ロジック的构造。
	 */
	public RPC00101EntryLogic() {
		super();
	}

	/**
	 * 業務処理を行う。
	 * 
	 * @param _event
	 *            event
	 * @return event処理結果
	 * @throws ApplicationException
	 *             event処理里应用程序例外发生的情况
	 * @throws SystemException
	 *             event処理里系统例外发生的情况
	 */
	public StandardEventResult service(StandardEvent _event) throws ApplicationException, SystemException {

		// event処理結果
		RPC00101Form inForm_ = (RPC00101Form) _event.getForm();

		// 患者基本テーブルの登录
		insertPatient(inForm_);

		// 门诊基本テーブルの登录
		insertOutpatient(inForm_);

		// 完了画面を表示する。
		this.connectCompleteDialog(MessageUtils.getMessage("I00001", "登录"),
				new ActionInfo(RPC00101Action.class, "doInit"));
		// 出力情報設定
		return this.getEventResult(inForm_);

	}

	/**
	 * 患者信息テーブルの登録を行う
	 * 
	 * @param _inForm
	 *            SCHM00301Form
	 * @throws ApplicationException
	 *             event処理里应用程序例外发生的情况
	 * @throws SystemException
	 *             event処理里系统例外发生的情况
	 */
	private void insertPatient(RPC00101Form _inForm) throws ApplicationException, SystemException {

		// 排他情報：患者プロ情報
		RpmPatientCondition patientCondition_ = new RpmPatientCondition();
		// 患者名
		patientCondition_.setUserName(_inForm.getPatientName());
		// 患者手机
		patientCondition_.setCeelNumber(_inForm.getCeelNumber());
		// 消除标识＝ [定数：消除标识．有効レコード]
		patientCondition_.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
		// [排他情報：患者プロファイル情報]
		RpmPatientModel patientModel_ = rpmPatientDao.select(patientCondition_);
		if (patientModel_ == null) {
			rpmPatientDao.updateLockTable();
			RpmPatientModel insertPatientModel_ = new RpmPatientModel();

			// 患者ID
			insertPatientModel_.setUserId(StringUtils.getUniqueId());
			// 患者名
			insertPatientModel_.setUserName(_inForm.getPatientName());
			// 生日
			insertPatientModel_.setBirthday(DateUtils.format(_inForm.getBirthday(),
					LeshanConstantsIF.DATE_FORMAT_YYYY_YEAR_MM_MONTH_DD_DATE, LeshanConstantsIF.DATE_FORMAT_YYYYMMDD));
			// 性别ID
			insertPatientModel_.setSexId(_inForm.getSexId());
			// 手机
			insertPatientModel_.setCeelNumber(_inForm.getCeelNumber());
			// 居住地
			insertPatientModel_.setAddr(_inForm.getAddr());
			
			// 民族
			insertPatientModel_.setNation(_inForm.getNation());
			// 出生地
			insertPatientModel_.setPlaceOfBirth(_inForm.getPlaceOfBirth());
			// 婚况
			insertPatientModel_.setMaritalStatus(_inForm.getMaritalStatus());
			// 农历生日
			insertPatientModel_.setLunarBirthday(DateUtils.format(_inForm.getLunarBirthday(),
					LeshanConstantsIF.DATE_FORMAT_YYYY_YEAR_MM_MONTH_DD_DATE, LeshanConstantsIF.DATE_FORMAT_YYYYMMDD));
			// 出生时间
			insertPatientModel_.setTimeOfBirth(DateUtils.format(_inForm.getTimeOfBirth(),
					LeshanConstantsIF.DATE_FORMAT_HH_HOUR_MM_MINUTE, LeshanConstantsIF.DATE_FORMAT_HHMM));
			// 属相
			insertPatientModel_.setZodiac(_inForm.getZodiac());
			// 单位
			insertPatientModel_.setCompany(_inForm.getCompany());
			// 职业
			insertPatientModel_.setProfession(_inForm.getProfession());

			// 消除标识
			insertPatientModel_.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
			// 创建者
			insertPatientModel_.setCreateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
			// 创建日
			insertPatientModel_.setCreateDate(TimestampUtils.getSysTimestamp());
			// 最終更新患者ID
			insertPatientModel_.setLastUpdateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
			// 最終更新患者名
			insertPatientModel_.setLastUpdateUserName(UserSessionUtils.getUserSessionInfo().getUserName());
			// 最終更新日
			insertPatientModel_.setLastUpdateDate(TimestampUtils.getSysTimestamp());

			// オブジェクトの空の文字列を削除する。
			ReflectUtils.clearAllSpace(insertPatientModel_);

			int PatientCount_ = rpmPatientDao.insert(insertPatientModel_);
			// 件数 ＝ 0 の場合
			if (PatientCount_ == 0) {
				this.addErrorMessage(MessageUtils.getMessage("E00047", "登录"));
				this.errorEnd();
			}
			_inForm.setCaseNumber(insertPatientModel_.getUserId());
		} else {
			_inForm.setCaseNumber(patientModel_.getUserId());
		}
	}

	/**
	 * 门诊テーブルの登录を行う
	 * 
	 * @param _inForm
	 *            RPC00101Form
	 * @throws ApplicationException
	 *             event処理里应用程序例外发生的情况
	 * @throws SystemException
	 *             event処理里系统例外发生的情况
	 */
	private void insertOutpatient(RPC00101Form _inForm) throws ApplicationException, SystemException {

		rpmOutpatientDao.updateLockTable();
		RpmOutpatientModel insertOutPatientModel_ = new RpmOutpatientModel();

		// 门诊ID
		insertOutPatientModel_.setRecordId(StringUtils.getUniqueId());
		// 患者ID
		insertOutPatientModel_.setPatientId(_inForm.getCaseNumber());
		// 就诊日期
		insertOutPatientModel_.setMedicalDate(DateUtils.format(_inForm.getMedicalDate(),
				LeshanConstantsIF.DATE_FORMAT_YYYY_YEAR_MM_MONTH_DD_DATE, LeshanConstantsIF.DATE_FORMAT_YYYYMMDD));
		// 主诉
		insertOutPatientModel_.setChiefComplaint(_inForm.getChiefComplaint());
		// 发病节气
		insertOutPatientModel_.setOnsetSolarTerms(_inForm.getOnsetSolarTerms());
		// 现病史
		insertOutPatientModel_.setPresentHistory(_inForm.getPresentHistory());
		// 既往史
		insertOutPatientModel_.setPreviousHistory(_inForm.getPreviousHistory());
		// 家族史
		insertOutPatientModel_.setFamilyHistory(_inForm.getFamilyHistory());
		// 过敏史
		insertOutPatientModel_.setAllergyHistory(_inForm.getAllergyHistory());
		// 其他情况
		insertOutPatientModel_.setOtherCases(_inForm.getOtherCases());
		// 四诊摘要
		insertOutPatientModel_.setFourDiagnosis(_inForm.getFourDiagnosis());
		// 舌诊
		insertOutPatientModel_.setTongueInspection(_inForm.getTongueInspection());
		// 脉诊
		insertOutPatientModel_.setPulseTaking(_inForm.getPulseTaking());
		// 体温
		insertOutPatientModel_.setTemperature(_inForm.getTemperature());
		// 脉搏
		insertOutPatientModel_.setPulse(_inForm.getPulse());
		// 呼吸
		insertOutPatientModel_.setBreath(_inForm.getBreath());
		// 血压
		insertOutPatientModel_.setBloodPressure(_inForm.getBloodPressure());
		// 身高
		insertOutPatientModel_.setHeight(_inForm.getHeight());
		// 体重
		insertOutPatientModel_.setWeight(_inForm.getWeight());
		// 辅助检查
		insertOutPatientModel_.setAssistantExamination(_inForm.getAssistantExamination());
		// 诊断
		insertOutPatientModel_.setDiagnosis(_inForm.getDiagnosis());
		// 处置
		insertOutPatientModel_.setDisposition(_inForm.getDisposition());
		// 医师
		insertOutPatientModel_.setPhysician(_inForm.getPhysician());
		// 备注
		insertOutPatientModel_.setNotes(_inForm.getNotes());
		// 病例记录人
		insertOutPatientModel_.setCaseRecorder(_inForm.getCaseRecorder());
		// 主诊助理
		insertOutPatientModel_.setAttendingAssistant(_inForm.getAttendingAssistant());
		// 初诊标识
		insertOutPatientModel_.setFirstVisitFlag(LeshanConstantsIF.KBN_KYOTU_YES);
		// 回访记录
		insertOutPatientModel_.setReturnVisit(_inForm.getReturnVisit());
		// 疗效
		insertOutPatientModel_.setEffect(_inForm.getEffect());

		// 消除标识
		insertOutPatientModel_.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
		// 创建者
		insertOutPatientModel_.setCreateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
		// 创建日
		insertOutPatientModel_.setCreateDate(TimestampUtils.getSysTimestamp());
		// 最終更新门诊ID
		insertOutPatientModel_.setLastUpdateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
		// 最終更新门诊名
		insertOutPatientModel_.setLastUpdateUserName(UserSessionUtils.getUserSessionInfo().getUserName());
		// 最終更新日
		insertOutPatientModel_.setLastUpdateDate(TimestampUtils.getSysTimestamp());

		// オブジェクトの空の文字列を削除する。
		ReflectUtils.clearAllSpace(insertOutPatientModel_);

		int PatientCount_ = rpmOutpatientDao.insert(insertOutPatientModel_);
		// 件数 ＝ 0 の場合
		if (PatientCount_ == 0) {
			this.addErrorMessage(MessageUtils.getMessage("E00047", "登录"));
			this.errorEnd();
		}
	}

	/**
	 * 患者基本テーブル DAO的设定
	 * 
	 * @param _rpmPatientDao
	 *            门诊基本テーブル DAO
	 */
	public void setRpmPatientDao(RpmPatientDao _rpmPatientDao) {
		this.rpmPatientDao = _rpmPatientDao;
	}

	/**
	 * 门诊基本テーブル DAO的设定
	 * 
	 * @param _rpmOutpatientDao
	 *            门诊基本テーブル DAO
	 */
	public void setRpmOutpatientDao(RpmOutpatientDao _rpmOutpatientDao) {
		this.rpmOutpatientDao = _rpmOutpatientDao;
	}

}
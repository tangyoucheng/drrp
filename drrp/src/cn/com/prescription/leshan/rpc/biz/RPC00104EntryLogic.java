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
import cn.com.prescription.leshan.common.data.model.RpmOutpatientModel;
import cn.com.prescription.leshan.rpc.action.RPC00104Action;
import cn.com.prescription.leshan.rpc.action.form.RPC00104Form;

/**
 * 复诊添加处理
 * 
 * @author fsb
 */
/*
 * 新規作成 DATE: 2012.11. NAME: fsb
 */
public class RPC00104EntryLogic extends StandardBiz implements StandardLogic {

	/**
	 * 门诊基本テーブル DAO
	 */
	private RpmOutpatientDao rpmOutpatientDao = null;

	/**
	 * 复诊添加构造函数
	 */
	public RPC00104EntryLogic() {
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
		RPC00104Form inForm_ = (RPC00104Form) _event.getForm();

		// 复诊添加登录
		insertOutpatient(inForm_);

		// 完了画面を表示する。
		this.connectCompleteDialog(MessageUtils.getMessage("I00001", "登录"),
				new ActionInfo(RPC00104Action.class, "doInit"));
		// 出力情報設定
		return this.getEventResult(inForm_);

	}

	/**
	 * 复诊添加登录
	 * 
	 * @param _inForm
	 *            RPC00104Form
	 * @throws ApplicationException
	 *             event処理里应用程序例外发生的情况
	 * @throws SystemException
	 *             event処理里系统例外发生的情况
	 */
	private void insertOutpatient(RPC00104Form _inForm) throws ApplicationException, SystemException {

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
		insertOutPatientModel_.setFirstVisitFlag(LeshanConstantsIF.KBN_KYOTU_NO);
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
	 * 门诊基本テーブル DAO的设定
	 * 
	 * @param _rpmOutpatientDao
	 *            门诊基本テーブル DAO
	 */
	public void setRpmOutpatientDao(RpmOutpatientDao _rpmOutpatientDao) {
		this.rpmOutpatientDao = _rpmOutpatientDao;
	}

}
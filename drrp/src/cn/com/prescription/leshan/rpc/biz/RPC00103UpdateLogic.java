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
import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.framework.util.DateUtils;
import cn.com.prescription.framework.util.ReflectUtils;
import cn.com.prescription.framework.util.TimestampUtils;
import cn.com.prescription.leshan.common.LeshanConstantsIF;
import cn.com.prescription.leshan.common.data.RpmOutpatientDao;
import cn.com.prescription.leshan.common.data.condition.RpmOutpatientCondition;
import cn.com.prescription.leshan.common.data.model.RpmOutpatientModel;
import cn.com.prescription.leshan.rpc.action.RPC00102Action;
import cn.com.prescription.leshan.rpc.action.form.RPC00103Form;

/**
 * 门诊情報設定 登录処理 ロジック
 * 
 * @author fsb
 */
/*
 * 新規作成 DATE: 2012.11. NAME: fsb
 */
public class RPC00103UpdateLogic extends StandardBiz implements StandardLogic {
	/**
	 * 门诊 DAO
	 */
	private RpmOutpatientDao rpmOutpatientDao = null;

	/**
	 * 门诊情報設定ロジック的构造。
	 */
	public RPC00103UpdateLogic() {
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
		RPC00103Form inForm_ = (RPC00103Form) _event.getForm();

		// 排他情報：门诊プロ情報
		RpmOutpatientModel outpatientModel_ = outpatientCheck(inForm_);

		// 门诊基本テーブルの登录
		updateOutpatient(inForm_, outpatientModel_);

		// 完了画面を表示する。
		this.connectCompleteDialog(MessageUtils.getMessage("I00001", "登录"),
				new ActionInfo(RPC00102Action.class, "doBack"));
		// 出力情報設定
		return this.getEventResult(inForm_);

	}

	/**
	 * 门诊プロファイルテーブルのデータを取得し、ロック（for update）を行う
	 * 
	 * @param _inForm
	 *            SCHM00301Form
	 * @return PatientModel_
	 * @throws ApplicationException
	 *             event処理里应用程序例外发生的情况
	 * @throws SystemException
	 *             event処理里系统例外发生的情况
	 */
	private RpmOutpatientModel outpatientCheck(RPC00103Form _inForm) throws ApplicationException, SystemException {

		// 排他情報：门诊プロ情報
		RpmOutpatientCondition outpatientCondition = new RpmOutpatientCondition();
		// 门诊ID
		outpatientCondition.setRecordId(_inForm.getRecordId());
		// 消除标识＝ [定数：消除标识．有効レコード]
		outpatientCondition.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
		// [排他情報：门诊プロファイル情報]
		RpmOutpatientModel outpatientModel_ = rpmOutpatientDao.selectForUpdate(outpatientCondition);
		// [排他情報：门诊プロ情報]の取得件数 ＝ 0 の場合
		if (outpatientModel_ == null) {
			// メッセージID【E00004】（パラメータ1："削除"、パラメータ２："登录"）
			this.addErrorMessage(MessageUtils.getMessage("E00004", "删除", "登录"));
			this.errorEnd();
		}
		// [排他情報：门诊プロ情報．最終更新日]≠[画面：最終更新日]の場合
		if (outpatientModel_ != null && !CheckUtils.isEqual(_inForm.getLastUpdateDate(),
				TimestampUtils.formatUpd(outpatientModel_.getLastUpdateDate()))) {
			// メッセージID【E00006】
			this.addErrorMessage(MessageUtils.getMessage("E00006", "更新", "登录", outpatientModel_.getLastUpdateUserName(),
					DateUtils.format(outpatientModel_.getLastUpdateDate(),
							LeshanConstantsIF.DATE_FORMAT_YYYY_MM_DD_HH_MM)));
			this.errorEnd();
		}

		return outpatientModel_;
	}

	/**
	 * 门诊プロファイルテーブルの登录を行う
	 * 
	 * @param _inForm
	 *            SCHM00301Form
	 * @param _patientModel
	 *            RpmPatientModel
	 * @throws ApplicationException
	 *             event処理里应用程序例外发生的情况
	 * @throws SystemException
	 *             event処理里系统例外发生的情况
	 */
	private void updateOutpatient(RPC00103Form _inForm, RpmOutpatientModel _outpatientModel)
			throws ApplicationException, SystemException {

		// 主诉
		_outpatientModel.setChiefComplaint(_inForm.getChiefComplaint());
		// 发病节气
		_outpatientModel.setOnsetSolarTerms(_inForm.getOnsetSolarTerms());
		// 现病史
		_outpatientModel.setPresentHistory(_inForm.getPresentHistory());
		// 既往史
		_outpatientModel.setPreviousHistory(_inForm.getPreviousHistory());
		// 家族史
		_outpatientModel.setFamilyHistory(_inForm.getFamilyHistory());
		// 过敏史
		_outpatientModel.setAllergyHistory(_inForm.getAllergyHistory());
		// 其他情况
		_outpatientModel.setOtherCases(_inForm.getOtherCases());
		// 四诊摘要
		_outpatientModel.setFourDiagnosis(_inForm.getFourDiagnosis());
		// 舌诊
		_outpatientModel.setTongueInspection(_inForm.getTongueInspection());
		// 脉诊
		_outpatientModel.setPulseTaking(_inForm.getPulseTaking());
		// 体温
		_outpatientModel.setTemperature(_inForm.getTemperature());
		// 脉搏
		_outpatientModel.setPulse(_inForm.getPulse());
		// 呼吸
		_outpatientModel.setBreath(_inForm.getBreath());
		// 血压
		_outpatientModel.setBloodPressure(_inForm.getBloodPressure());
		// 身高
		_outpatientModel.setHeight(_inForm.getHeight());
		// 体重
		_outpatientModel.setWeight(_inForm.getWeight());
		// 辅助检查
		_outpatientModel.setAssistantExamination(_inForm.getAssistantExamination());
		// 诊断
		_outpatientModel.setDiagnosis(_inForm.getDiagnosis());
		// 处置
		_outpatientModel.setDisposition(_inForm.getDisposition());
		// 医师
		_outpatientModel.setPhysician(_inForm.getPhysician());
		// 备注
		_outpatientModel.setNotes(_inForm.getNotes());
		// 病例记录人
		_outpatientModel.setCaseRecorder(_inForm.getCaseRecorder());
		// 主诊助理
		_outpatientModel.setAttendingAssistant(_inForm.getAttendingAssistant());

		// 消除标识
		_outpatientModel.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
		// 最終更新门诊ID
		_outpatientModel.setLastUpdateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
		// 最終更新门诊名
		_outpatientModel.setLastUpdateUserName(UserSessionUtils.getUserSessionInfo().getUserName());
		// 最終更新日
		_outpatientModel.setLastUpdateDate(TimestampUtils.getSysTimestamp());

		// オブジェクトの空の文字列を削除する。
		ReflectUtils.clearAllSpace(_outpatientModel);

		int outpatientCount_ = rpmOutpatientDao.update(_outpatientModel);
		// 件数 ＝ 0 の場合
		if (outpatientCount_ == 0) {
			this.addErrorMessage(MessageUtils.getMessage("E00047", "登录"));
			this.errorEnd();
		}
	}


	/**
	 * 门诊 DAO的设定
	 * 
	 * @param _rpmOutpatientDao
	 *            门诊 DAO
	 */
	public void setRpmOutpatientDao(RpmOutpatientDao _rpmOutpatientDao) {
		this.rpmOutpatientDao = _rpmOutpatientDao;
	}

}
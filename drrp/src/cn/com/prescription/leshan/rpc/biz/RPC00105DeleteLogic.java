/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpc.biz;

import cn.com.prescription.framework.biz.ActionInfo;
import cn.com.prescription.framework.biz.StandardBiz;
import cn.com.prescription.framework.biz.StandardLogic;
import cn.com.prescription.framework.event.StandardEvent;
import cn.com.prescription.framework.event.StandardEventResult;
import cn.com.prescription.framework.exception.ApplicationException;
import cn.com.prescription.framework.exception.SystemException;
import cn.com.prescription.framework.message.MessageUtils;
import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.framework.util.DateUtils;
import cn.com.prescription.framework.util.TimestampUtils;
import cn.com.prescription.leshan.common.LeshanConstantsIF;
import cn.com.prescription.leshan.common.data.RpmOutpatientDao;
import cn.com.prescription.leshan.common.data.condition.RpmOutpatientCondition;
import cn.com.prescription.leshan.common.data.model.RpmOutpatientModel;
import cn.com.prescription.leshan.rpc.action.RPC00102Action;
import cn.com.prescription.leshan.rpc.action.form.RPC00103Form;

/**
 * 用户情報設定 删除処理 ロジック
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11. NAME: fsb
 */
public class RPC00105DeleteLogic extends StandardBiz implements StandardLogic {
    
    /**
     * 门诊 DAO
     */
    private RpmOutpatientDao rpmOutpatientDao = null;

    /**
     * 用户情報設定ロジック的构造。
     */
    public RPC00105DeleteLogic() {
        super();
    }

    /**
     * 業務処理を行う。
     * 
     * @param _event event
     * @return event処理結果
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    public StandardEventResult service(StandardEvent _event) throws ApplicationException, SystemException {

        // event処理結果
        RPC00103Form inForm_ = (RPC00103Form) _event.getForm();

        // 排他情報：用户プロ情報
        RpmOutpatientModel outpatientModel_ = outpatientCheck(inForm_);

        // 用户基本テーブルの登録
        deletePatient(inForm_, outpatientModel_);

        // 完了画面を表示する。
        this.connectCompleteDialog(MessageUtils.getMessage("I00001", "登録"),
            new ActionInfo(RPC00102Action.class, "doBack"));
        // 出力情報設定
        return this.getEventResult(inForm_);

    }

    /**
     * 用户プロファイルテーブルのデータを取得し、ロック（for update）を行う
     * 
     * @param _inForm SCHM00301Form
     * @return PatientModel_
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    private RpmOutpatientModel outpatientCheck(RPC00103Form _inForm) throws ApplicationException, SystemException {

        // 排他情報：用户プロ情報
    	RpmOutpatientCondition outpatientCondition = new RpmOutpatientCondition();
        // 门诊ID
        outpatientCondition.setRecordId(_inForm.getRecordId());
        // 消除标识＝ [定数：消除标识．有効レコード]
        outpatientCondition.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        // [排他情報：用户プロファイル情報]
        RpmOutpatientModel outpatientModel_ = rpmOutpatientDao.selectForUpdate(outpatientCondition);
        // [排他情報：用户プロ情報]の取得件数 ＝ 0 の場合
        if (outpatientModel_ == null) {
            // メッセージID【E00004】（パラメータ1："削除"、パラメータ２："登録"）
            this.addErrorMessage(MessageUtils.getMessage("E00004", "删除", "删除"));
            this.errorEnd();
        }
        // [排他情報：用户プロ情報．最終更新日]≠[画面：最終更新日]の場合
        if (outpatientModel_ != null && !CheckUtils.isEqual(_inForm.getLastUpdateDate(),
            TimestampUtils.formatUpd(outpatientModel_.getLastUpdateDate()))) {
            // メッセージID【E00006】
            this.addErrorMessage(MessageUtils.getMessage("E00006", "更新", "删除", outpatientModel_.getLastUpdateUserName(),
                DateUtils.format(outpatientModel_.getLastUpdateDate(), LeshanConstantsIF.DATE_FORMAT_YYYY_MM_DD_HH_MM)));
            this.errorEnd();
        }
        return outpatientModel_;
    }

    /**
     * 门诊信息删除
     * 
     * @param _inForm RPC00103Form
     * @param _patientModel RpmPatientModel
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    private void deletePatient(RPC00103Form _inForm, RpmOutpatientModel _outpatientModel)
                    throws ApplicationException, SystemException {

        // 消除标识
        // _patientModel.setDeleteFlag(LeshanConstantsIF.DEL_FLG_DEL_RECORD);
        // // 最終更新用户ID
        // _patientModel.setLastUpdateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
        // // 最終更新用户名
        // _patientModel.setLastUpdateUserName(UserSessionUtils.getUserSessionInfo().getUserName());
        // // 最終更新日
        // _patientModel.setLastUpdateDate(TimestampUtils.getSysTimestamp());

        // オブジェクトの空の文字列を削除する。
        // ReflectUtils.clearAllSpace(_patientModel);

        int outpatientCount_ = rpmOutpatientDao.delete(_outpatientModel);
        // 件数 ＝ 0 の場合
        if (outpatientCount_ == 0) {
            this.addErrorMessage(MessageUtils.getMessage("E00047", "删除"));
            this.errorEnd();
        }
    }

	/**
	 * 门诊 DAO的设定
	 * @param _rpmOutpatientDao 门诊 DAO
	 */
	public void setRpmOutpatientDao(RpmOutpatientDao _rpmOutpatientDao) {
	    this.rpmOutpatientDao = _rpmOutpatientDao;
	}


}
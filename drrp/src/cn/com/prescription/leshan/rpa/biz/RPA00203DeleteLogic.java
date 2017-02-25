/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpa.biz;

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
import cn.com.prescription.leshan.common.data.RpmPatientDao;
import cn.com.prescription.leshan.common.data.condition.RpmPatientCondition;
import cn.com.prescription.leshan.common.data.model.RpmPatientModel;
import cn.com.prescription.leshan.rpa.action.RPA00202Action;
import cn.com.prescription.leshan.rpa.action.form.RPA00203Form;

/**
 * 用户情報設定 删除処理 ロジック
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11. NAME: fsb
 */
public class RPA00203DeleteLogic extends StandardBiz implements StandardLogic {

    /**
     * 用户基本テーブル DAO
     */
    private RpmPatientDao rpmPatientDao = null;

    /**
     * 用户情報設定ロジック的构造。
     */
    public RPA00203DeleteLogic() {
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
        RPA00203Form inForm_ = (RPA00203Form) _event.getForm();

        // 排他情報：用户プロ情報
        RpmPatientModel patientModel_ = patientCheck(inForm_);

        // 用户基本テーブルの登録
        deletePatient(inForm_, patientModel_);

        // 完了画面を表示する。
        this.connectCompleteDialog(MessageUtils.getMessage("I00001", "登録"),
            new ActionInfo(RPA00202Action.class, "doBack"));
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
    private RpmPatientModel patientCheck(RPA00203Form _inForm) throws ApplicationException, SystemException {

        // 排他情報：用户プロ情報
        RpmPatientCondition PatientCondition_ = new RpmPatientCondition();
        // 用户ID
        PatientCondition_.setUserId(_inForm.getUserId());
        // 消除标识＝ [定数：消除标识．有効レコード]
        PatientCondition_.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        // [排他情報：用户プロファイル情報]
        RpmPatientModel patientModel_ = rpmPatientDao.selectForUpdate(PatientCondition_);
        // [排他情報：用户プロ情報]の取得件数 ＝ 0 の場合
        if (patientModel_ == null) {
            // メッセージID【E00004】（パラメータ1："削除"、パラメータ２："登録"）
            this.addErrorMessage(MessageUtils.getMessage("E00004", "删除", "删除"));
            this.errorEnd();
        }
        // [排他情報：用户プロ情報．最終更新日]≠[画面：最終更新日]の場合
        if (patientModel_ != null && !CheckUtils.isEqual(_inForm.getLastUpdateDate(),
            TimestampUtils.formatUpd(patientModel_.getLastUpdateDate()))) {
            // メッセージID【E00006】
            this.addErrorMessage(MessageUtils.getMessage("E00006", "更新", "删除", patientModel_.getLastUpdateUserName(),
                DateUtils.format(patientModel_.getLastUpdateDate(), LeshanConstantsIF.DATE_FORMAT_YYYY_MM_DD_HH_MM)));
            this.errorEnd();
        }
        return patientModel_;
    }

    /**
     * 患者信息删除
     * 
     * @param _inForm RPA00203Form
     * @param _patientModel RpmPatientModel
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    private void deletePatient(RPA00203Form _inForm, RpmPatientModel _patientModel)
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

        int PatientCount_ = rpmPatientDao.delete(_patientModel);
        // 件数 ＝ 0 の場合
        if (PatientCount_ == 0) {
            this.addErrorMessage(MessageUtils.getMessage("E00047", "删除"));
            this.errorEnd();
        }
    }

    /**
     * 用户基本テーブル DAO的设定
     * 
     * @param _rpmPatientDao 用户基本テーブル DAO
     */
    public void setRpmPatientDao(RpmPatientDao _rpmPatientDao) {
        this.rpmPatientDao = _rpmPatientDao;
    }

}
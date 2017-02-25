/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpa.biz;

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
import cn.com.prescription.leshan.common.data.RpmPatientDao;
import cn.com.prescription.leshan.common.data.condition.RpmPatientCondition;
import cn.com.prescription.leshan.common.data.model.RpmPatientModel;
import cn.com.prescription.leshan.rpa.action.RPA00201Action;
import cn.com.prescription.leshan.rpa.action.form.RPA00201Form;

/**
 * 用户情報設定 登录処理 ロジック
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11. NAME: fsb
 */
public class RPA00201EntryLogic extends StandardBiz implements StandardLogic {

    /**
     * 用户基本テーブル DAO
     */
    private RpmPatientDao rpmPatientDao = null;

    /**
     * 日付
     */
    private static final String DATE_MAX = "99991231";

    /**
     * 用户情報設定ロジック的构造。
     */
    public RPA00201EntryLogic() {
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
        RPA00201Form inForm_ = (RPA00201Form) _event.getForm();

        // 排他情報：用户プロ情報
        RpmPatientModel patientModel_ = patientCheck(inForm_);

        // 用户基本テーブルの登录
        insertPatient(inForm_);

        // 完了画面を表示する。
        this.connectCompleteDialog(MessageUtils.getMessage("I00001", "登录"),
            new ActionInfo(RPA00201Action.class, "doInit"));
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
    private RpmPatientModel patientCheck(RPA00201Form _inForm) throws ApplicationException, SystemException {

        // 排他情報：用户プロ情報
        RpmPatientCondition patientCondition_ = new RpmPatientCondition();
        // 用户名
        patientCondition_.setUserName(_inForm.getUserName());
        // 用户手机
        patientCondition_.setCeelNumber(_inForm.getCeelNumber());
        // 消除标识＝ [定数：消除标识．有効レコード]
        patientCondition_.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        // [排他情報：用户プロファイル情報]
        RpmPatientModel patientModel_ = rpmPatientDao.selectForUpdate(patientCondition_);
        // 「削除」ボタンが表示された場合（更新の場合）、以下のチェックを行う
        // if (CheckUtils.isEqual(_inForm.getFormKbn(),
        // LeshanConstantsIF.NYUURYOKU_GAMEN_HYOJI_KUBUN_UPDATE_SAKUJO_KAKUNIN)) {
        // // [排他情報：用户プロ情報]の取得件数 ＝ 0 の場合
        // if (PatientModel_ == null) {
        // // メッセージID【E00004】（パラメータ1："削除"、パラメータ２："登录"）
        // this.addErrorMessage(MessageUtils.getMessage("E00004", "削除", "登录"));
        // this.errorEnd();
        // }
        // // [排他情報：用户プロ情報．最終更新日]≠[画面：最終更新日]の場合
        // if (PatientModel_ != null
        // && !CheckUtils.isEqual(_inForm.getHidPatientLastUpdateDate(),
        // PatientModel_.getLastUpdateDate())) {
        // // メッセージID【E00006】
        // this.addErrorMessage(MessageUtils.getMessage("E00006", "更新", "登录", PatientModel_
        // .getLastUpdateUserName(), DateUtils.format(PatientModel_.getLastUpdateDate(),
        // LeshanConstantsIF.DATE_FORMAT_YYYY_MM_DD_HH_MM)));
        // this.errorEnd();
        // }
        // }
        // // 「削除」ボタンが表示されなかった場合（新規の場合）、以下のチェックを行う
        // if (CheckUtils.isEqual(_inForm.getFormKbn(), LeshanConstantsIF.NYUURYOKU_GAMEN_HYOJI_KUBUN_NYUURYOKU)) {
        // // 排他情報：用户プロ情報
        // PatientCondition_ = new JktPatientCondition();
        // // 用户ID
        // PatientCondition_.setUserId(_inForm.getUserId());
        // // [排他情報：用户プロファイル情報]
        // PatientModel_ = PatientDao.selectForUpdate(PatientCondition_);
        //
        // [排他情報：用户プロ情報] の取得件数 ＞ 0
        if (patientModel_ != null) {
            this.addErrorMessage(MessageUtils.getMessage("E00024", "填写的用户名和手机号码", "重复登录"));
            this.errorEnd();
        }
        // }
        return patientModel_;
    }

    /**
     * 用户プロファイルテーブルの登录を行う
     * 
     * @param _inForm SCHM00301Form
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    private void insertPatient(RPA00201Form _inForm) throws ApplicationException, SystemException {

        rpmPatientDao.updateLockTable();
        RpmPatientModel insertPatientModel_ = new RpmPatientModel();

        // 用户ID
        insertPatientModel_.setUserId(StringUtils.getUniqueId());
        // 用户名
        insertPatientModel_.setUserName(_inForm.getUserName());
        // 生日
        insertPatientModel_.setBirthday(DateUtils.format(_inForm.getBirthday(),
            LeshanConstantsIF.DATE_FORMAT_YYYY_YEAR_MM_MONTH_DD_DATE, LeshanConstantsIF.DATE_FORMAT_YYYYMMDD));
        // 性别ID
        insertPatientModel_.setSexId(_inForm.getSexId());
        // 邮政番号
        insertPatientModel_.setPostNumber(_inForm.getPostNumber());
        // 住所
        insertPatientModel_.setAddr(_inForm.getAddr());
        // 座机
        insertPatientModel_.setPhoneNumber(_inForm.getPhoneNumber());
        // 手机
        insertPatientModel_.setCeelNumber(_inForm.getCeelNumber());
        // 身份证号码
        insertPatientModel_.setIdNumber(_inForm.getIdNumber());
        // 电子邮箱
        insertPatientModel_.setEmail(_inForm.getEmail());
        // 消除标识
        insertPatientModel_.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        // 创建者
        insertPatientModel_.setCreateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
        // 创建日
        insertPatientModel_.setCreateDate(TimestampUtils.getSysTimestamp());
        // 最終更新用户ID
        insertPatientModel_.setLastUpdateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
        // 最終更新用户名
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
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
import cn.com.prescription.framework.util.ReflectUtils;
import cn.com.prescription.framework.util.StringUtils;
import cn.com.prescription.framework.util.TimestampUtils;
import cn.com.prescription.leshan.common.LeshanConstantsIF;
import cn.com.prescription.leshan.common.data.RpmDrugDao;
import cn.com.prescription.leshan.common.data.condition.RpmDrugCondition;
import cn.com.prescription.leshan.common.data.model.RpmDrugModel;
import cn.com.prescription.leshan.rpa.action.RPA00301Action;
import cn.com.prescription.leshan.rpa.action.form.RPA00301Form;

/**
 * 药品信息添加 登录処理 ロジック
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11. NAME: fsb
 */
public class RPA00301EntryLogic extends StandardBiz implements StandardLogic {

    /**
     * 药品基本テーブル DAO
     */
    private RpmDrugDao rpmDrugDao = null;

    /**
     * 药品信息添加ロジック的构造。
     */
    public RPA00301EntryLogic() {
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
        RPA00301Form inForm_ = (RPA00301Form) _event.getForm();

        // 排他情報：用户プロ情報
        RpmDrugModel drugModel_ = drugCheck(inForm_);

        // 用户基本テーブルの登录
        insertDrug(inForm_);

        // 完了画面を表示する。
        this.connectCompleteDialog(MessageUtils.getMessage("I00001", "登录"),
            new ActionInfo(RPA00301Action.class, "doInit"));
        // 出力情報設定
        return this.getEventResult(inForm_);

    }

    /**
     * 药品テーブルのデータを取得し、ロック（for update）を行う
     * 
     * @param _inForm SCHM00301Form
     * @return PatientModel_
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    private RpmDrugModel drugCheck(RPA00301Form _inForm) throws ApplicationException, SystemException {

        // 排他情報：用户プロ情報
        RpmDrugCondition drugCondition_ = new RpmDrugCondition();
        // 药品名称
        drugCondition_.setDrugName(_inForm.getDrugName());
        // 厂商名称
        drugCondition_.setManufacturerName(_inForm.getManufacturerName());
        // 消除标识＝ [定数：消除标识．有効レコード]
        drugCondition_.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        // [排他情報：用户プロファイル情報]
        RpmDrugModel drugModel_ = rpmDrugDao.selectForUpdate(drugCondition_);
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
        if (drugModel_ != null) {
            this.addErrorMessage(MessageUtils.getMessage("E00024", "填写的药品名称和厂商名称", "重复登录"));
            this.errorEnd();
        }
        // }
        return drugModel_;
    }

    /**
     * 药品テーブルの登录を行う
     * 
     * @param _inForm SCHM00301Form
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    private void insertDrug(RPA00301Form _inForm) throws ApplicationException, SystemException {

        rpmDrugDao.updateLockTable();
        RpmDrugModel insertDrugModel_ = new RpmDrugModel();

        // 药品ID
        insertDrugModel_.setDrugId(StringUtils.getUniqueId());
        // 药品名
        insertDrugModel_.setDrugName(_inForm.getDrugName());
        // 厂商名
        insertDrugModel_.setManufacturerName(_inForm.getManufacturerName());
        // 价格
        insertDrugModel_.setPrice(_inForm.getPrice());
        // 单位
        insertDrugModel_.setUnit(_inForm.getUnit());
        // 备注
        insertDrugModel_.setNotes(_inForm.getNotes());
        // 消除标识
        insertDrugModel_.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        // 创建者
        insertDrugModel_.setCreateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
        // 创建日
        insertDrugModel_.setCreateDate(TimestampUtils.getSysTimestamp());
        // 最終更新用户ID
        insertDrugModel_.setLastUpdateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
        // 最終更新用户名
        insertDrugModel_.setLastUpdateUserName(UserSessionUtils.getUserSessionInfo().getUserName());
        // 最終更新日
        insertDrugModel_.setLastUpdateDate(TimestampUtils.getSysTimestamp());

        // オブジェクトの空の文字列を削除する。
        ReflectUtils.clearAllSpace(insertDrugModel_);

        int PatientCount_ = rpmDrugDao.insert(insertDrugModel_);
        // 件数 ＝ 0 の場合
        if (PatientCount_ == 0) {
            this.addErrorMessage(MessageUtils.getMessage("E00047", "登录"));
            this.errorEnd();
        }
    }

    /**
     * 药品基本テーブル DAO的设定
     * 
     * @param _rpmDrugDao 药品基本テーブル DAO
     */
    public void setRpmDrugDao(RpmDrugDao _rpmDrugDao) {
        this.rpmDrugDao = _rpmDrugDao;
    }

}
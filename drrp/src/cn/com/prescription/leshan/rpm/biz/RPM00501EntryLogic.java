/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpm.biz;

import cn.com.prescription.framework.biz.ActionInfo;
import cn.com.prescription.framework.biz.StandardBiz;
import cn.com.prescription.framework.biz.StandardLogic;
import cn.com.prescription.framework.common.session.UserSessionUtils;
import cn.com.prescription.framework.event.StandardEvent;
import cn.com.prescription.framework.event.StandardEventResult;
import cn.com.prescription.framework.exception.ApplicationException;
import cn.com.prescription.framework.exception.SystemException;
import cn.com.prescription.framework.message.MessageUtils;
import cn.com.prescription.framework.util.TimestampUtils;
import cn.com.prescription.leshan.common.LeshanConstantsIF;
import cn.com.prescription.leshan.common.data.RpmAdvertisement01Dao;
import cn.com.prescription.leshan.common.data.RpmAdvertisementDao;
import cn.com.prescription.leshan.common.data.condition.RpmAdvertisementCondition;
import cn.com.prescription.leshan.common.data.model.RpmAdvertisementModel;
import cn.com.prescription.leshan.rpm.action.RPM00501Action;
import cn.com.prescription.leshan.rpm.action.form.RPM00501Form;

/**
 * 用户情報設定 登録処理 ロジック
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11. NAME: fsb
 */
public class RPM00501EntryLogic extends StandardBiz implements StandardLogic {

    /**
     * 推广信息 DAO
     */
    private RpmAdvertisementDao rpmAdvertisementDao = null;
    /**
     * 推广信息 DAO
     */
    private RpmAdvertisement01Dao rpmAdvertisement01Dao = null;

    /**
     * 日付
     */
    private static final String DATE_MAX = "99991231";

    /**
     * 用户情報設定ロジック的构造。
     */
    public RPM00501EntryLogic() {
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
        RPM00501Form inForm_ = (RPM00501Form) _event.getForm();

        // 排他情報：用户基本情報
        RpmAdvertisementModel rpmAdvertisementModel_ = AdCheck(inForm_);

        // 用户基本テーブルの登録
        insertAdvertisement(inForm_);

        // 完了画面を表示する。
        this.connectCompleteDialog(MessageUtils.getMessage("I00001", "登録"),
            new ActionInfo(RPM00501Action.class, "doInit"));
        // 出力情報設定
        return this.getEventResult(inForm_);

    }

    /**
     * roleのデータを取得し、ロック（for update）を行う
     * 
     * @param inForm_ SCHM00301Form
     * @return userKihonModel_
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    private RpmAdvertisementModel AdCheck(RPM00501Form inForm_) throws ApplicationException, SystemException {
        RpmAdvertisementCondition rpmAdvertisementCondition_ = new RpmAdvertisementCondition();
        // 用户ID
        rpmAdvertisementCondition_.setAdId(inForm_.getAdId());
        // 消除标识＝ [定数：消除标识．有効レコード]
        rpmAdvertisementCondition_.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        // [排他情報：role情報]
        RpmAdvertisementModel rpmAdvertisementModel_ = rpmAdvertisementDao.selectForUpdate(rpmAdvertisementCondition_);

        // // 「削除」ボタンが表示された場合（更新の場合）、以下のチェックを行う
        // if (CheckUtils.isEqual(_inForm.getFormKbn(),
        // LeshanConstantsIF.NYUURYOKU_GAMEN_HYOJI_KUBUN_UPDATE_SAKUJO_KAKUNIN)) {
        // // [排他情報：用户基本情報]の取得件数 ＝ 0 の場合
        // if (userKihonModel_ == null) {
        // // メッセージID【E00004】（パラメータ1："削除"、パラメータ２："登録"）
        // this.addErrorMessage(MessageUtils.getMessage("E00004", "削除", "登録"));
        // this.errorEnd();
        // }
        // // [排他情報：用户基本情報．最終更新日]≠[画面：最終更新日]の場合
        // if (userKihonModel_ != null
        // && !CheckUtils.isEqual(_inForm.getHidUserKihonLastUpdateDate(),
        // userKihonModel_.getLastUpdateDate())) {
        // // メッセージID【E00006】
        // this.addErrorMessage(MessageUtils.getMessage("E00006", "更新", "登録",
        // userKihonModel_.getLastUpdateUserName(),
        // DateUtils.format(userKihonModel_.getLastUpdateDate(), LeshanConstantsIF.DATE_FORMAT_YYYY_MM_DD_HH_MM)));
        // this.errorEnd();
        // }
        // }
        // // 「削除」ボタンが表示されなかった場合（新規の場合）、以下のチェックを行う
        // if (CheckUtils.isEqual(_inForm.getFormKbn(), LeshanConstantsIF.NYUURYOKU_GAMEN_HYOJI_KUBUN_NYUURYOKU)) {
        // userKihonCondition_ = new JktUserKihonCondition();
        // // 用户ID
        // userKihonCondition_.setUserId(_inForm.getUserId());
        // // [排他情報：用户基本情報]
        // userKihonModel_ = rpmUserDao.selectForUpdate(userKihonCondition_);
        //
        // [情報：用户基本情報（新規登録用）] の取得件数 ＞ 0 の場合
        if (rpmAdvertisementModel_ != null) {
            // メッセージID【E20054】
            this.addErrorMessage(MessageUtils.getMessage("E20054"));
            this.errorEnd();
        }
        // }
        return rpmAdvertisementModel_;
    }

    /**
     * 用户基本テーブルの登録を行う
     * 
     * @param _inForm SCHM00301Form
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    private void insertAdvertisement(RPM00501Form _inForm) throws ApplicationException, SystemException {

        rpmAdvertisementDao.updateLockTable();
        RpmAdvertisementModel insertAdModel_ = new RpmAdvertisementModel();

        // AdＩＤ
        insertAdModel_.setAdId(_inForm.getAdId());
        // Ad名
        insertAdModel_.setAdName(_inForm.getAdName());
        // url
        insertAdModel_.setAdUrl(_inForm.getAdUrl());
        // Ad状态 (1.推广中；2.未使用)
        String adStatus = "2";
        if (_inForm.isCheckedFlag()) {
            adStatus = "1";
        }
        insertAdModel_.setAdStatus(adStatus);
        // Ad备注
        insertAdModel_.setNotes(_inForm.getNotes());
        // 消除标识
        insertAdModel_.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        // 创建者
        insertAdModel_.setCreateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
        // 创建日
        insertAdModel_.setCreateDate(TimestampUtils.getSysTimestamp());
        // 最終更新用户ID
        insertAdModel_.setLastUpdateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
        // 最終更新用户名
        insertAdModel_.setLastUpdateUserName(UserSessionUtils.getUserSessionInfo().getUserName());
        // 最終更新日
        insertAdModel_.setLastUpdateDate(TimestampUtils.getSysTimestamp());

        int AdCount_ = rpmAdvertisementDao.insert(insertAdModel_);
        // 件数 ＝ 0 の場合
        if (AdCount_ == 0) {
            this.addErrorMessage(MessageUtils.getMessage("E00047", "登録"));
            this.errorEnd();
        }
        if (_inForm.isCheckedFlag()) {
            RpmAdvertisementCondition _condition = new RpmAdvertisementCondition();
            _condition.setAdId(insertAdModel_.getAdId());
            rpmAdvertisement01Dao.updateAdStatus(_condition);
        }
    }

    /**
     * 推广信息 DAO的设定
     * 
     * @param _rpmRoleDao 推广信息 DAO
     */
    public void setAdvertisementDao(RpmAdvertisementDao _rpmAdvertisementDao) {
        this.rpmAdvertisementDao = _rpmAdvertisementDao;
    }

    /**
     * 推广信息 DAO的设定
     * 
     * @param _rpmAdvertisement01Dao 推广信息 DAO
     */
    public void setRpmAdvertisement01Dao(RpmAdvertisement01Dao _rpmAdvertisement01Dao) {
        this.rpmAdvertisement01Dao = _rpmAdvertisement01Dao;
    }

}
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
import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.framework.util.DateUtils;
import cn.com.prescription.framework.util.StringUtils;
import cn.com.prescription.framework.util.TimestampUtils;
import cn.com.prescription.leshan.common.LeshanConstantsIF;
import cn.com.prescription.leshan.common.data.RpmAdvertisement01Dao;
import cn.com.prescription.leshan.common.data.RpmAdvertisementDao;
import cn.com.prescription.leshan.common.data.condition.RpmAdvertisementCondition;
import cn.com.prescription.leshan.common.data.model.RpmAdvertisementModel;
import cn.com.prescription.leshan.rpm.action.RPM00502Action;
import cn.com.prescription.leshan.rpm.action.form.RPM00503Form;

/**
 * 推广信息更新処理 ロジック
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11. NAME: fsb
 */
public class RPM00503UpdateLogic extends StandardBiz implements StandardLogic {

    /**
     * 推广信息 DAO
     */
    private RpmAdvertisementDao rpmAdvertisementDao = null;
    /**
     * 推广信息 DAO
     */
    private RpmAdvertisement01Dao rpmAdvertisement01Dao = null;

    /**
     * 用户情報設定ロジック的构造。
     */
    public RPM00503UpdateLogic() {
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
        RPM00503Form inForm_ = (RPM00503Form) _event.getForm();

        // 排他情報：用户基本情報
        RpmAdvertisementModel AdModel_ = roleCheck(inForm_);

        // 用户基本テーブルの登録
        updateRole(inForm_, AdModel_);

        // 完了画面を表示する。
        this.connectCompleteDialog(MessageUtils.getMessage("I00001", "登録"),
            new ActionInfo(RPM00502Action.class, "doBack"));
        // 出力情報設定
        return this.getEventResult(inForm_);

    }

    /**
     * roleのデータを取得し、ロック（for update）を行う
     * 
     * @param _inForm SCHM00301Form
     * @return userKihonModel_
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    private RpmAdvertisementModel roleCheck(RPM00503Form _inForm) throws ApplicationException, SystemException {

        RpmAdvertisementCondition rpmAdvertisementCondition_ = new RpmAdvertisementCondition();
        // 用户ID
        rpmAdvertisementCondition_.setAdId(_inForm.getAdId());
        // 消除标识＝ [定数：消除标识．有効レコード]
        rpmAdvertisementCondition_.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        // [排他情報：role情報]
        RpmAdvertisementModel adModel_ = rpmAdvertisementDao.selectForUpdate(rpmAdvertisementCondition_);

        // [排他情報：用户基本情報]の取得件数 ＝ 0 の場合
        if (adModel_ == null) {
            // メッセージID【E00004】（パラメータ1："削除"、パラメータ２："登録"）
            this.addErrorMessage(MessageUtils.getMessage("E00004", "削除", "登録"));
            this.errorEnd();
        }
        // [排他情報：用户基本情報．最終更新日]≠[画面：最終更新日]の場合
        if (adModel_ != null && !CheckUtils.isEqual(_inForm.getLastUpdateDate(),
            TimestampUtils.formatUpd(adModel_.getLastUpdateDate()))) {
            // メッセージID【E00006】
            this.addErrorMessage(MessageUtils.getMessage("E00006", "更新", "登録", adModel_.getLastUpdateUserName(),
                DateUtils.format(adModel_.getLastUpdateDate(), LeshanConstantsIF.DATE_FORMAT_YYYY_MM_DD_HH_MM)));
            this.errorEnd();
        }
        return adModel_;
    }

    /**
     * 用户基本テーブルの登録を行う
     * 
     * @param _inForm SCHM00301Form
     * @param _roleModel RpmRoleModel
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    private void updateRole(RPM00503Form _inForm, RpmAdvertisementModel _adModel)
                    throws ApplicationException, SystemException {

        // Ad名
        _adModel.setAdName(_inForm.getAdName());
        // Ad名
        _adModel.setAdUrl(_inForm.getAdUrl());
        // Ad状态 (1.推广中；2.未使用)
        String adStatus = "2";
        if (_inForm.isCheckedFlag()) {
            adStatus = "1";
        }
        _adModel.setAdStatus(adStatus);
        // 備考
        _adModel.setNotes(StringUtils.trimToNull(_inForm.getNotes()));
        // 消除标识
        _adModel.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        // 最終更新用户ID
        _adModel.setLastUpdateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
        // 最終更新用户名
        _adModel.setLastUpdateUserName(UserSessionUtils.getUserSessionInfo().getUserName());
        // 最終更新日
        _adModel.setLastUpdateDate(TimestampUtils.getSysTimestamp());

        int userRoleCount_ = rpmAdvertisementDao.update(_adModel);
        // 件数 ＝ 0 の場合
        if (userRoleCount_ == 0) {
            this.addErrorMessage(MessageUtils.getMessage("E00047", "登録"));
            this.errorEnd();
        }
        if (_inForm.isCheckedFlag()) {
            RpmAdvertisementCondition _condition = new RpmAdvertisementCondition();
            _condition.setAdId(_adModel.getAdId());
            rpmAdvertisement01Dao.updateAdStatus(_condition);
        }
    }

    /**
     * 推广信息 DAO的取得
     * 
     * @return 推广信息 DAO
     */
    public RpmAdvertisementDao getRpmAdvertisementDao() {
        return rpmAdvertisementDao;
    }

    /**
     * 推广信息 DAO的设定
     * 
     * @param _rpmRoleDao 推广信息 DAO
     */
    public void setRpmAdvertisementDao(RpmAdvertisementDao _rpmAdvertisementDao) {
        this.rpmAdvertisementDao = _rpmAdvertisementDao;
    }

    /**
     * 推广信息 DAO的设定
     * @param _rpmAdvertisement01Dao 推广信息 DAO
     */
    public void setRpmAdvertisement01Dao(RpmAdvertisement01Dao _rpmAdvertisement01Dao) {
        this.rpmAdvertisement01Dao = _rpmAdvertisement01Dao;
    }

}
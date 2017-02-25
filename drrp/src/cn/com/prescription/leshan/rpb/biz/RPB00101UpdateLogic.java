/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpb.biz;

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
import cn.com.prescription.framework.util.StringUtils;
import cn.com.prescription.framework.util.TimestampUtils;
import cn.com.prescription.leshan.common.LeshanConstantsIF;
import cn.com.prescription.leshan.common.data.RpmStoreDao;
import cn.com.prescription.leshan.common.data.condition.RpmStoreCondition;
import cn.com.prescription.leshan.common.data.model.RpmStoreModel;
import cn.com.prescription.leshan.rpb.action.RPB00101Action;
import cn.com.prescription.leshan.rpb.action.form.RPB00101Form;

/**
 * 门店编辑页面 登録処理 ロジック
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11. NAME: fsb
 */
public class RPB00101UpdateLogic extends StandardBiz implements StandardLogic {

    /**
     * 门店信息DAO
     */
    private RpmStoreDao rpmStoreDao = null;

    /**
     * 门店编辑ロジック的构造。
     */
    public RPB00101UpdateLogic() {
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
        RPB00101Form inForm_ = (RPB00101Form) _event.getForm();

        // 排他情報：处方情報
        RpmStoreModel storeModel_ = storeCheck(inForm_);

        // 门店テーブルの登録
        updateStore(inForm_, storeModel_);

        // 完了画面を表示する。
        this.connectCompleteDialog(MessageUtils.getMessage("I00001", "编辑"),
            new ActionInfo(RPB00101Action.class, "doInit"));
        // 出力情報設定
        return this.getEventResult(inForm_);

    }

    /**
     * 用户プロファイルテーブルのデータを取得し、ロック（for update）を行う
     * 
     * @param _inForm RPB00101Form
     * @return PatientModel_
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    private RpmStoreModel storeCheck(RPB00101Form _inForm) throws ApplicationException, SystemException {
        RpmStoreModel returnModel_ = null;
        if (CheckUtils.isEmpty(_inForm.getStoreId())) {
            returnModel_ = rpmStoreDao.selectForUpdate(new RpmStoreCondition());
            if (returnModel_ != null) {
                this.addErrorMessage(MessageUtils.getMessage("E00024", "门户信息", "重复登录"));
                this.errorEnd();
            }
        } else {

            // 排他情報：处方情報
            RpmStoreCondition storeCondition_ = new RpmStoreCondition();
            // 门店ID
            storeCondition_.setStoreId(_inForm.getStoreId());
            // [排他情報：用户プロファイル情報]
            returnModel_ = rpmStoreDao.selectForUpdate(storeCondition_);
            // [排他情報：用户プロ情報]の取得件数 ＝ 0 の場合
            if (returnModel_ == null) {
                // メッセージID【E00004】（パラメータ1："削除"、パラメータ２："登录"）
                this.addErrorMessage(MessageUtils.getMessage("E00004", "删除", "登录"));
                this.errorEnd();
            }
            // [排他情報：用户プロ情報．最終更新日]≠[画面：最終更新日]の場合
            if (returnModel_ != null && !CheckUtils.isEqual(_inForm.getLastUpdateDate(),
                TimestampUtils.formatUpd(returnModel_.getLastUpdateDate()))) {
                // メッセージID【E00006】
                this.addErrorMessage(
                    MessageUtils.getMessage("E00006", "更新", "登录", returnModel_.getLastUpdateUserName(), DateUtils
                        .format(returnModel_.getLastUpdateDate(), LeshanConstantsIF.DATE_FORMAT_YYYY_MM_DD_HH_MM)));
                this.errorEnd();
            }
        }
        return returnModel_;
    }

    /**
     * 处方テーブルの登録を行う
     * 
     * @param _inForm SCHM00301Form
     * @param storeModel_ RpmPrescriptionModel
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    private void updateStore(RPB00101Form _inForm, RpmStoreModel storeModel_)
                    throws ApplicationException, SystemException {
        boolean newFlag = false;
        if (storeModel_ == null) {
            newFlag = true;
            storeModel_ = new RpmStoreModel();
            storeModel_.setStoreId(StringUtils.getUniqueId());
            // 消除标识
            storeModel_.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
            // 创建者
            storeModel_.setCreateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
            // 创建日
            storeModel_.setCreateDate(TimestampUtils.getSysTimestamp());
        }
        // 处方名
        storeModel_.setStoreName(_inForm.getStoreName());
        // 处方单前缀
        storeModel_.setRpCodePrefix(_inForm.getRpCodePrefix());
        // 门店二维码
        if (!CheckUtils.isEmpty(_inForm.getNewQrCodeImage())) {
            storeModel_.setQrCode(_inForm.getNewQrCodeImage());
        }
        // 备注
        storeModel_.setNotes(_inForm.getNotes());
        // 最終更新用户ID
        storeModel_.setLastUpdateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
        // 最終更新用户名
        storeModel_.setLastUpdateUserName(UserSessionUtils.getUserSessionInfo().getUserName());
        // 最終更新日
        storeModel_.setLastUpdateDate(TimestampUtils.getSysTimestamp());

        // オブジェクトの空の文字列を削除する。
        ReflectUtils.clearAllSpace(storeModel_);
        int returnCount_ = 0;
        if (newFlag) {
            returnCount_ = rpmStoreDao.insert(storeModel_);
        } else {
            returnCount_ = rpmStoreDao.update(storeModel_);
        }
        // 件数 ＝ 0 の場合
        if (returnCount_ == 0) {
            this.addErrorMessage(MessageUtils.getMessage("E00047", "登录"));
            this.errorEnd();
        }
    }

    /**
     * 门店信息DAO的设定
     * 
     * @param _rpmStoreDao 门店信息DAO
     */
    public void setRpmStoreDao(RpmStoreDao _rpmStoreDao) {
        this.rpmStoreDao = _rpmStoreDao;
    }

}
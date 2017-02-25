/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpb.biz;

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
import cn.com.prescription.leshan.common.data.RpmStoreDao;
import cn.com.prescription.leshan.common.data.condition.RpmStoreCondition;
import cn.com.prescription.leshan.common.data.model.RpmStoreModel;
import cn.com.prescription.leshan.rpb.action.RPB00101Action;
import cn.com.prescription.leshan.rpb.action.form.RPB00101Form;

/**
 * 门店信息编辑 删除処理 ロジック
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11. NAME: fsb
 */
public class RPB00101DeleteLogic extends StandardBiz implements StandardLogic {

    /**
     * 门店信息DAO
     */
    private RpmStoreDao rpmStoreDao = null;

    /**
     * 门店信息编辑ロジック的构造。
     */
    public RPB00101DeleteLogic() {
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

        // 门店テーブルの删除
        deleteStore(inForm_, storeModel_);

        // 完了画面を表示する。
        this.connectCompleteDialog(MessageUtils.getMessage("I00001", "删除"),
            new ActionInfo(RPB00101Action.class, "doInit"));
        // 出力情報設定
        return this.getEventResult(inForm_);

    }

    /**
     * 门店のデータを取得し、ロック（for update）を行う
     * 
     * @param _inForm RPB00101Form
     * @return PatientModel_
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    private RpmStoreModel storeCheck(RPB00101Form _inForm) throws ApplicationException, SystemException {

        // 排他情報：处方情報
        RpmStoreCondition _condition_ = new RpmStoreCondition();
        // 门店ＩＤ
        _condition_.setStoreId(_inForm.getStoreId());
        // 消除标识＝ [定数：消除标识．有効レコード]
        _condition_.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        // [排他情報：用户プロファイル情報]
        RpmStoreModel returnModel_ = rpmStoreDao.selectForUpdate(_condition_);
        // [排他情報：用户プロ情報]の取得件数 ＝ 0 の場合
        if (returnModel_ == null) {
            // メッセージID【E00004】（パラメータ1："削除"、パラメータ２："登录"）
            this.addErrorMessage(MessageUtils.getMessage("E00004", "删除", "删除"));
            this.errorEnd();
        }
        // [排他情報：门户情報．最終更新日]≠[画面：最終更新日]の場合
        if (returnModel_ != null && !CheckUtils.isEqual(_inForm.getLastUpdateDate(),
            TimestampUtils.formatUpd(returnModel_.getLastUpdateDate()))) {
            // メッセージID【E00006】
            this.addErrorMessage(MessageUtils.getMessage("E00006", "更新", "删除", returnModel_.getLastUpdateUserName(),
                DateUtils.format(returnModel_.getLastUpdateDate(), LeshanConstantsIF.DATE_FORMAT_YYYY_MM_DD_HH_MM)));
            this.errorEnd();
        }
        return returnModel_;
    }

    /**
     * 删除门店信息
     * 
     * @param _inForm SCHM00301Form
     * @param stroeModel_ RpmStoreModel
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    private void deleteStore(RPB00101Form _inForm, RpmStoreModel stroeModel_)
                    throws ApplicationException, SystemException {

        int returnCount_ = rpmStoreDao.delete(stroeModel_);
        // 件数 ＝ 0 の場合
        if (returnCount_ == 0) {
            this.addErrorMessage(MessageUtils.getMessage("E00047", "删除"));
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
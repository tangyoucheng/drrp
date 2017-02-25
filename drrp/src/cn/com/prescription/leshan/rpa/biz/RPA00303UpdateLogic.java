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
import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.framework.util.DateUtils;
import cn.com.prescription.framework.util.ReflectUtils;
import cn.com.prescription.framework.util.TimestampUtils;
import cn.com.prescription.leshan.common.LeshanConstantsIF;
import cn.com.prescription.leshan.common.data.RpmDrugDao;
import cn.com.prescription.leshan.common.data.condition.RpmDrugCondition;
import cn.com.prescription.leshan.common.data.model.RpmDrugModel;
import cn.com.prescription.leshan.rpa.action.RPA00302Action;
import cn.com.prescription.leshan.rpa.action.form.RPA00303Form;

/**
 * 药品一览 登录処理 ロジック
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11. NAME: fsb
 */
public class RPA00303UpdateLogic extends StandardBiz implements StandardLogic {

    /**
     * 药品基本テーブル DAO
     */
    private RpmDrugDao rpmDrugDao = null;

    /**
     * 药品一览ロジック的构造。
     */
    public RPA00303UpdateLogic() {
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
        RPA00303Form inForm_ = (RPA00303Form) _event.getForm();

        // 排他情報：药品プロ情報
        RpmDrugModel drugModel_ = drugCheck(inForm_);

        // 药品基本テーブルの登录
        updateDrug(inForm_, drugModel_);

        // 完了画面を表示する。
        this.connectCompleteDialog(MessageUtils.getMessage("I00001", "登录"),
            new ActionInfo(RPA00302Action.class, "doBack"));
        // 出力情報設定
        return this.getEventResult(inForm_);

    }

    /**
     * 药品プロファイルテーブルのデータを取得し、ロック（for update）を行う
     * 
     * @param _inForm SCHM00301Form
     * @return DrugModel_
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    private RpmDrugModel drugCheck(RPA00303Form _inForm) throws ApplicationException, SystemException {

        // 排他情報：药品プロ情報
        RpmDrugCondition drugCondition_ = new RpmDrugCondition();
        // 药品ID
        drugCondition_.setDrugId(_inForm.getDrugId());
        // 消除标识＝ [定数：消除标识．有効レコード]
        drugCondition_.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        // [排他情報：药品プロファイル情報]
        RpmDrugModel returnDrugModel_ = rpmDrugDao.selectForUpdate(drugCondition_);
        // [排他情報：药品プロ情報]の取得件数 ＝ 0 の場合
        if (returnDrugModel_ == null) {
            // メッセージID【E00004】（パラメータ1："削除"、パラメータ２："登录"）
            this.addErrorMessage(MessageUtils.getMessage("E00004", "删除", "登录"));
            this.errorEnd();
        }
        // [排他情報：药品プロ情報．最終更新日]≠[画面：最終更新日]の場合
        if (returnDrugModel_ != null && !CheckUtils.isEqual(_inForm.getLastUpdateDate(),
            TimestampUtils.formatUpd(returnDrugModel_.getLastUpdateDate()))) {
            // メッセージID【E00006】
            this.addErrorMessage(
                MessageUtils.getMessage("E00006", "更新", "登录", returnDrugModel_.getLastUpdateUserName(), DateUtils
                    .format(returnDrugModel_.getLastUpdateDate(), LeshanConstantsIF.DATE_FORMAT_YYYY_MM_DD_HH_MM)));
            this.errorEnd();
        }

        drugCondition_ = new RpmDrugCondition();
        // 药品名称
        drugCondition_.setDrugName(_inForm.getDrugName());
        // 厂商名称
        drugCondition_.setManufacturerName(_inForm.getManufacturerName());
        // 消除标识＝ [定数：消除标识．有効レコード]
        drugCondition_.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        // [排他情報：药品プロファイル情報]
        RpmDrugModel drugModel_ = rpmDrugDao.selectForUpdate(drugCondition_);

        if (drugModel_ != null && !CheckUtils.isEqual(drugModel_.getDrugId(), _inForm.getDrugId())) {
            this.addErrorMessage(MessageUtils.getMessage("E00024", "填写的药品名称和厂商名称", "重复登录"));
            this.errorEnd();
        }

        return returnDrugModel_;
    }

    /**
     * 药品プロファイルテーブルの登录を行う
     * 
     * @param _inForm SCHM00301Form
     * @param _drugModel RpmDrugModel
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    private void updateDrug(RPA00303Form _inForm, RpmDrugModel _drugModel)
                    throws ApplicationException, SystemException {

        // 药品ID
        _drugModel.setDrugId(_inForm.getDrugId());
        // 药品名称
        _drugModel.setDrugName(_inForm.getDrugName());
        // 厂商名称
        _drugModel.setManufacturerName(_inForm.getManufacturerName());
        // 价格
        _drugModel.setPrice(_inForm.getPrice());
        // 单位
        _drugModel.setUnit(_inForm.getUnit());
        // 备注
        _drugModel.setNotes(_inForm.getNotes());
        // 消除标识
        _drugModel.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        // 最終更新药品ID
        _drugModel.setLastUpdateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
        // 最終更新药品名
        _drugModel.setLastUpdateUserName(UserSessionUtils.getUserSessionInfo().getUserName());
        // 最終更新日
        _drugModel.setLastUpdateDate(TimestampUtils.getSysTimestamp());

        // オブジェクトの空の文字列を削除する。
        ReflectUtils.clearAllSpace(_drugModel);

        int DrugCount_ = rpmDrugDao.update(_drugModel);
        // 件数 ＝ 0 の場合
        if (DrugCount_ == 0) {
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
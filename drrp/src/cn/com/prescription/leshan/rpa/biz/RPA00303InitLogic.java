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
import cn.com.prescription.framework.util.TimestampUtils;
import cn.com.prescription.leshan.common.LeshanConstantsIF;
import cn.com.prescription.leshan.common.data.RpmDrugDao;
import cn.com.prescription.leshan.common.data.condition.RpmDrugCondition;
import cn.com.prescription.leshan.common.data.model.RpmDrugModel;
import cn.com.prescription.leshan.rpa.action.RPA00202Action;
import cn.com.prescription.leshan.rpa.action.form.RPA00303Form;

/**
 * 药品信息添加初期化处理。
 * 新規作成
 * DATE: 2016.03.24 NAME: tyc
 */
public class RPA00303InitLogic extends StandardBiz implements StandardLogic {
    /**
     * 药品基本テーブル DAO
     */
    private RpmDrugDao rpmDrugDao = null;

    /**
     * 药品信息設定画面 初期化業務クラス的构造。
     */
    public RPA00303InitLogic() {
        super();
    }

    /**
     * event処理を行う。
     * 
     * @return event処理結果
     * @param _event
     *            event
     * @throws ApplicationException
     *             event処理里应用程序例外发生的情况
     * @throws SystemException
     *             event処理里系统例外发生的情况
     */
    public StandardEventResult service(StandardEvent _event) throws ApplicationException, SystemException {

        // event処理結果
        RPA00303Form inForm_ = (RPA00303Form) _event.getForm();

        RpmDrugCondition drugCondition_ = new RpmDrugCondition();
        // 药品ID
        drugCondition_.setDrugId(inForm_.getDrugId());
        // 消除标识＝ [定数：消除标识．有効レコード]
        drugCondition_.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        // [排他情報：用户プロファイル情報]
        RpmDrugModel drugModel_ = rpmDrugDao.select(drugCondition_);
        if (drugModel_ != null) {

            // 药品ID
            inForm_.setDrugId(drugModel_.getDrugId());
            // 药品名称
            inForm_.setDrugName(drugModel_.getDrugName());
            // 厂商名称
            inForm_.setManufacturerName(drugModel_.getManufacturerName());
            // 价格
            inForm_.setPrice(drugModel_.getPrice());
            // 备注
            inForm_.setNotes(drugModel_.getNotes());
            // 最終更新日
            inForm_.setLastUpdateDate(TimestampUtils.formatUpd(drugModel_.getLastUpdateDate()));
        } else {
            // this.connectCompleteDialog(MessageUtils.getMessage("E00009"),
            // new ActionInfo(RPA00202Action.class, "doInit"));
            this.addErrorMessage(MessageUtils.getMessage("E00004", "削除", "表示"));
            this.errorEnd(new ActionInfo(RPA00202Action.class, "doBack"));
        }

        // 出力情報設定
        return this.getEventResult(inForm_);
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

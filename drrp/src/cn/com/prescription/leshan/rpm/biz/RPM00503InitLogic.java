/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpm.biz;

import cn.com.prescription.framework.biz.ActionInfo;
import cn.com.prescription.framework.biz.StandardBiz;
import cn.com.prescription.framework.biz.StandardLogic;
import cn.com.prescription.framework.event.StandardEvent;
import cn.com.prescription.framework.event.StandardEventResult;
import cn.com.prescription.framework.exception.ApplicationException;
import cn.com.prescription.framework.exception.SystemException;
import cn.com.prescription.framework.message.MessageUtils;
import cn.com.prescription.framework.util.StringUtils;
import cn.com.prescription.framework.util.TimestampUtils;
import cn.com.prescription.leshan.common.LeshanConstantsIF;
import cn.com.prescription.leshan.common.data.RpmAdvertisementDao;
import cn.com.prescription.leshan.common.data.condition.RpmAdvertisementCondition;
import cn.com.prescription.leshan.common.data.model.RpmAdvertisementModel;
import cn.com.prescription.leshan.rpm.action.RPM00302Action;
import cn.com.prescription.leshan.rpm.action.form.RPM00503Form;

/**
 * 推广信息编辑初期化处理。
 * 新規作成
 * DATE: 2016.03.24 NAME: tyc
 */
public class RPM00503InitLogic extends StandardBiz implements StandardLogic {

    /**
     * 役割マスタDAO
     */
    RpmAdvertisementDao rpmAdvertisementDao = null;

    /**
     * 用户情報設定画面（一般用户用） 初期化業務クラス的构造。
     */
    public RPM00503InitLogic() {
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
        RPM00503Form inForm_ = (RPM00503Form) _event.getForm();

        // roleを取得する
        RpmAdvertisementCondition advertisement = new RpmAdvertisementCondition();
        // AdID
        advertisement.setAdId(inForm_.getAdId());
        // 消除标识
        advertisement.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        // ソートキー ASC
        // roleCondition.addPageSortField("RPM_ROLE.SORT_KEY", LeshanConstantsIF.SORT_SEQ_ASC);
        RpmAdvertisementModel AdModel_ = rpmAdvertisementDao.select(advertisement);
        if (AdModel_ == null) {
            this.addErrorMessage(MessageUtils.getMessage("E00004", "削除", "表示"));
            this.errorEnd(new ActionInfo(RPM00302Action.class, "doBack"));
        } else {
            // adＩＤ
            inForm_.setAdId(AdModel_.getAdId());
            // ad名
            inForm_.setAdName(AdModel_.getAdName());
            // adUrl
            inForm_.setAdUrl(AdModel_.getAdUrl());
            // adStatus
            inForm_.setAdStatus(AdModel_.getAdStatus());
            // Ad状态 (1.推广中；2.未使用)
            if("1".equals(inForm_.getAdStatus())){
                inForm_.setCheckedFlag(true);
            }
            // 備考
            inForm_.setNotes(StringUtils.trimToNull(AdModel_.getNotes()));
            // 最終更新日
            inForm_.setLastUpdateDate(TimestampUtils.formatUpd(AdModel_.getLastUpdateDate()));
        }

        // 出力情報設定
        return this.getEventResult(inForm_);
    }

    /**
     * 役割マスタDAOを設定する。
     * 
     * @param _rpmRoleDao 役割マスタDAO
     */
    public void setRpmAdvertisementDao(RpmAdvertisementDao _rpmAdvertisementDao) {
        this.rpmAdvertisementDao = _rpmAdvertisementDao;
    }

}

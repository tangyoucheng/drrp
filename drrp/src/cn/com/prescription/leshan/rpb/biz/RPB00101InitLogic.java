/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpb.biz;

import cn.com.prescription.framework.biz.StandardBiz;
import cn.com.prescription.framework.biz.StandardLogic;
import cn.com.prescription.framework.event.StandardEvent;
import cn.com.prescription.framework.event.StandardEventResult;
import cn.com.prescription.framework.exception.ApplicationException;
import cn.com.prescription.framework.exception.SystemException;
import cn.com.prescription.framework.util.TimestampUtils;
import cn.com.prescription.leshan.common.data.RpmStoreDao;
import cn.com.prescription.leshan.common.data.condition.RpmStoreCondition;
import cn.com.prescription.leshan.common.data.model.RpmStoreModel;
import cn.com.prescription.leshan.rpb.action.form.RPB00101Form;

/**
 * 处方一览一览初期化处理。
 * 新規作成
 * DATE: 2016.03.24 NAME: tyc
 */
public class RPB00101InitLogic extends StandardBiz implements StandardLogic {

    /**
     * 门店信息DAO
     */
    private RpmStoreDao rpmStoreDao = null;

    /**
     * 用户情報設定画面（一般用户用） 初期化業務クラス的构造。
     */
    public RPB00101InitLogic() {
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
        RPB00101Form inForm_ = (RPB00101Form) _event.getForm();
        RpmStoreModel storeModel = rpmStoreDao.select(new RpmStoreCondition());
        if (storeModel != null) {
            inForm_.setStoreId(storeModel.getStoreId());
            inForm_.setStoreName(storeModel.getStoreName());
            inForm_.setRpCodePrefix(storeModel.getRpCodePrefix());
            inForm_.setOldQrCodeImage(storeModel.getQrCode());
            inForm_.setNotes(storeModel.getNotes());
            inForm_.setLastUpdateDate(TimestampUtils.formatUpd(storeModel.getLastUpdateDate()));
        }
        // 出力情報設定
        return this.getEventResult(inForm_);
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

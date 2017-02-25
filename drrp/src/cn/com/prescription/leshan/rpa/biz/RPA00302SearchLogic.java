/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpa.biz;

import java.util.List;

import cn.com.prescription.framework.biz.StandardBiz;
import cn.com.prescription.framework.biz.StandardLogic;
import cn.com.prescription.framework.event.StandardEvent;
import cn.com.prescription.framework.event.StandardEventResult;
import cn.com.prescription.framework.exception.ApplicationException;
import cn.com.prescription.framework.exception.SystemException;
import cn.com.prescription.framework.util.StringUtils;
import cn.com.prescription.leshan.common.LeshanConstantsIF;
import cn.com.prescription.leshan.common.data.RpmDrug01Dao;
import cn.com.prescription.leshan.common.data.condition.RpmDrug01Condition;
import cn.com.prescription.leshan.common.data.model.RpmDrug01Model;
import cn.com.prescription.leshan.rpa.action.form.RPA0030201Dto;
import cn.com.prescription.leshan.rpa.action.form.RPA00302Form;

/**
 * 药品一览检索处理。
 * 新規作成
 * DATE: 2016.03.24 NAME: tyc
 */
public class RPA00302SearchLogic extends StandardBiz implements StandardLogic {

    /**
     * 药品DAO
     */
    RpmDrug01Dao rpmDrug01Dao = null;

    /**
     * 药品一览 初期化業務クラス的构造。
     */
    public RPA00302SearchLogic() {
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
        RPA00302Form inForm_ = (RPA00302Form) _event.getForm();

        // (2)药品リストを取得する
        RpmDrug01Condition drugCondition = new RpmDrug01Condition();
        // 药品名称
        drugCondition.setDrugName(StringUtils.getLikeParameter(inForm_.getDrugName()));
        // 厂商名称
        drugCondition.setManufacturerName(StringUtils.getLikeParameter(inForm_.getManufacturerName()));
        // 消除标识
        drugCondition.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);

        long drugCount_ = rpmDrug01Dao.selectDrugListCount(drugCondition);
        // 前回検索結果クリア
        inForm_.getSubForm1().clear();
        // 件数チェック処理
        this.checkCount(inForm_, drugCount_);
        // ソートキー ASC
        // roleCondition.addPageSortField("RPM_ROLE.SORT_KEY", LeshanConstantsIF.SORT_SEQ_ASC);
        drugCondition.setPageStartRowNo(inForm_.getPageStartRowNo() + 1);
        List<RpmDrug01Model> roleModelList_ = rpmDrug01Dao.selectDrugList(drugCondition);
        for (RpmDrug01Model rpmPatientModel : roleModelList_) {
            RPA0030201Dto rpa0030201Dto = new RPA0030201Dto();
            rpa0030201Dto.setDrugId(rpmPatientModel.getDrugId());
            rpa0030201Dto.setDrugName(rpmPatientModel.getDrugName());
            rpa0030201Dto.setManufacturerName(rpmPatientModel.getManufacturerName());
            rpa0030201Dto.setPrice(rpmPatientModel.getPrice());
            rpa0030201Dto.setUnit(rpmPatientModel.getUnit());
            rpa0030201Dto.setNotes(rpmPatientModel.getNotes());
            inForm_.getSubForm1().add(rpa0030201Dto);
        }

        // 出力情報設定
        return this.getEventResult(inForm_);
    }

    /**
     * 药品DAO的设定
     * 
     * @param _rpmDrug01Dao 药品DAO
     */
    public void setRpmDrug01Dao(RpmDrug01Dao _rpmDrug01Dao) {
        this.rpmDrug01Dao = _rpmDrug01Dao;
    }

}

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
import cn.com.prescription.leshan.common.data.RpmPatient01Dao;
import cn.com.prescription.leshan.common.data.condition.RpmPatient01Condition;
import cn.com.prescription.leshan.common.data.model.RpmPatient01Model;
import cn.com.prescription.leshan.rpa.action.form.RPA0020201Dto;
import cn.com.prescription.leshan.rpa.action.form.RPA00202Form;

/**
 * 患者一览检索处理。
 * 新規作成
 * DATE: 2016.03.24 NAME: tyc
 */
public class RPA00202SearchLogic extends StandardBiz implements StandardLogic {

    /**
     * 患者DAO
     */
    RpmPatient01Dao rpmPatient01Dao = null;

    /**
     * 用户情報設定画面（一般用户用） 初期化業務クラス的构造。
     */
    public RPA00202SearchLogic() {
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
        RPA00202Form inForm_ = (RPA00202Form) _event.getForm();

        // (2)役割リストを取得する
        RpmPatient01Condition patientCondition = new RpmPatient01Condition();
        // 用户名
        patientCondition.setUserName(StringUtils.getLikeParameter(inForm_.getUserName()));
        // 手机
        patientCondition.setCeelNumber(StringUtils.getLikeParameter(inForm_.getCeelNumber()));
        // 座机
        // patientCondition.setPhoneNumber(inForm_.getPhoneNumber());
        // 消除标识
        patientCondition.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);

        long patientcount_ = rpmPatient01Dao.selectPatientListCount(patientCondition);
        // 前回検索結果クリア
        inForm_.getSubForm1().clear();
        // 件数チェック処理
        this.checkCount(inForm_, patientcount_);
        // ソートキー ASC
        // roleCondition.addPageSortField("RPM_ROLE.SORT_KEY", LeshanConstantsIF.SORT_SEQ_ASC);
        patientCondition.setPageStartRowNo(inForm_.getPageStartRowNo() + 1);
        List<RpmPatient01Model> roleModelList_ = rpmPatient01Dao.selectPatientList(patientCondition);
        for (RpmPatient01Model rpmPatientModel : roleModelList_) {
            RPA0020201Dto rpa0020201Dto = new RPA0020201Dto();
            rpa0020201Dto.setUserId(rpmPatientModel.getUserId());
            rpa0020201Dto.setUserName(rpmPatientModel.getUserName());
            rpa0020201Dto.setCeelNumber(rpmPatientModel.getCeelNumber());
            rpa0020201Dto.setPhoneNumber(rpmPatientModel.getPhoneNumber());
            inForm_.getSubForm1().add(rpa0020201Dto);
        }

        // 出力情報設定
        return this.getEventResult(inForm_);
    }

    /**
     * 患者DAO的设定
     * 
     * @param _rpmPatient01Dao 患者DAO
     */
    public void setRpmPatient01Dao(RpmPatient01Dao _rpmPatient01Dao) {
        this.rpmPatient01Dao = _rpmPatient01Dao;
    }

}

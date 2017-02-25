/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpa.biz;

import java.util.ArrayList;
import java.util.List;

import cn.com.prescription.framework.biz.StandardBiz;
import cn.com.prescription.framework.biz.StandardLogic;
import cn.com.prescription.framework.common.session.UserSessionUtils;
import cn.com.prescription.framework.event.StandardEvent;
import cn.com.prescription.framework.event.StandardEventResult;
import cn.com.prescription.framework.exception.ApplicationException;
import cn.com.prescription.framework.exception.SystemException;
import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.framework.util.StringUtils;
import cn.com.prescription.framework.util.TimestampUtils;
import cn.com.prescription.leshan.common.LeshanConstantsIF;
import cn.com.prescription.leshan.rpa.action.form.RPA0010201Dto;
import cn.com.prescription.leshan.rpa.action.form.RPA00102Form;
import cn.com.prescription.leshan.rpa.data.RPA00102Dao;
import cn.com.prescription.leshan.rpa.data.condition.RPA00102Condition;
import cn.com.prescription.leshan.rpa.data.model.RPA00102Model;

/**
 * 处方一览检索处理。
 * 新規作成
 * DATE: 2016.03.24 NAME: tyc
 */
public class RPA00102SearchLogic extends StandardBiz implements StandardLogic {

    /**
     * 处方一览DAO
     */
    RPA00102Dao rpa00102Dao = null;

    /**
     * 用户情報設定画面（一般用户用） 初期化業務クラス的构造。
     */
    public RPA00102SearchLogic() {
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
        RPA00102Form inForm_ = (RPA00102Form) _event.getForm();
        // 处方リストを取得する
        RPA00102Condition rpa00102Condition = new RPA00102Condition();
        // 处方审核的场合
        if (CheckUtils.isEqual("rpa00105", inForm_.getPageType())) {
            List<String> prescriptionStatus_ = new ArrayList<String>();
            prescriptionStatus_.add("3");
            prescriptionStatus_.add("4");
            rpa00102Condition.setPrescriptionStatus(prescriptionStatus_);
        }
        // 用户名
        rpa00102Condition.setUserName(StringUtils.getLikeParameter(inForm_.getUserName()));
        // 手机
        rpa00102Condition.setCeelNumber(StringUtils.getLikeParameter(inForm_.getCeelNumber()));
        // 处方创建者
        if (CheckUtils.isEqual("rpa00103", inForm_.getPageType())) {
            rpa00102Condition.setPrescriptionCreateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
        }
        // 消除标识
        rpa00102Condition.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);

        long patientcount_ = rpa00102Dao.selectPrescriptionListCount(rpa00102Condition);
        // 前回検索結果クリア
        inForm_.getSubForm1().clear();
        // 件数チェック処理
        this.checkCount(inForm_, patientcount_);
        // 排序
        rpa00102Condition.addPageSortField("prescription.prescription_status", LeshanConstantsIF.SORT_SEQ_ASC);
        rpa00102Condition.addPageSortField("prescription.create_date", LeshanConstantsIF.SORT_SEQ_DESC);
        // rpa00102Condition.addPageSortField("patient.user_name", LeshanConstantsIF.SORT_SEQ_ASC);
        rpa00102Condition.setPageStartRowNo(inForm_.getPageStartRowNo() + 1);
        List<RPA00102Model> rpa00102ModellList_ = rpa00102Dao.selectPrescriptionList(rpa00102Condition);
        for (RPA00102Model rpa00102ModelModel : rpa00102ModellList_) {
            RPA0010201Dto rpa0010201Dto = new RPA0010201Dto();
            rpa0010201Dto.setUserId(rpa00102ModelModel.getUserId());
            rpa0010201Dto.setPrescriptionId(rpa00102ModelModel.getPrescriptionId());
            rpa0010201Dto.setUserName(rpa00102ModelModel.getUserName());
            rpa0010201Dto.setCeelNumber(rpa00102ModelModel.getCeelNumber());
            rpa0010201Dto.setPrescriptionStatus(rpa00102ModelModel.getPrescriptionStatus());
            if (CheckUtils.isEqual("1", rpa00102ModelModel.getPrescriptionStatus())) {
                rpa0010201Dto.setPrescriptionStatusName("新规做成");
            }
            if (CheckUtils.isEqual("2", rpa00102ModelModel.getPrescriptionStatus())) {
                rpa0010201Dto.setPrescriptionStatusName("审核未通过");
            }
            if (CheckUtils.isEqual("3", rpa00102ModelModel.getPrescriptionStatus())) {
                rpa0010201Dto.setPrescriptionStatusName("配药完成");
            }
            if (CheckUtils.isEqual("4", rpa00102ModelModel.getPrescriptionStatus())) {
                rpa0010201Dto.setPrescriptionStatusName("审核通过");
            }
            rpa0010201Dto
                .setPrescriptionCreateDate(TimestampUtils.format(rpa00102ModelModel.getCreateDatePrescription(),
                    LeshanConstantsIF.DATE_FORMAT_YYYY_YEAR_MM_MONTH_DD_DATE + " HH点mm分"));
            inForm_.getSubForm1().add(rpa0010201Dto);
        }

        // 出力情報設定
        return this.getEventResult(inForm_);
    }

    /**
     * 处方一览DAO的设定
     * 
     * @param _rpa00102Dao 处方一览DAO
     */
    public void setRpa00102Dao(RPA00102Dao _rpa00102Dao) {
        this.rpa00102Dao = _rpa00102Dao;
    }

}

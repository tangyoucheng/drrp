/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpa.biz;

import java.util.List;

import cn.com.prescription.framework.CodeValueRecord;
import cn.com.prescription.framework.biz.StandardBiz;
import cn.com.prescription.framework.biz.StandardLogic;
import cn.com.prescription.framework.event.StandardEvent;
import cn.com.prescription.framework.event.StandardEventResult;
import cn.com.prescription.framework.exception.ApplicationException;
import cn.com.prescription.framework.exception.SystemException;
import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.leshan.rpa.action.form.RPA0010901Dto;
import cn.com.prescription.leshan.rpa.action.form.RPA00109Form;
import cn.com.prescription.leshan.rpa.data.RPA00109Dao;
import cn.com.prescription.leshan.rpa.data.condition.RPA00109Condition;
import cn.com.prescription.leshan.rpa.data.model.RPA00109Model;

/**
 * 处方统计检索处理。
 * 新規作成
 * DATE: 2016.03.24 NAME: tyc
 */
public class RPA00109SearchLogic extends StandardBiz implements StandardLogic {

    /**
     * 处方统计DAO
     */
    RPA00109Dao rpa00109Dao = null;

    /**
     * 处方统计初期化業務クラス的构造。
     */
    public RPA00109SearchLogic() {
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
        RPA00109Form inForm_ = (RPA00109Form) _event.getForm();

        // 处方リストを取得する
        RPA00109Condition rpa00109Condition = new RPA00109Condition();
        // 处方状态 审核的场合
        rpa00109Condition.setPrescriptionStatus("4");
        // 处方年度
        rpa00109Condition.setPrescriptionYear(inForm_.getPrescriptionYear());
        // // 消除标识
        // rpa00109Condition.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);

        long prescriptionInfoCount_ = rpa00109Dao.selectPrescriptionInfoCount(rpa00109Condition);
        // 前回検索結果クリア
        inForm_.getSubForm1().clear();
        // 件数チェック処理
        this.checkCount(inForm_, prescriptionInfoCount_);
        if (!CheckUtils.isEmpty(inForm_.getPrescriptionYear())) {
            List<RPA00109Model> rpa00109ModellList_ = rpa00109Dao.selectPrescriptionCount(rpa00109Condition);
            if (rpa00109ModellList_ != null) {
                for (RPA00109Model rpa00109ModelModel : rpa00109ModellList_) {
                    RPA0010901Dto rpa0010901Dto = new RPA0010901Dto();
                    rpa0010901Dto.setPrescriptionYear(inForm_.getPrescriptionYear());
                    rpa0010901Dto.setJanCount(rpa00109ModelModel.getJanCount());
                    rpa0010901Dto.setFebCount(rpa00109ModelModel.getFebCount());
                    rpa0010901Dto.setMarCount(rpa00109ModelModel.getMarCount());
                    rpa0010901Dto.setFirstQuarterCount(rpa00109ModelModel.getFirstQuarterCount());
                    rpa0010901Dto.setAprCount(rpa00109ModelModel.getAprCount());
                    rpa0010901Dto.setMayCount(rpa00109ModelModel.getMayCount());
                    rpa0010901Dto.setJuneCount(rpa00109ModelModel.getJuneCount());
                    rpa0010901Dto.setSecondQuarterCount(rpa00109ModelModel.getSecondQuarterCount());
                    rpa0010901Dto.setJulyCount(rpa00109ModelModel.getJulyCount());
                    rpa0010901Dto.setAugCount(rpa00109ModelModel.getAugCount());
                    rpa0010901Dto.setSepCount(rpa00109ModelModel.getSepCount());
                    rpa0010901Dto.setThirdQuarterCount(rpa00109ModelModel.getThirdQuarterCount());
                    rpa0010901Dto.setOctCount(rpa00109ModelModel.getOctCount());
                    rpa0010901Dto.setNovCount(rpa00109ModelModel.getNovCount());
                    rpa0010901Dto.setDecCount(rpa00109ModelModel.getDecCount());
                    rpa0010901Dto.setFourthQuarterCount(rpa00109ModelModel.getFourthQuarterCount());
                    rpa0010901Dto.setYearCount(rpa00109ModelModel.getYearCount());
                    inForm_.getSubForm1().add(rpa0010901Dto);
                }
            }
        } else {
            for (CodeValueRecord record : inForm_.getPrescriptionYearDataSource()) {
                if (!CheckUtils.isEmpty(record.getRecordCode())) {
                    // 处方年度
                    rpa00109Condition.setPrescriptionYear(record.getRecordCode());
                    List<RPA00109Model> rpa00109ModellList_ = rpa00109Dao.selectPrescriptionCount(rpa00109Condition);
                    if (rpa00109ModellList_ != null) {
                        for (RPA00109Model rpa00109ModelModel : rpa00109ModellList_) {
                            RPA0010901Dto rpa0010901Dto = new RPA0010901Dto();
                            rpa0010901Dto.setPrescriptionYear(record.getRecordCode());
                            rpa0010901Dto.setJanCount(rpa00109ModelModel.getJanCount());
                            rpa0010901Dto.setFebCount(rpa00109ModelModel.getFebCount());
                            rpa0010901Dto.setMarCount(rpa00109ModelModel.getMarCount());
                            rpa0010901Dto.setFirstQuarterCount(rpa00109ModelModel.getFirstQuarterCount());
                            rpa0010901Dto.setAprCount(rpa00109ModelModel.getAprCount());
                            rpa0010901Dto.setMayCount(rpa00109ModelModel.getMayCount());
                            rpa0010901Dto.setJuneCount(rpa00109ModelModel.getJuneCount());
                            rpa0010901Dto.setSecondQuarterCount(rpa00109ModelModel.getSecondQuarterCount());
                            rpa0010901Dto.setJulyCount(rpa00109ModelModel.getJulyCount());
                            rpa0010901Dto.setAugCount(rpa00109ModelModel.getAugCount());
                            rpa0010901Dto.setSepCount(rpa00109ModelModel.getSepCount());
                            rpa0010901Dto.setThirdQuarterCount(rpa00109ModelModel.getThirdQuarterCount());
                            rpa0010901Dto.setOctCount(rpa00109ModelModel.getOctCount());
                            rpa0010901Dto.setNovCount(rpa00109ModelModel.getNovCount());
                            rpa0010901Dto.setDecCount(rpa00109ModelModel.getDecCount());
                            rpa0010901Dto.setFourthQuarterCount(rpa00109ModelModel.getFourthQuarterCount());
                            rpa0010901Dto.setYearCount(rpa00109ModelModel.getYearCount());
                            inForm_.getSubForm1().add(rpa0010901Dto);
                        }
                    }
                }
            }
        }

        // 出力情報設定
        return this.getEventResult(inForm_);
    }

    /**
     * 处方一览DAO的设定
     * 
     * @param _rpa00109Dao 处方一览DAO
     */
    public void setRpa00109Dao(RPA00109Dao _rpa00109Dao) {
        this.rpa00109Dao = _rpa00109Dao;
    }

}

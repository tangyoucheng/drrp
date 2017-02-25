/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpa.biz;

import java.util.ArrayList;
import java.util.List;

import cn.com.prescription.framework.CodeValueRecord;
import cn.com.prescription.framework.biz.StandardBiz;
import cn.com.prescription.framework.biz.StandardLogic;
import cn.com.prescription.framework.event.StandardEvent;
import cn.com.prescription.framework.event.StandardEventResult;
import cn.com.prescription.framework.exception.ApplicationException;
import cn.com.prescription.framework.exception.SystemException;
import cn.com.prescription.framework.util.StringUtils;
import cn.com.prescription.leshan.rpa.action.form.RPA00109Form;
import cn.com.prescription.leshan.rpa.data.RPA00109Dao;
import cn.com.prescription.leshan.rpa.data.condition.RPA00109Condition;
import cn.com.prescription.leshan.rpa.data.model.RPA00109Model;

/**
 * 处方统计初期化处理。
 * 新規作成
 * DATE: 2016.03.24 NAME: tyc
 */
public class RPA00109InitLogic extends StandardBiz implements StandardLogic {

    /**
     * 处方统计DAO
     */
    RPA00109Dao rpa00109Dao = null;

    /**
     * 处方统计初期化業務クラス的构造。
     */
    public RPA00109InitLogic() {
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
        List<RPA00109Model> prescriptionYearInfo = rpa00109Dao.selectPrescriptionYearInfo(rpa00109Condition);
        List<CodeValueRecord> prescriptionYearList_ = new ArrayList<CodeValueRecord>();
        if (prescriptionYearInfo != null && !prescriptionYearInfo.isEmpty()) {
            CodeValueRecord record_ = new CodeValueRecord();
            record_.setRecordCode("");
            record_.setRecordValue("");
            prescriptionYearList_.add(record_);
            int maxYear = prescriptionYearInfo.get(0).getMaxYear();
            if (maxYear > 0) {
                int minYear = prescriptionYearInfo.get(0).getMinYear();
                for (int i = maxYear; i >= minYear; i--) {
                    record_ = new CodeValueRecord();
                    record_.setRecordCode(StringUtils.defaultString(i));
                    record_.setRecordValue(StringUtils.defaultString(i));
                    prescriptionYearList_.add(record_);
                }
            }
        }
        inForm_.setPrescriptionYearDataSource(prescriptionYearList_);
        // 出力情報設定
        return this.getEventResult(inForm_);
    }

    /**
     * 处方统计DAO的设定
     * 
     * @param _rpa00109Dao 处方统计DAO
     */
    public void setRpa00109Dao(RPA00109Dao _rpa00109Dao) {
        this.rpa00109Dao = _rpa00109Dao;
    }

}

/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpa.biz;

import java.util.ArrayList;
import java.util.List;

import cn.com.prescription.framework.CodeValueRecord;
import cn.com.prescription.framework.biz.StandardBiz;
import cn.com.prescription.framework.biz.StandardLogic;
import cn.com.prescription.framework.common.session.UserSessionUtils;
import cn.com.prescription.framework.event.StandardEvent;
import cn.com.prescription.framework.event.StandardEventResult;
import cn.com.prescription.framework.exception.ApplicationException;
import cn.com.prescription.framework.exception.SystemException;
import cn.com.prescription.framework.util.DateUtils;
import cn.com.prescription.leshan.common.LeshanConstantsIF;
import cn.com.prescription.leshan.rpa.action.form.RPA00101Form;

/**
 * 处方添加初期化处理。
 * 新規作成
 * DATE: 2016.03.24 NAME: tyc
 */
public class RPA00101InitLogic extends StandardBiz implements StandardLogic {

    /**
     * RPA00101InitLogic的构造。
     */
    public RPA00101InitLogic() {
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
        RPA00101Form inForm_ = (RPA00101Form) _event.getForm();

        inForm_.setPrescriptionCreateDate(DateUtils.format(DateUtils.getSysDateFromApServer(),
            LeshanConstantsIF.DATE_FORMAT_YYYY_YEAR_MM_MONTH_DD_DATE));
        inForm_.setPrescriptionCreateUserName(UserSessionUtils.getUserName());

        // 处方リストを取得する
        CodeValueRecord record_ = null;
        List<CodeValueRecord> prescriptionType = new ArrayList<CodeValueRecord>();
        record_ = new CodeValueRecord();
        record_.setRecordCode("0");
        record_.setRecordValue("无");
        prescriptionType.add(record_);
        record_ = new CodeValueRecord();
        record_.setRecordCode("1");
        record_.setRecordValue("图片");
        prescriptionType.add(record_);
        record_ = new CodeValueRecord();
        record_.setRecordCode("2");
        record_.setRecordValue("文字");
        prescriptionType.add(record_);

        inForm_.setPrescriptionTypeDataSource(prescriptionType);
        inForm_.setPrescriptionType("0");

        // 性別リストを取得する
        List<CodeValueRecord> sexList_ = new ArrayList<CodeValueRecord>();
        record_ = new CodeValueRecord();
        record_.setRecordCode("1");
        record_.setRecordValue("男");
        sexList_.add(record_);
        record_ = new CodeValueRecord();
        record_.setRecordCode("2");
        record_.setRecordValue("女");
        sexList_.add(record_);
        inForm_.setSexDataSource(sexList_);
        inForm_.setSexId("1");

        // 出力情報設定
        return this.getEventResult(inForm_);
    }

}

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
import cn.com.prescription.leshan.rpa.action.form.RPA00201Form;

/**
 * 患者信息添加初期化处理。
 * 新規作成
 * DATE: 2016.03.24 NAME: tyc
 */
public class RPA00201InitLogic extends StandardBiz implements StandardLogic {

    /**
     * 患者信息設定画面 初期化業務クラス的构造。
     */
    public RPA00201InitLogic() {
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
        RPA00201Form inForm_ = (RPA00201Form) _event.getForm();

        // 性別リストを取得する
        CodeValueRecord record_ = null;
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

/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpa.biz;

import cn.com.prescription.framework.biz.StandardBiz;
import cn.com.prescription.framework.biz.StandardLogic;
import cn.com.prescription.framework.event.StandardEvent;
import cn.com.prescription.framework.event.StandardEventResult;
import cn.com.prescription.framework.exception.ApplicationException;
import cn.com.prescription.framework.exception.SystemException;
import cn.com.prescription.leshan.rpa.action.form.RPA00302Form;

/**
 * 药品一览初期化处理。
 * 新規作成
 * DATE: 2016.03.24 NAME: tyc
 */
public class RPA00302InitLogic extends StandardBiz implements StandardLogic {

    /**
     * 药品一览初期化業務クラス的构造。
     */
    public RPA00302InitLogic() {
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

        // 出力情報設定
        return this.getEventResult(inForm_);
    }

}

/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpm.biz;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.intra_mart.framework.system.exception.ApplicationException;
import org.intra_mart.framework.system.exception.SystemException;

import cn.com.prescription.framework.CodeValueRecord;
import cn.com.prescription.framework.biz.StandardBiz;
import cn.com.prescription.framework.biz.StandardLogic;
import cn.com.prescription.framework.event.StandardEvent;
import cn.com.prescription.framework.event.StandardEventResult;
import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.leshan.rpm.action.form.RPM00101Form;

/**
 * 登录画面（一般用户用） 初期化ロジック。
 * 
 * @author tyc
 */
/*
 * 新規作成
 * DATE: 2016.03.20 NAME: tyc
 */
public class RPM00101InitLogic extends StandardBiz implements StandardLogic {

    /**
     * 登录画面（一般用户用） 初期化業務クラス的构造。
     */
    public RPM00101InitLogic() {
        super();
    }

    /**
     * event処理を行う。
     * 
     * @return event処理結果
     * @param _event
     * @throws ApplicationException
     * @throws SystemException
     */
    public StandardEventResult service(StandardEvent _event) throws ApplicationException, SystemException {
        StandardEventResult eventResult_ = new StandardEventResult();
        RPM00101Form inForm_ = (RPM00101Form) _event.getForm();

        // 分店一览设定
        List<CodeValueRecord> shopList_ = new ArrayList<CodeValueRecord>();
        ResourceBundle pattern_ = ResourceBundle.getBundle("store_config");
        for (String _key : pattern_.keySet()) {
            if (!CheckUtils.isEmpty(pattern_.getString(_key))) {
                if (CheckUtils.isEmpty(inForm_.getStoreCode())) {
                    inForm_.setStoreCode(_key);
                }
                CodeValueRecord record_ = new CodeValueRecord();
                record_.setRecordCode(_key);
                record_.setRecordValue(pattern_.getString(_key));
                shopList_.add(record_);
            }
        }
        inForm_.setStoreCodeDataSource(shopList_);

        eventResult_.setForm(inForm_);
        return eventResult_;
    }
}

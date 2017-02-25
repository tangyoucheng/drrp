/*
 * Copyright(c) 2011 
 */

package cn.com.prescription.leshan.scz.biz;

import cn.com.prescription.leshan.scz.action.form.SCZ9902Form;
import cn.com.prescription.framework.biz.StandardBiz;
import cn.com.prescription.framework.biz.StandardLogic;
import cn.com.prescription.framework.common.session.UserSessionUtils;
import cn.com.prescription.framework.event.StandardEvent;
import cn.com.prescription.framework.event.StandardEventResult;

import org.intra_mart.framework.system.exception.ApplicationException;
import org.intra_mart.framework.system.exception.SystemException;

/**
 * 画面ロック機能 初期表示ロジック。
 * 
 * @author t.d.m
 */
/*
 * 新規作成
 * DATE: 2010.05.26 NAME: t.d.m
 */
public class SCZ9902InitLogic extends StandardBiz implements StandardLogic {

    /**
     * 画面ロック機能 初期表示ロジック的构造。
     */
    public SCZ9902InitLogic() {
        super();
    }

    /**
     * 業務処理を行う。
     * 
     * @param _event event
     * @return event処理結果
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    public StandardEventResult service(StandardEvent _event) throws ApplicationException, SystemException {

        StandardEventResult eventResult = new StandardEventResult();
        SCZ9902Form inForm = (SCZ9902Form) _event.getForm();

        inForm.setUserName(UserSessionUtils.getUserName());

        eventResult.setForm(inForm);

        // 出力情報設定
        return eventResult;
    }

}

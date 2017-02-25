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
import cn.com.prescription.framework.exception.ApplicationException;
import cn.com.prescription.framework.exception.SystemException;
import cn.com.prescription.framework.message.MessageUtils;
import cn.com.prescription.framework.util.AclUtils;
import cn.com.prescription.framework.util.CheckUtils;

/**
 * 画面ロック機能 確認ロジック。
 * 
 * @author t.d.m
 */
/*
 * 新規作成
 * DATE: 2010.05.26 NAME: t.d.m
 */
public class SCZ9902ConfirmLogic extends StandardBiz implements StandardLogic {

    /**
     * 用户基本テーブルDAO
     */
//    private JktUserKihonDao userKihonDao = null;

    /**
     * 画面ロック機能 確認ロジック的构造。
     */
    public SCZ9902ConfirmLogic() {
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
        SCZ9902Form inForm = null;
//        JktUserKihonModel result = null;

        // 入力内容取得
        inForm = (SCZ9902Form) _event.getForm();

        // 最新教職員マスタ情報的取得
//        JktUserKihonCondition condition = new JktUserKihonCondition();
//        condition.setUserId(UserSessionUtils.getUserId());
//        result = userKihonDao.select(condition);
//
//        // 画面：密码≠取得処理１で取得した密码の場合、错误とする
//        try {
//            if (!CheckUtils.isEqual(AclUtils.decryptPassword(result.getPassword()), inForm.getPassword())) {
//                inForm.setMessage(MessageUtils.getMessage("W20003"));
//            } else {
//                inForm.setMessage("");
//            }
//        } catch (SystemException e) {
//            throw new SystemException(e);
//        }

        eventResult.setForm(inForm);

        // 出力情報設定
        return eventResult;
    }

    /**
     * 用户基本テーブルDAO的设定。
     * 
     * @param userKihonDao 用户基本テーブルDAO
     */
//    public void setUserKihonDao(JktUserKihonDao userKihonDao) {
//        this.userKihonDao = userKihonDao;
//    }

}

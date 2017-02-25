/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.leshan.scz.biz;

import cn.com.prescription.leshan.scz.action.form.SCZ990101Form;
import cn.com.prescription.framework.biz.StandardBiz;
import cn.com.prescription.framework.biz.StandardLogic;
import cn.com.prescription.framework.event.StandardEvent;
import cn.com.prescription.framework.event.StandardEventResult;

import org.intra_mart.framework.system.exception.ApplicationException;
import org.intra_mart.framework.system.exception.SystemException;

/**
 * SCZ990101 门户画面 action。
 * 
 * @author kourei
 */
/*
 * 新規作成
 * DATE: 2010.03.09 NAME: kourei
 */
public class SCZ990101LoginOutLogic extends StandardBiz implements StandardLogic {

    /**
     * 登录画面 登录業務クラス的构造。
     */
    public SCZ990101LoginOutLogic() {
        super();
    }

    /**
     * event処理を行う。
     * 
     * @param _event event
     * @return event処理結果
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    public StandardEventResult service(StandardEvent _event) throws ApplicationException, SystemException {

        // 初期化
        SCZ990101Form form = (SCZ990101Form) _event.getForm();
        // try {
        // JktUserKihonDao dao_ = (JktUserKihonDao) ServiceUtils.getDAO(JktUserKihonDao.class);
        //
        // JktUserKihonCondition _userKihonCondition = new JktUserKihonCondition();
        // _userKihonCondition.setIpAddr(form.getIpAddr());
        // _userKihonCondition.setDelFlg(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        // JktUserKihonModel record = dao_.selectForUpdate(_userKihonCondition);
        //
        // if (record != null && CheckUtils.isEqual(record.getIpAddr(), form.getIpAddr())) {
        // // 用户基本の登录失敗回数を更新する。
        // record.setIpAddr(null);
        // record.setSessionId(null);
        // dao_.update(record);
        // }
        //
        // } catch (DbExclusiveException e) {
        // // ほかの人を利用中、ロック不可の場合
        // this.addErrorMessage(MessageUtils.getMessage("E00005"));
        // this.checkErrorEnd();
        // }

        // 画面情報的设定。
        return this.getEventResult(form);
    }

}

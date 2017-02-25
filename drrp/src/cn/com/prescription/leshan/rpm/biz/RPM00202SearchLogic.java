/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpm.biz;

import java.util.List;

import cn.com.prescription.framework.biz.StandardBiz;
import cn.com.prescription.framework.biz.StandardLogic;
import cn.com.prescription.framework.event.StandardEvent;
import cn.com.prescription.framework.event.StandardEventResult;
import cn.com.prescription.framework.exception.ApplicationException;
import cn.com.prescription.framework.exception.SystemException;
import cn.com.prescription.framework.util.StringUtils;
import cn.com.prescription.leshan.common.LeshanConstantsIF;
import cn.com.prescription.leshan.rpm.action.form.RPM0020201Dto;
import cn.com.prescription.leshan.rpm.action.form.RPM00202Form;
import cn.com.prescription.leshan.rpm.data.RPM00202Dao;
import cn.com.prescription.leshan.rpm.data.condition.RPM00202Condition;
import cn.com.prescription.leshan.rpm.data.model.RPM00202Model;

/**
 * 用户一览检索处理。
 * 新規作成
 * DATE: 2016.03.24 NAME: tyc
 */
public class RPM00202SearchLogic extends StandardBiz implements StandardLogic {

    /**
     * 用户一览DAO
     */
    RPM00202Dao rpm00202Dao = null;

    /**
     * 用户情報設定画面（一般用户用） 初期化業務クラス的构造。
     */
    public RPM00202SearchLogic() {
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
        RPM00202Form inForm_ = (RPM00202Form) _event.getForm();

        // 用户信息取得
        RPM00202Condition rpm00202Condition = new RPM00202Condition();
        // 用户ID
        rpm00202Condition.setUserId(StringUtils.getLikeParameter(inForm_.getUserId()));
        // 用户名
        rpm00202Condition.setUserName(StringUtils.getLikeParameter(inForm_.getUserName()));
        // 消除标识
        rpm00202Condition.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);

        long rolecount_ = rpm00202Dao.selectUserInfoCount(rpm00202Condition);
        // 前回検索結果クリア
        inForm_.getSubForm1().clear();
        // 件数チェック処理
        this.checkCount(inForm_, rolecount_);
        // ソートキー ASC
        // roleCondition.addPageSortField("RPM_ROLE.SORT_KEY", LeshanConstantsIF.SORT_SEQ_ASC);
        rpm00202Condition.setPageStartRowNo(inForm_.getPageStartRowNo() + 1);
        List<RPM00202Model> userModelList_ = rpm00202Dao.selectUserInfo(rpm00202Condition);
        for (RPM00202Model userModel : userModelList_) {
            RPM0020201Dto rpm0020201Dto = new RPM0020201Dto();
            rpm0020201Dto.setUserId(userModel.getUserId());
            rpm0020201Dto.setUserName(userModel.getUserName());
            rpm0020201Dto.setNotes(userModel.getNotes());
            inForm_.getSubForm1().add(rpm0020201Dto);
        }

        // 出力情報設定
        return this.getEventResult(inForm_);
    }

    /**
     * 用户一览DAO的设定
     * 
     * @param _rpm00202Dao 用户一览DAO
     */
    public void setRpm00202Dao(RPM00202Dao _rpm00202Dao) {
        this.rpm00202Dao = _rpm00202Dao;
    }

}

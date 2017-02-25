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
import cn.com.prescription.leshan.common.data.RpmRole01Dao;
import cn.com.prescription.leshan.common.data.condition.RpmRoleCondition;
import cn.com.prescription.leshan.common.data.model.RpmRoleModel;
import cn.com.prescription.leshan.rpm.action.form.RPM0030201Dto;
import cn.com.prescription.leshan.rpm.action.form.RPM00302Form;

/**
 * 角色一览检索处理。
 * 新規作成
 * DATE: 2016.03.24 NAME: tyc
 */
public class RPM00302SearchLogic extends StandardBiz implements StandardLogic {

    /**
     * 角色DAO
     */
    RpmRole01Dao rpmRole01Dao = null;

    /**
     * 用户情報設定画面（一般用户用） 初期化業務クラス的构造。
     */
    public RPM00302SearchLogic() {
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
        RPM00302Form inForm_ = (RPM00302Form) _event.getForm();

        // (2)役割リストを取得する
        RpmRoleCondition roleCondition = new RpmRoleCondition();
        // 角色ID
        roleCondition.setRoleId(StringUtils.getLikeParameter(inForm_.getRoleId()));
        // 角色名
        roleCondition.setRoleName(StringUtils.getLikeParameter(inForm_.getRoleName()));
        // 消除标识
        roleCondition.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);

        long rolecount_ = rpmRole01Dao.selectRoleListCount(roleCondition);
        // 前回検索結果クリア
        inForm_.getSubForm1().clear();
        // 件数チェック処理
        this.checkCount(inForm_, rolecount_);
        // ソートキー ASC
        // roleCondition.addPageSortField("RPM_ROLE.SORT_KEY", LeshanConstantsIF.SORT_SEQ_ASC);
        roleCondition.setPageStartRowNo(inForm_.getPageStartRowNo() + 1);
        List<RpmRoleModel> roleModelList_ = rpmRole01Dao.selectRoleList(roleCondition);
        for (RpmRoleModel rpmRoleModel : roleModelList_) {
            RPM0030201Dto rpm0030201Dto = new RPM0030201Dto();
            rpm0030201Dto.setRoleId(rpmRoleModel.getRoleId());
            rpm0030201Dto.setRoleName(rpmRoleModel.getRoleName());
            rpm0030201Dto.setNotes(rpmRoleModel.getNotes());
            inForm_.getSubForm1().add(rpm0030201Dto);
        }

        // 出力情報設定
        return this.getEventResult(inForm_);
    }

    /**
     * 角色DAOを設定する。
     * 
     * @param _rpmRole01Dao 角色DAO
     */
    public void setRpmRole01Dao(RpmRole01Dao _rpmRole01Dao) {
        this.rpmRole01Dao = _rpmRole01Dao;
    }

}

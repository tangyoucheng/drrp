/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpm.biz;

import cn.com.prescription.framework.biz.ActionInfo;
import cn.com.prescription.framework.biz.StandardBiz;
import cn.com.prescription.framework.biz.StandardLogic;
import cn.com.prescription.framework.common.session.UserSessionUtils;
import cn.com.prescription.framework.event.StandardEvent;
import cn.com.prescription.framework.event.StandardEventResult;
import cn.com.prescription.framework.exception.ApplicationException;
import cn.com.prescription.framework.exception.SystemException;
import cn.com.prescription.framework.message.MessageUtils;
import cn.com.prescription.framework.util.TimestampUtils;
import cn.com.prescription.leshan.common.data.RpmRoleMenu01Dao;
import cn.com.prescription.leshan.common.data.RpmRoleMenuDao;
import cn.com.prescription.leshan.common.data.condition.RpmRoleMenuCondition;
import cn.com.prescription.leshan.common.data.model.RpmRoleMenuModel;
import cn.com.prescription.leshan.rpm.action.RPM00401Action;
import cn.com.prescription.leshan.rpm.action.form.RPM0040101Dto;
import cn.com.prescription.leshan.rpm.action.form.RPM00401Form;
import cn.com.prescription.leshan.rpm.data.RPM00401Dao;

/**
 * 用户菜单权限设定初期化处理。
 * 新規作成
 * DATE: 2016.03.24 NAME: tyc
 */
public class RPM00401EntryLogic extends StandardBiz implements StandardLogic {

    /**
     * 用户菜单权限设定DAO
     */
    RpmRoleMenuDao rpmRoleMenuDao = null;
    /**
     * 用户菜单权限设定DAO
     */
    RpmRoleMenu01Dao rpmRoleMenu01Dao = null;

    /**
     * 用户菜单权限设定DAO
     */
    RPM00401Dao rpm00401Dao = null;

    /**
     * 用户菜单权限设定画面 初期化業務クラス的构造。
     */
    public RPM00401EntryLogic() {
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
        RPM00401Form inForm_ = (RPM00401Form) _event.getForm();
        // [画面：設定情報一覧]の件数分で繰り返して、以下の処理を行う。
        for (int i = 0; i < inForm_.getSubForm1().size(); i++) {

            RPM0040101Dto rpm0040101Dto_ = inForm_.getSubForm1().get(i);

            // [画面：許可]が選択した場合、以下の処理を行う
            if (rpm0040101Dto_.isCheck()) {

                // 設定情報マスタに該当情報の更新Z
                RpmRoleMenuModel model_ = new RpmRoleMenuModel();
                // 役割権限情報マスタ．役割番号
                model_.setRoleId(inForm_.getRoleId());
                // 役割権限情報マスタ．機能ＩＤ
                model_.setMenuId(rpm0040101Dto_.getHidMenuId());
                // 最終更新用户ID
                model_.setLastUpdateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
                // 最終更新用户名
                model_.setLastUpdateUserName(UserSessionUtils.getUserSessionInfo().getUserName());
                // 最終更新日
                model_.setLastUpdateDate(TimestampUtils.getSysTimestamp());

                int count_ = rpmRoleMenuDao.update(model_);
                // (1)の更新件数が０件場合、新規登録処理を行う
                if (count_ == 0) {
                    int countUpdate_ = rpmRoleMenuDao.insert(model_);

                    if (countUpdate_ == 0) {
                        this.addErrorMessage(MessageUtils.getMessage("E00047", "登录"));
                        this.errorEnd();
                    }
                }
            } else {
                RpmRoleMenuCondition _condition = new RpmRoleMenuCondition();
                _condition.setRoleId(inForm_.getRoleId());
                _condition.setMenuId(rpm0040101Dto_.getHidMenuId());
                rpmRoleMenu01Dao.deleteRoleMenu(_condition);
            }
        }

        // 完了画面を表示する。
        this.connectCompleteDialog(MessageUtils.getMessage("I00001", "登录"),
            new ActionInfo(RPM00401Action.class, "doInit"));

        // 出力情報設定
        return this.getEventResult(inForm_);
    }

    /**
     * 用户菜单权限设定DAO的设定
     * 
     * @param _rpmRoleMenuDao 用户菜单权限设定DAO
     */
    public void setRpmRoleMenuDao(RpmRoleMenuDao _rpmRoleMenuDao) {
        this.rpmRoleMenuDao = _rpmRoleMenuDao;
    }

    /**
     * 用户菜单权限设定DAO的设定
     * 
     * @param _rpmRoleMenu01Dao 用户菜单权限设定DAO
     */
    public void setRpmRoleMenu01Dao(RpmRoleMenu01Dao _rpmRoleMenu01Dao) {
        this.rpmRoleMenu01Dao = _rpmRoleMenu01Dao;
    }

    /**
     * 用户菜单权限设定DAO的设定
     * 
     * @param _rpm00401Dao 用户菜单权限设定DAO
     */
    public void setRpm00401Dao(RPM00401Dao _rpm00401Dao) {
        this.rpm00401Dao = _rpm00401Dao;
    }

}

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
import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.framework.util.DateUtils;
import cn.com.prescription.framework.util.StringUtils;
import cn.com.prescription.framework.util.TimestampUtils;
import cn.com.prescription.leshan.common.LeshanConstantsIF;
import cn.com.prescription.leshan.common.data.RpmRoleDao;
import cn.com.prescription.leshan.common.data.condition.RpmRoleCondition;
import cn.com.prescription.leshan.common.data.model.RpmRoleModel;
import cn.com.prescription.leshan.rpm.action.RPM00302Action;
import cn.com.prescription.leshan.rpm.action.form.RPM00303Form;

/**
 * 角色更新処理 ロジック
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11. NAME: fsb
 */
public class RPM00303UpdateLogic extends StandardBiz implements StandardLogic {

    /**
     * 角色 DAO
     */
    private RpmRoleDao rpmRoleDao = null;

    /**
     * 用户情報設定ロジック的构造。
     */
    public RPM00303UpdateLogic() {
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

        // event処理結果
        RPM00303Form inForm_ = (RPM00303Form) _event.getForm();

        // 排他情報：用户基本情報
        RpmRoleModel roleModel_ = roleCheck(inForm_);

        // 用户基本テーブルの登録
        updateRole(inForm_, roleModel_);

        // 完了画面を表示する。
        this.connectCompleteDialog(MessageUtils.getMessage("I00001", "登録"),
            new ActionInfo(RPM00302Action.class, "doBack"));
        // 出力情報設定
        return this.getEventResult(inForm_);

    }

    /**
     * roleのデータを取得し、ロック（for update）を行う
     * 
     * @param _inForm SCHM00301Form
     * @return userKihonModel_
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    private RpmRoleModel roleCheck(RPM00303Form _inForm) throws ApplicationException, SystemException {

        RpmRoleCondition userRoleCondition_ = new RpmRoleCondition();
        // 用户ID
        userRoleCondition_.setRoleId(_inForm.getRoleId());
        // 消除标识＝ [定数：消除标识．有効レコード]
        userRoleCondition_.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        // [排他情報：role情報]
        RpmRoleModel roleModel_ = rpmRoleDao.selectForUpdate(userRoleCondition_);

        // [排他情報：用户基本情報]の取得件数 ＝ 0 の場合
        if (roleModel_ == null) {
            // メッセージID【E00004】（パラメータ1："削除"、パラメータ２："登録"）
            this.addErrorMessage(MessageUtils.getMessage("E00004", "削除", "登録"));
            this.errorEnd();
        }
        // [排他情報：用户基本情報．最終更新日]≠[画面：最終更新日]の場合
        if (roleModel_ != null && !CheckUtils.isEqual(_inForm.getLastUpdateDate(),
            TimestampUtils.formatUpd(roleModel_.getLastUpdateDate()))) {
            // メッセージID【E00006】
            this.addErrorMessage(MessageUtils.getMessage("E00006", "更新", "登録", roleModel_.getLastUpdateUserName(),
                DateUtils.format(roleModel_.getLastUpdateDate(), LeshanConstantsIF.DATE_FORMAT_YYYY_MM_DD_HH_MM)));
            this.errorEnd();
        }
        return roleModel_;
    }

    /**
     * 用户基本テーブルの登録を行う
     * 
     * @param _inForm SCHM00301Form
     * @param _roleModel RpmRoleModel
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    private void updateRole(RPM00303Form _inForm, RpmRoleModel _roleModel)
                    throws ApplicationException, SystemException {

        // role名
        _roleModel.setRoleName(_inForm.getRoleName());
        // 備考
        _roleModel.setNotes(StringUtils.trimToNull(_inForm.getNotes()));
        // 消除标识
        _roleModel.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        // 最終更新用户ID
        _roleModel.setLastUpdateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
        // 最終更新用户名
        _roleModel.setLastUpdateUserName(UserSessionUtils.getUserSessionInfo().getUserName());
        // 最終更新日
        _roleModel.setLastUpdateDate(TimestampUtils.getSysTimestamp());

        int userRoleCount_ = rpmRoleDao.update(_roleModel);
        // 件数 ＝ 0 の場合
        if (userRoleCount_ == 0) {
            this.addErrorMessage(MessageUtils.getMessage("E00047", "登録"));
            this.errorEnd();
        }
    }

    /**
     * 角色 DAO的取得
     * 
     * @return 角色 DAO
     */
    public RpmRoleDao getRpmRoleDao() {
        return rpmRoleDao;
    }

    /**
     * 角色 DAO的设定
     * 
     * @param _rpmRoleDao 角色 DAO
     */
    public void setRpmRoleDao(RpmRoleDao _rpmRoleDao) {
        this.rpmRoleDao = _rpmRoleDao;
    }

}
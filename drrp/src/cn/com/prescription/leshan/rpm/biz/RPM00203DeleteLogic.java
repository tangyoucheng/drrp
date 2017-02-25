/*
* Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpm.biz;

import cn.com.prescription.framework.biz.ActionInfo;
import cn.com.prescription.framework.biz.StandardBiz;
import cn.com.prescription.framework.biz.StandardLogic;
import cn.com.prescription.framework.event.StandardEvent;
import cn.com.prescription.framework.event.StandardEventResult;
import cn.com.prescription.framework.exception.ApplicationException;
import cn.com.prescription.framework.exception.SystemException;
import cn.com.prescription.framework.message.MessageUtils;
import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.framework.util.DateUtils;
import cn.com.prescription.framework.util.TimestampUtils;
import cn.com.prescription.leshan.common.LeshanConstantsIF;
import cn.com.prescription.leshan.common.data.RpmUserDao;
import cn.com.prescription.leshan.common.data.RpmUserProfileDao;
import cn.com.prescription.leshan.common.data.RpmUserRoleDao;
import cn.com.prescription.leshan.common.data.condition.RpmUserCondition;
import cn.com.prescription.leshan.common.data.condition.RpmUserProfileCondition;
import cn.com.prescription.leshan.common.data.condition.RpmUserRoleCondition;
import cn.com.prescription.leshan.common.data.model.RpmUserModel;
import cn.com.prescription.leshan.common.data.model.RpmUserProfileModel;
import cn.com.prescription.leshan.common.data.model.RpmUserRoleModel;
import cn.com.prescription.leshan.rpm.action.RPM00202Action;
import cn.com.prescription.leshan.rpm.action.form.RPM00203Form;

/**
 * 用户情報設定 削除処理 ロジック
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPM00203DeleteLogic extends StandardBiz implements StandardLogic {

    /**
     * 用户基本テーブル DAO
     */
    RpmUserDao rpmUserDao = null;

    /**
     * 用户プロファイルテーブル DAO
     */
    RpmUserProfileDao userProfileDao = null;

    /**
     * 用户ロール権限テーブル DAO
     */
    RpmUserRoleDao rptUserRoleDao = null;

    /**
     * 用户情報設定ロジック的构造。
     */
    public RPM00203DeleteLogic() {
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

        // 入力情報を取得する
        RPM00203Form inForm_ = (RPM00203Form) _event.getForm();

        // 排他情報：用户基本情報
        RpmUserModel userKihonModel_ = userKihonCheck(inForm_);
        // 排他情報：用户プロ情報
        RpmUserProfileModel userProfileModel_ = userProfileCheck(inForm_);
        // 排他情報：用户ロール権限情報
        RpmUserRoleModel userRollKengenModel_ = userRollKengenCheck(inForm_);

        // 用户基本テーブルの削除
        updateUser(userKihonModel_);
        // 用户プロファイルテーブルの削除
        updateUserProfile(userProfileModel_);
        // 用户ロール権限テーブルの削除
        deleteUserRoll(userRollKengenModel_);

        // 完了画面を表示する。
        this.connectCompleteDialog(MessageUtils.getMessage("I00001", "删除"),
            new ActionInfo(RPM00202Action.class, "doBack"));

        // 出力情報設定
        return this.getEventResult(inForm_);

    }

    /**
     * 用户基本テーブルのデータを取得し、ロック（for update）を行う
     * 
     * @param _inForm SCM0301Form
     * @return userKihonModel_
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    private RpmUserModel userKihonCheck(RPM00203Form _inForm) throws ApplicationException, SystemException {

        // 用户基本テーブルをロックする
        RpmUserCondition userKihonCondition_ = new RpmUserCondition();
        // 用户ID
        userKihonCondition_.setUserId(_inForm.getUserId());
        // 消除标识
        userKihonCondition_.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        RpmUserModel userKihonModel_ = rpmUserDao.selectForUpdate(userKihonCondition_);

        // [排他情報：用户基本情報]の取得件数 ＝ 0 の場合
        if (userKihonModel_ == null) {
            // メッセージID【E00004】（パラメータ1："削除"、パラメータ２："削除"）
            this.addErrorMessage(MessageUtils.getMessage("E00004", "削除", "削除"));
            this.errorEnd();
        }
        // [排他情報：用户基本情報．最終更新日]≠[画面：用户基本最終更新日]の場合
        else if (!CheckUtils.isEqual(TimestampUtils.formatUpd(userKihonModel_.getLastUpdateDate()),
            _inForm.getLastUpdateDateUser())) {
            // メッセージID【E00006】
            this.addErrorMessage(MessageUtils.getMessage("E00006", "更新", "削除", userKihonModel_.getLastUpdateUserName(),
                DateUtils.format(userKihonModel_.getLastUpdateDate(), LeshanConstantsIF.DATE_FORMAT_YYYY_MM_DD_HH_MM)));
            this.errorEnd();
        }
        return userKihonModel_;
    }

    /**
     * 用户プロファイルテーブルのデータを取得し、ロック（for update）を行う
     * 
     * @param _inForm SCM0301Form
     * @return userProfileModel
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    private RpmUserProfileModel userProfileCheck(RPM00203Form _inForm) throws ApplicationException, SystemException {

        // 排他情報：用户プロ情報
        RpmUserProfileCondition userProfileCondition_ = new RpmUserProfileCondition();
        // 用户ID
        userProfileCondition_.setUserId(_inForm.getUserId());
        // 消除标识
        userProfileCondition_.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        RpmUserProfileModel userProfileModel_ = userProfileDao.selectForUpdate(userProfileCondition_);

        // [排他情報：用户プロ情報]の取得件数 ＝ 0 の場合
        if (userProfileModel_ == null) {
            // メッセージID【E00004】（パラメータ1："削除"、パラメータ２："削除"）
            this.addErrorMessage(MessageUtils.getMessage("E00004", "削除", "削除"));
            this.errorEnd();
        }
        // [排他情報：用户プロ情報．最終更新日]≠[画面：用户プロファイル最終更新日]の場合
        else if (!CheckUtils.isEqual(TimestampUtils.formatUpd(userProfileModel_.getLastUpdateDate()),
            _inForm.getLastUpdateDateUserProfile())) {
            // メッセージID【E00006】
            this.addErrorMessage(
                MessageUtils.getMessage("E00006", "更新", "削除", userProfileModel_.getLastUpdateUserName(), DateUtils
                    .format(userProfileModel_.getLastUpdateDate(), LeshanConstantsIF.DATE_FORMAT_YYYY_MM_DD_HH_MM)));
            this.errorEnd();
        }
        return userProfileModel_;
    }

    /**
     * 用户ロール権限テーブルのデータを取得し、ロック（for update）を行う
     * 
     * @param _inForm SCM0301Form
     * @return userRollKengenModel
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    private RpmUserRoleModel userRollKengenCheck(RPM00203Form _inForm) throws ApplicationException, SystemException {

        // 排他情報：用户ロール権限情報
        RpmUserRoleCondition userRollKengenCondition_ = new RpmUserRoleCondition();
        // 用户ID
        userRollKengenCondition_.setUserId(_inForm.getUserId());
        RpmUserRoleModel userRollKengenModel_ = rptUserRoleDao.selectForUpdate(userRollKengenCondition_);
        // [排他情報：用户ロール権限情報]の取得件数 ＝ 0 の場合
        if (userRollKengenModel_ == null) {
            // メッセージID【E00004】（パラメータ1："削除"、パラメータ２："削除"）
            // this.addErrorMessage(MessageUtils.getMessage("E00004", "削除", "削除"));
            // this.errorEnd();
        }
        // [排他情報：用户ロール権限情報．最終更新日]≠[画面：用户ロール権限最終更新日]の場合
        else if (!CheckUtils.isEqual(TimestampUtils.formatUpd(userRollKengenModel_.getLastUpdateDate()),
            _inForm.getLastUpdateDateUserRole())) {
            // メッセージID【E00006】
            this.addErrorMessage(
                MessageUtils.getMessage("E00006", "更新", "削除", userRollKengenModel_.getLastUpdateUserName(), DateUtils
                    .format(userRollKengenModel_.getLastUpdateDate(), LeshanConstantsIF.DATE_FORMAT_YYYY_MM_DD_HH_MM)));
            this.errorEnd();
        }
        return userRollKengenModel_;
    }

    /**
     * 用户基本テーブルの更新を行う
     * 
     * @param _updateUserKihonModel RpmUserModel
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    private void updateUser(RpmUserModel _updateUserKihonModel) throws ApplicationException, SystemException {

        int kihonCount_ = rpmUserDao.delete(_updateUserKihonModel);
        // 件数 ＝ 0 の場合
        if (kihonCount_ == 0) {
            this.addErrorMessage(MessageUtils.getMessage("E00047", "削除"));
            this.errorEnd();
        }
    }

    /**
     * 用户プロファイルテーブルの更新を行う
     * 
     * @param _updateUserProfileModel RptUserProfileModel
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    private void updateUserProfile(RpmUserProfileModel _updateUserProfileModel)
                    throws ApplicationException, SystemException {

        int profileCount_ = userProfileDao.delete(_updateUserProfileModel);
        // 件数 ＝ 0 の場合
        if (profileCount_ == 0) {
            this.addErrorMessage(MessageUtils.getMessage("E00047", "削除"));
            this.errorEnd();
        }
    }

    /**
     * 用户ロール権限テーブルの更新を行う
     * 
     * @param _updateUserRollKengenModel RptUserRoleModel
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    private void deleteUserRoll(RpmUserRoleModel _updateUserRollKengenModel)
                    throws ApplicationException, SystemException {

        int rollKengenCount_ = rptUserRoleDao.delete(_updateUserRollKengenModel);
        // 件数 ＝ 0 の場合
        if (rollKengenCount_ == 0) {
            this.addErrorMessage(MessageUtils.getMessage("E00047", "削除"));
            this.errorEnd();
        }
    }

    /**
     * 用户基本テーブル DAOを設定する。
     * 
     * @param _rpmUserDao 用户基本テーブル DAO
     */
    public void setRpmUserDao(RpmUserDao _rpmUserDao) {
        this.rpmUserDao = _rpmUserDao;
    }

    /**
     * 用户プロファイルテーブル DAOを設定する。
     * 
     * @param _userProfileDao 用户プロファイルテーブル DAO
     */
    public void setUserProfileDao(RpmUserProfileDao _userProfileDao) {
        this.userProfileDao = _userProfileDao;
    }

    /**
     * 用户ロール権限テーブル DAOを設定する。
     * 
     * @param _rptUserRoleDao 用户ロール権限テーブル DAO
     */
    public void setRptUserRoleDao(RpmUserRoleDao _rptUserRoleDao) {
        this.rptUserRoleDao = _rptUserRoleDao;
    }

}

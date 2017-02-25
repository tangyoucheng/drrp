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
import cn.com.prescription.framework.util.ReflectUtils;
import cn.com.prescription.framework.util.StringUtils;
import cn.com.prescription.framework.util.TimestampUtils;
import cn.com.prescription.leshan.common.LeshanConstantsIF;
import cn.com.prescription.leshan.common.data.RpmUserDao;
import cn.com.prescription.leshan.common.data.RpmUserProfileDao;
import cn.com.prescription.leshan.common.data.RpmUserRoleDao;
import cn.com.prescription.leshan.common.data.condition.RpmUserCondition;
import cn.com.prescription.leshan.common.data.condition.RpmUserProfileCondition;
import cn.com.prescription.leshan.common.data.model.RpmUserModel;
import cn.com.prescription.leshan.common.data.model.RpmUserProfileModel;
import cn.com.prescription.leshan.common.data.model.RpmUserRoleModel;
import cn.com.prescription.leshan.rpm.action.RPM00201Action;
import cn.com.prescription.leshan.rpm.action.form.RPM00201Form;

/**
 * 用户情報設定 登録処理 ロジック
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11. NAME: fsb
 */
public class RPM00201EntryLogic extends StandardBiz implements StandardLogic {

    /**
     * 用户基本テーブル DAO
     */
    private RpmUserDao rpmUserDao = null;

    /**
     * 用户プロファイルテーブル DAO
     */
    private RpmUserProfileDao userProfileDao = null;

    /**
     * 用户ロール権限テーブル DAO
     */
    private RpmUserRoleDao rpmUserRoleDao = null;

    /**
     * 日付
     */
    private static final String DATE_MAX = "99991231";

    /**
     * 用户情報設定ロジック的构造。
     */
    public RPM00201EntryLogic() {
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
        RPM00201Form inForm_ = (RPM00201Form) _event.getForm();

        // 排他情報：用户基本情報
        RpmUserModel userKihonModel_ = userKihonCheck(inForm_);
        // 排他情報：用户プロ情報
        RpmUserProfileModel userProfileModel_ = userProfileCheck(inForm_);

        // 用户基本テーブルの登録
        insertUserKihon(inForm_);
        // 用户プロファイルテーブルの登録
        insertUserProfile(inForm_);

        if (!CheckUtils.isEmpty(inForm_.getRoleId())) {
            insertUserRollKengen(inForm_);
        }

        // 完了画面を表示する。
        this.connectCompleteDialog(MessageUtils.getMessage("I00001", "登録"),
            new ActionInfo(RPM00201Action.class, "doInit"));
        // 出力情報設定
        return this.getEventResult(inForm_);

    }

    /**
     * 用户基本テーブルのデータを取得し、ロック（for update）を行う
     * 
     * @param _inForm SCHM00301Form
     * @return userKihonModel_
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    private RpmUserModel userKihonCheck(RPM00201Form _inForm) throws ApplicationException, SystemException {

        RpmUserCondition userKihonCondition_ = new RpmUserCondition();
        // 用户ID
        userKihonCondition_.setUserId(_inForm.getUserId());
        // 消除标识＝ [定数：消除标识．有効レコード]
        userKihonCondition_.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        // [排他情報：用户基本情報]
        RpmUserModel userKihonModel_ = rpmUserDao.selectForUpdate(userKihonCondition_);

        // // 「削除」ボタンが表示された場合（更新の場合）、以下のチェックを行う
        // if (CheckUtils.isEqual(_inForm.getFormKbn(),
        // LeshanConstantsIF.NYUURYOKU_GAMEN_HYOJI_KUBUN_UPDATE_SAKUJO_KAKUNIN)) {
        // // [排他情報：用户基本情報]の取得件数 ＝ 0 の場合
        // if (userKihonModel_ == null) {
        // // メッセージID【E00004】（パラメータ1："削除"、パラメータ２："登録"）
        // this.addErrorMessage(MessageUtils.getMessage("E00004", "削除", "登録"));
        // this.errorEnd();
        // }
        // // [排他情報：用户基本情報．最終更新日]≠[画面：最終更新日]の場合
        // if (userKihonModel_ != null
        // && !CheckUtils.isEqual(_inForm.getHidUserKihonLastUpdateDate(),
        // userKihonModel_.getLastUpdateDate())) {
        // // メッセージID【E00006】
        // this.addErrorMessage(MessageUtils.getMessage("E00006", "更新", "登録",
        // userKihonModel_.getLastUpdateUserName(),
        // DateUtils.format(userKihonModel_.getLastUpdateDate(), LeshanConstantsIF.DATE_FORMAT_YYYY_MM_DD_HH_MM)));
        // this.errorEnd();
        // }
        // }
        // // 「削除」ボタンが表示されなかった場合（新規の場合）、以下のチェックを行う
        // if (CheckUtils.isEqual(_inForm.getFormKbn(), LeshanConstantsIF.NYUURYOKU_GAMEN_HYOJI_KUBUN_NYUURYOKU)) {
        // userKihonCondition_ = new JktUserKihonCondition();
        // // 用户ID
        // userKihonCondition_.setUserId(_inForm.getUserId());
        // // [排他情報：用户基本情報]
        // userKihonModel_ = rpmUserDao.selectForUpdate(userKihonCondition_);
        //
        // [情報：用户基本情報（新規登録用）] の取得件数 ＞ 0 の場合
        if (userKihonModel_ != null) {
            // メッセージID【E20054】
            this.addErrorMessage(MessageUtils.getMessage("E20054"));
            this.errorEnd();
        }
        // }
        return userKihonModel_;
    }

    /**
     * 用户プロファイルテーブルのデータを取得し、ロック（for update）を行う
     * 
     * @param _inForm SCHM00301Form
     * @return userProfileModel_
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    private RpmUserProfileModel userProfileCheck(RPM00201Form _inForm) throws ApplicationException, SystemException {

        // 排他情報：用户プロ情報
        RpmUserProfileCondition userProfileCondition_ = new RpmUserProfileCondition();
        // 用户ID
        userProfileCondition_.setUserId(_inForm.getUserId());
        // 消除标识＝ [定数：消除标识．有効レコード]
        userProfileCondition_.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        // [排他情報：用户プロファイル情報]
        RpmUserProfileModel userProfileModel_ = userProfileDao.selectForUpdate(userProfileCondition_);
        // 「削除」ボタンが表示された場合（更新の場合）、以下のチェックを行う
        // if (CheckUtils.isEqual(_inForm.getFormKbn(),
        // LeshanConstantsIF.NYUURYOKU_GAMEN_HYOJI_KUBUN_UPDATE_SAKUJO_KAKUNIN)) {
        // // [排他情報：用户プロ情報]の取得件数 ＝ 0 の場合
        // if (userProfileModel_ == null) {
        // // メッセージID【E00004】（パラメータ1："削除"、パラメータ２："登録"）
        // this.addErrorMessage(MessageUtils.getMessage("E00004", "削除", "登録"));
        // this.errorEnd();
        // }
        // // [排他情報：用户プロ情報．最終更新日]≠[画面：最終更新日]の場合
        // if (userProfileModel_ != null
        // && !CheckUtils.isEqual(_inForm.getHidUserProfileLastUpdateDate(),
        // userProfileModel_.getLastUpdateDate())) {
        // // メッセージID【E00006】
        // this.addErrorMessage(MessageUtils.getMessage("E00006", "更新", "登録", userProfileModel_
        // .getLastUpdateUserName(), DateUtils.format(userProfileModel_.getLastUpdateDate(),
        // LeshanConstantsIF.DATE_FORMAT_YYYY_MM_DD_HH_MM)));
        // this.errorEnd();
        // }
        // }
        // // 「削除」ボタンが表示されなかった場合（新規の場合）、以下のチェックを行う
        // if (CheckUtils.isEqual(_inForm.getFormKbn(), LeshanConstantsIF.NYUURYOKU_GAMEN_HYOJI_KUBUN_NYUURYOKU)) {
        // // 排他情報：用户プロ情報
        // userProfileCondition_ = new JktUserProfileCondition();
        // // 用户ID
        // userProfileCondition_.setUserId(_inForm.getUserId());
        // // [排他情報：用户プロファイル情報]
        // userProfileModel_ = userProfileDao.selectForUpdate(userProfileCondition_);
        //
        // [排他情報：用户プロ情報] の取得件数 ＞ 0
        if (userProfileModel_ != null) {
            // メッセージID【E00008】（パラメータ1："用户ID"）
            this.addErrorMessage(MessageUtils.getMessage("E20054"));
            this.errorEnd();
        }
        // }
        return userProfileModel_;
    }

    /**
     * 用户基本テーブルの登録を行う
     * 
     * @param _inForm SCHM00301Form
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    private void insertUserKihon(RPM00201Form _inForm) throws ApplicationException, SystemException {

        rpmUserDao.updateLockTable();
        RpmUserModel insertUserKihonModel_ = new RpmUserModel();

        // 用户ID
        insertUserKihonModel_.setUserId(_inForm.getUserId());
        // 開始日
        if (CheckUtils.isEmpty(_inForm.getStartDate())) {
            insertUserKihonModel_.setStartDate("");
        } else {
            insertUserKihonModel_.setStartDate(DateUtils.format(_inForm.getStartDate(),
                LeshanConstantsIF.DATE_FORMAT_YYYY_YEAR_MM_MONTH_DD_DATE, LeshanConstantsIF.DATE_FORMAT_YYYYMMDD));
        }
        // 終了日
        if (CheckUtils.isEmpty(_inForm.getEndDate())) {
            // 画面：有効期間（終了）]が空白の場合は、’99999999’
            insertUserKihonModel_.setEndDate(DATE_MAX);
        } else {
            insertUserKihonModel_.setEndDate(DateUtils.format(_inForm.getEndDate(),
                LeshanConstantsIF.DATE_FORMAT_YYYY_YEAR_MM_MONTH_DD_DATE, LeshanConstantsIF.DATE_FORMAT_YYYYMMDD));
        }
        // TODO 密码加密未定 パスワード
        // insertUserKihonModel_.setPassword(AclUtils.encryptPassword(_inForm.getPassword()));
        insertUserKihonModel_.setPassword(_inForm.getPassword());
        // 備考
        insertUserKihonModel_.setNotes(StringUtils.trimToNull(_inForm.getNotes()));
        // 消除标识
        insertUserKihonModel_.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        // 创建者
        insertUserKihonModel_.setCreateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
        // 创建日
        insertUserKihonModel_.setCreateDate(TimestampUtils.getSysTimestamp());
        // 最終更新用户ID
        insertUserKihonModel_.setLastUpdateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
        // 最終更新用户名
        insertUserKihonModel_.setLastUpdateUserName(UserSessionUtils.getUserSessionInfo().getUserName());
        // 最終更新日
        insertUserKihonModel_.setLastUpdateDate(TimestampUtils.getSysTimestamp());

        int userKihonCount_ = rpmUserDao.insert(insertUserKihonModel_);
        // 件数 ＝ 0 の場合
        if (userKihonCount_ == 0) {
            this.addErrorMessage(MessageUtils.getMessage("E00047", "登录"));
            this.errorEnd();
        }
    }

    /**
     * 用户プロファイルテーブルの登録を行う
     * 
     * @param _inForm SCHM00301Form
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    private void insertUserProfile(RPM00201Form _inForm) throws ApplicationException, SystemException {

        userProfileDao.updateLockTable();
        RpmUserProfileModel insertUserProfileModel_ = new RpmUserProfileModel();

        // 用户ID
        insertUserProfileModel_.setUserId(_inForm.getUserId());
        // 用户名
        insertUserProfileModel_.setUserName(_inForm.getUserName());
        // 生日
        insertUserProfileModel_.setBirthday(DateUtils.format(_inForm.getBirthday(),
            LeshanConstantsIF.DATE_FORMAT_YYYY_YEAR_MM_MONTH_DD_DATE, LeshanConstantsIF.DATE_FORMAT_YYYYMMDD));
        // 证件图片
        if (!CheckUtils.isEmpty(_inForm.getNewIdentityImage())) {
            insertUserProfileModel_.setIdentityImage(_inForm.getNewIdentityImage());
        }
        // 性别ID
        insertUserProfileModel_.setSexId(_inForm.getSexId());
        // 邮政番号
        insertUserProfileModel_.setPostNumber(_inForm.getPostNumber());
        // 住所
        insertUserProfileModel_.setAddr(_inForm.getAddr());
        // 座机
        insertUserProfileModel_.setPhoneNumber(_inForm.getPhoneNumber());
        // 手机
        insertUserProfileModel_.setCeelNumber(_inForm.getCeelNumber());
        // 电子邮箱
        insertUserProfileModel_.setEmail(_inForm.getEmail());
        // 消除标识
        insertUserProfileModel_.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        // 创建者
        insertUserProfileModel_.setCreateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
        // 创建日
        insertUserProfileModel_.setCreateDate(TimestampUtils.getSysTimestamp());
        // 最終更新用户ID
        insertUserProfileModel_.setLastUpdateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
        // 最終更新用户名
        insertUserProfileModel_.setLastUpdateUserName(UserSessionUtils.getUserSessionInfo().getUserName());
        // 最終更新日
        insertUserProfileModel_.setLastUpdateDate(TimestampUtils.getSysTimestamp());

        // オブジェクトの空の文字列を削除する。
        ReflectUtils.clearAllSpace(insertUserProfileModel_);

        int userProfileCount_ = userProfileDao.insert(insertUserProfileModel_);
        // 件数 ＝ 0 の場合
        if (userProfileCount_ == 0) {
            this.addErrorMessage(MessageUtils.getMessage("E00047", "登录"));
            this.errorEnd();
        }
    }

    /**
     * 用户ロール権限テーブルの登録を行う
     * 
     * @param _inForm SCHM00301Form
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    private void insertUserRollKengen(RPM00201Form _inForm) throws ApplicationException, SystemException {

        rpmUserRoleDao.updateLockTable();
        RpmUserRoleModel insertUserRollKengenModel_ = new RpmUserRoleModel();

        // 用户ID
        insertUserRollKengenModel_.setUserId(_inForm.getUserId());
        // 役割番号
        insertUserRollKengenModel_.setRoleId(_inForm.getRoleId());
        // 最終更新用户ID
        insertUserRollKengenModel_.setLastUpdateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
        // 最終更新用户名
        insertUserRollKengenModel_.setLastUpdateUserName(UserSessionUtils.getUserSessionInfo().getUserName());
        // 最終更新日
        insertUserRollKengenModel_.setLastUpdateDate(TimestampUtils.getSysTimestamp());

        int rollKengenCount_ = rpmUserRoleDao.insert(insertUserRollKengenModel_);
        // 件数 ＝ 0 の場合
        if (rollKengenCount_ == 0) {
            this.addErrorMessage(MessageUtils.getMessage("E00047", "登录"));
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
     * 用户ロール権限テーブル DAO的设定
     * 
     * @param _rpmUserRoleDao 用户ロール権限テーブル DAO
     */
    public void setRpmUserRoleDao(RpmUserRoleDao _rpmUserRoleDao) {
        this.rpmUserRoleDao = _rpmUserRoleDao;
    }

}
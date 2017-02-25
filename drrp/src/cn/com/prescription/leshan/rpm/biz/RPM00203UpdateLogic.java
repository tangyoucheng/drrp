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
import cn.com.prescription.leshan.common.data.RpmUserRole01Dao;
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
 * 用户情報設定 登録処理 ロジック
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11. NAME: fsb
 */
public class RPM00203UpdateLogic extends StandardBiz implements StandardLogic {

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
    RpmUserRoleDao rpmUserRoleDao = null;

    /**
     * 用户ロール権限テーブル DAO
     */
    RpmUserRole01Dao rpmUserRole01Dao = null;

    /**
     * 日付
     */
    private static final String DATE_MAX = "99999999";

    /**
     * 用户情報設定ロジック的构造。
     */
    public RPM00203UpdateLogic() {
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
        RPM00203Form inForm_ = (RPM00203Form) _event.getForm();

        // 排他情報：用户基本情報
        RpmUserModel userKihonModel_ = userKihonCheck(inForm_);
        // // 排他情報：用户プロ情報
        RpmUserProfileModel userProfileModel_ = userProfileCheck(inForm_);
        //
        //
        // // 用户基本テーブルの更新
        updateUserKihon(inForm_, userKihonModel_);
        // // 用户プロファイルテーブルの更新
        updateUserProfile(inForm_, userProfileModel_);
        // 更新用户角色信息
        updateUserRollKengen(inForm_);

        // 完了画面を表示する。
        this.connectCompleteDialog(MessageUtils.getMessage("I00001", "登录"),
            new ActionInfo(RPM00202Action.class, "doBack"));
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
    private RpmUserModel userKihonCheck(RPM00203Form _inForm) throws ApplicationException, SystemException {

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
        // [排他情報：用户基本情報]の取得件数 ＝ 0 の場合
        if (userKihonModel_ == null) {
            // メッセージID【E00004】（パラメータ1："削除"、パラメータ２："登録"）
            this.addErrorMessage(MessageUtils.getMessage("E00004", "削除", "登録"));
            this.errorEnd();
        }
        // [排他情報：用户基本情報．最終更新日]≠[画面：最終更新日]の場合
        if (userKihonModel_ != null && !CheckUtils.isEqual(_inForm.getLastUpdateDateUser(),
            TimestampUtils.formatUpd(userKihonModel_.getLastUpdateDate()))) {
            // メッセージID【E00006】
            this.addErrorMessage(MessageUtils.getMessage("E00006", "更新", "登録", userKihonModel_.getLastUpdateUserName(),
                DateUtils.format(userKihonModel_.getLastUpdateDate(), LeshanConstantsIF.DATE_FORMAT_YYYY_MM_DD_HH_MM)));
            this.errorEnd();
        }
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
    private RpmUserProfileModel userProfileCheck(RPM00203Form _inForm) throws ApplicationException, SystemException {

        // 排他情報：用户プロ情報
        RpmUserProfileCondition userProfileCondition_ = new RpmUserProfileCondition();
        // 用户ID
        userProfileCondition_.setUserId(_inForm.getUserId());
        // 消除标识＝ [定数：消除标识．有効レコード]
        userProfileCondition_.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        // [排他情報：用户プロファイル情報]
        RpmUserProfileModel userProfileModel_ = userProfileDao.selectForUpdate(userProfileCondition_);
        // 「削除」ボタンが表示された場合（更新の場合）、以下のチェックを行う
        // [排他情報：用户プロ情報]の取得件数 ＝ 0 の場合
        if (userProfileModel_ == null) {
            // メッセージID【E00004】（パラメータ1："削除"、パラメータ２："登録"）
            this.addErrorMessage(MessageUtils.getMessage("E00004", "削除", "登録"));
            this.errorEnd();
        }
        // [排他情報：用户プロ情報．最終更新日]≠[画面：最終更新日]の場合
        if (userProfileModel_ != null && !CheckUtils.isEqual(_inForm.getLastUpdateDateUserProfile(),
            TimestampUtils.formatUpd(userProfileModel_.getLastUpdateDate()))) {
            // メッセージID【E00006】
            this.addErrorMessage(
                MessageUtils.getMessage("E00006", "更新", "登録", userProfileModel_.getLastUpdateUserName(), DateUtils
                    .format(userProfileModel_.getLastUpdateDate(), LeshanConstantsIF.DATE_FORMAT_YYYY_MM_DD_HH_MM)));
            this.errorEnd();
        }
        return userProfileModel_;
    }

    /**
     * 用户基本テーブルの更新を行う
     * 
     * @param _inForm SCHM00301Form
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    private void updateUserKihon(RPM00203Form _inForm, RpmUserModel _updateUserKihonModel)
                    throws ApplicationException, SystemException {
        // 開始日
        if (CheckUtils.isEmpty(_inForm.getStartDate())) {
            _updateUserKihonModel.setStartDate("");
        } else {
            _updateUserKihonModel.setStartDate(DateUtils.format(_inForm.getStartDate(),
                LeshanConstantsIF.DATE_FORMAT_YYYY_YEAR_MM_MONTH_DD_DATE, LeshanConstantsIF.DATE_FORMAT_YYYYMMDD));
        }
        // 終了日
        if (CheckUtils.isEmpty(_inForm.getEndDate())) {
            // 画面：有効期間（終了）]が空白の場合は、’99999999’
            _updateUserKihonModel.setEndDate(DATE_MAX);
        } else {
            _updateUserKihonModel.setEndDate(DateUtils.format(_inForm.getEndDate(),
                LeshanConstantsIF.DATE_FORMAT_YYYY_YEAR_MM_MONTH_DD_DATE, LeshanConstantsIF.DATE_FORMAT_YYYYMMDD));
        }
        // パスワード TODO 加密处理未定
        // _updateUserKihonModel.setPassword(AclUtils.encryptPassword(_inForm.getPassword()));
        _updateUserKihonModel.setPassword(_inForm.getPassword());
        // 備考
        _updateUserKihonModel.setNotes(StringUtils.trimToNull(_inForm.getNotes()));
        // 消除标识
        _updateUserKihonModel.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        // 最終更新用户ID
        _updateUserKihonModel.setLastUpdateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
        // 最終更新用户名
        _updateUserKihonModel.setLastUpdateUserName(UserSessionUtils.getUserSessionInfo().getUserName());
        // 最終更新日
        _updateUserKihonModel.setLastUpdateDate(TimestampUtils.getSysTimestamp());

        int kihonCount_ = rpmUserDao.update(_updateUserKihonModel);
        // 件数 ＝ 0 の場合
        if (kihonCount_ == 0) {
            this.addErrorMessage(MessageUtils.getMessage("E00047", "更新"));
            this.errorEnd();
        }
    }

    /**
     * 用户プロファイルテーブルの更新を行う
     * 
     * @param _inForm SCHM00301Form
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    private void updateUserProfile(RPM00203Form _inForm, RpmUserProfileModel _updateUserProfileModel)
                    throws ApplicationException, SystemException {
        // 用户名
        _updateUserProfileModel.setUserName(_inForm.getUserName());
        // 生日
        _updateUserProfileModel.setBirthday(DateUtils.format(_inForm.getBirthday(),
            LeshanConstantsIF.DATE_FORMAT_YYYY_YEAR_MM_MONTH_DD_DATE, LeshanConstantsIF.DATE_FORMAT_YYYYMMDD));
        // 性别ID
        _updateUserProfileModel.setSexId(_inForm.getSexId());
        // 邮政番号
        _updateUserProfileModel.setPostNumber(_inForm.getPostNumber());
        // 住所
        _updateUserProfileModel.setAddr(_inForm.getAddr());
        // 座机
        _updateUserProfileModel.setPhoneNumber(_inForm.getPhoneNumber());
        // 手机
        _updateUserProfileModel.setCeelNumber(_inForm.getCeelNumber());
        // 电子邮箱
        _updateUserProfileModel.setEmail(_inForm.getEmail());
        // 证件图片
        if (!CheckUtils.isEmpty(_inForm.getNewIdentityImage())) {
            _updateUserProfileModel.setIdentityImage(_inForm.getNewIdentityImage());
        }
        // 消除标识
        _updateUserProfileModel.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        // 最終更新用户ID
        _updateUserProfileModel.setLastUpdateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
        // 最終更新用户名
        _updateUserProfileModel.setLastUpdateUserName(UserSessionUtils.getUserSessionInfo().getUserName());
        // 最終更新日
        _updateUserProfileModel.setLastUpdateDate(TimestampUtils.getSysTimestamp());

        // オブジェクトの空の文字列を削除する。
        ReflectUtils.clearAllSpace(_updateUserProfileModel);
        int profileCount_ = userProfileDao.update(_updateUserProfileModel);
        // 件数 ＝ 0 の場合
        if (profileCount_ == 0) {
            this.addErrorMessage(MessageUtils.getMessage("E00047", "更新"));
            this.errorEnd();
        }
    }

    /**
     * 用户ロール権限テーブルの更新を行う
     * 
     * @param _inForm SCHM00301Form
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    private void updateUserRollKengen(RPM00203Form _inForm) throws ApplicationException, SystemException {

        RpmUserRoleCondition _condition = new RpmUserRoleCondition();
        _condition.setUserId(_inForm.getUserId());
        rpmUserRole01Dao.deleteUserRole(_condition);
        if (!CheckUtils.isEmpty(_inForm.getRoleId())) {
            RpmUserRoleModel _userRoleModel = new RpmUserRoleModel();
            _userRoleModel.setUserId(_inForm.getUserId());
            _userRoleModel.setRoleId(_inForm.getRoleId());
            // 最終更新用户ID
            _userRoleModel.setLastUpdateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
            // 最終更新用户名
            _userRoleModel.setLastUpdateUserName(UserSessionUtils.getUserSessionInfo().getUserName());
            // 最終更新日
            _userRoleModel.setLastUpdateDate(TimestampUtils.getSysTimestamp());
            int count_ = rpmUserRoleDao.update(_userRoleModel);
            // (1)の更新件数が０件場合、新規登録処理を行う
            if (count_ == 0) {
                int countUpdate_ = rpmUserRoleDao.insert(_userRoleModel);

                if (countUpdate_ == 0) {
                    this.addErrorMessage(MessageUtils.getMessage("E00047", "登录"));
                    this.errorEnd();
                }
            }
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

    /**
     * 用户ロール権限テーブル DAO的设定
     * 
     * @param _rpmUserRole01Dao 用户ロール権限テーブル DAO
     */
    public void setRpmUserRole01Dao(RpmUserRole01Dao _rpmUserRole01Dao) {
        this.rpmUserRole01Dao = _rpmUserRole01Dao;
    }

}
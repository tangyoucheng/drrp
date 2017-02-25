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
import cn.com.prescription.framework.util.AclUtils;
import cn.com.prescription.framework.util.ReflectUtils;
import cn.com.prescription.framework.util.StringUtils;
import cn.com.prescription.framework.util.TimestampUtils;
import cn.com.prescription.leshan.common.LeshanConstantsIF;
import cn.com.prescription.leshan.common.data.RpmUserDao;
import cn.com.prescription.leshan.common.data.RpmUserProfileDao;
import cn.com.prescription.leshan.common.data.RpmUserRoleDao;
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
public class RPM00201UpdateLogic extends StandardBiz implements StandardLogic {

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
     * 日付
     */
    private static final String DATE_MAX = "99999999";

    /**
     * 用户情報設定ロジック的构造。
     */
    public RPM00201UpdateLogic() {
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
        // RpmUserModel userKihonModel_ = userKihonCheck(inForm_);
        // // 排他情報：用户プロ情報
        // RptUserProfileModel userProfileModel_ = userProfileCheck(inForm_);
        //
        //
        // // 用户基本テーブルの更新
        // updateUserKihon(inForm_, userKihonModel_);
        // // 用户プロファイルテーブルの更新
        // updateUserProfile(inForm_, userProfileModel_);

        // 完了画面を表示する。
        this.connectCompleteDialog(MessageUtils.getMessage("I00001", "登録"),
            new ActionInfo(RPM00201Action.class, "doInit"));
        // 出力情報設定
        return this.getEventResult(inForm_);

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
        // if (!CheckUtils.isEmpty(_inForm.getYuukoKikanKaishiNen())) {
        // insertUserKihonModel_
        // .setKaishibi(DateUtils.warekiToSeireki(_inForm.getYuukoKikanKaishiGengo(),
        // _inForm.getYuukoKikanKaishiNen(), _inForm.getYuukoKikanKaishiTsuki(),
        // _inForm.getYuukoKikanKaishiDate()));
        // } else {
        // insertUserKihonModel_.setSatrtDate("");
        // }
        // 終了日
        // if (CheckUtils.isEmpty(_inForm.getYuukoKikanShuuryoNen())) {
        // // 画面：有効期間（終了）]が空白の場合は、’99999999’
        // insertUserKihonModel_.setEndStart(DATE_MAX);
        // } else {
        // insertUserKihonModel_.setShuuryobi(DateUtils.warekiToSeireki(_inForm.getYuukoKikanShuuryoGengo(),
        // _inForm.getYuukoKikanShuuryoNen(), _inForm.getYuukoKikanShuuryoTsuki(),
        // _inForm.getYuukoKikanShuuryoDate()));
        // }
        // パスワード TODO 加密处理未定
        // insertUserKihonModel_.setPassword(AclUtils.encryptPassword(_inForm.getPassword()));
        insertUserKihonModel_.setPassword(_inForm.getPassword());
        // 備考
        insertUserKihonModel_.setNotes(StringUtils.trimToNull(_inForm.getNotes()));
        // 消除标识
        insertUserKihonModel_.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        // 最終更新用户ID
        insertUserKihonModel_.setLastUpdateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
        // 最終更新用户名
        insertUserKihonModel_.setLastUpdateUserName(UserSessionUtils.getUserSessionInfo().getUserName());
        // 最終更新日
        insertUserKihonModel_.setLastUpdateDate(TimestampUtils.getSysTimestamp());

        int userKihonCount_ = rpmUserDao.insert(insertUserKihonModel_);
        // 件数 ＝ 0 の場合
        if (userKihonCount_ == 0) {
            this.addErrorMessage(MessageUtils.getMessage("E00047", "登録"));
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
        // 消除标识
        insertUserProfileModel_.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
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
            this.addErrorMessage(MessageUtils.getMessage("E00047", "登録"));
            this.errorEnd();
        }
    }

    // /**
    // * 用户ロール権限テーブルの登録を行う
    // *
    // * @param _inForm SCHM00301Form
    // * @throws ApplicationException event処理里应用程序例外发生的情况
    // * @throws SystemException event処理里系统例外发生的情况
    // */
    // private void insertUserRollKengen(RPM00201Form _inForm) throws ApplicationException, SystemException {
    //
    // rptUserRoleDao.updateLockTable();
    // RptUserRoleModel insertUserRollKengenModel_ = new RptUserRoleModel();
    //
    // // 用户ID
    // insertUserRollKengenModel_.setUserId(_inForm.getUserId());
    // // 役割番号
    // insertUserRollKengenModel_.setRoleId(_inForm.getYakuwari());
    // // 最終更新用户ID
    // insertUserRollKengenModel_.setLastUpdateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
    // // 最終更新日
    // insertUserRollKengenModel_.setLastUpdateDate(TimestampUtils.getSysTimestamp());
    //
    // int rollKengenCount_ = rptUserRoleDao.insert(insertUserRollKengenModel_);
    // // 件数 ＝ 0 の場合
    // if (rollKengenCount_ == 0) {
    // this.addErrorMessage(MessageUtils.getMessage("E00047", "登録"));
    // this.errorEnd();
    // }
    //
    // }

    /**
     * 用户基本テーブルの更新を行う
     * 
     * @param _inForm SCHM00301Form
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    private void updateUserKihon(RPM00201Form _inForm, RpmUserModel _updateUserKihonModel)
                    throws ApplicationException, SystemException {

        // 開始日
        // if (!CheckUtils.isEmpty(_inForm.getYuukoKikanKaishiNen())) {
        // _updateUserKihonModel
        // .setKaishibi(DateUtils.warekiToSeireki(_inForm.getYuukoKikanKaishiGengo(),
        // _inForm.getYuukoKikanKaishiNen(), _inForm.getYuukoKikanKaishiTsuki(),
        // _inForm.getYuukoKikanKaishiDate()));
        // } else {
        // _updateUserKihonModel.setKaishibi("");
        // }
        // 終了日
        // if (CheckUtils.isEmpty(_inForm.getYuukoKikanShuuryoNen())) {
        // // 画面：有効期間（終了）]が空白の場合は、’99999999’
        // _updateUserKihonModel.setShuuryobi(DATE_MAX);
        // } else {
        // _updateUserKihonModel.setShuuryobi(DateUtils.warekiToSeireki(_inForm.getYuukoKikanShuuryoGengo(),
        // _inForm.getYuukoKikanShuuryoNen(), _inForm.getYuukoKikanShuuryoTsuki(),
        // _inForm.getYuukoKikanShuuryoDate()));
        // }
        // パスワード

        _updateUserKihonModel.setPassword(AclUtils.encryptPassword(_inForm.getPassword()));

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
    private void updateUserProfile(RPM00201Form _inForm, RpmUserProfileModel _updateUserProfileModel)
                    throws ApplicationException, SystemException {

        // 生年月日
        // if (!CheckUtils.isEmpty(_inForm.getSeinengappiNen())) {
        // _updateUserProfileModel.setSeinengappi(DateUtils.warekiToSeireki(_inForm.getSeinengappiGengo(),
        // _inForm.getSeinengappiNen(), _inForm.getSeinengappiTsuki(), _inForm.getSeinengappiDate()));
        // } else {
        // _updateUserProfileModel.setSeinengappi(null);
        // }
        // 性別コード
        _updateUserProfileModel.setSexId("");
        ;
        // 郵便番号
        _updateUserProfileModel.setPostNumber("");
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
    private void updateUserRollKengen(RPM00201Form _inForm, RpmUserRoleModel _updateUserRollKengenModel)
                    throws ApplicationException, SystemException {

        // // 用户ロール権限テーブルモデル
        // RptUserRoleModel userRollKengenModel_ = new RptUserRoleModel();
        // // [画面：hid役割番号]≠[画面：役割]の場合
        // if (!CheckUtils.isEqual(_inForm.getHidYakuwariBango(), _inForm.getYakuwari())) {
        // // 用户ID
        // userRollKengenModel_.setUserId(_inForm.getUserId());
        // // 役割番号
        // userRollKengenModel_.setYakuwariBango(_inForm.getHidYakuwariBango());
        // // 消除标识
        // userRollKengenModel_.setDelFlg(LeshanConstantsIF.DEL_FLG_DEL_RECORD);
        // // 最終更新用户ID
        // userRollKengenModel_.setLastUpdateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
        // // 最終更新用户名
        // userRollKengenModel_.setLastUpdateUserName(UserSessionUtils.getUserSessionInfo().getUserName());
        // // 最終更新日
        // userRollKengenModel_.setLastUpdateDate(TimestampUtils.getSysTimestamp());
        //
        // int rollKengenCount_ = rptUserRoleDao.update(userRollKengenModel_);
        // // 件数 ＝ 0 の場合
        // if (rollKengenCount_ == 0) {
        // this.addErrorMessage(MessageUtils.getMessage("E00047", "削除"));
        // this.errorEnd();
        // }
        // // [排他情報：用户ロール権限情報（更新用）] の取得件数 ＝ 0の場合
        // if (_updateUserRollKengenModel == null) {
        //
        // userRollKengenModel_ = new RptUserRoleModel();
        // // 用户ID
        // userRollKengenModel_.setUserId(_inForm.getUserId());
        // // 役割番号
        // userRollKengenModel_.setRoleId(_inForm.getYakuwari());
        // // 最終更新用户ID
        // userRollKengenModel_.setLastUpdateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
        // // 最終更新日
        // userRollKengenModel_.setLastUpdateDate(TimestampUtils.getSysTimestamp());
        //
        // rollKengenCount_ = rptUserRoleDao.insert(userRollKengenModel_);
        // // 件数 ＝ 0 の場合
        // if (rollKengenCount_ == 0) {
        // this.addErrorMessage(MessageUtils.getMessage("E00047", "登録"));
        // this.errorEnd();
        // }
        //
        // } else {
        // // [画面：hid役割番号]=[画面：役割]の場合
        // // 消除标识
        // _updateUserRollKengenModel.setDelFlg(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        // // 最終更新用户ID
        // _updateUserRollKengenModel.setLastUpdateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
        // // 最終更新用户名
        // _updateUserRollKengenModel.setLastUpdateUserName(UserSessionUtils.getUserSessionInfo().getUserName());
        // // 最終更新日
        // _updateUserRollKengenModel.setLastUpdateDate(TimestampUtils.getSysTimestamp());
        //
        // rollKengenCount_ = rptUserRoleDao.update(_updateUserRollKengenModel);
        // // 件数 ＝ 0 の場合
        // if (rollKengenCount_ == 0) {
        // this.addErrorMessage(MessageUtils.getMessage("E00047", "更新"));
        // this.errorEnd();
        // }
        // }
        // } else {
        // // [画面：hid役割番号]=[画面：役割]の場合
        // // 消除标识
        // _updateUserRollKengenModel.setDelFlg(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        // // 最終更新用户ID
        // _updateUserRollKengenModel.setLastUpdateUserId(UserSessionUtils.getUserSessionInfo().getUserId());
        // // 最終更新用户名
        // _updateUserRollKengenModel.setLastUpdateUserName(UserSessionUtils.getUserSessionInfo().getUserName());
        // // 最終更新日
        // _updateUserRollKengenModel.setLastUpdateDate(TimestampUtils.getSysTimestamp());
        //
        // int rollKengenCount_ = rptUserRoleDao.update(_updateUserRollKengenModel);
        // // 件数 ＝ 0 の場合
        // if (rollKengenCount_ == 0) {
        // this.addErrorMessage(MessageUtils.getMessage("E00047", "更新"));
        // this.errorEnd();
        // }
        // }
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
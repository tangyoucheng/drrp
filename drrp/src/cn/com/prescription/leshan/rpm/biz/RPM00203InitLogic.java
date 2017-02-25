/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpm.biz;

import java.util.ArrayList;
import java.util.List;

import cn.com.prescription.framework.CodeValueRecord;
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
import cn.com.prescription.framework.util.StringUtils;
import cn.com.prescription.framework.util.TimestampUtils;
import cn.com.prescription.leshan.common.LeshanConstantsIF;
import cn.com.prescription.leshan.common.data.RpmRole01Dao;
import cn.com.prescription.leshan.common.data.RpmRoleDao;
import cn.com.prescription.leshan.common.data.RpmUserRoleDao;
import cn.com.prescription.leshan.common.data.condition.RpmRoleCondition;
import cn.com.prescription.leshan.common.data.condition.RpmUserRoleCondition;
import cn.com.prescription.leshan.common.data.model.RpmRoleModel;
import cn.com.prescription.leshan.common.data.model.RpmUserRoleModel;
import cn.com.prescription.leshan.rpm.action.RPM00302Action;
import cn.com.prescription.leshan.rpm.action.form.RPM00203Form;
import cn.com.prescription.leshan.rpm.data.RPM00203Dao;
import cn.com.prescription.leshan.rpm.data.condition.RPM00203Condition;
import cn.com.prescription.leshan.rpm.data.model.RPM00203Model;

/**
 * 用户情報設定画面 初期化 ロジック。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPM00203InitLogic extends StandardBiz implements StandardLogic {

    /**
     * 役割マスタDAO
     */
    RpmRoleDao rpmRoleDao = null;
    /**
     * 用户一览DAO
     */
    RPM00203Dao rpm00203Dao = null;

    /**
     * 角色DAO
     */
    RpmRole01Dao rpmRole01Dao = null;

    /**
     * 用户ロール権限テーブル DAO
     */
    RpmUserRoleDao rpmUserRoleDao = null;

    /**
     * 用户情報設定画面（一般用户用） 初期化業務クラス的构造。
     */
    public RPM00203InitLogic() {
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
        RPM00203Form inForm_ = (RPM00203Form) _event.getForm();
        // 用户信息取得
        RPM00203Condition rpm00203Condition = new RPM00203Condition();
        // 用户ID
        rpm00203Condition.setUserId(inForm_.getUserId());
        // 用户名
        // rpm00203Condition.setUserName(inForm_.getUserName());
        // 消除标识
        rpm00203Condition.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        rpm00203Condition.setPageStartRowNo(0);
        RPM00203Model rpm00203Model = rpm00203Dao.selectUserInfo(rpm00203Condition);
        if (rpm00203Model == null) {
            this.addErrorMessage(MessageUtils.getMessage("E00004", "削除", "表示"));
            this.errorEnd(new ActionInfo(RPM00302Action.class, "doBack"));
        } else {
            // 開始日
            if (!CheckUtils.isEmpty(rpm00203Model.getStartDate())) {
                inForm_.setStartDate(DateUtils.format(rpm00203Model.getStartDate(),
                    LeshanConstantsIF.DATE_FORMAT_YYYYMMDD, LeshanConstantsIF.DATE_FORMAT_YYYY_YEAR_MM_MONTH_DD_DATE));
            }
            // 終了日
            if (!CheckUtils.isEmpty(rpm00203Model.getEndDate())) {
                inForm_.setEndDate(DateUtils.format(rpm00203Model.getEndDate(), LeshanConstantsIF.DATE_FORMAT_YYYYMMDD,
                    LeshanConstantsIF.DATE_FORMAT_YYYY_YEAR_MM_MONTH_DD_DATE));
            }
            // パスワード TODO 加密解密处理未定
            if (!CheckUtils.isEmpty(rpm00203Model.getPassword())) {
                // inForm_.setPassword(AclUtils.decryptPassword(rpm00203Model.getPassword()));
                inForm_.setPassword(rpm00203Model.getPassword());
            }
            // 備考
            inForm_.setNotes(StringUtils.trimToNull(rpm00203Model.getNotes()));
            // 用户名
            inForm_.setUserName(rpm00203Model.getUserName());
            // 用户角色ID
            inForm_.setRoleId(rpm00203Model.getRoleId());
            // 生日
            if (!CheckUtils.isEmpty(rpm00203Model.getBirthday())) {
                inForm_.setBirthday(DateUtils.format(rpm00203Model.getBirthday(),
                    LeshanConstantsIF.DATE_FORMAT_YYYYMMDD, LeshanConstantsIF.DATE_FORMAT_YYYY_YEAR_MM_MONTH_DD_DATE));
            }
            // 证件图片
            inForm_.setOldIdentityImage(rpm00203Model.getIdentityImage());
            // 性别ID
            inForm_.setSexId(rpm00203Model.getSexId());
            // 邮政番号
            inForm_.setPostNumber(rpm00203Model.getPostNumber());
            // 住所
            inForm_.setAddr(rpm00203Model.getAddr());
            // 座机
            inForm_.setPhoneNumber(rpm00203Model.getPhoneNumber());
            // 手机
            inForm_.setCeelNumber(rpm00203Model.getCeelNumber());
            // 电子邮箱
            inForm_.setEmail(rpm00203Model.getEmail());
            // 最终更新日
            inForm_.setLastUpdateDateUser(TimestampUtils.formatUpd(rpm00203Model.getLastUpdateDateUser()));
            inForm_
                .setLastUpdateDateUserProfile(TimestampUtils.formatUpd(rpm00203Model.getLastUpdateDateUserProfile()));
            inForm_.setLastUpdateDateUserRole(TimestampUtils.formatUpd(rpm00203Model.getLastUpdateDateUserRole()));
            inForm_.setRoleId(rpm00203Model.getRoleId());

        }

        // 性別リストを取得する
        CodeValueRecord record_ = null;
        List<CodeValueRecord> sexList_ = new ArrayList<CodeValueRecord>();
        record_ = new CodeValueRecord();
        record_.setRecordCode("1");
        record_.setRecordValue("男");
        sexList_.add(record_);
        record_ = new CodeValueRecord();
        record_.setRecordCode("2");
        record_.setRecordValue("女");
        sexList_.add(record_);

        inForm_.setSexDataSource(sexList_);

        // 角色リストを取得する
        RpmRoleCondition roleCondition = new RpmRoleCondition();
        // 消除标识
        roleCondition.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        List<RpmRoleModel> roleModelList_ = rpmRole01Dao.selectAllRoleList(roleCondition);
        List<CodeValueRecord> roleList_ = new ArrayList<CodeValueRecord>();
        roleList_.add(new CodeValueRecord());
        if (roleModelList_ != null && !roleModelList_.isEmpty()) {
            for (RpmRoleModel rpmRoleModel : roleModelList_) {
                if (CheckUtils.isEqual(rpm00203Model.getRoleId(), "admin")
                                && CheckUtils.isEqual(rpmRoleModel.getRoleId(), "admin")) {
                    roleList_ = new ArrayList<CodeValueRecord>();
                    record_ = new CodeValueRecord();
                    record_.setRecordCode(rpmRoleModel.getRoleId());
                    record_.setRecordValue(rpmRoleModel.getRoleName());
                    roleList_.add(record_);
                    break;
                }
                if (!CheckUtils.isEqual(rpmRoleModel.getRoleId(), "admin")) {
                    record_ = new CodeValueRecord();
                    record_.setRecordCode(rpmRoleModel.getRoleId());
                    record_.setRecordValue(rpmRoleModel.getRoleName());
                    roleList_.add(record_);
                }
            }
            // inForm_.setRoleId(roleList_.get(0).getRecordCode());
        }
        inForm_.setRoleDataSource(roleList_);

        RpmUserRoleCondition _condition = new RpmUserRoleCondition();
        _condition.setUserId(inForm_.getUserId());
        RpmUserRoleModel userRoleModel = rpmUserRoleDao.select(_condition);
        if (userRoleModel != null) {
            inForm_.setRoleId(userRoleModel.getRoleId());
        }

        // 出力情報設定
        return this.getEventResult(inForm_);
    }

    /**
     * 役割マスタDAOを設定する。
     * 
     * @param _rpmRoleDao 役割マスタDAO
     */
    public void setRpmRoleDao(RpmRoleDao _rpmRoleDao) {
        this.rpmRoleDao = _rpmRoleDao;
    }

    /**
     * 用户一览DAO的设定
     * 
     * @param _rpm00203Dao 用户一览DAO
     */
    public void setRpm00203Dao(RPM00203Dao _rpm00203Dao) {
        this.rpm00203Dao = _rpm00203Dao;
    }

    /**
     * 角色DAO的设定
     * 
     * @param _rpmRole01Dao 角色DAO
     */
    public void setRpmRole01Dao(RpmRole01Dao _rpmRole01Dao) {
        this.rpmRole01Dao = _rpmRole01Dao;
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

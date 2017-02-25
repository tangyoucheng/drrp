/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpm.biz;

import java.util.ArrayList;
import java.util.List;

import cn.com.prescription.framework.CodeValueRecord;
import cn.com.prescription.framework.biz.StandardBiz;
import cn.com.prescription.framework.biz.StandardLogic;
import cn.com.prescription.framework.event.StandardEvent;
import cn.com.prescription.framework.event.StandardEventResult;
import cn.com.prescription.framework.exception.ApplicationException;
import cn.com.prescription.framework.exception.SystemException;
import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.leshan.common.LeshanConstantsIF;
import cn.com.prescription.leshan.common.data.RpmRole01Dao;
import cn.com.prescription.leshan.common.data.RpmRoleDao;
import cn.com.prescription.leshan.common.data.condition.RpmRoleCondition;
import cn.com.prescription.leshan.common.data.model.RpmRoleModel;
import cn.com.prescription.leshan.rpm.action.form.RPM00201Form;

/**
 * 用户情報設定画面 初期化 ロジック。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPM00201InitLogic extends StandardBiz implements StandardLogic {

    /**
     * 役割マスタDAO
     */
    RpmRoleDao rpmRoleDao = null;

    /**
     * 角色DAO
     */
    RpmRole01Dao rpmRole01Dao = null;

    /**
     * 用户情報設定画面（一般用户用） 初期化業務クラス的构造。
     */
    public RPM00201InitLogic() {
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
        RPM00201Form inForm_ = (RPM00201Form) _event.getForm();

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
                if (!CheckUtils.isEqual("admin", rpmRoleModel.getRoleId())) {
                    record_ = new CodeValueRecord();
                    record_.setRecordCode(rpmRoleModel.getRoleId());
                    record_.setRecordValue(rpmRoleModel.getRoleName());
                    roleList_.add(record_);
                }
            }
            inForm_.setRoleId(roleList_.get(0).getRecordCode());
        }
        inForm_.setRoleDataSource(roleList_);

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
     * 角色DAO的设定
     * 
     * @param _rpmRole01Dao 角色DAO
     */
    public void setRpmRole01Dao(RpmRole01Dao _rpmRole01Dao) {
        this.rpmRole01Dao = _rpmRole01Dao;
    }

}

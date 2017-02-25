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
import cn.com.prescription.leshan.common.LeshanConstantsIF;
import cn.com.prescription.leshan.common.data.RpmRole01Dao;
import cn.com.prescription.leshan.common.data.condition.RpmRoleCondition;
import cn.com.prescription.leshan.common.data.model.RpmRoleModel;
import cn.com.prescription.leshan.rpm.action.RPM00301Action;
import cn.com.prescription.leshan.rpm.action.form.RPM00401Form;
import cn.com.prescription.leshan.rpm.data.RPM00401Dao;
import cn.com.prescription.leshan.rpm.data.condition.RPM00401Condition;
import cn.com.prescription.leshan.rpm.data.model.RPM00401Model;

/**
 * 编辑用户菜单权限 初期化处理。
 * 新規作成
 * DATE: 2016.03.24 NAME: tyc
 */
public class RPM00401InitLogic extends StandardBiz implements StandardLogic {

    /**
     * 角色DAO
     */
    RpmRole01Dao rpmRole01Dao = null;

    /**
     * 角色权限管理画面DAO
     */
    RPM00401Dao rpm00401Dao = null;

    /**
     * 编辑用户菜单权限画面 初期化業務クラス的构造。
     */
    public RPM00401InitLogic() {
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

        // 菜单マスタのデータ的取得
        RPM00401Condition _condition = new RPM00401Condition();
        // 定数:1
        _condition.setTeisu1("1");
        // 定数：菜单
        _condition.setMenu(LeshanConstantsIF.MENU_TYPE_MENU);
        // 消除标识
        _condition.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        List<RPM00401Model> menuHierarchy1List = rpm00401Dao.selectMenuList(_condition);
        List<CodeValueRecord> menuHierarchy1List_ = new ArrayList<CodeValueRecord>();
        menuHierarchy1List_.add(new CodeValueRecord());
        if (menuHierarchy1List != null && !menuHierarchy1List.isEmpty()) {
            for (RPM00401Model menuHierarchy1Model : menuHierarchy1List) {
                CodeValueRecord record_ = new CodeValueRecord();
                record_.setRecordCode(menuHierarchy1Model.getMenuId());
                record_.setRecordValue(menuHierarchy1Model.getMenuName());
                menuHierarchy1List_.add(record_);
            }
            inForm_.setMenuHierarchy1Id(null);
        }
        inForm_.setMenuHierarchy1DataSource(menuHierarchy1List_);

        // 角色リストを取得する
        RpmRoleCondition roleCondition = new RpmRoleCondition();
        // 消除标识
        roleCondition.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        List<RpmRoleModel> roleModelList_ = rpmRole01Dao.selectAllRoleList(roleCondition);
        List<CodeValueRecord> roleList_ = new ArrayList<CodeValueRecord>();
        if (roleModelList_ != null && !roleModelList_.isEmpty()) {
            for (RpmRoleModel rpmRoleModel : roleModelList_) {
                if (!CheckUtils.isEqual("admin", rpmRoleModel.getRoleId())) {
                    CodeValueRecord record_ = new CodeValueRecord();
                    record_.setRecordCode(rpmRoleModel.getRoleId());
                    record_.setRecordValue(rpmRoleModel.getRoleName());
                    roleList_.add(record_);
                }
            }
            if (roleList_.size() == 0) {
                inForm_.setRoleDataSource(roleList_);
                this.addErrorMessage(MessageUtils.getMessage("E00033", "角色信息取得"));
                this.errorEnd(new ActionInfo(RPM00301Action.class, "doInit"));
            }
            inForm_.setRoleId(roleList_.get(0).getRecordCode());
        }
        inForm_.setRoleDataSource(roleList_);

        // 出力情報設定
        return this.getEventResult(inForm_);
    }

    /**
     * 角色DAO的设定
     * 
     * @param __rpmRole01Dao 角色DAO
     */
    public void setRpmRole01Dao(RpmRole01Dao _rpmRole01Dao) {
        this.rpmRole01Dao = _rpmRole01Dao;
    }

    /**
     * 角色权限管理画面DAO的设定
     * 
     * @param _rpm00401Dao 角色权限管理画面DAO
     */
    public void setRpm00401Dao(RPM00401Dao _rpm00401Dao) {
        this.rpm00401Dao = _rpm00401Dao;
    }

}

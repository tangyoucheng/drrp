/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpm.biz;

import java.util.ArrayList;
import java.util.List;

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
import cn.com.prescription.leshan.common.data.RpmRoleMenuDao;
import cn.com.prescription.leshan.rpm.action.form.RPM0040101Dto;
import cn.com.prescription.leshan.rpm.action.form.RPM00401Form;
import cn.com.prescription.leshan.rpm.data.RPM00401Dao;
import cn.com.prescription.leshan.rpm.data.condition.RPM00401Condition;
import cn.com.prescription.leshan.rpm.data.model.RPM00401Model;

/**
 * 患者信息导出初期化处理。
 * 新規作成
 * DATE: 2016.03.24 NAME: tyc
 */
public class RPM00401SearchLogic extends StandardBiz implements StandardLogic {

    /**
     * 角色DAO
     */
    RpmRole01Dao rpmRole01Dao = null;
    /**
     * 角色权限DAO
     */
    RpmRoleMenuDao rpmRoleMenuDao = null;

    /**
     * 角色权限管理画面DAO
     */
    RPM00401Dao rpm00401Dao = null;

    /**
     * 患者信息导出画面 初期化業務クラス的构造。
     */
    public RPM00401SearchLogic() {
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

        // 前回検索結果クリア
        inForm_.getMenuMiddleList().clear();

        RPM00401Condition rpm00401Condition_ = new RPM00401Condition();

        // 定数:1
        rpm00401Condition_.setTeisu1("1");
        // 定数：ページ
        rpm00401Condition_.setPage(LeshanConstantsIF.MENU_TYPE_PAGE);
        // 定数：菜单
        rpm00401Condition_.setMenu(LeshanConstantsIF.MENU_TYPE_MENU);
        // 画面：役割番号
        rpm00401Condition_.setRoleId(inForm_.getRoleId());
        // 画面：菜单ＩＤ
        rpm00401Condition_.setMenuId(inForm_.getMenuHierarchy1Id());
        rpm00401Condition_.addPageSortField("C.sort_key", "ASC");
        rpm00401Condition_.addPageSortField("B.sort_key", "ASC");
        rpm00401Condition_.addPageSortField("A.sort_key", "ASC");
        // 消除标识
        rpm00401Condition_.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        // 権限情報（役割）登録菜单情報の件数
        long infoCount_ = rpm00401Dao.selectMenuInfoCount(rpm00401Condition_);
        // 前回検索結果クリア
        inForm_.getSubForm1().clear();
        // 件数チェック処理
        if (infoCount_ == 0) {
            this.addErrorMessage(MessageUtils.getMessage("E00002", "指定的菜单数据"));
            this.errorEnd();
        }

        // 菜单情報リスト
        List<RPM00401Model> menuInfoList_ = rpm00401Dao.selectMenuInfoList(rpm00401Condition_);

        // 権限情報（役割）登録検索一覧formDto
        List<RPM0040101Dto> subForm_ = new ArrayList<RPM0040101Dto>();

        // 取得情報：菜单情報の件数 ＞ 0 の場合、一覧を表示する
        for (RPM00401Model rpm00401Model_ : menuInfoList_) {
            // 菜单情報一覧
            RPM0040101Dto rpm00401Dto_ = new RPM0040101Dto();
            // 許可
            // [取得情報：菜单情報リスト]．チェック機能ＩＤが空の場合、チェックなし。以外の場合、チェックする
            if (CheckUtils.isEmpty(rpm00401Model_.getChikkukinoId())) {
                rpm00401Dto_.setCheck(false);
            } else {
                rpm00401Dto_.setCheck(true);
            }
            // 菜单
            rpm00401Dto_.setMenu(rpm00401Model_.getMenuName());
            // サブ菜单
            rpm00401Dto_.setSubMenu(rpm00401Model_.getSubMenuName());
            // 機能菜单
            rpm00401Dto_.setKinoMenu(rpm00401Model_.getKinoMenuName());
            // hid機能菜单ＩＤ
            rpm00401Dto_.setHidMenuId(rpm00401Model_.getMenuId());
            // hid機能ＩＤ
            // rpm00401Dto_.setHidKinoId(rpm00401Model_.getKinoId());
            // hid許可
            rpm00401Dto_.setHidChikkuKinoId(rpm00401Model_.getChikkukinoId());

            subForm_.add(rpm00401Dto_);
        }

        inForm_.setSubForm1(subForm_);

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
     * 角色权限DAO的设定
     * 
     * @param _rpmRoleMenuDao 角色权限DAO
     */
    public void setRpmRoleMenuDao(RpmRoleMenuDao _rpmRoleMenuDao) {
        this.rpmRoleMenuDao = _rpmRoleMenuDao;
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

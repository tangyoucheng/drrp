/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.leshan.scz.biz;

import java.util.ArrayList;
import java.util.List;

import cn.com.prescription.leshan.common.LeshanConstantsIF;
import cn.com.prescription.leshan.scz.action.form.SCZ990102Dto;
import cn.com.prescription.leshan.scz.action.form.SCZ990102Form;
import cn.com.prescription.leshan.scz.data.SCZ9901Dao;
import cn.com.prescription.leshan.scz.data.condition.SCZ9901Condition;
import cn.com.prescription.leshan.scz.data.model.SCZ9901Model;
import cn.com.prescription.framework.biz.StandardBiz;
import cn.com.prescription.framework.biz.StandardLogic;
import cn.com.prescription.framework.common.session.UserSessionUtils;
import cn.com.prescription.framework.event.StandardEvent;
import cn.com.prescription.framework.event.StandardEventResult;
import cn.com.prescription.framework.util.CheckUtils;

import org.intra_mart.framework.system.exception.ApplicationException;
import org.intra_mart.framework.system.exception.SystemException;

/**
 * SCZ990102 门户（サブ菜单）画面 action。
 * 
 * @author kourei
 */
/*
 * 新規作成
 * DATE: 2010.03.09 NAME: kourei
 */
public class SCZ990102InitLogic extends StandardBiz implements StandardLogic {

    /**
     * 门户画面DAO
     */
    private SCZ9901Dao scz9901Dao = null;

    /**
     * 登录画面 登录業務クラス的构造。
     */
    public SCZ990102InitLogic() {
        super();
    }

    /**
     * event処理を行う。
     * 
     * @param _event event
     * @return event処理結果
     * @throws ApplicationException event処理里应用程序例外发生的情况
     * @throws SystemException event処理里系统例外发生的情况
     */
    public StandardEventResult service(StandardEvent _event) throws ApplicationException, SystemException {

        // 画面情報的取得
        SCZ990102Form inForm = (SCZ990102Form) _event.getForm();
        List<SCZ990102Dto> _menuMiddleList = new ArrayList<SCZ990102Dto>();
        List<SCZ990102Dto> _menuLinkList = new ArrayList<SCZ990102Dto>();
        SCZ990102Dto dto_ = new SCZ990102Dto();

        if (!CheckUtils.isEmpty(inForm.getParentMenuId())) {
            // 2.1.登录情報の組織ＩＤに紐付く組織情報から、利用可能な菜单を限定する
            SCZ9901Condition condition = new SCZ9901Condition();
            condition.setRoleIdList(UserSessionUtils.getRoleIdArray());
            condition.setParentMenuId(inForm.getParentMenuId());
            // 公開标识
            condition.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
            List<SCZ9901Model> dataList = scz9901Dao.selectMenuHierarchy2(condition);

            // 利用可能な菜单的设定
            for (SCZ9901Model data : dataList) {
                dto_ = new SCZ990102Dto();
                // 菜单ID
                dto_.setMenuId(data.getMenuId());
                // 菜单名
                dto_.setMenuName(data.getMenuName());
                // 菜单タイプ
                dto_.setMenuType(data.getMenuType());
                // ＵＲＬ
                dto_.setUrl(data.getUrl());
                // タイトル
                inForm.setTitle(data.getParentMenuName());

                if (CheckUtils.isEqual(data.getMenuType(), LeshanConstantsIF.MENU_TYPE_PAGE)) {
                    _menuMiddleList.add(dto_);
                    continue;
                }
                _menuLinkList = new ArrayList<SCZ990102Dto>();
                condition.setParentMenuId(data.getMenuId());
                List<SCZ9901Model> resultList = scz9901Dao.selectMenuHierarchy3(condition);
                for (SCZ9901Model result : resultList) {
                    SCZ990102Dto menuLinkDto_ = new SCZ990102Dto();
                    // 菜单ID
                    menuLinkDto_.setMenuId(result.getMenuId());
                    // 菜单名
                    menuLinkDto_.setMenuName(result.getMenuName());
                    // 菜单タイプ
                    menuLinkDto_.setMenuType(result.getMenuType());
                    // ＵＲＬ
                    menuLinkDto_.setUrl(result.getUrl());

                    _menuLinkList.add(menuLinkDto_);
                }

                dto_.setMenuLinkList(_menuLinkList);
                _menuMiddleList.add(dto_);
            }

            // 画面に菜单リスト的设定
            inForm.setMenuMiddleList(_menuMiddleList);
        }

        // 画面情報的设定。
        return this.getEventResult(inForm);
    }

    /**
     * 门户画面DAOを設定します。
     * 
     * @param scz9901Dao 门户画面DAO
     */
    public void setScz9901Dao(SCZ9901Dao scz9901Dao) {
        this.scz9901Dao = scz9901Dao;
    }
}

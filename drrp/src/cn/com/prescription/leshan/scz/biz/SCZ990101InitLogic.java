/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.leshan.scz.biz;

import java.util.ArrayList;
import java.util.List;

import cn.com.prescription.leshan.common.LeshanConstantsIF;
import cn.com.prescription.leshan.scz.action.form.SCZ990101Dto;
import cn.com.prescription.leshan.scz.action.form.SCZ990101Form;
import cn.com.prescription.leshan.scz.data.SCZ9901Dao;
import cn.com.prescription.leshan.scz.data.condition.SCZ9901Condition;
import cn.com.prescription.leshan.scz.data.model.SCZ9901Model;
import cn.com.prescription.framework.StandardConstantsIF;
import cn.com.prescription.framework.biz.StandardBiz;
import cn.com.prescription.framework.biz.StandardLogic;
import cn.com.prescription.framework.common.session.UserSessionUtils;
import cn.com.prescription.framework.event.StandardEvent;
import cn.com.prescription.framework.event.StandardEventResult;

import org.intra_mart.framework.system.exception.ApplicationException;
import org.intra_mart.framework.system.exception.SystemException;

/**
 * SCZ990101 门户画面 action。
 * 
 * @author kourei
 */
/*
 * 新規作成
 * DATE: 2010.03.09 NAME: kourei
 */
public class SCZ990101InitLogic extends StandardBiz implements StandardLogic {

    /**
     * 门户画面DAO
     */
    private SCZ9901Dao scz9901Dao = null;

    /**
     * 登录画面 登录業務クラス的构造。
     */
    public SCZ990101InitLogic() {
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

        // 初期化
        SCZ990101Form inForm = (SCZ990101Form) _event.getForm();

        // 菜单マスタのデータ的取得
        SCZ9901Condition _condition = new SCZ9901Condition();
        // 角色ID
        _condition.setRoleIdList(UserSessionUtils.getRoleIdArray());
        // 消除标识
        _condition.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        List<SCZ9901Model> resultList = scz9901Dao.selectMenuHierarchy1(_condition);

        List<List<SCZ990101Dto>> subForm_ = new ArrayList<List<SCZ990101Dto>>();
        List<List<SCZ9901Model>> resultList_ = editMenuList(resultList);
        for (int i = resultList_.size() - 1; i >= 0; i--) {
            List<SCZ990101Dto> menuList_ = new ArrayList<SCZ990101Dto>();
            for (int j = 0; j < resultList_.get(i).size(); j++) {
                SCZ9901Model model = resultList_.get(i).get(j);
                menuList_.add(new SCZ990101Dto(model.getMenuId(), model.getMenuName()));

            }
            subForm_.add(menuList_);
        }

        inForm.setSubForm(subForm_);

        inForm.setUserId(UserSessionUtils.getUserId());

        inForm.setUserName(UserSessionUtils.getUserName());

        // 画面情報的设定。
        return this.getEventResult(inForm);
    }

    /**
     * 菜单编辑
     * 
     * @param _menuList 菜单リスト
     * @return 编辑結果
     */
    private List<List<SCZ9901Model>> editMenuList(List<SCZ9901Model> _menuList) {

        List<SCZ9901Model> rowMenuList_ = new ArrayList<SCZ9901Model>();
        List<List<SCZ9901Model>> resultList_ = new ArrayList<List<SCZ9901Model>>();

        if (_menuList == null || _menuList.isEmpty()) {
            return resultList_;
        }

        int menuTabJogenValue = StandardConstantsIF.KYOTU_MENU_TAB_JOGEN_VALUE.intValue();
        int minMenu = Math.min(_menuList.size(), StandardConstantsIF.KYOTU_MENU_JOGEN_VALUE.intValue());
        for (int i = 0; i < minMenu; i++) {
            SCZ9901Model _menu = _menuList.get(i);
            if (i != 0 && i % menuTabJogenValue == 0) {
                resultList_.add(rowMenuList_);
                rowMenuList_ = new ArrayList<SCZ9901Model>();
            }
            rowMenuList_.add(_menu);
        }
        resultList_.add(rowMenuList_);

        return resultList_;
    }

    /**
     * 门户画面DAO的设定。
     * 
     * @param scz9901Dao 门户画面DAO
     */
    public void setScz9901Dao(SCZ9901Dao scz9901Dao) {
        this.scz9901Dao = scz9901Dao;
    }
}

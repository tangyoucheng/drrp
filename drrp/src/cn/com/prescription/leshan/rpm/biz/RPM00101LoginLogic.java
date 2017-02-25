/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpm.biz;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import cn.com.prescription.framework.StandardConstantsIF;
import cn.com.prescription.framework.biz.StandardBiz;
import cn.com.prescription.framework.biz.StandardLogic;
import cn.com.prescription.framework.common.session.UserSessionInfo;
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
import cn.com.prescription.leshan.common.data.RpmUserDao;
import cn.com.prescription.leshan.common.data.condition.RpmUserCondition;
import cn.com.prescription.leshan.common.data.model.RpmUserModel;
import cn.com.prescription.leshan.rpm.action.form.RPM00101Form;
import cn.com.prescription.leshan.rpm.data.RPM00101Dao;
import cn.com.prescription.leshan.rpm.data.condition.RPM00101Condition;
import cn.com.prescription.leshan.rpm.data.model.RPM00101Model;

/**
 * 登录画面登录处理。
 * 
 * @author tyc
 */
/*
 * 新規作成
 * DATE: 2016.03.20 NAME: tyc
 */
public class RPM00101LoginLogic extends StandardBiz implements StandardLogic {

    /**
     * 登录画面DAO
     */
    private RPM00101Dao rpm00101Dao = null;

    /**
     * 用户基本表DAO
     */
    private RpmUserDao rpmUserDao = null;

    /**
     * 登录画面 登录業務クラス的构造。
     */
    public RPM00101LoginLogic() {
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

        RPM00101Form form = (RPM00101Form) _event.getForm();
        // 更新日時的取得。
        Timestamp sysTimestamp = TimestampUtils.getSysTimestamp();
        String sysdDate = DateUtils.format(DateUtils.getSysDate(), StandardConstantsIF.DATE_FORMAT_YYYYMMDD);

        RpmUserCondition _userKihonCondition = new RpmUserCondition();
        RpmUserModel record = new RpmUserModel();

        // 用户ＩＤの存在チェック
        RPM00101Condition _condition = new RPM00101Condition();
        _condition.setUserId(form.getUserId());
        _condition.setDeleteFlag(LeshanConstantsIF.DEL_FLG_YUKO_RECORD);
        List<RPM00101Model> userInfo = rpm00101Dao.selectUserInfo(_condition);

        if (userInfo == null || userInfo.isEmpty()) {
            this.addErrorMessage(MessageUtils.getMessage("W20003"));
            this.checkErrorEnd();
        }

        RPM00101Model userKihon = userInfo.get(0);
        // 用户名
        String userName = StringUtils.defaultString(userInfo.get(0).getUserName());

        // 密码＜＞「画面：密码」の場合
        // if (!CheckUtils.isEqual(AclUtils.decryptPassword(userKihon.getPassword()), form.getPassword())) {
        if (!CheckUtils.isEqual(userKihon.getPassword(), form.getPassword())) {
            // 密码がＤＢと一致しない場合
            form.setErrMsg(MessageUtils.getMessage("W20003"));
        }

        // 有効期間チェック
        if ((sysdDate.compareTo(userKihon.getEndDate()) > 0) || sysdDate.compareTo(userKihon.getStartDate()) < 0) {

            // 用户が有効期限外
            form.setErrMsg(MessageUtils.getMessage("W20003"));
            return this.getEventResult(form);
        }

        // 角色ID
        List<String> roleIdList = new ArrayList<String>();
        for (RPM00101Model model : userInfo) {
            // 角色ID
            String roleId = StringUtils.defaultString(model.getRoleId());
            if (!CheckUtils.isEmpty(roleId)) {
                roleIdList.add(roleId);
            }
        }
        // 角色ID
        HashSet<String> tempObject = new HashSet<String>(roleIdList);
        roleIdList.clear();
        roleIdList.addAll(tempObject);

        UserSessionInfo userSessionInfo = new UserSessionInfo();
        // 用户ID
        userSessionInfo.setUserId(form.getUserId());
        // 用户名
        userSessionInfo.setUserName(userName);
        // 角色ID
        userSessionInfo.setRoleId(roleIdList);
        // 登录日時
        userSessionInfo.setLoginTime(DateUtils.parseYMD(sysdDate));
        // schema
        userSessionInfo.setSchemaName(form.getStoreCode());
        form.setUserInfo(userSessionInfo);

        // セッション情報「 ページカラーパターン」の保存処理を行う
        // ServiceUtils.putSession("colorPattern", userKihon.getPageColorPtn());

        form.setGamenId("SCZ9901");
        return this.getEventResult(form);
    }

    /**
     * 登录画面DAO的设定。
     * 
     * @param rpm00101Dao 登录画面DAO
     */
    public void setRpm00101Dao(RPM00101Dao rpm00101Dao) {
        this.rpm00101Dao = rpm00101Dao;
    }

    /**
     * 用户基本表DAO的设定。
     * 
     * @param rpmUserDao 用户基本表DAO
     */
    public void setRpmUserDao(RpmUserDao rpmUserDao) {
        this.rpmUserDao = rpmUserDao;
    }

}

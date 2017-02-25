/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.common.session;

import java.util.Date;
import java.util.List;

import cn.com.prescription.framework.common.session.UserSessionInfo.MenuInfo;
import cn.com.prescription.framework.util.ServiceUtils;

/**
 * 用户セッション情報ユーティリティ.
 * 
 * @author nttdc
 */
public class UserSessionUtils {

    /** 登録用户のセッション情報を保持するKEY */
    public static final String SESSION_KEY = UserSessionUtils.class.getName().concat(".UserSessionInfo");

    /**
     * 用户ID的取得
     * 
     * @return 用户ID
     */
    public static String getUserId() {
        return getUserSessionInfo().getUserId();
    }

    /**
     * 用户名的取得
     * 
     * @return 用户名
     */
    public static String getUserName() {
        return getUserSessionInfo().getUserName();
    }

    /**
     * 角色ID的取得
     * 
     * @return 角色ID
     */
    public static List<String> getYakuwariNo() {
        return getUserSessionInfo().getRoleId();
    }

    /**
     * 角色ID的取得
     * 
     * @return 角色ID
     */
    public static Object[] getRoleIdArray() {
        Object[] yakuwariNoList = getUserSessionInfo().getRoleId().toArray();
        if (yakuwariNoList == null || yakuwariNoList.length == 0) {
            yakuwariNoList = new String[] { "" };
        }
        return yakuwariNoList;
    }

    /**
     * 登录タイム的取得
     * 
     * @return 登录タイム
     */
    public static Date getLoginTime() {
        return getUserSessionInfo().getLoginTime();
    }

    /**
     * 登录URL的取得
     * 
     * @return 登录URL
     */
    public static String getLoginUrl() {
        return getUserSessionInfo().getLoginUrl();
    }

    /**
     * スキーマ名的取得 　
     * 
     * @return スキーマ名
     */
//    public static String getSchemaName() {
//        return getUserSessionInfo().getSchemaName();
//    }

    /**
     * @return 菜单ID
     * @deprecated getMenuInfo() を利用して下さい
     */
    @Deprecated
    public static String getMenuId() {
        return getMenuInfo().getMenuId();
    }

    /**
     * @return 菜单情報
     */
    public static MenuInfo getMenuInfo() {
        return getUserSessionInfo().getMenuInfo();
    }

    /**
     * @return 菜单階層情報
     */
    public static List<MenuInfo> getMenuTree() {
        return getUserSessionInfo().getMenuTree();
    }

    /**
     * 登録用户のセッション情報を設定 <br>
     * 
     * @param _userInfo 登録用户のセッション情報
     */
    public static void setUserSessionInfo(UserSessionInfo _userInfo) {
        ServiceUtils.putSession(SESSION_KEY, _userInfo);
    }

    /**
     * 登録用户のセッション情報を取得 <br>
     * 
     * @return 登録用户のセッション情報
     */
    public static UserSessionInfo getUserSessionInfo() {
        if (ServiceUtils.getSession(SESSION_KEY) == null) {
            return new UserSessionInfo();
        }
        return ServiceUtils.getSession(SESSION_KEY);
    }

    /**
     * 登録用户のセッション情報クリア
     */
    public static void clearUserSessionInfo() {
        ServiceUtils.removeSession(SESSION_KEY);
    }
}

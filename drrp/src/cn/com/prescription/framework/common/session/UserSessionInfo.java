/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.common.session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户セッション情報.
 * <p>
 * 登录時、このインスタンスがセッションに格納される。
 * </p>
 * 
 * @author nttdc
 */
public class UserSessionInfo extends BackupDataSessionInfo {

    /** 串行版本号 */
    private static final long serialVersionUID = 779043845576160528L;

    /** 用户ID */
    private String userId = null;

    /** 用户名 */
    private String userName = null;

    /** 登录日時 */
    private Date loginTime = null;

    /** 登录URL */
    private String loginUrl = null;

    /** 接続スキーマ */
    private String schemaName = null;

    /** IPアドレス */
    private String userIp = null;

    /** OS */
    private String userOS = null;

    /** ブラウザ */
    private String userBrowser = null;

    /** 菜单情報 */
    private MenuInfo menuInfo = null;

    /** 菜单階層情報 */
    private List<MenuInfo> menuTree = new ArrayList<MenuInfo>();

    /** 角色ID */
    private List<String> roleId = new ArrayList<String>();

    /**
     * 用户ID的取得。
     * 
     * @return 用户ID
     */
    public final String getUserId() {
        return userId;
    }

    /**
     * 用户ID的设定。
     * 
     * @param _userId 用户ID
     */
    public final void setUserId(String _userId) {
        userId = _userId;
    }

    /**
     * 用户名的取得。
     * 
     * @return 用户名
     */
    public final String getUserName() {
        return userName;
    }

    /**
     * 用户名的设定。
     * 
     * @param _userName 用户名
     */
    public final void setUserName(String _userName) {
        userName = _userName;
    }

    /**
     * グループID的取得。
     * 
     * @return グループID
     */
    public final Date getLoginTime() {
        return loginTime;
    }

    /**
     * グループID的设定。
     * 
     * @param _loginTime グループID
     */
    public final void setLoginTime(Date _loginTime) {
        loginTime = _loginTime;
    }

    /**
     * 登录URL的取得。
     * 
     * @return 登录URL
     */
    public final String getLoginUrl() {
        return loginUrl;
    }

    /**
     * 登录URL的设定。
     * 
     * @param _loginUrl 登录URL
     */
    public final void setLoginUrl(String _loginUrl) {
        loginUrl = _loginUrl;
    }

    /**
     * 接続スキーマ的取得。
     * 
     * @return 接続スキーマ
     */
    public final String getSchemaName() {
        return schemaName;
    }

    /**
     * 接続スキーマ的设定。
     * 
     * @param _schemaName 接続スキーマ
     */
    public final void setSchemaName(String _schemaName) {
        schemaName = _schemaName;
    }

    /**
     * 組織名的取得。
     * 
     * @return 組織名
     */
    public final String getUserIp() {
        return userIp;
    }

    /**
     * 組織名的设定。
     * 
     * @param _userIp 組織名
     */
    public final void setUserIp(String _userIp) {
        userIp = _userIp;
    }

    /**
     * OS的取得。
     * 
     * @return OS
     */
    public final String getUserOS() {
        return userOS;
    }

    /**
     * OS的设定。
     * 
     * @param _userOS OS
     */
    public final void setUserOS(String _userOS) {
        userOS = _userOS;
    }

    /**
     * 角色ID的取得。
     * 
     * @return 角色ID
     */
    public List<String> getRoleId() {
        return roleId;
    }

    /**
     * 角色ID的设定。
     * 
     * @param roleId 角色ID
     */
    public void setRoleId(List<String> roleId) {
        this.roleId = roleId;
    }

    /**
     * ブラウザ的取得。
     * 
     * @return ブラウザ
     */
    public final String getUserBrowser() {
        return userBrowser;
    }

    /**
     * ブラウザ的设定。
     * 
     * @param _userBrowser ブラウザ
     */
    public final void setUserBrowser(String _userBrowser) {
        userBrowser = _userBrowser;
    }

    /**
     * 菜单情報的取得。
     * 
     * @return 菜单情報
     */
    public final MenuInfo getMenuInfo() {
        return menuInfo;
    }

    /**
     * 菜单情報的设定。
     * 
     * @param _menuInfo 菜单情報
     */
    public final void setMenuInfo(MenuInfo _menuInfo) {
        menuInfo = _menuInfo;
    }

    /**
     * @return 菜单階層情報
     */
    public final List<MenuInfo> getMenuTree() {
        return menuTree;
    }

    /**
     * @param _menuTree 菜单階層情報
     */
    public final void setMenuTree(List<MenuInfo> _menuTree) {
        menuTree = _menuTree;
    }

    /**
     * 菜单保持用クラス.
     */
    public static class MenuInfo implements Serializable {

        /** 串行版本号 */
        private static final long serialVersionUID = -6920117882385222025L;

        /** 菜单ID */
        private String menuId = null;
        /** 菜单名称 */
        private String menuName = null;

        /**
         * コンストラクタ.
         * 
         * @param _menuId 菜单ID
         */
        public MenuInfo(String _menuId) {
            menuId = _menuId;
        }

        /**
         * コンストラクタ.
         * 
         * @param _menuId 菜单ID
         * @param _menuName 菜单名称
         */
        public MenuInfo(String _menuId, String _menuName) {
            menuId = _menuId;
            menuName = _menuName;
        }

        /**
         * @return 菜单ID
         */
        public final String getMenuId() {
            return menuId;
        }

        /**
         * @param _menuId 菜单ID
         */
        public final void setMenuId(String _menuId) {
            menuId = _menuId;
        }

        /**
         * @return 菜单名称
         */
        public final String getMenuName() {
            return menuName;
        }

        /**
         * @param _menuName 菜单名称
         */
        public final void setMenuName(String _menuName) {
            menuName = _menuName;
        }
    }

}

/*
 * Copyright(c) 2016
 */

package cn.com.prescription.leshan.common.data.model;

import java.sql.Timestamp;
import cn.com.prescription.framework.data.model.StandardModel;

/**
 * 角色?限信息表 モデルオブジェクト。
 *
 * @author gl
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */
public class RpmRoleMenuModel extends StandardModel {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * テーブル定義
     */
    public static final String TABLE = "RPM_ROLE_MENU";

    /**
     * 角色ID
     */
    private String roleId = null;

    /**
     * 菜?ID
     */
    private String menuId = null;

    /**
     * 最?更新者
     */
    private String lastUpdateUserId = null;

    /**
     * 最?更新者名
     */
    private String lastUpdateUserName = null;

    /**
     * 最?更新日期
     */
    private Timestamp lastUpdateDate = null;

    /**
     * 角色?限信息表モデルBean 的构造。
     */
    public RpmRoleMenuModel() {
        super();
    }

    /**
     * 角色IDを取得する。
     *
     * @return 角色ID
     */
    public String getRoleId() {
        return this.roleId;
    }

    /**
     * 角色IDを設定する。
     *
     * @param _roleId 角色ID
     */
    public void setRoleId(String _roleId) {
        this.roleId = _roleId;
    }

    /**
     * 菜?IDを取得する。
     *
     * @return 菜?ID
     */
    public String getMenuId() {
        return this.menuId;
    }

    /**
     * 菜?IDを設定する。
     *
     * @param _menuId 菜?ID
     */
    public void setMenuId(String _menuId) {
        this.menuId = _menuId;
    }

    /**
     * 最?更新者を取得する。
     *
     * @return 最?更新者
     */
    public String getLastUpdateUserId() {
        return this.lastUpdateUserId;
    }

    /**
     * 最?更新者を設定する。
     *
     * @param _lastUpdateUserId 最?更新者
     */
    public void setLastUpdateUserId(String _lastUpdateUserId) {
        this.lastUpdateUserId = _lastUpdateUserId;
    }

    /**
     * 最?更新者名を取得する。
     *
     * @return 最?更新者名
     */
    public String getLastUpdateUserName() {
        return this.lastUpdateUserName;
    }

    /**
     * 最?更新者名を設定する。
     *
     * @param _lastUpdateUserName 最?更新者名
     */
    public void setLastUpdateUserName(String _lastUpdateUserName) {
        this.lastUpdateUserName = _lastUpdateUserName;
    }

    /**
     * 最?更新日期を取得する。
     *
     * @return 最?更新日期
     */
    public Timestamp getLastUpdateDate() {
        return this.lastUpdateDate;
    }

    /**
     * 最?更新日期を設定する。
     *
     * @param _lastUpdateDate 最?更新日期
     */
    public void setLastUpdateDate(Timestamp _lastUpdateDate) {
        this.lastUpdateDate = _lastUpdateDate;
    }

}

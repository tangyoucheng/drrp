/*
 * Copyright(c) 2016
 */

package cn.com.prescription.leshan.common.data.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import cn.com.prescription.framework.data.model.StandardModel;

/**
 * 菜?基本信息表 モデルオブジェクト。
 *
 * @author gl
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */
public class RpmMenuModel extends StandardModel {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * テーブル定義
     */
    public static final String TABLE = "RPM_MENU";

    /**
     * 菜?ID
     */
    private String menuId = null;

    /**
     * 菜?名
     */
    private String menuName = null;

    /**
     * 菜??型
     */
    private String menuType = null;

    /**
     * 菜???ID
     */
    private String hierarchyId = null;

    /**
     * 排序?
     */
    private BigDecimal sortKey = null;

    /**
     * 父菜?ID
     */
    private String parentMenuId = null;

    /**
     * URL
     */
    private String url = null;

    /**
     * ?除??
     */
    private String deleteFlag = null;

    /**
     * ?建者
     */
    private String createUserId = null;

    /**
     * ?建日期
     */
    private String createDate = null;

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
     * 菜?基本信息表モデルBean 的构造。
     */
    public RpmMenuModel() {
        super();
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
     * 菜?名を取得する。
     *
     * @return 菜?名
     */
    public String getMenuName() {
        return this.menuName;
    }

    /**
     * 菜?名を設定する。
     *
     * @param _menuName 菜?名
     */
    public void setMenuName(String _menuName) {
        this.menuName = _menuName;
    }

    /**
     * 菜??型を取得する。
     *
     * @return 菜??型
     */
    public String getMenuType() {
        return this.menuType;
    }

    /**
     * 菜??型を設定する。
     *
     * @param _menuType 菜??型
     */
    public void setMenuType(String _menuType) {
        this.menuType = _menuType;
    }

    /**
     * 菜???IDを取得する。
     *
     * @return 菜???ID
     */
    public String getHierarchyId() {
        return this.hierarchyId;
    }

    /**
     * 菜???IDを設定する。
     *
     * @param _hierarchyId 菜???ID
     */
    public void setHierarchyId(String _hierarchyId) {
        this.hierarchyId = _hierarchyId;
    }

    /**
     * 排序?を取得する。
     *
     * @return 排序?
     */
    public BigDecimal getSortKey() {
        return this.sortKey;
    }

    /**
     * 排序?を設定する。
     *
     * @param _sortKey 排序?
     */
    public void setSortKey(BigDecimal _sortKey) {
        this.sortKey = _sortKey;
    }

    /**
     * 父菜?IDを取得する。
     *
     * @return 父菜?ID
     */
    public String getParentMenuId() {
        return this.parentMenuId;
    }

    /**
     * 父菜?IDを設定する。
     *
     * @param _parentMenuId 父菜?ID
     */
    public void setParentMenuId(String _parentMenuId) {
        this.parentMenuId = _parentMenuId;
    }

    /**
     * URLを取得する。
     *
     * @return URL
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * URLを設定する。
     *
     * @param _url URL
     */
    public void setUrl(String _url) {
        this.url = _url;
    }

    /**
     * ?除??を取得する。
     *
     * @return ?除??
     */
    public String getDeleteFlag() {
        return this.deleteFlag;
    }

    /**
     * ?除??を設定する。
     *
     * @param _deleteFlag ?除??
     */
    public void setDeleteFlag(String _deleteFlag) {
        this.deleteFlag = _deleteFlag;
    }

    /**
     * ?建者を取得する。
     *
     * @return ?建者
     */
    public String getCreateUserId() {
        return this.createUserId;
    }

    /**
     * ?建者を設定する。
     *
     * @param _createUserId ?建者
     */
    public void setCreateUserId(String _createUserId) {
        this.createUserId = _createUserId;
    }

    /**
     * ?建日期を取得する。
     *
     * @return ?建日期
     */
    public String getCreateDate() {
        return this.createDate;
    }

    /**
     * ?建日期を設定する。
     *
     * @param _createDate ?建日期
     */
    public void setCreateDate(String _createDate) {
        this.createDate = _createDate;
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

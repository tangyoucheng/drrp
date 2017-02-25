/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.leshan.common.action.form;

import cn.com.prescription.framework.action.form.AbstractForm;

/**
 * 菜单起動時処理用form
 * 
 * @author bpchikazawa
 */
public class MenuBootForm extends AbstractForm {

    /** serialVersionUID */
    private static final long serialVersionUID = -290638759896761522L;

    /**
     * 菜单ID
     */
    private String menuId = null;

    /**
     * 菜单表示名
     */
    private String menuDisplayName = null;

    /**
     * 親菜单ＩＤ
     */
    private String parentMenuId = null;

    /**
     * 親菜单名
     */
    private String parentMenuDisplayName = null;

    /** URL */
    private String url = null;

    /**
     * 菜单IDを取得します。
     * @return 菜单ID
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * 菜单IDを設定します。
     * @param menuId 菜单ID
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    /**
     * 菜单表示名を取得します。
     * @return 菜单表示名
     */
    public String getMenuDisplayName() {
        return menuDisplayName;
    }

    /**
     * 菜单表示名を設定します。
     * @param menuDisplayName 菜单表示名
     */
    public void setMenuDisplayName(String menuDisplayName) {
        this.menuDisplayName = menuDisplayName;
    }

    /**
     * 親菜单ＩＤを取得します。
     * @return 親菜单ＩＤ
     */
    public String getParentMenuId() {
        return parentMenuId;
    }

    /**
     * 親菜单ＩＤを設定します。
     * @param parentMenuId 親菜单ＩＤ
     */
    public void setParentMenuId(String parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    /**
     * 親菜单名を取得します。
     * @return 親菜单名
     */
    public String getParentMenuDisplayName() {
        return parentMenuDisplayName;
    }

    /**
     * 親菜单名を設定します。
     * @param parentMenuDisplayName 親菜单名
     */
    public void setParentMenuDisplayName(String parentMenuDisplayName) {
        this.parentMenuDisplayName = parentMenuDisplayName;
    }

    /**
     * URLを取得します。
     * @return URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * URLを設定します。
     * @param _url URL
     */
    public void setUrl(String _url) {
        url = _url;
    }

}

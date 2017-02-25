/*
 * Copyright(c) 2011 
 */

package cn.com.prescription.leshan.scz.action.form;

import cn.com.prescription.framework.action.form.AbstractDto;

/**
 * SCZ990101Dto。
 * 
 * @author kourei
 */
/*
 * 新規作成 DATE: 2012.02.13 NAME: kourei
 */
public class SCZ990101Dto extends AbstractDto {

    /**
     * 串行版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 菜单ＩＤ
     */
    private String menuId = null;

    /**
     * 菜单名
     */
    private String menuName = null;

    /**
     * コンストラクタ.
     */
    public SCZ990101Dto() {
        super();
    }

    /**
     * コンストラクタ.
     * 
     * @param _menuId 菜单ＩＤ
     * @param _menuName 菜单名
     */
    public SCZ990101Dto(String _menuId, String _menuName) {
        super();
        menuId = _menuId;
        menuName = _menuName;
    }

    /**
     * 菜单ＩＤを取得します。
     * 
     * @return 菜单ＩＤ
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * 菜单ＩＤを設定します。
     * 
     * @param menuId 菜单ＩＤ
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    /**
     * 菜单名を取得します。
     * 
     * @return 菜单名
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 菜单名を設定します。
     * 
     * @param menuName 菜单名
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}

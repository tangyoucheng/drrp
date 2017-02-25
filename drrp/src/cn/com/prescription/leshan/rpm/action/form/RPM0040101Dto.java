/*
 * Copyright(c) 2016 
 */

package cn.com.prescription.leshan.rpm.action.form;

import cn.com.prescription.framework.action.form.AbstractDto;

/**
 * 患者信息导出dto。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.09 NAME: fsb
 */
public class RPM0040101Dto extends AbstractDto {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * メンニュ
     */
    private String menu = null;

    /**
     * サブメンニュ
     */
    private String subMenu = null;

    /**
     * 機能メンニュ
     */
    private String kinoMenu = null;

    /**
     * hid機能菜单ＩＤ
     */
    private String hidMenuId = null;

    /**
     * hid機能ＩＤ
     */
    private String hidKinoId = null;

    /**
     * hid許可
     */
    private String hidChikkuKinoId = null;

    /**
     * メンニュ的取得
     * 
     * @return メンニュ
     */
    public String getMenu() {
        return menu;
    }

    /**
     * メンニュ的设定
     * 
     * @param _menu メンニュ
     */
    public void setMenu(String _menu) {
        this.menu = _menu;
    }

    /**
     * サブメンニュ的取得
     * 
     * @return サブメンニュ
     */
    public String getSubMenu() {
        return subMenu;
    }

    /**
     * サブメンニュ的设定
     * 
     * @param _subMenu サブメンニュ
     */
    public void setSubMenu(String _subMenu) {
        this.subMenu = _subMenu;
    }

    /**
     * 機能メンニュ的取得
     * 
     * @return 機能メンニュ
     */
    public String getKinoMenu() {
        return kinoMenu;
    }

    /**
     * 機能メンニュ的设定
     * 
     * @param _kinoMenu 機能メンニュ
     */
    public void setKinoMenu(String _kinoMenu) {
        this.kinoMenu = _kinoMenu;
    }

    /**
     * hid機能菜单ＩＤ的取得
     * 
     * @return hid機能菜单ＩＤ
     */
    public String getHidMenuId() {
        return hidMenuId;
    }

    /**
     * hid機能菜单ＩＤ的设定
     * 
     * @param _hidMenuId hid機能菜单ＩＤ
     */
    public void setHidMenuId(String _hidMenuId) {
        this.hidMenuId = _hidMenuId;
    }

    /**
     * hid機能ＩＤ的取得
     * 
     * @return hid機能ＩＤ
     */
    public String getHidKinoId() {
        return hidKinoId;
    }

    /**
     * hid機能ＩＤ的设定
     * 
     * @param _hidKinoId hid機能ＩＤ
     */
    public void setHidKinoId(String _hidKinoId) {
        this.hidKinoId = _hidKinoId;
    }

    /**
     * hid許可的取得
     * 
     * @return hid許可
     */
    public String getHidChikkuKinoId() {
        return hidChikkuKinoId;
    }

    /**
     * hid許可的设定
     * 
     * @param _hidChikkuKinoId hid許可
     */
    public void setHidChikkuKinoId(String _hidChikkuKinoId) {
        this.hidChikkuKinoId = _hidChikkuKinoId;
    }

}

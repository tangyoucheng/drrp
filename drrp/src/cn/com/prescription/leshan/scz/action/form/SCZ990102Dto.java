/*
 * Copyright(c) 2011 
 */

package cn.com.prescription.leshan.scz.action.form;

import java.util.ArrayList;
import java.util.List;

import cn.com.prescription.framework.action.form.AbstractDto;

/**
 * SCZ990102Dto。
 * 
 * @author kourei
 */
/*
 * 新規作成 DATE: 2012.02.13 NAME: kourei
 */
public class SCZ990102Dto extends AbstractDto {

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
     * ＵＲＬ
     */
    private String url = null;

    /**
     * 菜单タイプ
     */
    private String menuType = null;

    /**
     * 一覧データ
     */
    private List<SCZ990102Dto> menuLinkList = new ArrayList<SCZ990102Dto>();

    /**
     * コンストラクタ.
     */
    public SCZ990102Dto() {
        super();
    }

    /**
     * コンストラクタ.
     * 
     * @param _menuId 菜单ＩＤ
     * @param _menuName 菜单名
     * @param _url ＵＲＬ
     * @param _menuType ＵＲＬ
     */
    public SCZ990102Dto(String _menuId, String _url, String _menuName, String _menuType) {
        super();
        menuId = _menuId;
        menuName = _menuName;
        url = _url;
        menuType = _menuType;
    }

    /**
     * 菜单ＩＤを取得します。
     * @return 菜单ＩＤ
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * 菜单ＩＤを設定します。
     * @param menuId 菜单ＩＤ
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    /**
     * 菜单名を取得します。
     * @return 菜单名
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 菜单名を設定します。
     * @param menuName 菜单名
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * ＵＲＬを取得します。
     * @return ＵＲＬ
     */
    public String getUrl() {
        return url;
    }

    /**
     * ＵＲＬを設定します。
     * @param url ＵＲＬ
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 菜单タイプを取得します。
     * @return 菜单タイプ
     */
    public String getMenuType() {
        return menuType;
    }

    /**
     * 菜单タイプを設定します。
     * @param menuType 菜单タイプ
     */
    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    /**
     * 一覧データ的取得。
     * @return 一覧データ
     */
    public List<SCZ990102Dto> getMenuLinkList() {
        return menuLinkList;
    }

    /**
     * 一覧データ的设定。
     * @param menuLinkList 一覧データ
     */
    public void setMenuLinkList(List<SCZ990102Dto> menuLinkList) {
        this.menuLinkList = menuLinkList;
    }
}

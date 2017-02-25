/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.leshan.scz.action.form;

import java.util.ArrayList;
import java.util.List;

import cn.com.prescription.framework.action.form.AbstractForm;

/**
 * SCZ990102Form 门户画面 form。
 * 
 * @author kourei
 */
/*
 * 新規作成 DATE: 2010.03.10 NAME: kourei
 */
public class SCZ990102Form extends AbstractForm {

    private static final long serialVersionUID = 1L;

    /**
     * タイトル
     */
    private String title = null;

    /**
     * 親菜单ＩＤ
     */
    private String parentMenuId = null;

    /**
     * 親菜单名
     */
    private String parentMenuDisplayName = null;

    /**
     * 一覧データ
     */
    private List<SCZ990102Dto> menuMiddleList = new ArrayList<SCZ990102Dto>();

    /**
     * 登录form 的构造。
     */
    public SCZ990102Form() {
        super();
    }

    /**
     * タイトル的取得。
     * 
     * @return タイトル
     */
    public String getTitle() {
        return title;
    }

    /**
     * タイトル的设定。
     * 
     * @param _title タイトル
     */
    public void setTitle(String _title) {
        this.title = _title;
    }

    /**
     * 親菜单ＩＤ的取得。
     * 
     * @return 親菜单ＩＤ
     */
    public String getParentMenuId() {
        return parentMenuId;
    }

    /**
     * 親菜单ＩＤ的设定。
     * 
     * @param _parentMenuId 親菜单ＩＤ
     */
    public void setParentMenuId(String _parentMenuId) {
        this.parentMenuId = _parentMenuId;
    }

    /**
     * 親菜单名的取得。
     * 
     * @return 親菜单名
     */
    public String getParentMenuDisplayName() {
        return this.parentMenuDisplayName;
    }

    /**
     * 親菜单名的设定。
     * 
     * @param _parentMenuDisplayName 親菜单名
     */
    public void setParentMenuDisplayName(String _parentMenuDisplayName) {
        this.parentMenuDisplayName = _parentMenuDisplayName;
    }

    /**
     * 一覧データ的取得。
     * 
     * @return 一覧データ
     */
    public List<SCZ990102Dto> getMenuMiddleList() {
        return menuMiddleList;
    }

    /**
     * 一覧データ的设定。
     * 
     * @param menuMiddleList 一覧データ
     */
    public void setMenuMiddleList(List<SCZ990102Dto> menuMiddleList) {
        this.menuMiddleList = menuMiddleList;
    }

}

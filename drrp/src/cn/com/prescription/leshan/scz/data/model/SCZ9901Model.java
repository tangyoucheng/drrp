/*
 * Copyright(c) 2011 
 */

package cn.com.prescription.leshan.scz.data.model;

import cn.com.prescription.leshan.common.data.model.RpmMenuModel;

/**
 * SCZ9901 门户画面のモードル
 * 
 * @author t.y.c
 */
/*
 * 新規作成 DATE: 2012.04.10 NAME: t.y.c
 */
public class SCZ9901Model extends RpmMenuModel {

    /**
     * 串行版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * URL
     */
    private String url = null;

    /**
     * 親菜单名
     */
    private String parentMenuName = null;

    /**
     * 説明
     */
    private String setsumei = null;

    /**
     * SCZ9901 门户画面的构造。
     */
    public SCZ9901Model() {
        super();
    }

    /**
     * URLを取得します。
     * 
     * @return URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * URLを設定します。
     * 
     * @param url URL
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 親菜单名を取得します。
     * 
     * @return 親菜单名
     */
    public String getParentMenuName() {
        return parentMenuName;
    }

    /**
     * 親菜单名を設定します。
     * 
     * @param parentMenuName 親菜单名
     */
    public void setParentMenuName(String parentMenuName) {
        this.parentMenuName = parentMenuName;
    }

    /**
     * 説明的取得。
     * @return 説明
     */
    public String getSetsumei() {
        return setsumei;
    }

    /**
     * 説明的设定。
     * @param _setsumei 説明
     */
    public void setSetsumei(String _setsumei) {
        this.setsumei = _setsumei;
    }

}

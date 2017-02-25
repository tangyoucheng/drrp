/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpm.data.model;

import cn.com.prescription.leshan.common.data.model.RpmMenuModel;

/**
 * 患者情报检索画面のモードル
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPM00401Model extends RpmMenuModel {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * サブ菜单名
     */
    private String subMenuName = null;

    /**
     * 機能菜单名
     */
    private String kinoMenuName = null;

    /**
     * チェック機能ＩＤ
     */
    private String chikkukinoId = null;

    /**
     * ログイン画面モデル 的构造。
     */
    public RPM00401Model() {
        super();
    }

    /**
     * サブ菜单名的取得
     * @return サブ菜单名
     */
    public String getSubMenuName() {
        return subMenuName;
    }

    /**
     * サブ菜单名的设定
     * @param __subMenuName サブ菜单名
     */
    public void setSubMenuName(String _subMenuName) {
        this.subMenuName = _subMenuName;
    }

    /**
     * 機能菜单名的取得
     * @return 機能菜单名
     */
    public String getKinoMenuName() {
        return kinoMenuName;
    }

    /**
     * 機能菜单名的设定
     * @param __kinoMenuName 機能菜单名
     */
    public void setKinoMenuName(String _kinoMenuName) {
        this.kinoMenuName = _kinoMenuName;
    }

    /**
     * チェック機能ＩＤ的取得
     * @return チェック機能ＩＤ
     */
    public String getChikkukinoId() {
        return chikkukinoId;
    }

    /**
     * チェック機能ＩＤ的设定
     * @param _chikkukinoId チェック機能ＩＤ
     */
    public void setChikkukinoId(String _chikkukinoId) {
        this.chikkukinoId = _chikkukinoId;
    }

}

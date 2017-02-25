/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpm.data.condition;

import cn.com.prescription.leshan.common.data.condition.RpmRoleMenuCondition;

/**
 * 患者情报检索画面のコンディション
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPM00401Condition extends RpmRoleMenuCondition {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 定数：ページ
     */
    private String Page = null;

    /**
     * 定数：1
     */
    private String teisu1 = null;

    /**
     * 定数:菜单
     */
    private String Menu = null;

    /**
     * 删除标识
     */
    private String deleteFlag = null;

    /**
     * 用户情報設定画面Condition的构造
     */
    public RPM00401Condition() {
        super();
    }

    /**
     * 定数：ページ的取得
     * 
     * @return 定数：ページ
     */
    public String getPage() {
        return Page;
    }

    /**
     * 定数：ページ的设定
     * 
     * @param __Page 定数：ページ
     */
    public void setPage(String _Page) {
        this.Page = _Page;
    }

    /**
     * 定数：1的取得
     * 
     * @return 定数：1
     */
    public String getTeisu1() {
        return teisu1;
    }

    /**
     * 定数：1的设定
     * 
     * @param __teisu1 定数：1
     */
    public void setTeisu1(String _teisu1) {
        this.teisu1 = _teisu1;
    }

    /**
     * 定数:菜单的取得
     * 
     * @return 定数:菜单
     */
    public String getMenu() {
        return Menu;
    }

    /**
     * 定数:菜单的设定
     * 
     * @param __Menu 定数:菜单
     */
    public void setMenu(String _Menu) {
        this.Menu = _Menu;
    }

    /**
     * 删除标识的取得
     * 
     * @return 删除标识
     */
    public String getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * 删除标识的设定
     * 
     * @param _deleteFlag 删除标识
     */
    public void setDeleteFlag(String _deleteFlag) {
        this.deleteFlag = _deleteFlag;
    }
}
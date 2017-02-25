/*
 * Copyright(c) 2016 
 */

package cn.com.prescription.leshan.rpm.action.form;

import java.util.ArrayList;
import java.util.List;

import cn.com.prescription.framework.CodeValueRecord;
import cn.com.prescription.framework.action.form.AbstractForm;

/**
 * 患者信息导出 form。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.09 NAME: fsb
 */
public class RPM00401Form extends AbstractForm {

    /**
     * 串行版本UID
     */
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
    private List<RPM0040101Dto> menuMiddleList = new ArrayList<RPM0040101Dto>();

    /**
     * 機能菜单一覧データソース
     */
    private List<RPM0040101Dto> subForm1 = new ArrayList<RPM0040101Dto>();

    /**
     * 登录form 的构造。
     */
    public RPM00401Form() {
        super();
    }

    /**
     * 角色ID
     */
    private String roleId;
    /**
     * 角色
     */
    private List<CodeValueRecord> roleDataSource;
    /**
     * 菜单分类ID
     */
    private String menuHierarchy1Id;
    /**
     * 菜单分类
     */
    private List<CodeValueRecord> menuHierarchy1DataSource;

    /**
     * タイトル的取得
     * @return タイトル
     */
    public String getTitle() {
        return title;
    }

    /**
     * タイトル的设定
     * @param __title タイトル
     */
    public void setTitle(String _title) {
        this.title = _title;
    }

    /**
     * 親菜单ＩＤ的取得
     * @return 親菜单ＩＤ
     */
    public String getParentMenuId() {
        return parentMenuId;
    }

    /**
     * 親菜单ＩＤ的设定
     * @param __parentMenuId 親菜单ＩＤ
     */
    public void setParentMenuId(String _parentMenuId) {
        this.parentMenuId = _parentMenuId;
    }

    /**
     * 親菜单名的取得
     * @return 親菜单名
     */
    public String getParentMenuDisplayName() {
        return this.parentMenuDisplayName;
    }

    /**
     * 親菜单名的设定
     * @param __parentMenuDisplayName 親菜单名
     */
    public void setParentMenuDisplayName(String _parentMenuDisplayName) {
        this.parentMenuDisplayName = _parentMenuDisplayName;
    }

    /**
     * 一覧データ的取得
     * @return 一覧データ
     */
    public List<RPM0040101Dto> getMenuMiddleList() {
        return menuMiddleList;
    }

    /**
     * 一覧データ的设定
     * @param _menuMiddleList 一覧データ
     */
    public void setMenuMiddleList(List<RPM0040101Dto> menuMiddleList) {
        this.menuMiddleList = menuMiddleList;
    }

    /**
     * 機能菜单一覧データソース的取得
     * @return 機能菜单一覧データソース
     */
    public List<RPM0040101Dto> getSubForm1() {
        return subForm1;
    }

    /**
     * 機能菜单一覧データソース的设定
     * @param _subForm1 機能菜单一覧データソース
     */
    public void setSubForm1(List<RPM0040101Dto> _subForm1) {
        this.subForm1 = _subForm1;
    }

    /**
     * 角色ID的取得
     * @return 角色ID
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 角色ID的设定
     * @param __roleId 角色ID
     */
    public void setRoleId(String _roleId) {
        this.roleId = _roleId;
    }

    /**
     * 角色的取得
     * @return 角色
     */
    public List<CodeValueRecord> getRoleDataSource() {
        return roleDataSource;
    }

    /**
     * 角色的设定
     * @param __roleDataSource 角色
     */
    public void setRoleDataSource(List<CodeValueRecord> _roleDataSource) {
        this.roleDataSource = _roleDataSource;
    }

    /**
     * 菜单分类ID的取得
     * @return 菜单分类ID
     */
    public String getMenuHierarchy1Id() {
        return menuHierarchy1Id;
    }

    /**
     * 菜单分类ID的设定
     * @param __menuHierarchy1Id 菜单分类ID
     */
    public void setMenuHierarchy1Id(String _menuHierarchy1Id) {
        this.menuHierarchy1Id = _menuHierarchy1Id;
    }

    /**
     * 菜单分类的取得
     * @return 菜单分类
     */
    public List<CodeValueRecord> getMenuHierarchy1DataSource() {
        return menuHierarchy1DataSource;
    }

    /**
     * 菜单分类的设定
     * @param __menuHierarchy1DataSource 菜单分类
     */
    public void setMenuHierarchy1DataSource(List<CodeValueRecord> _menuHierarchy1DataSource) {
        this.menuHierarchy1DataSource = _menuHierarchy1DataSource;
    }
}

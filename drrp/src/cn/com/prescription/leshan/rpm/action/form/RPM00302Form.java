/*
 * Copyright(c) 2016 
 */

package cn.com.prescription.leshan.rpm.action.form;

import java.util.ArrayList;
import java.util.List;

import cn.com.prescription.framework.action.form.AbstractForm;

/**
 * 角色一览form。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.09 NAME: fsb
 */
public class RPM00302Form extends AbstractForm {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private String roleId = null;

    /**
     * 角色名
     */
    private String roleName = null;

    /**
     * 页面类型
     */
    private String pageType = null;

    /**
     * subForm1
     */
    private List<RPM0030201Dto> subForm1 = new ArrayList<RPM0030201Dto>();

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
     * 角色名的取得
     * @return 角色名
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 角色名的设定
     * @param __roleName 角色名
     */
    public void setRoleName(String _roleName) {
        this.roleName = _roleName;
    }

    /**
     * 页面类型的取得
     * @return 页面类型
     */
    public String getPageType() {
        return pageType;
    }

    /**
     * 页面类型的设定
     * @param _pageType 页面类型
     */
    public void setPageType(String _pageType) {
        this.pageType = _pageType;
    }

    /**
     * subForm1的取得
     * @return subForm1
     */
    public List<RPM0030201Dto> getSubForm1() {
        return subForm1;
    }

    /**
     * subForm1的设定
     * @param __subForm1 subForm1
     */
    public void setSubForm1(List<RPM0030201Dto> _subForm1) {
        this.subForm1 = _subForm1;
    }

}

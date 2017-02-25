/*
 * Copyright(c) 2016 
 */

package cn.com.prescription.leshan.rpm.action.form;

import cn.com.prescription.framework.action.form.AbstractForm;

/**
 * 角色编辑dto。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.09 NAME: fsb
 */
public class RPM00303Form extends AbstractForm {

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
     * 备注
     */
    private String notes = null;

    /**
     * 最终更新日期
     */
    private String lastUpdateDate = null;

    /**
     * 角色ID的取得
     * 
     * @return 角色ID
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 角色ID的设定
     * 
     * @param __roleId 角色ID
     */
    public void setRoleId(String _roleId) {
        this.roleId = _roleId;
    }

    /**
     * 角色名的取得
     * 
     * @return 角色名
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 角色名的设定
     * 
     * @param _roleName 角色名
     */
    public void setRoleName(String _roleName) {
        this.roleName = _roleName;
    }

    /**
     * 备注的取得
     * 
     * @return 备注
     */
    public String getNotes() {
        return notes;
    }

    /**
     * 备注的设定
     * 
     * @param __notes 备注
     */
    public void setNotes(String _notes) {
        this.notes = _notes;
    }

    /**
     * 最终更新日期的取得
     * 
     * @return 最终更新日期
     */
    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * 最终更新日期的设定
     * 
     * @param _lastUpdateDate 最终更新日期
     */
    public void setLastUpdateDate(String _lastUpdateDate) {
        this.lastUpdateDate = _lastUpdateDate;
    }

}

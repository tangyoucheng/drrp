/*
 * Copyright(c) 2011 
 */

package cn.com.prescription.leshan.scz.data.condition;

import cn.com.prescription.leshan.common.data.condition.RpmMenuCondition;

/**
 * SCZ9901 门户画面のコンディション
 * 
 * @author t.y.c
 */
/*
 * 新規作成 DATE: 2012.03.07 NAME: t.y.c
 */
public class SCZ9901Condition extends RpmMenuCondition {

    /**
     * 串行版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private String userId = null;

    /**
     * 角色ID
     */
    private Object[] roleIdList = null;

    /**
     * SCZ9901 门户画面Condition的构造
     */
    public SCZ9901Condition() {
        super();
    }

    /**
     * 用户ID的取得。
     * @return 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 用户ID的设定。
     * @param userId 用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 角色ID的取得。
     * @return 角色ID
     */
    public Object[] getRoleIdList() {
        return roleIdList;
    }

    /**
     * 角色ID的设定。
     * @param objects 角色ID
     */
    public void setRoleIdList(Object[] objects) {
        this.roleIdList = objects;
    }
}
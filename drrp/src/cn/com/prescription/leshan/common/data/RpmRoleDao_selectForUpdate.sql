/*
 * Copyright(c) 2016
 */
/**
 * 角色信息表DAOIF 排他処理
 * RpmRoleDao_selectForUpdate.sql
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */

SELECT
    role_id,
    role_name,
    notes,
    delete_flag,
    create_user_id,
    create_date,
    last_update_user_id,
    last_update_user_name,
    last_update_date
FROM
    rpm_role
/*BEGIN*/WHERE
    /*IF condition.roleId != null && condition.roleId != "" */
        AND role_id = /*condition.roleId*/
    /*END*/
    /*IF condition.roleName != null && condition.roleName != "" */
        AND role_name = /*condition.roleName*/
    /*END*/
    /*IF condition.notes != null && condition.notes != "" */
        AND notes = /*condition.notes*/
    /*END*/
    /*IF condition.deleteFlag != null && condition.deleteFlag != "" */
        AND delete_flag = /*condition.deleteFlag*/
    /*END*/
    /*IF condition.createUserId != null && condition.createUserId != "" */
        AND create_user_id = /*condition.createUserId*/
    /*END*/
    /*IF condition.createDate != null */
        AND create_date = /*condition.createDate*/
    /*END*/
    /*IF condition.lastUpdateUserId != null && condition.lastUpdateUserId != "" */
        AND last_update_user_id = /*condition.lastUpdateUserId*/
    /*END*/
    /*IF condition.lastUpdateUserName != null && condition.lastUpdateUserName != "" */
        AND last_update_user_name = /*condition.lastUpdateUserName*/
    /*END*/
    /*IF condition.lastUpdateDate != null */
        AND last_update_date = /*condition.lastUpdateDate*/
    /*END*/
/*END*/
FOR UPDATE NOWAIT

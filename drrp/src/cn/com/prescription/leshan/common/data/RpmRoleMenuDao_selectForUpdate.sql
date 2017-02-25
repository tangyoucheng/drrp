/*
 * Copyright(c) 2016
 */
/**
 * 角色?限信息表DAOIF 排他処理
 * RpmRoleMenuDao_selectForUpdate.sql
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */

SELECT
    role_id,
    menu_id,
    last_update_user_id,
    last_update_user_name,
    last_update_date
FROM
    rpm_role_menu
/*BEGIN*/WHERE
    /*IF condition.roleId != null && condition.roleId != "" */
        AND role_id = /*condition.roleId*/
    /*END*/
    /*IF condition.menuId != null && condition.menuId != "" */
        AND menu_id = /*condition.menuId*/
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

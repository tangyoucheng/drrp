/*
 * 
 */
/**
 * 角色信息表DAOIF ソート処理
 * RpmRoleDao_selectAllRoleList.sql
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */

SELECT
    role_id,
    role_name,
    notes
FROM
    rpm_role
/*BEGIN*/WHERE
    /*IF condition.roleId != null && condition.roleId != "" */
        AND role_id like /*condition.roleId*/
    /*END*/
    /*IF condition.roleName != null && condition.roleName != "" */
        AND role_name like /*condition.roleName*/
    /*END*/
    /*IF condition.deleteFlag != null && condition.deleteFlag != "" */
        AND delete_flag = /*condition.deleteFlag*/
    /*END*/
/*END*/
ORDER BY role_name

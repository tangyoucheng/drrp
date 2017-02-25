/*
 * Copyright(c) 2016
 */
/**
 * 菜?基本信息表DAOIF ソート処理
 * RpmMenuDao_selectBySort.sql
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */

SELECT
    menu_id,
    menu_name,
    menu_type,
    hierarchy_id,
    sort_key,
    parent_menu_id,
    url,
    delete_flag,
    create_user_id,
    create_date,
    last_update_user_id,
    last_update_user_name,
    last_update_date
FROM
    rpm_menu
/*BEGIN*/WHERE
    /*IF condition.menuId != null && condition.menuId != "" */
        AND menu_id = /*condition.menuId*/
    /*END*/
    /*IF condition.menuName != null && condition.menuName != "" */
        AND menu_name = /*condition.menuName*/
    /*END*/
    /*IF condition.menuType != null && condition.menuType != "" */
        AND menu_type = /*condition.menuType*/
    /*END*/
    /*IF condition.hierarchyId != null && condition.hierarchyId != "" */
        AND hierarchy_id = /*condition.hierarchyId*/
    /*END*/
    /*IF condition.sortKey != null */
        AND sort_key = /*condition.sortKey*/
    /*END*/
    /*IF condition.parentMenuId != null && condition.parentMenuId != "" */
        AND parent_menu_id = /*condition.parentMenuId*/
    /*END*/
    /*IF condition.url != null && condition.url != "" */
        AND url = /*condition.url*/
    /*END*/
    /*IF condition.deleteFlag != null && condition.deleteFlag != "" */
        AND delete_flag = /*condition.deleteFlag*/
    /*END*/
    /*IF condition.createUserId != null && condition.createUserId != "" */
        AND create_user_id = /*condition.createUserId*/
    /*END*/
    /*IF condition.createDate != null && condition.createDate != "" */
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
/*IF condition.sortClause != null && condition.sortClause != ""*/
   ORDER BY /*$condition.sortClause*/
/*END*/

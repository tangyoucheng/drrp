/*
 * Copyright(c) 2016
 */
/**
 * 用?基本信息表DAOIF 排他処理
 * RpmUserDao_selectForUpdate.sql
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */

SELECT
    user_id,
    start_date,
    end_date,
    password,
    last_login_date,
    notes,
    delete_flag,
    create_user_id,
    create_date,
    last_update_user_id,
    last_update_user_name,
    last_update_date
FROM
    rpm_user
/*BEGIN*/WHERE
    /*IF condition.userId != null && condition.userId != "" */
        AND user_id = /*condition.userId*/
    /*END*/
    /*IF condition.startDate != null && condition.startDate != "" */
        AND start_date = /*condition.startDate*/
    /*END*/
    /*IF condition.endDate != null && condition.endDate != "" */
        AND end_date = /*condition.endDate*/
    /*END*/
    /*IF condition.password != null && condition.password != "" */
        AND password = /*condition.password*/
    /*END*/
    /*IF condition.lastLoginDate != null */
        AND last_login_date = /*condition.lastLoginDate*/
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

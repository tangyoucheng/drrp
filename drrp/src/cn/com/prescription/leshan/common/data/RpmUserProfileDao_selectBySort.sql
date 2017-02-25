/*
 * Copyright(c) 2016
 */
/**
 * 用?基本信息表DAOIF ソート処理
 * RpmUserProfileDao_selectBySort.sql
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */

SELECT
    user_id,
    user_name,
    birthday,
    sex_id,
    post_number,
    addr,
    phone_number,
    ceel_number,
    email,
    identity_image,
    delete_flag,
    create_user_id,
    create_date,
    last_update_user_id,
    last_update_user_name,
    last_update_date
FROM
    rpm_user_profile
/*BEGIN*/WHERE
    /*IF condition.userId != null && condition.userId != "" */
        AND user_id = /*condition.userId*/
    /*END*/
    /*IF condition.userName != null && condition.userName != "" */
        AND user_name = /*condition.userName*/
    /*END*/
    /*IF condition.birthday != null && condition.birthday != "" */
        AND birthday = /*condition.birthday*/
    /*END*/
    /*IF condition.sexId != null && condition.sexId != "" */
        AND sex_id = /*condition.sexId*/
    /*END*/
    /*IF condition.postNumber != null && condition.postNumber != "" */
        AND post_number = /*condition.postNumber*/
    /*END*/
    /*IF condition.addr != null && condition.addr != "" */
        AND addr = /*condition.addr*/
    /*END*/
    /*IF condition.phoneNumber != null && condition.phoneNumber != "" */
        AND phone_number = /*condition.phoneNumber*/
    /*END*/
    /*IF condition.ceelNumber != null && condition.ceelNumber != "" */
        AND ceel_number = /*condition.ceelNumber*/
    /*END*/
    /*IF condition.email != null && condition.email != "" */
        AND email = /*condition.email*/
    /*END*/
    /*IF condition.identityImage != null && condition.identityImage != "" */
        AND identity_image = /*condition.identityImage*/
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
/*IF condition.sortClause != null && condition.sortClause != ""*/
   ORDER BY /*$condition.sortClause*/
/*END*/

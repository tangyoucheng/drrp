/*
 * Copyright(c) 2016
 */
/**
 * 推广信息表DAOIF ソート処理
 * RpmAdvertisementDao_selectBySort.sql
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */

SELECT
    ad_id,
    ad_name,
    ad_url,
    ad_status,
    notes,
    delete_flag,
    create_user_id,
    create_date,
    last_update_user_id,
    last_update_user_name,
    last_update_date
FROM
    rpm_advertisement
/*BEGIN*/WHERE
    /*IF condition.adId != null && condition.adId != "" */
        AND ad_id = /*condition.adId*/
    /*END*/
    /*IF condition.adName != null && condition.adName != "" */
        AND ad_name = /*condition.adName*/
    /*END*/
    /*IF condition.adUrl != null && condition.adUrl != "" */
        AND ad_url = /*condition.adUrl*/
    /*END*/
    /*IF condition.adStatus != null && condition.adStatus != "" */
        AND ad_status = /*condition.adStatus*/
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
/*IF condition.sortClause != null && condition.sortClause != ""*/
   ORDER BY /*$condition.sortClause*/
/*END*/

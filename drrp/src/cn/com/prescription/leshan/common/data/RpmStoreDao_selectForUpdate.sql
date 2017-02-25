/*
 * Copyright(c) 2016
 */
/**
 * ?店信息表DAOIF 排他処理
 * RpmStoreDao_selectForUpdate.sql
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */

SELECT
    store_id,
    store_name,
    rp_code_prefix,
    qr_code,
    notes,
    delete_flag,
    create_user_id,
    create_date,
    last_update_user_id,
    last_update_user_name,
    last_update_date
FROM
    rpm_store
/*BEGIN*/WHERE
    /*IF condition.storeId != null && condition.storeId != "" */
        AND store_id = /*condition.storeId*/
    /*END*/
    /*IF condition.storeName != null && condition.storeName != "" */
        AND store_name = /*condition.storeName*/
    /*END*/
    /*IF condition.rpCodePrefix != null && condition.rpCodePrefix != "" */
        AND rp_code_prefix = /*condition.rpCodePrefix*/
    /*END*/
    /*IF condition.qrCode != null && condition.qrCode != "" */
        AND qr_code = /*condition.qrCode*/
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

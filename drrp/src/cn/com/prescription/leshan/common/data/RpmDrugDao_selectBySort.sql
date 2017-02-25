/*
 * Copyright(c) 2016
 */
/**
 * ?品信息表DAOIF ソート処理
 * RpmDrugDao_selectBySort.sql
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */

SELECT
    drug_id,
    drug_name,
    manufacturer_name,
    price,
    unit,
    notes,
    delete_flag,
    create_user_id,
    create_date,
    last_update_user_id,
    last_update_user_name,
    last_update_date
FROM
    rpm_drug
/*BEGIN*/WHERE
    /*IF condition.drugId != null && condition.drugId != "" */
        AND drug_id = /*condition.drugId*/
    /*END*/
    /*IF condition.drugName != null && condition.drugName != "" */
        AND drug_name = /*condition.drugName*/
    /*END*/
    /*IF condition.manufacturerName != null && condition.manufacturerName != "" */
        AND manufacturer_name = /*condition.manufacturerName*/
    /*END*/
    /*IF condition.price != null && condition.price != "" */
        AND price = /*condition.price*/
    /*END*/
    /*IF condition.unit != null && condition.unit != "" */
        AND unit = /*condition.unit*/
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

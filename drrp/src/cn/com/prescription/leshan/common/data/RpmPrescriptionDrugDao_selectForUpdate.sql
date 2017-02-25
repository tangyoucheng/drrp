/*
 * Copyright(c) 2016
 */
/**
 * ?方?品一?信息表DAOIF 排他処理
 * RpmPrescriptionDrugDao_selectForUpdate.sql
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */

SELECT
    prescription_id,
    drug_id,
    quantity,
    sort_key,
    notes,
    last_update_user_id,
    last_update_user_name,
    last_update_date
FROM
    rpm_prescription_drug
/*BEGIN*/WHERE
    /*IF condition.prescriptionId != null && condition.prescriptionId != "" */
        AND prescription_id = /*condition.prescriptionId*/
    /*END*/
    /*IF condition.drugId != null && condition.drugId != "" */
        AND drug_id = /*condition.drugId*/
    /*END*/
    /*IF condition.quantity != null && condition.quantity != "" */
        AND quantity = /*condition.quantity*/
    /*END*/
    /*IF condition.sortKey != null */
        AND sort_key = /*condition.sortKey*/
    /*END*/
    /*IF condition.notes != null && condition.notes != "" */
        AND notes = /*condition.notes*/
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

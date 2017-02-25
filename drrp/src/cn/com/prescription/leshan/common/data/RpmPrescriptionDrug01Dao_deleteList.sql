/*
 * Copyright(c) 2016
 */
/**
 * 处方药品一览信息表DAOIF ソート処理
 * RpmPrescriptionDrug01Dao_deleteList.sql
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */

DELETE
FROM rpm_prescription_drug 
/*BEGIN*/WHERE
    /*IF condition.prescriptionId != null && condition.prescriptionId != "" */
        AND prescription_id = /*condition.prescriptionId*/
    /*END*/
    /*IF condition.drugId != null && condition.drugId != "" */
        AND drug_id = /*condition.drugId*/
    /*END*/
/*END*/

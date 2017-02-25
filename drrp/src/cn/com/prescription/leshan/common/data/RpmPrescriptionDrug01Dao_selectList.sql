/*
 * Copyright(c) 2016
 */
/**
 * 处方药品一览信息表DAOIF ソート処理
 * RpmPrescriptionDrug01Dao_selectList.sql
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */

SELECT
    prescription_drug.prescription_id,
    prescription_drug.drug_id,
    prescription_drug.quantity,
    prescription_drug.sort_key,
    drug.drug_name,
    drug.manufacturer_name,
    drug.price,
    drug.unit
FROM
    rpm_prescription_drug prescription_drug
    LEFT JOIN rpm_drug drug ON (drug.drug_id = prescription_drug.drug_id)
/*BEGIN*/WHERE
    /*IF condition.prescriptionId != null && condition.prescriptionId != "" */
        AND prescription_drug.prescription_id = /*condition.prescriptionId*/
    /*END*/
    /*IF condition.drugId != null && condition.drugId != "" */
        AND drug.drug_id = /*condition.drugId*/
    /*END*/
/*END*/
/*IF condition.sortClause != null && condition.sortClause != ""*/
   ORDER BY /*$condition.sortClause*/
/*END*/

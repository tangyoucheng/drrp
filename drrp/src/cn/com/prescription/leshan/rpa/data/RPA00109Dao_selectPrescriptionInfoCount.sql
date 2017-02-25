/*
 * 
 */
/**
 * 处方信息表DAOIF ソート処理
 * RPA00109Dao_selectPrescriptionInfoCount.sql
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */

SELECT
    COUNT(*)
FROM rpm_prescription
where
    prescription_status = '4'
    /*IF condition.prescriptionYear != null && condition.prescriptionYear != "" */
        AND extract(year from create_date)::text=/*condition.prescriptionYear*/
    /*END*/
GROUP BY extract(year from create_date)

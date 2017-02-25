/*
 * Copyright(c) 2016
 */
/**
 * 处方基本信息表DAOIF ソート処理
 * RpmPrescription01Dao_selectPrescriptionNumber.sql
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */

SELECT
    count(*)+1
FROM
    rpm_prescription prescription
WHERE  prescription.prescription_status = '4'    
    AND  create_date < (select create_date FROM rpm_prescription WHERE prescription_id = /*condition.prescriptionId*/)
    AND prescription.delete_flag = '0'

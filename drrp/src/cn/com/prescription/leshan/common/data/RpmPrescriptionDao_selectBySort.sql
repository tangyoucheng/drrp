/*
 * Copyright(c) 2016
 */
/**
 * 患者基本信息表DAOIF ソート処理
 * RpmPrescriptionDao_selectBySort.sql
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */

SELECT
    prescription_id,
    patient_id,
    prescription_status,
    prescription_type,
    contents,
    file_contents,
    prescription_create_user_id,
    prescription_create_user_name,
    dispense_user_id,
    dispense_user_name,
    confirm_user_id,
    confirm_user_name,
    inpatient_number,
    department,
    bed_no,
    allergy_history,
    diagnosis,
    price,
    notes,
    delete_flag,
    create_user_id,
    create_date,
    last_update_user_id,
    last_update_user_name,
    last_update_date
FROM
    rpm_prescription
/*BEGIN*/WHERE
    /*IF condition.prescriptionId != null && condition.prescriptionId != "" */
        AND prescription_id = /*condition.prescriptionId*/
    /*END*/
    /*IF condition.patientId != null && condition.patientId != "" */
        AND patient_id = /*condition.patientId*/
    /*END*/
    /*IF condition.prescriptionStatus != null && condition.prescriptionStatus != "" */
        AND prescription_status = /*condition.prescriptionStatus*/
    /*END*/
    /*IF condition.prescriptionType != null && condition.prescriptionType != "" */
        AND prescription_type = /*condition.prescriptionType*/
    /*END*/
    /*IF condition.contents != null && condition.contents != "" */
        AND contents = /*condition.contents*/
    /*END*/
    /*IF condition.fileContents != null && condition.fileContents != "" */
        AND file_contents = /*condition.fileContents*/
    /*END*/
    /*IF condition.prescriptionCreateUserId != null && condition.prescriptionCreateUserId != "" */
        AND prescription_create_user_id = /*condition.prescriptionCreateUserId*/
    /*END*/
    /*IF condition.prescriptionCreateUserName != null && condition.prescriptionCreateUserName != "" */
        AND prescription_create_user_name = /*condition.prescriptionCreateUserName*/
    /*END*/
    /*IF condition.dispenseUserId != null && condition.dispenseUserId != "" */
        AND dispense_user_id = /*condition.dispenseUserId*/
    /*END*/
    /*IF condition.dispenseUserName != null && condition.dispenseUserName != "" */
        AND dispense_user_name = /*condition.dispenseUserName*/
    /*END*/
    /*IF condition.confirmUserId != null && condition.confirmUserId != "" */
        AND confirm_user_id = /*condition.confirmUserId*/
    /*END*/
    /*IF condition.confirmUserName != null && condition.confirmUserName != "" */
        AND confirm_user_name = /*condition.confirmUserName*/
    /*END*/
    /*IF condition.inpatientNumber != null && condition.inpatientNumber != "" */
        AND inpatient_number = /*condition.inpatientNumber*/
    /*END*/
    /*IF condition.department != null && condition.department != "" */
        AND department = /*condition.department*/
    /*END*/
    /*IF condition.bedNo != null && condition.bedNo != "" */
        AND bed_no = /*condition.bedNo*/
    /*END*/
    /*IF condition.allergyHistory != null && condition.allergyHistory != "" */
        AND allergy_history = /*condition.allergyHistory*/
    /*END*/
    /*IF condition.diagnosis != null && condition.diagnosis != "" */
        AND diagnosis = /*condition.diagnosis*/
    /*END*/
    /*IF condition.price != null && condition.price != "" */
        AND price = /*condition.price*/
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

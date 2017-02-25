/*
 * 
 */
/**
 * 患者信息表DAOIF ソート処理
 * RpmPatient01Dao_selectPrescriptionInfo.sql
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */

SELECT
    patient.*
    ,prescription.*
    ,prescription.last_update_date as lastUpdateDatePrescription
    ,prescription.create_date as create_date_prescription
FROM
    rpm_patient patient
    INNER JOIN rpm_prescription prescription ON (prescription.patient_id=patient.user_id)
/*BEGIN*/WHERE
    /*IF condition.userId != null && condition.userId != "" */
        AND patient.user_id = /*condition.userId*/
    /*END*/
    /*IF condition.prescriptionId != null && condition.prescriptionId != "" */
        AND prescription.prescription_id = /*condition.prescriptionId*/
    /*END*/
    /*IF condition.userName != null && condition.userName != "" */
        AND patient.user_name = /*condition.userName*/
    /*END*/
    /*IF condition.birthday != null && condition.birthday != "" */
        AND patient.birthday = /*condition.birthday*/
    /*END*/
    /*IF condition.sexId != null && condition.sexId != "" */
        AND patient.sex_id = /*condition.sexId*/
    /*END*/
    /*IF condition.postNumber != null && condition.postNumber != "" */
        AND patient.post_number = /*condition.postNumber*/
    /*END*/
    /*IF condition.addr != null && condition.addr != "" */
        AND patient.addr = /*condition.addr*/
    /*END*/
    /*IF condition.phoneNumber != null && condition.phoneNumber != "" */
        AND patient.phone_number = /*condition.phoneNumber*/
    /*END*/
    /*IF condition.ceelNumber != null && condition.ceelNumber != "" */
        AND patient.ceel_number = /*condition.ceelNumber*/
    /*END*/
    /*IF condition.email != null && condition.email != "" */
        AND patient.email = /*condition.email*/
    /*END*/
    /*IF condition.deleteFlag != null && condition.deleteFlag != "" */
        AND patient.delete_flag = /*condition.deleteFlag*/
    /*END*/
/*END*/
/*IF condition.sortClause != null && condition.sortClause != ""*/
   ORDER BY /*$condition.sortClause*/
/*END*/

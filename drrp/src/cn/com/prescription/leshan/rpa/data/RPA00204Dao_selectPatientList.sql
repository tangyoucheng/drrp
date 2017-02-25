/*
 * 
 */
/**
 * 患者信息表DAOIF ソート処理
 * RPA00204Dao_selectPatientList.sql
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */

SELECT
    patient.user_id,
    patient.user_name,
    patient.birthday,
    patient.sex_id,
    patient.post_number,
    patient.addr,
    patient.phone_number,
    patient.ceel_number,
    patient.id_number,
    patient.email,
    prescription.create_date as create_date_prescription,
    prescriptionInfo.prescription_type,
    prescriptionInfo.contents,
    prescriptionInfo.file_contents,
    prescriptionInfo.price
FROM
    rpm_patient patient
    LEFT JOIN (SELECT patient_id,MAX(create_date) as create_date
                FROM
                rpm_prescription
                GROUP BY patient_id
                ) prescription ON (prescription.patient_id=patient.user_id)
    LEFT JOIN rpm_prescription prescriptionInfo ON (prescriptionInfo.patient_id=prescription.patient_id 
                                                    AND prescriptionInfo.create_date = prescription.create_date)
/*BEGIN*/WHERE
    /*IF condition.userId != null && condition.userId != "" */
        AND patient.user_id = /*condition.userId*/
    /*END*/
    /*IF condition.userName != null && condition.userName != "" */
        AND patient.user_name like /*condition.userName*/
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
        AND patient.ceel_number like /*condition.ceelNumber*/
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
ORDER BY patient.user_name
LIMIT /*condition.limit*/ OFFSET /*condition.offset*/

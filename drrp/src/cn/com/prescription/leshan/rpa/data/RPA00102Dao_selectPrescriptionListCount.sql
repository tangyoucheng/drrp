/*
 * 
 */
/**
 * 处方信息表DAOIF ソート処理
 * RPA00102Dao_selectPrescriptionListCount.sql
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */

SELECT
    COUNT(*)
FROM
    rpm_patient patient
    INNER JOIN rpm_prescription prescription ON (prescription.patient_id=patient.user_id)
/*BEGIN*/WHERE
    /*IF condition.prescriptionStatus != null && condition.prescriptionStatus.size > 0 */
        AND prescription.prescription_status IN /*condition.prescriptionStatus*/('aaa','bbb')
    /*END*/
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
    /*IF condition.prescriptionCreateUserId != null && condition.prescriptionCreateUserId != "" */
        AND prescription.create_user_id = /*condition.prescriptionCreateUserId*/
    /*END*/
/*END*/


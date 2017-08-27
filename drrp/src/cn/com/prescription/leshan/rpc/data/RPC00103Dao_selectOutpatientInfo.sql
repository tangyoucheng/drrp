/*
 * 
 */
/**
 * 门诊信息表DAOIF ソート処理
 * RPC00103Dao_selectOutpatient.sql
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */

SELECT
    outpatient.*,
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
    patient.place_of_birth, 
    patient.marital_status, 
    patient.lunar_birthday, 
    patient.nation, 
    patient.zodiac, 
    patient.company, 
    patient.profession, 
    patient.time_of_birth,
    CASE WHEN patient.sex_id='1' THEN '男'
            WHEN patient.sex_id='2' THEN '女'
    END as sex
FROM
    rpm_outpatient outpatient
    INNER JOIN rpm_patient patient ON (outpatient.patient_id=patient.user_id)
/*BEGIN*/WHERE
    /*IF condition.recordId != null && condition.recordId != "" */
        AND outpatient.record_id like /*condition.recordId*/
    /*END*/
    /*IF condition.deleteFlag != null && condition.deleteFlag != "" */
        AND outpatient.delete_flag = /*condition.deleteFlag*/
    /*END*/
/*END*/

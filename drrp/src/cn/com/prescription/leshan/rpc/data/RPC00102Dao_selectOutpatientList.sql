
/**
 * 门诊信息表DAOIF ソート処理
 * RPC00102Dao_selectOutpatientList.sql
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */

SELECT
    outpatient.record_id,
    patient.user_name,
    patient.ceel_number,
    outpatient.first_visit_flag,
    CASE WHEN outpatient.first_visit_flag='0' THEN '复诊'
            WHEN outpatient.first_visit_flag='1' THEN '初诊'
    END as outpatientTypeName,
    outpatient.medical_date
FROM
    rpm_outpatient outpatient
    INNER JOIN rpm_patient patient ON (outpatient.patient_id=patient.user_id)
/*BEGIN*/WHERE
    /*IF condition.userName != null && condition.userName != "" */
        AND patient.user_name like /*condition.userName*/
    /*END*/
    /*IF condition.firstVisitFlag != null && condition.firstVisitFlag != "" */
        AND outpatient.first_visit_flag = /*condition.firstVisitFlag*/
    /*END*/
    /*IF condition.deleteFlag != null && condition.deleteFlag != "" */
        AND outpatient.delete_flag = /*condition.deleteFlag*/
    /*END*/
    /*IF condition.outpatientCreateUserId != null && condition.outpatientCreateUserId != "" */
        AND outpatient.create_user_id = /*condition.outpatientCreateUserId*/
    /*END*/
/*END*/
ORDER BY patient.user_name asc, outpatient.create_date desc
LIMIT /*condition.limit*/ OFFSET /*condition.offset*/

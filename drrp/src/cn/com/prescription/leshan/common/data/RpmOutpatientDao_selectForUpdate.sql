/*
 * Copyright(c) 
 */
/**
 * 门诊信息AOIF 排他処理
 * RpmOutpatientDao_selectForUpdate.sql
 */
/*
 * ﾐﾂﾒ邪ﾉ
 * DATE: 2012.02.13 NAME: gl
 */

SELECT
    record_id,
    patient_id,
    medical_date,
    chief_complaint,
    onset_solar_terms,
    present_history,
    previous_history,
    family_history,
    allergy_history,
    other_cases,
    four_diagnosis,
    tongue_inspection,
    pulse_taking,
    temperature,
    pulse,
    breath,
    blood_pressure,
    height,
    weight,
    assistant_examination,
    diagnosis,
    disposition,
    physician,
    notes,
    case_recorder,
    attending_assistant,
    first_visit_flag,
    return_visit,
    effect,
    delete_flag,
    create_user_id,
    create_date,
    last_update_user_id,
    last_update_user_name,
    last_update_date
FROM
    rpm_outpatient
/*BEGIN*/WHERE
    /*IF condition.recordId != null && condition.recordId != "" */
        AND record_id = /*condition.recordId*/
    /*END*/
    /*IF condition.patientId != null && condition.patientId != "" */
        AND patient_id = /*condition.patientId*/
    /*END*/
    /*IF condition.medicalDate != null && condition.medicalDate != "" */
        AND medical_date = /*condition.medicalDate*/
    /*END*/
    /*IF condition.chiefComplaint != null && condition.chiefComplaint != "" */
        AND chief_complaint = /*condition.chiefComplaint*/
    /*END*/
    /*IF condition.onsetSolarTerms != null && condition.onsetSolarTerms != "" */
        AND onset_solar_terms = /*condition.onsetSolarTerms*/
    /*END*/
    /*IF condition.presentHistory != null && condition.presentHistory != "" */
        AND present_history = /*condition.presentHistory*/
    /*END*/
    /*IF condition.previousHistory != null && condition.previousHistory != "" */
        AND previous_history = /*condition.previousHistory*/
    /*END*/
    /*IF condition.familyHistory != null && condition.familyHistory != "" */
        AND family_history = /*condition.familyHistory*/
    /*END*/
    /*IF condition.allergyHistory != null && condition.allergyHistory != "" */
        AND allergy_history = /*condition.allergyHistory*/
    /*END*/
    /*IF condition.otherCases != null && condition.otherCases != "" */
        AND other_cases = /*condition.otherCases*/
    /*END*/
    /*IF condition.fourDiagnosis != null && condition.fourDiagnosis != "" */
        AND four_diagnosis = /*condition.fourDiagnosis*/
    /*END*/
    /*IF condition.tongueInspection != null && condition.tongueInspection != "" */
        AND tongue_inspection = /*condition.tongueInspection*/
    /*END*/
    /*IF condition.pulseTaking != null && condition.pulseTaking != "" */
        AND pulse_taking = /*condition.pulseTaking*/
    /*END*/
    /*IF condition.temperature != null && condition.temperature != "" */
        AND temperature = /*condition.temperature*/
    /*END*/
    /*IF condition.pulse != null && condition.pulse != "" */
        AND pulse = /*condition.pulse*/
    /*END*/
    /*IF condition.breath != null && condition.breath != "" */
        AND breath = /*condition.breath*/
    /*END*/
    /*IF condition.bloodPressure != null && condition.bloodPressure != "" */
        AND blood_pressure = /*condition.bloodPressure*/
    /*END*/
    /*IF condition.height != null && condition.height != "" */
        AND height = /*condition.height*/
    /*END*/
    /*IF condition.weight != null && condition.weight != "" */
        AND weight = /*condition.weight*/
    /*END*/
    /*IF condition.assistantExamination != null && condition.assistantExamination != "" */
        AND assistant_examination = /*condition.assistantExamination*/
    /*END*/
    /*IF condition.diagnosis != null && condition.diagnosis != "" */
        AND diagnosis = /*condition.diagnosis*/
    /*END*/
    /*IF condition.disposition != null && condition.disposition != "" */
        AND disposition = /*condition.disposition*/
    /*END*/
    /*IF condition.physician != null && condition.physician != "" */
        AND physician = /*condition.physician*/
    /*END*/
    /*IF condition.notes != null && condition.notes != "" */
        AND notes = /*condition.notes*/
    /*END*/
    /*IF condition.caseRecorder != null && condition.caseRecorder != "" */
        AND case_recorder = /*condition.caseRecorder*/
    /*END*/
    /*IF condition.attendingAssistant != null && condition.attendingAssistant != "" */
        AND attending_assistant = /*condition.attendingAssistant*/
    /*END*/
    /*IF condition.firstVisitFlag != null && condition.firstVisitFlag != "" */
        AND first_visit_flag = /*condition.firstVisitFlag*/
    /*END*/
    /*IF condition.returnVisit != null && condition.returnVisit != "" */
        AND return_visit = /*condition.returnVisit*/
    /*END*/
    /*IF condition.effect != null && condition.effect != "" */
        AND effect = /*condition.effect*/
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
FOR UPDATE NOWAIT

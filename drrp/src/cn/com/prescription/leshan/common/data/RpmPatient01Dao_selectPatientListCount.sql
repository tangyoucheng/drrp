/*
 * 
 */
/**
 * 患者信息表DAOIF ソート処理
 * RpmPatientDao_selectPatientListCount.sql
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */

SELECT
    COUNT(*)
FROM
    rpm_patient
/*BEGIN*/WHERE
    /*IF condition.userId != null && condition.userId != "" */
        AND user_id = /*condition.userId*/
    /*END*/
    /*IF condition.userName != null && condition.userName != "" */
        AND user_name like /*condition.userName*/
    /*END*/
    /*IF condition.birthday != null && condition.birthday != "" */
        AND birthday = /*condition.birthday*/
    /*END*/
    /*IF condition.sexId != null && condition.sexId != "" */
        AND sex_id = /*condition.sexId*/
    /*END*/
    /*IF condition.postNumber != null && condition.postNumber != "" */
        AND post_number = /*condition.postNumber*/
    /*END*/
    /*IF condition.addr != null && condition.addr != "" */
        AND addr = /*condition.addr*/
    /*END*/
    /*IF condition.phoneNumber != null && condition.phoneNumber != "" */
        AND phone_number = /*condition.phoneNumber*/
    /*END*/
    /*IF condition.ceelNumber != null && condition.ceelNumber != "" */
        AND ceel_number like /*condition.ceelNumber*/
    /*END*/
    /*IF condition.email != null && condition.email != "" */
        AND email = /*condition.email*/
    /*END*/
        
    /*IF condition.nation != null && condition.nation != "" */
        AND email = /*condition.nation*/
    /*END*/
    /*IF condition.placeOfBirth != null && condition.placeOfBirth != "" */
        AND email = /*condition.placeOfBirth*/
    /*END*/
    /*IF condition.maritalStatus != null && condition.maritalStatus != "" */
        AND email = /*condition.maritalStatus*/
    /*END*/
    /*IF condition.lunarBirthday != null && condition.lunarBirthday != "" */
        AND email = /*condition.lunarBirthday*/
    /*END*/
    /*IF condition.timeOfBirth != null && condition.timeOfBirth != "" */
        AND email = /*condition.timeOfBirth*/
    /*END*/
    /*IF condition.zodiac != null && condition.zodiac != "" */
        AND email = /*condition.zodiac*/
    /*END*/
    /*IF condition.company != null && condition.company != "" */
        AND email = /*condition.company*/
    /*END*/
    /*IF condition.profession != null && condition.profession != "" */
        AND email = /*condition.profession*/
    /*END*/
        
    /*IF condition.deleteFlag != null && condition.deleteFlag != "" */
        AND delete_flag = /*condition.deleteFlag*/
    /*END*/
/*END*/


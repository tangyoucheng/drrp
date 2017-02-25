/*
 * Copyright(c) 2016 
 */
/**
 * 用户情報を取得する。
 * RPM00203Dao_selectUserInfo.sql
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
SELECT A.*
    ,B.*
    ,A.LAST_UPDATE_DATE AS lastUpdateDateUser
    ,B.LAST_UPDATE_DATE AS lastUpdateDateUserProfile
    ,C.ROLE_ID
    ,C.LAST_UPDATE_DATE AS lastUpdateDateUserRole
FROM  RPM_USER AS A
LEFT JOIN RPM_USER_PROFILE AS B ON (A.USER_ID=B.USER_ID
   AND B.DELETE_FLAG=/*condition.deleteFlag*/)
LEFT JOIN rpm_user_role AS C ON (C.USER_ID=A.USER_ID)
WHERE A.DELETE_FLAG = /*condition.deleteFlag*/
    /*IF condition.userId != null && condition.userId != "" */
        AND A.USER_ID = /*condition.userId*/
    /*END*/


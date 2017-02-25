/*
 * Copyright(c) 2016 
 */
/**
 * 用户情報を取得する。
 * RPM00201Dao_selectUserInfo.sql
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
SELECT A.*
    ,B.*
    ,C.*
    ,A.LAST_UPDATE_DATE AS LAST_UPDATE_DATE_KIHON
    ,B.LAST_UPDATE_DATE AS LAST_UPDATE_DATE_PROFILE
    ,C.LAST_UPDATE_DATE AS LAST_UPDATE_DATE_ROLL_KENGEN
FROM  RPM_USER AS A
LEFT JOIN RPM_USER_PROFILE AS B ON (A.USER_ID=B.USER_ID
   AND B.DELETE_FLAG=/*condition.deleteFlag*/)
LEFT JOIN RPM_USER_ROLE AS C ON(A.USER_ID=C.USER_ID
   AND C.DELETE_FLAG=/*condition.deleteFlag*/)
WHERE A.USER_ID=/*condition.userId*/
   AND A.DELETE_FLAG=/*condition.deleteFlag*/
/*
 * Copyright(c) 2016 
 */
/**
 * 用户情報を取得する。
 * RPM00202Dao_selectUserInfoCount.sql
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
SELECT 
    COUNT(*)
FROM  RPM_USER AS A
LEFT JOIN RPM_USER_PROFILE AS B ON (A.USER_ID=B.USER_ID
    AND B.DELETE_FLAG=/*condition.deleteFlag*/)
WHERE A.DELETE_FLAG = /*condition.deleteFlag*/
    /*IF condition.userId != null && condition.userId != "" */
        AND A.USER_ID like /*condition.userId*/
    /*END*/
    /*IF condition.userName != null && condition.userName != "" */
        AND B.USER_NAME like /*condition.userName*/
    /*END*/

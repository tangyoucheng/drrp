/**
 * 取得用户信息。
 * RPM00101Dao_selectUserInfo.sql
 */
/*
 * 新規作成
 * DATE: 2016.03.20 NAME: tyc
 */
SELECT
    A.*,
    COALESCE(B.user_name,'') AS userName,
    C.role_id
FROM
    rpm_user A
    LEFT JOIN rpm_user_profile B ON (B.user_id = A.user_id AND B.delete_flag = /*condition.deleteFlag*/)
    LEFT JOIN rpm_user_role C ON (C.user_id = A.user_id )
WHERE 
    A.user_id = /*condition.userId*/
    AND A.delete_flag = /*condition.deleteFlag*/

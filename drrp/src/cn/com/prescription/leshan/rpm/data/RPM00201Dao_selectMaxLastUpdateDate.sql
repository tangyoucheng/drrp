/*
 * Copyright(c) 2016 
 */
/**
 * 用户マイ菜单テーブル情報を取得する。
 * RPM00201Dao_selectMaxLastUpdateDate.sql
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
SELECT MAX(JKT_USER_MYMENU.LAST_UPDATE_DATE)
FROM JKT_USER_MYMENU
WHERE JKT_USER_MYMENU.USER_ID=/*condition.userId*/
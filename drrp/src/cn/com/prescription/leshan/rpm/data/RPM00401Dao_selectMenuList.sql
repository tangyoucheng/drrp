/*
 * Copyright(c) 2011 
 */
/**
 * 権限情報（役割）登録 菜单リストを取得する。
 * RPM00401Dao_selectMenuList.sql
 */
/*
 * 新規作成
 * DATE: 2012.05.03 NAME:lz
 */
SELECT 
    A.menu_id,
    A.menu_name

FROM 
    rpm_menu AS A

WHERE 
    A.delete_flag=/*condition.deleteFlag*/  AND
    A.hierarchy_id =/*condition.teisu1*/ AND
    A.menu_type=/*condition.Menu*/ 

ORDER BY  
    A.sort_key  ASC ,
    A.menu_id ASC
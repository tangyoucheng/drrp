/*
 * Copyright(c) 2011 
 */
/**
 * 権限情報（役割）登録 菜单情報リストを取得する。
 *RPM00401Dao_selectMenuInfoList.sql
 */
/*
 * 新規作成
 * DATE: 2012.05.03 NAME:lz
 */

SELECT 
    A.menu_id,
    A.menu_name AS kinoMenuName,
    A.sort_key,
    --A.kino_id,
    COALESCE(B.menu_type,'0') AS sortType,
    B.menu_name AS subMenuName,
    C.menu_name,
    D.menu_id AS chikkukinoId
    
FROM
    rpm_menu AS A
    LEFT JOIN rpm_menu AS B ON(B.menu_id=A.parent_menu_id AND
                                 COALESCE(B.hierarchy_id,'') !=/*condition.teisu1*/ AND
                                 B.delete_flag=/*condition.deleteFlag*/)
                                 
    INNER JOIN rpm_menu AS C ON(C.menu_id=A.parent_menu_id OR
                                  C.menu_id=B.parent_menu_id AND
                                  C.delete_flag=/*condition.deleteFlag*/)  
                                
    LEFT JOIN rpm_role_menu AS D ON(D.menu_id=A.menu_id AND
                                 D.role_id=/*condition.roleId*/ )
WHERE
     A.menu_type=/*condition.Page*/ AND              
     A.delete_flag=/*condition.deleteFlag*/ AND
    /*IF condition.menuId != null && condition.menuId != "" */
     C.menu_id=/*condition.menuId*/ AND
    /*END*/
     C.hierarchy_id=/*condition.teisu1*/ AND
     C.menu_type=/*condition.Menu*/ 
     
/*IF condition.sortClause != null && condition.sortClause != ""*/
   ORDER BY /*$condition.sortClause*/
/*END*/


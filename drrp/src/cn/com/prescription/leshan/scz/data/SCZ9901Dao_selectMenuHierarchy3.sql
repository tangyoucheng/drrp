/*
 * Copyright(c) 2011 
 */
/**
 * 3階層菜单情報的取得
 * SCZ9901Dao_selectMenuHierarchy3.sql
 */
/*
 * 新規作成
 * DATE: 2012.04.10 NAME: tyc
 */

SELECT 
	DISTINCT
	menu3.*,
	menu3.menu_url AS url
FROM
	rpm_role_menu A
	INNER JOIN rpm_role role ON (role.role_id=A.role_id AND role.delete_flag=/*condition.deleteFlag*/)
	INNER JOIN rpm_menu menu3 on (menu3.menu_id=A.menu_id AND menu3.delete_flag=/*condition.deleteFlag*/)
WHERE  
	A.role_id IN /*condition.roleIdList*/('a','b') AND 
	menu3.parent_menu_id=/*condition.parentMenuId*/
ORDER BY 
	menu3.sort_key,
	menu3.menu_id


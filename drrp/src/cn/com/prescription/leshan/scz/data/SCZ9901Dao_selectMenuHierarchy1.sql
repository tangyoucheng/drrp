/*
 * Copyright(c) 2011 
 */
/**
 * 1階層菜单情報的取得
 * SCZ9901Dao_selectMenuHierarchy1.sql
 */
/*
 * 新規作成
 * DATE: 2012.04.10 NAME: tyc
 */

SELECT 
	mainMenu.*
FROM 
	rpm_menu mainMenu 

	LEFT JOIN  
		 (
			SELECT 
				DISTINCT
				menu1.*
			FROM
				rpm_role_menu A
				INNER JOIN rpm_role role ON (role.role_id=A.role_id AND role.delete_flag=/*condition.deleteFlag*/)
				INNER JOIN rpm_menu menu2 ON (menu2.menu_id=A.menu_id AND menu2.menu_type='0' AND menu2.hierarchy_id='2' AND menu2.delete_flag=/*condition.deleteFlag*/)
				INNER JOIN rpm_menu menu1 ON (menu1.menu_id=menu2.parent_menu_id AND menu1.menu_type='1' AND menu1.hierarchy_id='1' AND menu1.delete_flag=/*condition.deleteFlag*/)
			
            /*IF condition.roleIdList != null && condition.roleIdList.length > 0 */
			WHERE 
				A.role_id IN /*condition.roleIdList*/('a','b')
			/*END*/
	
		 ) subMenu1 ON (mainMenu.menu_id = subMenu1.menu_id)

	LEFT JOIN  
		 (
			SELECT 
				DISTINCT
				menu1.*
			FROM
				rpm_role_menu A
				INNER JOIN rpm_role role ON (role.role_id=A.role_id AND role.delete_flag=/*condition.deleteFlag*/)
				INNER JOIN rpm_menu menu3 ON (menu3.menu_id=A.menu_id AND menu3.menu_type='0' AND menu3.hierarchy_id='3' AND menu3.delete_flag=/*condition.deleteFlag*/)
				INNER JOIN rpm_menu menu2 ON (menu2.menu_id=menu3.parent_menu_id AND menu2.menu_type='1' AND menu2.hierarchy_id='2' AND menu2.delete_flag=/*condition.deleteFlag*/)
				INNER JOIN rpm_menu menu1 ON (menu1.menu_id=menu2.parent_menu_id AND menu1.menu_type='1' AND menu1.hierarchy_id='1' AND menu1.delete_flag=/*condition.deleteFlag*/)
			
            /*IF condition.roleIdList != null && condition.roleIdList.length > 0 */
            WHERE 
				A.role_id IN /*condition.roleIdList*/('a','b')
            /*END*/
	
		 ) subMenu2 ON (mainMenu.menu_id = subMenu2.menu_id)

WHERE 
	mainMenu.hierarchy_id = '1' 
	AND mainMenu.delete_flag=/*condition.deleteFlag*/ 
	AND (COALESCE(subMenu1.menu_id,'') <> '' OR COALESCE(subMenu2.menu_id,'') <> '')

ORDER BY 
	mainMenu.sort_key,mainMenu.menu_id

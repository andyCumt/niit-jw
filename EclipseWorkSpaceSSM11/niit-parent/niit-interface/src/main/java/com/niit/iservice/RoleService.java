package com.niit.iservice;

import java.util.List;
import java.util.Map;

import com.niit.entity.Role;
import com.niit.entity.UserRoleKey;

/**
 *@CLassName: RoleService
 *@Description: 角色模块业务层
 *@Author andy
 */
public interface RoleService extends BaseService<Role> {
	
	/**
	 * 根据主键删除角色及角色资源中间表及用户角色中间表
	 **/
	void deleteByids(String[] ids);
	
	/**
	 * 查询所有的用户信息
	 **/
	 List<Map<String, Object>> selectAllRole(String userId );
	 
	 /**绑定角色 */
	 void bingRole(Map<String,Object> map);

}

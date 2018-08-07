package com.niit.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.entity.Job;
import com.niit.entity.Role;
import com.niit.entity.RoleResourceKey;
import com.niit.entity.UserRoleKey;
import com.niit.iservice.RoleService;
import com.niit.mapper.JobMapper;
import com.niit.mapper.RoleMapper;
import com.niit.mapper.RoleResourceMapper;
import com.niit.mapper.UserRoleMapper;

/**
 *@CLassName: RoleServiceImpl
 *@Description: 角色模块实现层
 *@Author andy
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
	
	
	/*注入mapper接口*/
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private RoleResourceMapper roleResourceMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	public void deleteByids(String[] ids) {
		
		try{
			for (int i = 0; i < ids.length; i++) {
				baseMapper.deleteByPrimaryKey(ids[i]);
				RoleResourceKey roleResourceKey = new RoleResourceKey();
				roleResourceKey.setRoleId(ids[i]);
				roleResourceMapper.deleteByIds(roleResourceKey);
				UserRoleKey userRoleKey = new UserRoleKey();
				userRoleKey.setRoleId(ids[i]);
				userRoleMapper.deleteByIds(userRoleKey);
			}
		}catch(Exception ex){
			throw new RuntimeException("根据主键删除方法出现了异常！", ex);
		}
	}

	@Override
	public List<Map<String, Object>> selectAllRole(String userId) {
		
		 try{
			 UserRoleKey userRoleKey = new UserRoleKey();
			 userRoleKey.setUserId(userId);
			 
			 //根据用户userId查询用户,根据用户查询他所有绑定的角色
			 List<Role> roles = userRoleMapper.selectAllRoleByUserId(userRoleKey);
			  //定义Set集合封装该用户已绑定的角色的id
			 List<String> roleIds =   new ArrayList<String>();
			 for (int i = 0; i < roles.size(); i++) {
				 roleIds.add(roles.get(i).getId());
			 }
			
			 //查询所有的角色  
			 List<Role> allRoles = roleMapper.selectPageInfoByWhere(new Role());
			 
			 List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
			 //对一行数据做标记
			 for (int i = 0; i < allRoles.size(); i++) {
				Map<String, Object> map =  new HashMap<>();
				map.put("id", allRoles.get(i).getId());
				map.put("name", allRoles.get(i).getName());
				map.put("code", allRoles.get(i).getCode());
				map.put("remark", allRoles.get(i).getRemark());			
				//设置这一行的是否被选中
			    map.put("checked", roleIds.contains(allRoles.get(i).getId()));	
			    data.add(map);
			 }
			  return data;
		   }catch(Exception ex){
				throw new RuntimeException("查询所有的角色！", ex);
		}
	}

	@Override
	public void bingRole(Map<String, Object> map) {

		 try{	
			 String userId = (String) map.get("userId");
			 String idsArr = ((String) map.get("ids"));
			 
			 /**解除用户已经绑定的角色*/
			 UserRoleKey userRoleKey = new UserRoleKey();
			 userRoleKey.setUserId(userId);
			 userRoleMapper.deleteByIds(userRoleKey);
			 
			 if (StringUtils.isNotEmpty(idsArr)&&StringUtils.isNotBlank(idsArr)) {
				 String[] ids = idsArr.split(",");
				//循环新的角色
				 for (String id :ids) {
					//创建角色
					 UserRoleKey userRole = new UserRoleKey();
					 userRole.setUserId(userId);
					 userRole.setRoleId(id);
					 userRoleMapper.insertSelective(userRole);
				}
			}
		 }catch(Exception ex){
				throw new RuntimeException("绑定角色方法出现了异常！", ex);
		}
	}
   
   
	
}

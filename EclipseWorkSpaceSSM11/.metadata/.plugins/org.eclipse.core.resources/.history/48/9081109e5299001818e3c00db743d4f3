package com.niit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

import com.niit.core.utils.uuid.DefaultMySQLUUIDImpl;
import com.niit.core.vo.DataGridResult;
import com.niit.core.vo.DataResult;
import com.niit.entity.Role;
import com.niit.iservice.RoleService;

/**
 *@CLassName:RoleController
 *@Description: 角色控制器
 *@Author andy
 */
@RequestMapping("/role")
@Controller
public class RoleController {
	
    /*注入业务层RoleService*/
	@Autowired
	private RoleService roleService;
	
	/**
	 *权限模块登陆成功，页面跳转role.jsp
	 */
	@RequestMapping(value="/toRole")
	public String toRole(){
		return "role/role";
	}
		
	/**
	 *分页查询获取数据
	 */
	@RequestMapping(value="/selectRole")
	@ResponseBody
	public DataGridResult selectRole(@RequestParam(value = "page" ,required=true,defaultValue="1") Integer pageNum ,
			                @RequestParam(value = "rows" ,required=true,defaultValue="5") Integer pageSize ){
		Role role = new Role();
		DataGridResult dataGridResult = roleService.selectPageInfoByWhere(role , pageSize, pageNum);
		return dataGridResult;
	}
	
	/**
	 *获取所有角色数据
	 */
	@RequestMapping(value="/selectAllRole")
	@ResponseBody
	public  List<Map<String, Object>> selectAllRole(@RequestParam(value = "userId" ,required=true) String userId ){
		return roleService.selectAllRole(userId);
	}
	

	/**
	 *权限模块登陆成功，页面跳转addRole.jsp
	 */
	@RequestMapping(value="/showAddRole")
	public String showAddRole(){
	
		return "role/addRole";
	}
	
	/**
	 *添加角色
	 */
	@RequestMapping(value="/addRole",method=RequestMethod.POST)
	@ResponseBody
	public DataResult addRole(@RequestParam  Map<String,Object> map){
		DataResult dataResult = new DataResult();
		try {
			Role role = new Role();
			role.setId(new DefaultMySQLUUIDImpl().generateUuidByTime());
			role.setName((String)map.get("name"));
			role.setCode((String)map.get("code"));
			role.setRemark((String)map.get("remark"));
	        roleService.insertSelective(role);
	        dataResult.setTip(1);
		} catch (Exception e) {
			dataResult.setTip(0);
		    e.printStackTrace();
		}
	    
		return dataResult;		
	}
	
	
	/**
	 *权限模块登陆成功，页面跳转updateRole.jsp
	 */
	@RequestMapping(value="/showUpdateRole")
	public ModelAndView showUpdateRole(@RequestParam(required=true) String id){
		Role role = roleService.selectByPrimaryKey(id);
		ModelAndView m = new ModelAndView("/role/updateRole");
		m.addObject("role", role);
		return m;
	}
	
	
	/**
	 *更新角色
	 */
	@RequestMapping(value="/updateRole",method=RequestMethod.POST)
	@ResponseBody
	public DataResult updateRole(@RequestParam  Map<String,Object> map){
		DataResult dataResult = new DataResult();
		try {
			Role role = new Role();
			role.setId((String)map.get("id"));
			role.setName((String)map.get("name"));
			role.setCode((String)map.get("code"));
			role.setRemark((String)map.get("remark"));
	        roleService.updateByPrimaryKeySelective(role);
	        dataResult.setTip(1);
		} catch (Exception e) {
			dataResult.setTip(0);
		    e.printStackTrace();
		}
	    
		return dataResult;		
	}
	
	/**
	 *删除角色
	 */
	@RequestMapping(value="/deleteRole")
	@ResponseBody
	public DataResult deleteRole(@RequestParam String ids ){
		DataResult dataResult = new DataResult();
		try {
		    String[] id = ids.split(",");        
		    roleService.deleteByids(id);	
	        dataResult.setTip(1);       
		} catch (Exception e) {
			dataResult.setTip(0);
		    e.printStackTrace();
		}
	    
		return dataResult;		
	}

	/**
	 *权限模块登陆成功，页面跳转bindResource.jsp
	 */
	@RequestMapping(value="/showBindResource")
	public ModelAndView showBindResource(@RequestParam(required=true) String id){
		Role role = roleService.selectByPrimaryKey(id);
		ModelAndView m = new ModelAndView("/role/bindResource");
		m.addObject("role", role);
		return m;
	}
	
	
	/**
	 *绑定角色
	 */
	@RequestMapping(value="/bindRole",method=RequestMethod.POST)
	@ResponseBody
	public DataResult bindRole(@RequestParam  Map<String,Object> map){
		DataResult dataResult = new DataResult();
		try {
	        roleService.bingRole(map);
	        dataResult.setTip(1);
		} catch (Exception e) {
			dataResult.setTip(0);
		    e.printStackTrace();
		}
	    
		return dataResult;		
	
	}
	
	
	/**
	 *异步加载部门列表
	 */
	@RequestMapping(value="/loadDept",method=RequestMethod.POST)
	@ResponseBody
	public List<Role> loadDept(){
	
	   Role role = new Role();   
	   return  (List<Role>) roleService.selectPageInfoByWhere(role, 0, 0).getRows(); 
			
	}
	
	
	
	
	
	
	
	
}

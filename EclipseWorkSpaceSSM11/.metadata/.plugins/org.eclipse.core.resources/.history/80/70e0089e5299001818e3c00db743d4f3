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
import com.niit.entity.Dept;
import com.niit.iservice.DeptService;

/**
 *@CLassName:DeptController
 *@Description: 部门控制器
 *@Author andy
 */
@RequestMapping("/dept")
@Controller
public class DeptController {
	
    /*注入业务层DeptService*/
	@Autowired
	private DeptService deptService;
	
	
	/**
	 *权限模块登陆成功，页面跳转dept.jsp
	 */
	@RequestMapping(value="/toDept")
	public String toDept(){
		return "dept/dept";
	}
		

	/**
	 *权限模块登陆成功，页面跳转addDept.jsp
	 */
	@RequestMapping(value="/showAddDept")
	public String showAddDept(){
	
		return "dept/addDept";
	}
	
	/**
	 *权限模块登陆成功，页面跳转updateDept.jsp
	 */
	@RequestMapping(value="/showUpdateDept")
	public ModelAndView showUpdateDept(@RequestParam(required=true) String id){
		Dept dept = deptService.selectByPrimaryKey(id);
		ModelAndView m = new ModelAndView("/dept/updateDept");
		m.addObject("dept", dept);
		System.out.println("==========="+dept.getName()+dept.getRemark());
		return m;
	}
	
	/**
	 *异步加载部门列表
	 */
	@RequestMapping(value="/loadDept",method=RequestMethod.POST)
	@ResponseBody
	public List<Dept> loadDept(){
	
	   Dept dept = new Dept();   
	   return  (List<Dept>) deptService.selectPageInfoByWhere(dept, 0, 0).getRows(); 
			
	}
	
	/**
	 *添加部门
	 */
	@RequestMapping(value="/addDept",method=RequestMethod.POST)
	@ResponseBody
	public DataResult addDept(@RequestParam  Map<String,Object> map){
		DataResult dataResult = new DataResult();
		try {
			Dept dept = new Dept();
			dept.setId(new DefaultMySQLUUIDImpl().generateUuidByTime());
			dept.setName((String)map.get("name"));
			dept.setRemark((String)map.get("remark"));
	        deptService.insertSelective(dept);
	        dataResult.setTip(1);
		} catch (Exception e) {
			dataResult.setTip(0);
		    e.printStackTrace();
		}
	    
		return dataResult;		
	}
	
	
	
	/**
	 *更新部门
	 */
	@RequestMapping(value="/updateDept",method=RequestMethod.POST)
	@ResponseBody
	public DataResult updateDept(@RequestParam  Map<String,Object> map){
		DataResult dataResult = new DataResult();
		try {
			Dept dept = new Dept();
			dept.setId((String)map.get("id"));
			dept.setName((String)map.get("name"));
			dept.setRemark((String)map.get("remark"));
	        deptService.updateByPrimaryKeySelective(dept);
	        dataResult.setTip(1);
		} catch (Exception e) {
			dataResult.setTip(0);
		    e.printStackTrace();
		}
	    
		return dataResult;		
	}
	
	
	
	/**
	 *删除部门
	 */
	@RequestMapping(value="/deleteDept")
	@ResponseBody
	public DataResult deleteDept(@RequestParam String ids ){
		DataResult dataResult = new DataResult();
		try {
		    String[] id = ids.split(",");        
		    deptService.deleteByPrimaryKey(id);	
	        dataResult.setTip(1);       
		} catch (Exception e) {
			dataResult.setTip(0);
		    e.printStackTrace();
		}
	    
		return dataResult;		
	}
	
	/**
	 *分页查询获取数据
	 */
	@RequestMapping(value="/selectDept")
	@ResponseBody
	public DataGridResult selectDept(@RequestParam(value = "page" ,required=true,defaultValue="1") Integer pageNum ,
			                @RequestParam(value = "rows" ,required=true,defaultValue="5") Integer pageSize ){
		Dept dept = new Dept();
		DataGridResult dataGridResult = deptService.selectPageInfoByWhere(dept , pageSize, pageNum);
		return dataGridResult;
	}
}

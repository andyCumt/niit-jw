package com.niit.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.niit.core.security.CryptographyHelper;
import com.niit.core.utils.uuid.DefaultMySQLUUIDImpl;
import com.niit.core.vo.DataGridResult;
import com.niit.core.vo.DataResult;
import com.niit.core.vo.UserResult;
import com.niit.entity.Dept;
import com.niit.entity.Job;
import com.niit.entity.User;
import com.niit.iservice.DeptService;
import com.niit.iservice.JobService;
import com.niit.iservice.UserService;

/**
 *@CLassName: IdentityController
 *@Description: 身份控制器
 *@Author andy
 */
@RequestMapping("/user")
@Controller
public class UserController {
	
    private static final String String = null;

	/*注入业务层IdentityService*/
	@Autowired
	private UserService userService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private JobService jobService;
	
	/*作为相应数据*/
	private Map<String, Object> result;
	
	
	
	/**
	 *权限模块登陆成功，页面跳转到addUser.jsp
	 */
	@RequestMapping(value="/showAddUser")
	public String showAddUser(){
		return "user/addUser";
	}
	
	/**
	 *权限模块登陆成功，页面跳转到user.jsp
	 */
	@RequestMapping(value="/toUser")
	public String toUser(){
		return "user/user";
	}

	/**
	 *权限模块登陆成功，页面跳转bindRole.jsp
	 */
	@RequestMapping(value="/showBindRole")
	public ModelAndView showBindRole(@RequestParam(value = "userId", required=true) String userId){
        User user = userService.selectByPrimaryKey(userId);
		ModelAndView m = new ModelAndView("/user/bindRole");
		m.addObject("user", user);
		return m;
	}
	
	/**
	 *权限模块登陆成功，页面跳转updateUser.jsp
	 */
	@RequestMapping(value="/showUpdateUser")
	public ModelAndView showUpdateJob(@RequestParam(value = "userId", required=true) String userId){
		User user = new User();
		user.setUserId(userId);
		DataGridResult selectPageInfoByWhere = userService.selectPageInfoByWhere(user, 0, 0);
		UserResult userResult = (UserResult) selectPageInfoByWhere.getRows().get(0);
		ModelAndView m = new ModelAndView("/user/updateUser");
		m.addObject("user", userResult);
		return m;
	}
	
	/**
	 *审批用户
	 */
	@RequestMapping(value="/checkUser", method =RequestMethod.POST)
	@ResponseBody
	public DataResult checkUser(@RequestParam(value = "userIds") String ids,
			                    @RequestParam(value = "status") String status){
		DataResult dataResult = new DataResult();
		User user = new User();
		user.setStatus(status);
		try {
		    String[] id = ids.split(","); 	
		    userService.updateSelective(user,id);
	        dataResult.setTip(1);       
		} catch (Exception e) {
			dataResult.setTip(0);
		    e.printStackTrace();
		}
	    
		return dataResult;		
	}
	
	/**
	 *删除用户
	 */
	@RequestMapping(value="/deleteUser")
	@ResponseBody
	public DataResult deleteDept(@RequestParam(value = "userIds") String ids ){
		DataResult dataResult = new DataResult();
		try {
		    String[] id = ids.split(",");        
		    userService.deleteByPrimaryKey(id);	
	        dataResult.setTip(1);       
		} catch (Exception e) {
			dataResult.setTip(0);
		    e.printStackTrace();
		}
	    
		return dataResult;		
	}
	
	/**
	 *修改用户
	 */
	@RequestMapping(value="/updateUser",method=RequestMethod.POST)
	@ResponseBody
	public DataResult updateUser(@RequestParam  Map<String,Object> map){
		DataResult dataResult = new DataResult();
		/**获取参数 */
		String birth = (String)map.get("birthday");
		String deptName = (String)map.get("deptId");
		String email = (String)map.get("email");
		String jobName = (String)map.get("jobId");
		String name = (String)map.get("name");
		String phone = (String)map.get("phone");
		String sal = (String)map.get("salary");
		String sex = (String)map.get("sex");
		String userId = (String)map.get("userId");
		try {				   
			User user = userService.selectByPrimaryKey(userId);
	        /** 生日字符串转换成时间*/
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
		    Date birthday=sdf.parse(birth); 
		    user.setBirthday(birthday);
		    /** 薪水转换*/
		    BigDecimal salary = new BigDecimal(sal);
		    user.setSalary(salary);    
		    /** 获取部门和职位的id*/
		    Dept dept = deptService.selectByName(deptName);
		    Job job = jobService.selectByName(jobName);
		    /** 其他参数封装*/
		    user.setDeptId(dept.getId());
		    user.setEmail(email);
		    user.setJobId(job.getId());
		    user.setName(name);
		    user.setPhone(phone);
		    user.setSex(sex);
		    user.setUserId(userId);
		    
	        userService.updateByPrimaryKeySelective(user);
	        dataResult.setTip(1);
		} catch (Exception e) {
			dataResult.setTip(0);
		    e.printStackTrace();
		}
	    
		return dataResult;		
	}

	/**
	 *添加用户
	 */
	@RequestMapping(value="/addUser",method=RequestMethod.POST)
	@ResponseBody
	public DataResult addUser(@RequestParam  Map<String,Object> map){
		DataResult dataResult = new DataResult();
		/**获取参数 */
		String birth = (String)map.get("birthday");
		String deptId = (String)map.get("deptId");
		String email = (String)map.get("email");
		String jobId = (String)map.get("jobId");
		String name = (String)map.get("name");
		String passWord = (String)map.get("passWord");
		String phone = (String)map.get("phone");
		String sal = (String)map.get("salary");
		String sex = (String)map.get("sex");
		String userId = (String)map.get("userId");
		try {
			
			
		   
			User user = new User();	
		    /**获取加密盐*/
		    String salt = CryptographyHelper.getRandomSalt();
		    user.setSalt(salt);
		    /**获取加密密码*/
		    String password = CryptographyHelper.encrypt(passWord, salt);
	        user.setPassWord(password);
	        /** 生日字符串转换成时间*/
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
		    Date birthday=sdf.parse(birth); 
		    user.setBirthday(birthday);
		    /** 薪水转换*/
		    BigDecimal salary = new BigDecimal(sal);
		    user.setSalary(salary);
		    /** 其他参数封装*/
		    user.setDeptId(deptId);
		    user.setEmail(email);
		    user.setJobId(jobId);
		    user.setName(name);
		    user.setPhone(phone);
		    user.setSex(sex);
		    user.setStatus("0");
		    user.setUserId(userId);
		    
	        userService.insertSelective(user);
	        dataResult.setTip(1);
		} catch (Exception e) {
			dataResult.setTip(0);
		    e.printStackTrace();
		}
	    
		return dataResult;		
	}
	
	/**
	 *添加用户之校验
	 */
	@RequestMapping(value="/validUserId",method=RequestMethod.POST)
	@ResponseBody
	public DataResult validUserId(@RequestParam  Map<String,Object> map){
		DataResult dataResult = new DataResult();
		/*获取用户id*/
	    String userId = (String) map.get("userId");
	    User user = userService.selectByPrimaryKey(userId);
	    if (user == null) {
	    	dataResult.setTip(1);
		}else{
			dataResult.setTip(0);
		}
		return dataResult;		
	}
	
	/**
	 *分页查询获取数据
	 */
	@RequestMapping(value="/selectUser")
	@ResponseBody
	public DataGridResult selectUser(
			@RequestParam(value = "page" ,required=true,defaultValue="1") Integer pageNum ,
            @RequestParam(value = "rows" ,required=true,defaultValue="5") Integer pageSize,
            @RequestParam Map<Object, Object> map){
		User user = new User();
		String deptId =(String)map.get("deptId");
		String name = (String)map.get("name");
		String phone = (String)map.get("phone");
		user.setDeptId(deptId);
		user.setName(name);
		user.setPhone(phone);
		DataGridResult dataGridResult = userService.selectPageInfoByWhere(user ,pageSize,pageNum);
		return dataGridResult;
	}
	
	/**
	 *权限模块登陆成功，页面跳转
	 */
	@RequestMapping(value="/main")
	public String tomain(){
		return "main";
	}
	
	/**
	 *权限模块登陆失败，页面跳转到登陆页面
	 */
	@RequestMapping(value="/fail")
	public String tologin(){
		return "forward:/login.jsp";
	}
	
	/**
	 *权限模块登陆功能
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(@RequestParam Map<String, Object> entity,HttpServletRequest request,HttpServletResponse response){
		/*获取参数*/
		String userId = (String) entity.get("userId");
		String password = (String) entity.get("password");
		String vcode = (String) entity.get("vcode");
		String keyStr = (String) entity.get("key");
		
		//判断是否选中记住我,页面选中key:1,未选中key:null,
		if (keyStr == null) {
			//将未选中记住我设置为key = 0
			keyStr = "0";
		}
		Integer key = Integer.parseInt(keyStr);
		/*+++++++++++++++++TODO+++++++对参数进行校验+++++++++++++*/
	
	    /*调用业务层*/	
		result = userService.login(userId,password,vcode,key,request,response);
		return result;
	}

}

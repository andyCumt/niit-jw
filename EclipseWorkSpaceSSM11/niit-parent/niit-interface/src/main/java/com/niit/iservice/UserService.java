package com.niit.iservice;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.niit.entity.Job;
import com.niit.entity.User;

/**
 *@CLassName: IdentityService
 *@Description: 权限模块业务层
 *@Author andy
 */
public interface UserService extends BaseService<User>{
	
	
	/**
	 *@Description: 权限模块登录功能
     *@Author andy
     *@CreateDate: 2017年11月24日 下午8:15:58
     *@return Map
     *@param userId:用户名  password:密码 vcode:验证码 key:记住我选中为1,未选中为0
	 */
	Map<String,Object> login(String userId,String password,String vcode,Integer key,HttpServletRequest request,HttpServletResponse response);
	
	/**
	 * @param t  ids 实体类
	 * @return int 执行条数
	 * 描述:选择行的更新审核实体对象
	 * */
	void updateSelective(User user,String[] ids);
}

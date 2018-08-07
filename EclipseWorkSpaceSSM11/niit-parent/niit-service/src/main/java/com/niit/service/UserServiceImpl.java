package com.niit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.core.common.Constants;
import com.niit.core.common.CookieTools;
import com.niit.core.security.CryptographyHelper;
import com.niit.core.security.MD5;
import com.niit.core.utils.verify.VerifyAction;
import com.niit.entity.Dept;
import com.niit.entity.Job;
import com.niit.entity.User;
import com.niit.iservice.UserService;
import com.niit.mapper.DeptMapper;
import com.niit.mapper.JobMapper;
import com.niit.mapper.UserMapper;

/**
 *@CLassName: IdentityServiceImpl
 *@Description: 权限模块实现层
 *@Author andy
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
	
	/*注入mapper接口*/
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private DeptMapper deptMapper;
	
	@Autowired
	private JobMapper jobMapper;
	

	@Override
	public Map<String, Object> login(String userId, String password, String vcode, Integer key,HttpServletRequest request,HttpServletResponse response) {
		
		try{
			/** 定义Map集合封装提示信息 */
			Map<String,Object> map = new HashMap<>();
			map.put("tip", "验证码不正确！");
			map.put("status", 1);
			/** 判断验证码是否正确 */
			String oldCode = (String) request.getSession().getAttribute(VerifyAction.VERIFY_CODE);
			if (oldCode.equalsIgnoreCase(vcode)){
				/** 判断账号与密码 */
				if (!StringUtils.isEmpty(userId) && !StringUtils.isEmpty(password)){
					/** 根据用户id查询用户 */
					User user = userMapper.selectByPrimaryKey(userId);
					/** 判断账号与密码是否正确  MD5加密 */
					if (user != null && user.getPassWord().equals(CryptographyHelper.encrypt(password, user.getSalt()))){
						/**判断用户状态 */
						if (user.getStatus().equals("1")) {//审核状态
							/** 存入Session */
							request.getSession().setAttribute(Constants.SESSION_USER, user);
						    /** key 是否记住用户 1:记住用户  0:不记住 */
							if (key == 1){
								/** 调用操作Cookie的工具类，添加一个Cookie到用户的浏览器 */
								CookieTools.addCookie(Constants.COOKIE_NAME, user.getUserId(), Constants.MAX_AGE,response,request);
							}
							map.put("tip", "登录成功！");
							map.put("status", 0);
						}else{
							// 0新建,1审核,2不通过,3冻结
							String[] tipArr = {"新建","审核","不通过","冻结"};
							Integer index = Integer.parseInt(user.getStatus());
							map.put("tip", "您处于【<font color='red'>"+tipArr[index]+"</font>】状态，请联系管理员！");
							map.put("status", 4);
						}
						
					}else{
						map.put("tip", "账号或密码不正确！");
						map.put("status", 3);
					}
				}else{
					map.put("tip", "账号或密码为空！");
					map.put("status", 2);
				}
			}
			return map;
		}catch(Exception ex){
			throw new RuntimeException("登录方法出现了异常！", ex);
		}
	
	}

	@Override
	public void updateSelective(User user, String[] ids) {
		try{
			for (int i = 0; i < ids.length; i++) {
				user.setUserId(ids[i]);
				baseMapper.updateByPrimaryKeySelective(user);
			} 
		}catch(Exception ex){
			throw new RuntimeException("选择性更新审核方法出现了异常！", ex);
		}
		
	}
}

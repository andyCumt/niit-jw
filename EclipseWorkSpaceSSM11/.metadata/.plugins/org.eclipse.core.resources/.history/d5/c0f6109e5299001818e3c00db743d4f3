package com.niit.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.niit.iservice.SystemService;
import com.niit.iservice.UserService;

/**
 *@CLassName: SystemController
 *@Description:系统控制器
 *@Author andy
 */
@RequestMapping("/system")
@Controller
public class SystemController {
	
	/**注入用户服务*/
	@Autowired
	private SystemService systemService;
	
	/**
	 *获取验证码
	 */
	@RequestMapping("/loadUserMenu")
	@ResponseBody
	public List<Map<String,Object>>loadUserMenu(HttpServletRequest request,HttpServletResponse response){
		return systemService.loadUserMenu(request,response);
	}
	
}
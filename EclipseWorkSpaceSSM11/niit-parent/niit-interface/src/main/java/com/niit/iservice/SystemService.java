package com.niit.iservice;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *@CLassName: SystemService
 *@Description: 系统模块业务层
 *@Author andy
 */
public interface SystemService{
	/** 异步加载菜单栏*/
	List<Map<String,Object>>loadUserMenu(HttpServletRequest request,HttpServletResponse response);
}

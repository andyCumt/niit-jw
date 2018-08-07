package com.niit.interceptor;


import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.niit.core.common.Constants;
import com.niit.core.common.CookieTools;
import com.niit.entity.User;
import com.niit.iservice.UserService;


/**
 * 登录拦截器
 * @author andy
 * @email andy@163.com
 * @version 1.0
 */
public class LoginInterceptor extends HandlerInterceptorAdapter{

	private static final long serialVersionUID = 2762407143361520359L;
	
	/** 注入业务层接口 */
	@Resource
	private	UserService userService;	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		/**从Session中获取当前用户*/
		User user = (User) request.getSession().getAttribute(Constants.SESSION_USER);
		System.out.println("Session中的用户：" + user);
		/** 判断用户是否为空，为空代表没有登录 */
		if (user == null){
			/** 获取用户浏览器中的Cookie */
			Cookie cookie = CookieTools.getCookie(Constants.COOKIE_NAME,request);
			if (cookie != null){ // 记住了用户 
				/** 获取cookie的值 */
				String userId = cookie.getValue();
				/** 根据主键id查询用户 */
			    user = userService.selectByPrimaryKey(userId);
				/** 再把用户存入Session */
				if (user != null){
					request.getSession().setAttribute(Constants.SESSION_USER, user);
					//return true表示放行
					return true;
				}
				
			}
			
			 //执行这里表示用户身份需要认证，跳转登陆页面
	        request.getRequestDispatcher("/user/fail").forward(request, response);
			 //return false表示拦截，不向下执行
			return false;
		}
        return true;
	}

}

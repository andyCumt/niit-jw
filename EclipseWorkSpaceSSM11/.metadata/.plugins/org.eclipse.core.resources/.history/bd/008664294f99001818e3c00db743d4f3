package com.niit.core.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 操作cookie的工具类
 * @author andy.sun
 * @email andysun@163.com
 * @version 1.0
 */
public final class CookieTools {
	/*
	 * 根据Cookie的名字获取一个Cookie
	 * @param cookieName cookie 的名字
	 * @return Cookie
	 * */
     public static Cookie getCookie(String cookieName,HttpServletRequest request){
    	/* 获取当前浏览器中所有的Cookie*/
    	 Cookie[] cookies = request.getCookies();
    	/* 编列Cookies,判断Cookie数组*/
    	 if (cookies!=null&&cookies.length>0) {
    		 for (Cookie cookie : cookies) {
    			 if (cookie.getName().equals(cookieName)) {
    				 return cookie;
    			 }
    		 }
			
		}
    	 return null;
     }
     
     /*
 	 * 添加Cookie
 	 * @param cookieName cookie 的名字
 	 * @param cookieValue cookie 的值
 	 * @param maxAge cookie 的存活时间(秒)
 	 * */
      public static void addCookie(String cookieName,String cookieValue,int maxAge,HttpServletResponse response,HttpServletRequest request){
    	    //根据CookieName获取指定的Cookie
     	     Cookie cookie = getCookie(cookieName,request);
     	     //如果Cookie为null,创建新的Cookie
     	     if (cookie == null) {
				//创建一个新的Cookie
     	    	  cookie = new Cookie(cookieName, cookieValue);
			}
     	     //设置从Cookie的过期时间
     	     cookie.setMaxAge(maxAge);
     	     //设置Cookie的访问路径
     	     cookie.setPath("/");
     	     //设置Cookie跨子域访问 HTTP://www.taobap.cn  子域 http://aaa.taobao.cn
     	     cookie.setComment(".it.com");
     	     //将Cookie添加到浏览器
     	     response.addCookie(cookie);
      }
     
      /*
   	 * 删除Cookie
   	 * @param cookieName cookie 的名字
   	 * */
      public static void removeCookie(String cookieName,HttpServletResponse response,HttpServletRequest request){
  		/** 根据Cookie的名称获取指定的Cookie */
  		Cookie cookie = getCookie(cookieName,request);
  		if (cookie != null){
  			/** 设置Cookie立即失效 */
  			cookie.setMaxAge(0);
  			/** 设置Cookie的访问路径  http://localhost:8080/bos/ http://www.it.com/ */
  			cookie.setPath("/");
  			/** 添加Cookie到用户的浏览器 */
  			response.addCookie(cookie);
  		}
  	}
}

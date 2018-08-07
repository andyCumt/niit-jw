package com.niit.core.common;
/**
 * 常量类
 * @author andy.sun
 * @email andysun@163.com
 * @version 1.0
 */
public final class Constants {
    //定义存放的session用户
	public static final String SESSION_USER = "session_user";
	//定义Cookie的名字
	public static final String COOKIE_NAME = "bos_cookie";
	//定义Cookie的生命周期7天
	public static final int MAX_AGE = 60*60*24*7;
	/** 定义登录用户总数量 */
	public static final String LOGIN_USER_COUNT = "login_user_count";
	/** 定义存放在Session中的短信验证码 */
	public static final String SMS_VERIFY_CODE = "sms_verify_code";
	/** 定义存放在Session中的用户id */
	public static final String SESSION_USER_ID = "session_user_id";
	/** 定义资源的分节符4位一个级别 */
	public static final Integer CODE_LEN = 4;


}

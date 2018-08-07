package com.niit.core.security;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * 密码帮助类
 * @author andy.sun
 * @email andysun@163.com
 * @version 1.0
 */

public final class CryptographyHelper {
     /**定义安全随机生成器*/
	 private static SecureRandomNumberGenerator srg = new SecureRandomNumberGenerator();
	 /**定义加密算法名称*/
	 private static final String ALGORITHM_NAME ="md5";
	 /**定义加密迭代次数*/
	 private static final int HASH_ITERATIONS = 5;
     /**
     *获取一个随机加密盐
     *@return 32位长度的字符串 
     */
	 public static  String getRandomSalt(){
		 return srg.nextBytes().toHex();
	 }
	 /**
	  *加密方法
	  *@param password 明文
	 * @param salt 盐
	 * @return 加密过后的字符串 
	  */
	 public static String encrypt(String password,String salt){
		 return new SimpleHash(ALGORITHM_NAME, password, salt, HASH_ITERATIONS).toHex();
	 }
}

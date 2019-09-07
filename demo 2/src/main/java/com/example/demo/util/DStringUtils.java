package com.example.demo.util;

import java.util.regex.Pattern;

public class DStringUtils {
	
	private DStringUtils() {}
	
	/**
	 * 判断字符是否是带两位数字的小数
	 * @param str
	 * @return
	 */
	public static boolean isDouble(String str){
		if (null == str || "".equals(str)) {
			return false;
		}
		Pattern pattern= Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式
		return pattern.matcher(str).matches();
	}
	
	
}

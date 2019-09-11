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
	
	/**
	 * 判断字符全部是中文
	 * @param str
	 * @return
	 */
	public static boolean isChineseAll(String str) {
		if (null == str || "".equals(str)) {
			return false;
		}
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]+");
		return p.matcher(str).matches();
	}
	
	/**
	 * 判断字符是否包含中文
	 * @param str
	 * @return
	 */
	public static boolean isContainsChinese(String str) {
		if (null == str || "".equals(str)) {
			return false;
		}
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		return p.matcher(str).find();
	}
	
	/**
	 * 判断字符全部是英文
	 * @param str
	 * @return
	 */
	public static boolean isEnglishAll(String str) {
		if (null == str || "".equals(str)) {
			return false;
		}
		Pattern p = Pattern.compile("[a-zA-z]+");
		return p.matcher(str).matches();
	}
	
	/**
	 * 判断字符是否包含英文
	 * @param str
	 * @return
	 */
	public static boolean isContainsEnglish(String str) {
		if (null == str || "".equals(str)) {
			return false;
		}
		Pattern p = Pattern.compile("[a-zA-z]");
		return p.matcher(str).find();
	}
	
}

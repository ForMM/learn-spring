package com.example.demo.util.encrpyt;

import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

public class MD5Util {
	private static Logger logger = LoggerFactory.getLogger(MD5Util.class);
	/**
	 * 使用spring的MD5加密
	 * @param param
	 * @return
	 */
	public static String getMd5Simple(String param){
		return DigestUtils.md5DigestAsHex(param.getBytes());
	}
	
	/**
	 * 自定义加密规则
	 * 原字符串加密后8位+加密字符，再加密
	 * @param param
	 * @return
	 */
	public static String getMd5(String param){
		String md5DigestAsHex = DigestUtils.md5DigestAsHex(param.getBytes());
		String temp = md5DigestAsHex.substring(0, 8);
		return DigestUtils.md5DigestAsHex((md5DigestAsHex+temp).getBytes());
	}
	
	/**
	 * java原生自带MD5加密
	 * @param source
	 * @return
	 */
	public static String getMD5(String param) {
		String s = null;
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };// 用来将字节转换成16进制表示的字符
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			md.update(param.getBytes());
			byte tmp[] = md.digest();// MD5的计算结果是一个128位的长整数，用字节表示就是16个字节
			char str[] = new char[16 * 2];// 每个字节用16进制表示的话，使用两个字符，所以表示成16进制需要32个字符
			int k = 0;// 表示转换结果中对应的字符位置
			for (int i = 0; i < 16; i++) {// 从第一个字节开始，对MD5的每一个字节转换成16进制字符的转换
				byte byte0 = tmp[i];// 取第 i 个字节
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];// 取字节中高 4 位的数字转换,为逻辑右移，将符号位一起右移
				str[k++] = hexDigits[byte0 & 0xf];// 取字节中低 4 位的数字转换
			}
			s = new String(str);// 换后的结果转换为字符串 
		} catch (NoSuchAlgorithmException e) {
			logger.error("getMD5 error", e);
		}
		return s;
	}
	
	
	public static void main(String[] args) {
		String hh="uuuuuuuu";
		String a = getMd5Simple(hh);
		System.out.println(a+",length:"+a.length());
		
		String md5 = getMD5(hh);
		System.out.println(md5);
		
	}
}

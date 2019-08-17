package com.example.demo.util.encrpyt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShaUtil {
	private static Logger logger = LoggerFactory.getLogger(ShaUtil.class);

	// 实私有例化，不允许创建对象
	private ShaUtil() {
	};

	/**
	 * 获取sha-1计算值
	 * 
	 * @param param
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String getSha1(String param) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
		byte[] cipherBytes = messageDigest.digest(param.getBytes());
		return Hex.encodeHexString(cipherBytes);
	}

	/**
	 * 获取sha-256计算值
	 * 
	 * @param param
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String getSha256(String param) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		byte[] cipherBytes = messageDigest.digest(param.getBytes());
		return Hex.encodeHexString(cipherBytes);
	}

	public static void main(String[] args) {
		String hh = "uuuuuuuu";
		String a;
		String b;
		try {
			a = getSha1(hh);
			b = getSha256(hh);
			logger.info("a value:{},lenth:{}", a, a.length());
			logger.info("b value:{},lenth:{}", b, b.length());
		} catch (NoSuchAlgorithmException e) {
			logger.error("getSha1 have a error!", e);
		}
	}
}

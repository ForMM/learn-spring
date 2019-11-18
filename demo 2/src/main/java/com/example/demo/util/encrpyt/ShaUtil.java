package com.example.demo.util.encrpyt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShaUtil {
	private static Logger logger = LoggerFactory.getLogger(ShaUtil.class);

	// 实私有例化，不允许创建对象
	private ShaUtil() {
	}

	/**
	 * 获取sha-1计算值
	 * 
	 * @param param
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String getSha1(byte[] param) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
		byte[] cipherBytes = messageDigest.digest(param);
		return Hex.encodeHexString(cipherBytes);
	}

	/**
	 * 获取sha-256计算值
	 * 
	 * @param param
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String getSha256(byte[] param) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		byte[] cipherBytes = messageDigest.digest(param);
		return Hex.encodeHexString(cipherBytes);
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		String hh = "uuuuuuuu爱情";
		String a;
		String b;
		try {
			a = getSha1(hh.getBytes("utf-8"));
			b = getSha256(hh.getBytes("utf-8"));
			StringBuilder sb = new StringBuilder();
			for(;;) {
				sb.append("u");
				Encoder encoder = Base64.getEncoder();
				String string = new String(encoder.encode(sb.toString().getBytes()), "utf-8");
				if(string.length() == 128) {
					logger.info("sb value:{},lenth:{}", sb, sb.length());
					break;
				}
			}
			
			
			Encoder encoder = Base64.getEncoder();
			String string = new String(encoder.encode(b.getBytes()), "utf-8");
			logger.info("a value:{},lenth:{}", a, a.length());
			logger.info("b value:{},lenth:{}", b, b.length());
			logger.info("string value:{},lenth:{}", string, string.length());
		} catch (NoSuchAlgorithmException e) {
			logger.error("getSha1 have a error!", e);
		}
	}
}

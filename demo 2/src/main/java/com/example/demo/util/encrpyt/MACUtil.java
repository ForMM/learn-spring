package com.example.demo.util.encrpyt;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MACUtil {
	private static Logger logger = LoggerFactory.getLogger(MACUtil.class);

	private MACUtil() {
	}

	/**
	 * HmacMD5、HmacSHA1、HmacSHA256等类型的密钥
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] initHmacKey(String type) throws NoSuchAlgorithmException {
		KeyGenerator generator = KeyGenerator.getInstance(type);
		SecretKey generateKey = generator.generateKey();
		byte[] encoded = generateKey.getEncoded();
		logger.info("key:{}",Hex.encodeHexString(encoded));
		return encoded;
	}

	/**
	 * HmacMD5、HmacSHA1、HmacSHA256等哈希计算摘要
	 * @param param
	 * @param key
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	public static String encodeHmac(String param, byte[] key,String type) throws NoSuchAlgorithmException, InvalidKeyException {
		SecretKey secretKey = new SecretKeySpec(key, type);
		// 实例化Mac
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		// 初始化mac
		mac.init(secretKey);
		// 执行消息摘要
		byte[] digest = mac.doFinal(param.getBytes());
		return Hex.encodeHexString(digest);
	}
	
	public static void main(String[] args) throws DecoderException {
		String hh = "uuuuuuuu";
		try {
			String key="b7e324b28c66cb5a74eb8a245dd48f48d203c44f9f4c2d2a371e4740e0eb218aa2c83c6bc7a21a40f06f7b89bcba6da9fd0bb7118a810f3221a831f165c2b4f7";
			byte[] decodeHex = Hex.decodeHex(key);
			
			String encodeHmacMD5 = encodeHmac(hh,initHmacKey("HmacMD5"),"HmacMD5");
			logger.info("value:{},length:{}",encodeHmacMD5,encodeHmacMD5.length());
			String encodeHmacSHA1 = encodeHmac(hh,initHmacKey("HmacSHA1"),"HmacSHA1");
			logger.info("value:{},length:{}",encodeHmacSHA1,encodeHmacSHA1.length());
			String encodeHmacSHA256 = encodeHmac(hh,initHmacKey("HmacSHA256"),"HmacSHA256");
			logger.info("value:{},length:{}",encodeHmacSHA256,encodeHmacSHA256.length());
		} catch (InvalidKeyException e) {
			logger.error("encodeHmacMD5 InvalidKeyException",e);
		} catch (NoSuchAlgorithmException e) {
			logger.error("encodeHmacMD5 NoSuchAlgorithmException",e);
		}
	}

}

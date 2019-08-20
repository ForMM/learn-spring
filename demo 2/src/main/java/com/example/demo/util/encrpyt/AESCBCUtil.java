package com.example.demo.util.encrpyt;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AESCBCUtil {
	private static Logger logger = LoggerFactory.getLogger(AESCBCUtil.class);

	private AESCBCUtil() {
	}

	/**
	 * 生成AES密钥
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] initKey() throws NoSuchAlgorithmException {
		KeyGenerator keygen = KeyGenerator.getInstance("AES");
		SecureRandom random = new SecureRandom();
		keygen.init(random);
		byte[] encoded = keygen.generateKey().getEncoded();
		logger.info("key:{},length:{}", Hex.encodeHexString(encoded), Hex.encodeHexString(encoded).length());
		return encoded;
	}

	/**
	 * AES加密(默认EBC加密模式)
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptAES(byte[] data, byte[] key) throws Exception {
		SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// 创建密码器
		IvParameterSpec iv = new IvParameterSpec(key);// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);// 初始化
		return cipher.doFinal(data);
	}

	/**
	 * AES解密
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptAES(byte[] data, byte[] key) throws Exception {
		SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// 创建密码器
		IvParameterSpec iv = new IvParameterSpec(key);// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);// 初始化
		return cipher.doFinal(data);
	}

	public static void main(String[] args) throws DecoderException {
		String hh = "uuuuuuuu爱情";
		try {

			byte[] enkey = initKey();
			byte[] encryptAES = encryptAES(hh.getBytes("UTF-8"), enkey);
			String encodeHexString = Hex.encodeHexString(encryptAES);
			logger.info("value:{},length:{}", encodeHexString, encodeHexString.length());

			byte[] decryptAES = decryptAES(encryptAES, enkey);
			String origin = new String(decryptAES, "UTF-8");
			logger.info("value:{},length:{}", origin, origin.length());

		} catch (Exception e) {
			logger.error("encryptDES Exception", e);
		}
	}

}

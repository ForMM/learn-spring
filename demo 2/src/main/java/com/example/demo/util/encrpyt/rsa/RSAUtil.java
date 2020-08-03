package com.example.demo.util.encrpyt.rsa;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import static com.alibaba.druid.support.json.JSONUtils.toJSONString;

public class RSAUtil {
	private static Logger logger = LoggerFactory.getLogger(RSAUtil.class);

	private RSAUtil() {
	}

	/**
	 * 生成密钥对（私钥、公钥）
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 */
	public static Map<String, String> generateKeyPair() throws Exception {
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		SecureRandom random = new SecureRandom();
		kpg.initialize(1024, random);

		// 生成密钥对
		KeyPair keyPair = kpg.generateKeyPair();
		Encoder encoder = Base64.getEncoder();

		// 得到公钥
		PublicKey publicKey = keyPair.getPublic();
		byte[] encoded = publicKey.getEncoded();
		String pub = new String(encoder.encode(encoded), "utf-8");

		// 得到私钥
		PrivateKey privateKey = keyPair.getPrivate();
		byte[] pricode = privateKey.getEncoded();
		String pri = new String(encoder.encode(pricode), "utf-8");

		Map<String, String> map = new HashMap<>();
		map.put("publicKey", pub);
		map.put("privateKey", pri);

		RSAPublicKey rsp = (RSAPublicKey) keyPair.getPublic();
		BigInteger bint = rsp.getModulus();
		byte[] b = bint.toByteArray();
		String retValue = new String(encoder.encode(b), "utf-8");
		map.put("modulus", retValue);
		String jsonString = new Gson().toJson(map);
		logger.info("key:{}", jsonString);
		return map;
	}

	/**
	 * 加密方法 source： 源数据
	 */
	public static String encrypt(String source, String publicKey) throws Exception {
		Key key = getPublicKey(publicKey);
		/** 得到Cipher对象来实现对源数据的RSA加密 */
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] b = source.getBytes();
		/** 执行加密操作 */
		byte[] b1 = cipher.doFinal(b);
		Encoder encoder = Base64.getEncoder();
		return new String(encoder.encode(b1), "utf-8");
	}

	/**
	 * 解密算法 cryptograph:密文
	 */
	public static String decrypt(String cryptograph, String privateKey) throws Exception {
		Key key = getPrivateKey(privateKey);
		/** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, key);
		Decoder decoder = Base64.getDecoder();
		byte[] b1 = decoder.decode(cryptograph.getBytes());
		/** 执行解密操作 */
		byte[] b = cipher.doFinal(b1);
		return new String(b);
	}

	/**
	 * 得到公钥
	 * 
	 * @param key
	 *            密钥字符串（经过base64编码）
	 * @throws Exception
	 */
	public static PublicKey getPublicKey(String key) throws Exception {
		Decoder decoder = Base64.getDecoder();
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decoder.decode(key.getBytes()));
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		return keyFactory.generatePublic(keySpec);
	}

	/**
	 * 得到私钥
	 * 
	 * @param key
	 *            密钥字符串（经过base64编码）
	 * @throws Exception
	 */
	public static PrivateKey getPrivateKey(String key) throws Exception {
		Decoder decoder = Base64.getDecoder();
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decoder.decode(key.getBytes()));
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		return keyFactory.generatePrivate(keySpec);
	}

	/**
	 * 计算签名（私钥）
	 * @param content
	 * @param privateKey
	 * @return
	 */
	public static String sign(String content, String privateKey) {
		Decoder decoder = Base64.getDecoder();
		Encoder encoder = Base64.getEncoder();
		try {
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(decoder.decode(privateKey.getBytes()));
			KeyFactory keyf = KeyFactory.getInstance("RSA");
			PrivateKey priKey = keyf.generatePrivate(priPKCS8);

			Signature signature = Signature.getInstance("SHA256WithRSA");

			signature.initSign(priKey);
			signature.update(content.getBytes("utf-8"));

			byte[] signed = signature.sign();

			return new String(encoder.encode(signed));
		} catch (Exception e) {
			logger.error("sign error", e);
		}
		return null;
	}

	/**
	 * 验签（公钥）
	 * @param content
	 * @param sign
	 * @param publicKey
	 * @return
	 */
	public static boolean checkSign(String content, String sign, String publicKey) {
		Decoder decoder = Base64.getDecoder();
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			byte[] encodedKey = decoder.decode(publicKey);
			PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

			java.security.Signature signature = java.security.Signature.getInstance("SHA256WithRSA");
			signature.initVerify(pubKey);
			signature.update(content.getBytes("utf-8"));
			return signature.verify(decoder.decode(sign));
		} catch (Exception e) {
			logger.error("checkSign error", e);
		}
		return false;
	}

	public static void main(String[] args) {
		try {
//			generateKeyPair();
			
			String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKjQ06QJDcJdQXVrq5mGqj6HwS98ewYttIU7/1+wj+ZI3JrOfpZmDgQZFTMkzp73PZSKHmVLtdF2pzEyKPvfrtwv/l1/YFcVY+vCy1B8Y4ZiN404TQYO9MCBXE9Y5Mq1BfdQ93XpyriH3WwQK2SrJIxb+8wNqp1mjqHHev4X1j7PAgMBAAECgYARQNaAi1RWqwVHpOty02qO3X0HB2fjZ8RtYYZmFzt9w7CYOcaCRIfmL1IVWgur/4TRq5QTsjsw3zmTM5cwttm+CLHjYUZm6zuXfn1b/C6gxn6O/XYTjUwaz9KzEQdAMrgqbQwzqWMdgA2/nMZRSNgWaLtEDdBQi93WA7el+DasAQJBANwB3Lp+AbwS5ch6wwtwJz2H8oaqUa+2aqWHude2Hmy6Wb+6oiK3hJPD2HDh6bl4Zg4CWXD5bOWLqJlCivbhKQECQQDEbwIjhqxtyHjxCF/S2QoZKzqH27DNoxSBMLzOOPIS7QO44uflsrNpdxOQYj6qEk2QVl6eql9a7fTqvHmOFxfPAkBcQp+oyUy67QXVc721XzvlbsxCssv8rre8YNWLY0ERAXi/kWmKu4DqYneyptpXv+i0OUH+wWpsPIvgcWpLK5MBAkB+5wRntSKCTigr93AIhGTsu6u1h21ifD/L8pOXWtyw82QbNqQAFgGoWeBOv9mh/+8SF2pLcN/nm6FFYPvamvqrAkEAhsFC6fYFKLKc6GNCebODZstesDJ+afn9fsFBURg4d+A0De+i/gcg0OBWjO20+c81X99aWBNrqoTQNEu8OdAPLg==";
			String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCo0NOkCQ3CXUF1a6uZhqo+h8EvfHsGLbSFO/9fsI/mSNyazn6WZg4EGRUzJM6e9z2Uih5lS7XRdqcxMij7367cL/5df2BXFWPrwstQfGOGYjeNOE0GDvTAgVxPWOTKtQX3UPd16cq4h91sECtkqySMW/vMDaqdZo6hx3r+F9Y+zwIDAQAB";
			String modulus = "AKjQ06QJDcJdQXVrq5mGqj6HwS98ewYttIU7/1+wj+ZI3JrOfpZmDgQZFTMkzp73PZSKHmVLtdF2pzEyKPvfrtwv/l1/YFcVY+vCy1B8Y4ZiN404TQYO9MCBXE9Y5Mq1BfdQ93XpyriH3WwQK2SrJIxb+8wNqp1mjqHHev4X1j7P";
			
			String param = "hhhhhhh爱情";
			String encrypt = encrypt(param,publicKey);
			logger.info("加密后值:{}",encrypt);
			String decrypt = decrypt(encrypt,privateKey);
			logger.info("解密后值:{}",decrypt);
			
			
			String sign = sign(param,privateKey);
			logger.info("签名:{}",sign);
			String param1 = "hhhhhhh爱情1";
			boolean checkSign = checkSign(param1,sign,publicKey);
			logger.info("checkSign:{}",checkSign);
			
		} catch (Exception e) {
			logger.error("generateKeyPair error", e);
		}
	}

}

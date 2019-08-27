package com.example.demo.util.encrpyt.rsa;

import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.HashMap;
import java.util.Map;

public class RSAUtil {
	private RSAUtil() {}
	
	/**
	 * 生成密钥对（私钥、公钥）
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 */
	public static Map<String,String> generateKeyPair() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		SecureRandom random = new SecureRandom();
		kpg.initialize(1024, random);
		
		//生成密钥对
		KeyPair keyPair = kpg.generateKeyPair();
		Encoder encoder = Base64.getEncoder();
		
		//得到公钥
		PublicKey publicKey = keyPair.getPublic();
		byte[] encoded = publicKey.getEncoded();
		String pub = new String(encoder.encode(encoded),"utf-8");
		
		//得到私钥
		PrivateKey privateKey = keyPair.getPrivate();
		byte[] pricode = privateKey.getEncoded();
		String pri = new String(encoder.encode(pricode),"utf-8");
		
		Map<String,String> map = new HashMap<>();
		map.put("publicKey",pub);
		map.put("privateKey",pri);
		
		
		return map;
		
		
	}
	
	
}

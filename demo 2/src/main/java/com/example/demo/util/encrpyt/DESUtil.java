package com.example.demo.util.encrpyt;

import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.enums.DESType;

public class DESUtil {
	private static Logger logger = LoggerFactory.getLogger(DESUtil.class);

	private DESUtil() {
	}

	/**
	 * DES、3DES等类型的密钥
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] initKey(String type,int keySize) throws NoSuchAlgorithmException {
		KeyGenerator generator = KeyGenerator.getInstance(type);
		generator.init(keySize);
		SecretKey generateKey = generator.generateKey();
		byte[] encoded = generateKey.getEncoded();
		logger.info("key:{}",Hex.encodeHexString(encoded));
		return encoded;
	}

	/**
	 * DES、3DES等类型的加密
	 * @param data
	 * @param key
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptDES(byte[] data, byte[] key,String type) throws Exception{
		SecretKey secretKey = new SecretKeySpec(key, type);
		Cipher cipher = Cipher.getInstance(type);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] encrypt = cipher.doFinal(data);
		return encrypt;
	}
	
	/**
	 * DES、3DES等类型的解密
	 * @param data
	 * @param key
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptDES(byte[] data, byte[] key,String type) throws Exception{
		SecretKey secretKey = new SecretKeySpec(key, type);
		Cipher cipher = Cipher.getInstance(type);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] encrypt = cipher.doFinal(data);
		return encrypt;
	}
	
	public static void main(String[] args) throws DecoderException {
		String hh = "uuuuuuuu";
		try {
			
			byte[] enkey = initKey(DESType.DES.getValue(),DESType.DES.getKeySize());
			logger.info("key size:{}",enkey.length);
			byte[] encryptDES = encryptDES(hh.getBytes(),enkey,DESType.DES.getValue());
			logger.info("value:{},length:{}",encryptDES,encryptDES.length);
			
			byte[] decryptDES = decryptDES(encryptDES,enkey,DESType.DES.getValue());
			String origin = new String(decryptDES);
			logger.info("value:{},length:{}",origin,origin.length());
			
			//---3DES
			
			byte[] enkey3 = initKey(DESType.DES3.getValue(),DESType.DES3.getKeySize());
			logger.info("key size:{}",enkey3.length);
			byte[] encryptDES3 = encryptDES(hh.getBytes(),enkey3,DESType.DES3.getValue());
			logger.info("value:{},length:{}",encryptDES3,encryptDES3.length);
			
			byte[] decryptDES3 = decryptDES(encryptDES3,enkey3,DESType.DES3.getValue());
			String origin3 = new String(decryptDES3);
			logger.info("value:{},length:{}",origin3,origin3.length());
			
		} catch (Exception e) {
			logger.error("encryptDES Exception",e);
		} 
	}

}

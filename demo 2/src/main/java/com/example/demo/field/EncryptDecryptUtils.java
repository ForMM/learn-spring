package com.example.demo.field;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Objects;

import org.bouncycastle.util.encoders.Hex;

import com.example.demo.enums.DESType;
import com.example.demo.field.annotation.EncryptDecryptField;
import com.example.demo.util.encrpyt.DESUtil;

public class EncryptDecryptUtils {
	private static String key = "e98a5e920d92267a2f2929b3b979865e0d2ca829c107074a";
	/**
	 * 多field加密方法
	 * @param declaredFields
	 * @param parameterObject
	 * @param <T>
	 * @return
	 * @throws Exception 
	 */
	public static <T> T encrypt(Field[] declaredFields, T parameterObject ) throws Exception {
		for (Field field : declaredFields){
			EncryptDecryptField annotation = field.getAnnotation(EncryptDecryptField.class);
			if (Objects.isNull(annotation)){
				continue;
			}
			encrypt(field, parameterObject);
		}
		return parameterObject;
	}


	/**
	 * 单个field加密方法
	 * @param field
	 * @param parameterObject
	 * @param <T>
	 * @return
	 * @throws Exception 
	 */
	public static <T> T  encrypt(Field field, T parameterObject ) throws Exception {
		field.setAccessible(true);
		Object object = field.get(parameterObject);
		if (object instanceof BigDecimal){
			BigDecimal value = (BigDecimal)object;
			long longValue = value.movePointRight(4).subtract(BigDecimal.valueOf(Integer.MAX_VALUE >> 3)).longValue();
			field.set(parameterObject, BigDecimal.valueOf(longValue));
		}else if (object instanceof Integer){
			//TODO 定制Integer类型的加密算法
		}else if (object instanceof Long){
			//TODO 定制Long类型的加密算法
		}else if (object instanceof String){
			String value = (String)object;
			byte[] decode = Hex.decode(key);
			byte[] encryptDES3 = DESUtil.encryptDES(value.getBytes(),decode,DESType.DES3.getValue());
			field.set(parameterObject, Hex.toHexString(encryptDES3));
			//TODO 定制String类型的加密算法

		}
		return parameterObject;
	}

	/**
	 * 解密方法
	 * @param result
	 * @param <T>
	 * @return
	 * @throws Exception 
	 */
	public static <T> T decrypt(T result) throws Exception {
		Class<?> parameterObjectClass = result.getClass();
		Field[] declaredFields = parameterObjectClass.getDeclaredFields();
		decrypt(declaredFields, result);
		return result;
	}

	/**
	 * 多个field解密方法
	 * @param declaredFields
	 * @param result
	 * @throws Exception 
	 */
	public static void decrypt(Field[] declaredFields, Object result) throws Exception {
		for (Field field : declaredFields){
			EncryptDecryptField annotation = field.getAnnotation(EncryptDecryptField.class);
			if (Objects.isNull(annotation)){
				continue;
			}
			decrypt(field, result);
		}
	}

	/**
	 * 单个field解密方法
	 * @param field
	 * @param result
	 * @throws Exception 
	 */
	public static void decrypt(Field field, Object result) throws Exception {
			field.setAccessible(true);
			Object object = field.get(result);
			if (object instanceof BigDecimal){
				BigDecimal value = (BigDecimal)object;
				double doubleValue = value.add(BigDecimal.valueOf(Integer.MAX_VALUE >> 3)).movePointLeft(4).doubleValue();
				field.set(result, BigDecimal.valueOf(doubleValue));
			}else if (object instanceof Integer){
				//TODO 定制Integer类型的加密算法
			}else if (object instanceof Long){
				//TODO 定制Long类型的加密算法
			}else if (object instanceof String){
				//TODO 定制String类型的加密算法
				String value = (String)object;
				byte[] decode = Hex.decode(key);
				byte[] dvalue = Hex.decode(value);
				byte[] decryptDES3 = DESUtil.decryptDES(value.getBytes(),decode,DESType.DES3.getValue());
				field.set(result, new String(decryptDES3));

			}
	}
}

package com.example.demo.field.interceptor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.util.Objects;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import com.example.demo.enums.DESType;
import com.example.demo.field.annotation.EncryptDecryptClass;
import com.example.demo.util.encrpyt.DESUtil;

@Intercepts({@Signature(type = ParameterHandler.class, method = "update", args = {PreparedStatement.class, Object.class})})
@ConditionalOnProperty(value = "domain.encrypt", havingValue = "true")
@Component
public class FiledEncyptInterceptor implements Interceptor {

	private static Logger logger = LoggerFactory.getLogger(FiledEncyptInterceptor.class);
	private static String hh = "uuuuuuuu";
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		
		if(invocation.getTarget() instanceof ParameterHandler) {
			ParameterHandler parameterHandler = (ParameterHandler) invocation.getTarget();
			PreparedStatement ps = (PreparedStatement) invocation.getArgs()[0];
			
			// 反射获取 参数对像
			Field parameterField =
					parameterHandler.getClass().getDeclaredField("parameterObject");
			parameterField.setAccessible(true);
			Object parameterObject = parameterField.get(parameterHandler);
			if (Objects.nonNull(parameterObject)){
				Class<?> parameterObjectClass = parameterObject.getClass();
				EncryptDecryptClass encryptDecryptClass = AnnotationUtils.findAnnotation(parameterObjectClass, EncryptDecryptClass.class);
				if (Objects.nonNull(encryptDecryptClass)){
					Field[] declaredFields = parameterObjectClass.getDeclaredFields();

					byte[] enkey3 = DESUtil.initKey(DESType.DES3.getValue(),DESType.DES3.getKeySize());
					byte[] encryptDES3 = DESUtil.encryptDES(hh.getBytes(),enkey3,DESType.DES3.getValue());
					
					final Object encrypt = encryptDecrypt.encrypt(declaredFields, parameterObject);
				}
			}
		}
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
		
	}

}

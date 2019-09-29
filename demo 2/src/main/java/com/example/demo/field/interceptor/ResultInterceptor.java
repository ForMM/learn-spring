package com.example.demo.field.interceptor;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import com.example.demo.field.EncryptDecryptUtils;
import com.example.demo.field.annotation.EncryptDecryptClass;

@Intercepts({@Signature(type = ResultSetHandler.class, method = "handleResultSets", args={Statement.class})})
@ConditionalOnProperty(value = "domain.decrypt", havingValue = "true")
@Component
public class ResultInterceptor implements Interceptor {

	private static Logger logger = LoggerFactory.getLogger(ResultInterceptor.class);
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		logger.info("ResultInterceptor into");
		Object result = invocation.proceed();
		if (Objects.isNull(result)){
			return null;
		}
		
		if (result instanceof ArrayList) {
			ArrayList resultList = (ArrayList) result;
			if (!resultList.isEmpty() && needToDecrypt(resultList.get(0))){
				for (int i = 0; i < resultList.size(); i++) {
					EncryptDecryptUtils.decrypt(resultList.get(i));
				}
			}
		}else {
			if (needToDecrypt(result)){
				EncryptDecryptUtils.decrypt(result);
			}
		}
		return result;
	}
	
	public boolean needToDecrypt(Object object){
		Class<?> objectClass = object.getClass();
		EncryptDecryptClass encryptDecryptClass = AnnotationUtils.findAnnotation(objectClass, EncryptDecryptClass.class);
		if (Objects.nonNull(encryptDecryptClass)){
			return true;
		}
		return false;
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target,this);
	}

	@Override
	public void setProperties(Properties properties) {
		
	}

}

package com.example.demo.field.interceptor;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.util.Objects;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
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

@Intercepts({
	@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
@ConditionalOnProperty(value = "domain.encrypt", havingValue = "true")
@Component
public class ParamInterceptor implements Interceptor {

	private static Logger logger = LoggerFactory.getLogger(ParamInterceptor.class);
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		logger.info("ParamInterceptor enter");
		if(invocation.getTarget() instanceof Executor) {
//			Executor executor = (Executor) invocation.getTarget();
//			MappedStatement statement = (MappedStatement)invocation.getArgs()[0];
			Object parameterObject = invocation.getArgs()[1];
//			BoundSql boundSql = statement.getBoundSql(parameterObject);
//			logger.info("sql:{}",boundSql.getSql());
			if (Objects.nonNull(parameterObject)){
				Class<?> parameterObjectClass = parameterObject.getClass();
				EncryptDecryptClass encryptDecryptClass = AnnotationUtils.findAnnotation(parameterObjectClass, EncryptDecryptClass.class);
				if (Objects.nonNull(encryptDecryptClass)){
					Field[] declaredFields = parameterObjectClass.getDeclaredFields();
					EncryptDecryptUtils.encrypt(declaredFields, parameterObject);
				}
			}
		}
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target,this);
	}

	@Override
	public void setProperties(Properties properties) {
		
	}

}
package com.example.demo.log.aop;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.aspectj.lang.reflect.MethodSignature;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

@Aspect
@Component
public class SyslogAop {
	private static final String MethodSignature = null;
	private static Logger logger = LoggerFactory.getLogger(SyslogAop.class);
	private static String mdcKeyProName = "userName";
    private static String mdcKeyReqId = "reqId";

    @Pointcut("@annotation(com.example.demo.log.annotation.LogAnnotation)")
    public void webLog(){}

//    @Before("webLog()")
//    public void before(JoinPoint joinPoint){
//        MDC.put(mdcKeyProName,"demo");
//        MDC.put(mdcKeyReqId, UUID.randomUUID().toString().replace("-", ""));
//        logger.info("beforeMethod");
//        
//        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
//        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
//        HttpServletRequest request = sra.getRequest();
// 
//        String url = request.getRequestURL().toString();
//        String method = request.getMethod();
//        String queryString = request.getQueryString();
//        Object[] args = joinPoint.getArgs();
//        String params = "";
//        //获取请求参数集合并进行遍历拼接
//        if(args.length>0){
//            if("POST".equals(method)){
//                Object object = args[0];
//                params = JSON.toJSONString(object, SerializerFeature.WriteMapNullValue);
//            }else if("GET".equals(method)){
//                params = queryString;
//            }
//        }
// 
//        logger.info("请求地址:{}",url);
//        logger.info("请求类型:{}",method);
//        logger.info("请求参数:{}",params);
//        
//    }
    
    @Around("webLog()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
    	long startTime = System.currentTimeMillis();
    	MDC.put(mdcKeyProName,"demo");
        MDC.put(mdcKeyReqId, UUID.randomUUID().toString().replace("-", ""));
        
        Object[] parameterValues = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        String name = method.getName();
        
        Map<String, Object> param = new HashMap<>();
        String[] parameterNames = signature.getParameterNames();
        for(int i=0;i<parameterNames.length;i++) {
        	param.put(parameterNames[i], parameterValues[i]);
        }
        String params = JSON.toJSONString(param, SerializerFeature.WriteMapNullValue);
        logger.info("method:{},params:{}",name,params);
        
    	Object proceed = joinPoint.proceed();
    	long endTime = System.currentTimeMillis();
    	logger.info("times:{}",(endTime-startTime));
    	
    	String result = JSON.toJSONString(proceed, SerializerFeature.WriteMapNullValue);
    	logger.info("result:{}",result);
    	return proceed;
    }
    
    
   /* @After("webLog()")
    public void after(JoinPoint joinPoint){
    	logger.info("afterMethod");
    }*/

    @AfterReturning(pointcut = "webLog()")
    public void afterReturning(){
        MDC.remove(mdcKeyProName);
        MDC.remove(mdcKeyReqId);
    }
    
    @AfterThrowing(pointcut = "webLog()")
    public void afterThrowing(){
    	 MDC.remove(mdcKeyProName);
         MDC.remove(mdcKeyReqId);
    	logger.info("afterThrowing");
    }
    
    

}

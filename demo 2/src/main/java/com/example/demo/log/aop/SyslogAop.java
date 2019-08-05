package com.example.demo.log.aop;

import java.util.UUID;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SyslogAop {
	private static Logger logger = LoggerFactory.getLogger(SyslogAop.class);
	private static String mdcKeyProName = "userName";
    private static String mdcKeyReqId = "reqId";

    @Pointcut("@annotation(com.example.demo.log.annotation.LogAnnotation)")
    public void webLog(){}

    @Before("webLog()")
    public void before(JoinPoint joinPoint){
        MDC.put(mdcKeyProName,"demo");
        MDC.put(mdcKeyReqId, UUID.randomUUID().toString());
        logger.info("beforeMethod");
        
        
    }
    
    @After("webLog()")
    public void after(JoinPoint joinPoint){
    	logger.info("afterMethod");
    }

    @AfterReturning(pointcut = "webLog()", returning = "result")
    public void afterReturning(Object result){
    	logger.info("afterReturning:"+result);
        MDC.remove(mdcKeyProName);
        MDC.remove(mdcKeyReqId);
    }

}

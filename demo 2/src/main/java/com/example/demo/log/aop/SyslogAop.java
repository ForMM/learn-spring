package com.example.demo.log.aop;

import java.util.UUID;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SyslogAop {
	private static String mdcKeyProName = "userName";
    private static String mdcKeyReqId = "reqId";

    @Pointcut("execution(public * com.example.demo.controller..*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void before(JoinPoint joinPoint){
        MDC.put(mdcKeyProName,"demo");
        MDC.put(mdcKeyReqId, UUID.randomUUID().toString());
    }

    @AfterReturning(pointcut = "webLog()", returning = "ret")
    public void afterReturning(Object ret){
        MDC.remove(mdcKeyProName);
        MDC.remove(mdcKeyReqId);
    }

}

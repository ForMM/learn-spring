package com.example.demo.validator.aop;

import com.example.demo.validator.annotation.ValidatorAnn;
import com.google.common.collect.Maps;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Map;

@Aspect
@Component
public class ValidatorAop {

    @Pointcut("execution(* com.example.demo.controller.*.*(..))")
    public void validatorPoint(){}

    @Around("validatorPoint() && @annotation(validatorAnn)")
    public Object doAround(ProceedingJoinPoint jp, ValidatorAnn validatorAnn){
        Object result;
        Class<?> dtoObject;
        Class<?> resultObject;

        Object object = jp.getTarget();
        Object[] args = jp.getArgs();


        return  "";
    }

}

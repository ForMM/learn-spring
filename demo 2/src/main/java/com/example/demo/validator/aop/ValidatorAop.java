package com.example.demo.validator.aop;

import com.example.demo.common.Result;
import com.example.demo.controller.AccountController;
import com.example.demo.enums.ResultCode;
import com.example.demo.exception.ValidatorException;
import com.example.demo.validator.annotation.ObjectValidatorAnn;
import com.example.demo.validator.annotation.StringValidatorAnn;
import com.example.demo.validator.annotation.ValidatorAnn;
import com.example.demo.validator.process.StringValidator;
import com.example.demo.validator.process.ValidatorProcessManager;
import com.google.common.collect.Maps;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around
        ;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

@Aspect
@Component
public class ValidatorAop {
    private static Logger logger = LoggerFactory.getLogger(ValidatorAop.class);
    @Resource
    public ValidatorProcessManager validatorProcessManager;

    @Pointcut("execution(* com.example.demo.controller.*.*(..))")
    public void validatorPoint(){}

    @Around("validatorPoint() && @annotation(validatorAnn)")
    public Object doAround(ProceedingJoinPoint jp, ValidatorAnn validatorAnn)throws Throwable{
        Object result;
        Object[] args = jp.getArgs();
        try{
            MethodSignature methodSignature = (MethodSignature)jp.getSignature();
            Method method = methodSignature.getMethod();
            String methodName = method.getName();
            Annotation[][] annotations = method.getParameterAnnotations();
            for (int i=0;i<args.length;i++){
                for(int j = 0; j < annotations[i].length; j++){
                    Annotation annotation = annotations[i][j];
                    if (annotation instanceof ObjectValidatorAnn){
                        Object obj = args[i];
                        Field[] declaredFields = obj.getClass().getDeclaredFields();
                        if(declaredFields!=null && declaredFields.length>0){
                            for (Field field:declaredFields){
                                field.setAccessible(true);
                                Object o = field.get(obj);
                                field.setAccessible(false);
                                Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
                                for (Annotation anno:declaredAnnotations){
                                    validatorProcessManager.handler(anno,o,null);
                                }
                            }
                        }
                        continue;
                    }

                    if(annotation instanceof StringValidatorAnn){
                        Object obj = args[i];
                        validatorProcessManager.handler(annotation,obj,null);
                    }
                }
            }

            long start = System.currentTimeMillis();
            result = jp.proceed();
            long end = System.currentTimeMillis();
            logger.info(String.format("接口名=%s,调用耗时=%s", methodName, (end - start)+"ms"));

        }catch (ValidatorException e){
            result = "success";
            return new Result(e.getCode(), e.getMsg());
        }
        return  result;
    }

}

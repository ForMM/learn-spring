package com.example.demo.validator.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.annotation.Annotation;
import java.util.Map;

@Component
public class ValidatorProcessManager<T> {

    /**
     * 责任链第一个链
     */
    private BaseValidatorProcess firstProcess;

    /**
     * 责任链中集合
     */
    @Autowired(required = false)
    private Map<String,BaseValidatorProcess> validatorProcessMap;

    @PostConstruct
    public void init(){
        BaseValidatorProcess validatorProcess = null;
        if(validatorProcessMap.isEmpty()){
            return;
        }

        //逻辑感觉好别扭
        for (BaseValidatorProcess process : validatorProcessMap.values()){
            if(validatorProcess == null){
                firstProcess = process;
                validatorProcess = process;
            }else{
                validatorProcess.setNextProcess(process);
            }
        }
    }

    public void handler(Annotation annotation, T value, Map<String, Object> targetMap){
        firstProcess.dosomething(annotation,value,targetMap);
    }



}

package com.example.demo.validator.process;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 *责任链的抽象类
 */
public abstract class BaseValidatorProcess<T> {
    private BaseValidatorProcess nextProcess;

    public void handleValidator(Annotation annotation, T value, Map<String, Object> targetMap){
        if(isMatch(annotation)){
            //如果当前节点可以处理，直接处理
            dosomething(annotation,value,targetMap);
            return;
        }
        if(nextProcess != null){
            nextProcess.handleValidator(annotation,value,targetMap);
        }
    }

    /**
     * 逻辑处理方法
     */
    abstract void dosomething(Annotation annotation, T value, Map<String, Object> targetMap);

    public abstract boolean isMatch(Annotation annotation);

    public BaseValidatorProcess getNextProcess() {
        return nextProcess;
    }

    public void setNextProcess(BaseValidatorProcess nextProcess) {
        this.nextProcess = nextProcess;
    }
}


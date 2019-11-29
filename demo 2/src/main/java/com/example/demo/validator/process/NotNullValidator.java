package com.example.demo.validator.process;

import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Map;

@Component
public class NotNullValidator extends BaseValidatorProcess<Object> {
    @Override
    void dosomething(Annotation annotation, Object value, Map<String, Object> targetMap) {

    }
}

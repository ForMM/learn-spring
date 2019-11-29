package com.example.demo.validator.process;

import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Map;

@Component
public class StringValidator extends BaseValidatorProcess<String> {

    @Override
    void dosomething(Annotation annotation, String value, Map<String, Object> targetMap) {

    }
}

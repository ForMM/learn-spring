package com.example.demo.validator.annotation;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface StringValidatorAnn {
    boolean notEmpty() default false;
    int maxLength() default 0;
}

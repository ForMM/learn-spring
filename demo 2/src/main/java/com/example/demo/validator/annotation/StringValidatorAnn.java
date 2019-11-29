package com.example.demo.validator.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface StringValidatorAnn {
    boolean notEmpty() default false;
    int minLength() default 0;
    int maxLength() default 0;
}

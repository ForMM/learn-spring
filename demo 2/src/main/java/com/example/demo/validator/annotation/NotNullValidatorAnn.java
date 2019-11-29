package com.example.demo.validator.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface NotNullValidatorAnn {
    boolean isNull() default true;
    String[] relateFiled() default {};
}

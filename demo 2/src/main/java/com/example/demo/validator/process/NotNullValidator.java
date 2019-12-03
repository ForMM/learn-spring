package com.example.demo.validator.process;

import com.example.demo.enums.ResultCode;
import com.example.demo.exception.ValidatorException;
import com.example.demo.validator.annotation.NotNullValidatorAnn;
import com.example.demo.validator.annotation.StringValidatorAnn;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Map;

@Component
public class NotNullValidator extends BaseValidatorProcess<Object> {
    @Override
    void dosomething(Annotation annotation, Object value, Map<String, Object> targetMap) {
        NotNullValidatorAnn validatorAnn = (NotNullValidatorAnn)annotation;
        boolean aNull = validatorAnn.isNull();

        if(aNull){

        }else{
            String[] relateFiled = validatorAnn.relateFiled();
            if (relateFiled!=null&relateFiled.length>0){
                for (String filed:relateFiled){

                }
            }

        }
    }

    @Override
    public boolean isMatch(Annotation annotation) {
        return annotation!=null&&annotation instanceof NotNullValidatorAnn;
    }
}

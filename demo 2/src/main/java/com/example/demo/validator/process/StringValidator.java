package com.example.demo.validator.process;

import com.example.demo.enums.ResultCode;
import com.example.demo.exception.ValidatorException;
import com.example.demo.validator.annotation.StringValidatorAnn;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Map;

@Component
public class StringValidator extends BaseValidatorProcess<String> {

    @Override
    void dosomething(Annotation annotation, String value, Map<String, Object> targetMap) {
        StringValidatorAnn validatorAnn = (StringValidatorAnn)annotation;
        boolean notEmpty = validatorAnn.notEmpty();
        int maxLength = validatorAnn.maxLength();

        if(notEmpty && StringUtils.isBlank(value)){
            throw new ValidatorException(ResultCode.PARAM_IS_BLANK);
        }

        if(StringUtils.isNotBlank(value)){
            if(maxLength>0 && value.length()>maxLength){
                throw new ValidatorException(ResultCode.PARAM_LENGTH_IS_OVER);
            }
        }

    }

    @Override
    public boolean isMatch(Annotation annotation) {
        return annotation!=null && annotation instanceof StringValidatorAnn;
    }
}

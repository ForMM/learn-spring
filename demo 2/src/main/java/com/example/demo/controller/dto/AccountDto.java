package com.example.demo.controller.dto;

import com.example.demo.validator.annotation.NotNullValidatorAnn;
import com.example.demo.validator.annotation.StringValidatorAnn;
import lombok.Data;

@Data
public class AccountDto {
    @StringValidatorAnn(notEmpty=true,maxLength = 10)
    private String name;
    @NotNullValidatorAnn(isNull = false,relateFiled = {"mobile"})
    private String email;
    private String mobile;
}

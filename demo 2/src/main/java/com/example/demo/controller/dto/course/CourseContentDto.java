package com.example.demo.controller.dto.course;

import com.example.demo.validator.annotation.NotNullValidatorAnn;
import com.example.demo.validator.annotation.StringValidatorAnn;
import lombok.Data;

import java.io.File;

@Data
public class CourseContentDto {
    private String sort;

    @StringValidatorAnn(notEmpty=true,maxLength = 40)
    private String title;

    @StringValidatorAnn(notEmpty=true,maxLength = 1000)
    private String content;
}

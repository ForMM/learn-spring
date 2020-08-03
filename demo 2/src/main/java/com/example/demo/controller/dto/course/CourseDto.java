package com.example.demo.controller.dto.course;

import com.example.demo.validator.annotation.NotNullValidatorAnn;
import com.example.demo.validator.annotation.StringValidatorAnn;
import lombok.Data;

import java.io.File;

@Data
public class CourseDto {
    @StringValidatorAnn(notEmpty=true,maxLength = 40)
    private String title;

    @StringValidatorAnn(notEmpty=true,maxLength = 100)
    private String subTitle;

    private String price;
    private String type;

    private File img;
}

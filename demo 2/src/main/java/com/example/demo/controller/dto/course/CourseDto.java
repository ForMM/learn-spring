package com.example.demo.controller.dto.course;

import com.example.demo.validator.annotation.StringValidatorAnn;
import lombok.Data;

@Data
public class CourseDto {
    @StringValidatorAnn(notEmpty=true,maxLength = 40)
    private String title;

    @StringValidatorAnn(notEmpty=true,maxLength = 100)
    private String subTitle;

    private Double price;

    @StringValidatorAnn(notEmpty=true,maxLength = 100)
    private String type;

    @StringValidatorAnn(notEmpty=true,maxLength = 100)
    private String img;
}

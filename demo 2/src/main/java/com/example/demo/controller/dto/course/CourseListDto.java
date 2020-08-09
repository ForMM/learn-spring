package com.example.demo.controller.dto.course;

import lombok.Data;

@Data
public class CourseListDto {

    private String type;

    private Integer page;

    private  Integer pageSize;
}

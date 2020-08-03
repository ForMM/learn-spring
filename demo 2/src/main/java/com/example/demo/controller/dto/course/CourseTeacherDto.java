package com.example.demo.controller.dto.course;

import lombok.Data;

import java.io.File;

@Data
public class CourseTeacherDto {
    private String name;

    private String profession;

    private String title;
    private String content;

    private File photo;
}

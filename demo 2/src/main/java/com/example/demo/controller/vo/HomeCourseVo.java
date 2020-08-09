package com.example.demo.controller.vo;

import lombok.Data;

import java.util.List;

@Data
public class HomeCourseVo {
    private List<CourseVo> coreCourses;
    private List<CourseVo> pblCourses;
    private List<CourseVo> excellentCourses;
}

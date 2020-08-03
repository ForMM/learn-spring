package com.example.demo.dao;

import com.example.demo.dao.entity.CourseTeacher;
import java.util.List;

public interface CourseTeacherMapper {
    int deleteByPrimaryKey(String id);

    int insert(CourseTeacher record);

    CourseTeacher selectByPrimaryKey(String id);

    List<CourseTeacher> selectAll();

    int updateByPrimaryKey(CourseTeacher record);
}
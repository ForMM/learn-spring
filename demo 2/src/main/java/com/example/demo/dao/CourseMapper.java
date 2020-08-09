package com.example.demo.dao;

import com.example.demo.dao.entity.Course;
import java.util.List;
import java.util.Map;

public interface CourseMapper {
    int deleteByPrimaryKey(String id);

    int insert(Course record);

    Course selectByPrimaryKey(String id);

    List<Course> selectAll();

    int updateByPrimaryKey(Course record);

    List<Course> findByParam(Map<String,Object> param);

    int countByParam(Map<String,Object> param);


}
package com.example.demo.dao;

import com.example.demo.dao.entity.CourseContent;
import java.util.List;

public interface CourseContentMapper {
    int deleteByPrimaryKey(String id);

    int insert(CourseContent record);

    CourseContent selectByPrimaryKey(String id);

    List<CourseContent> selectAll();

    int updateByPrimaryKey(CourseContent record);
}
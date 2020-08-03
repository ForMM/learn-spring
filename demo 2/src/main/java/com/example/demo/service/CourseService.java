package com.example.demo.service;

import com.example.demo.common.Result;
import com.example.demo.controller.dto.AccountDto;

public interface CourseService {
	public Result addCourse(AccountDto accountDto);
	public Result updateCourse(String account);
	public Result selectCourseById(String courseId);
	public Result selectCourseList();
}

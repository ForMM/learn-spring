package com.example.demo.service;

import com.example.demo.common.Result;
import com.example.demo.controller.dto.AccountDto;
import com.example.demo.controller.dto.course.CourseDto;
import com.example.demo.controller.dto.course.CourseListDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface CourseService {




	public Result uploadCourseImg(MultipartFile file);
	public byte[] loadCourseImg(String uuid);
	public Result addCourse(CourseDto courseDto);
	public Result updateCourse(String account);
	public Result selectCourseById(String courseId);
	public Result selectCourseList();

	public Result courseList(CourseListDto courseListDto);


}

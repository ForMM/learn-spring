package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.controller.dto.course.CourseDto;
import com.example.demo.controller.dto.course.CourseListDto;
import com.example.demo.log.annotation.LogAnnotation;
import com.example.demo.service.AccountService;
import com.example.demo.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
@RequestMapping("/course")
public class CourseController {
	
	private static Logger logger = LoggerFactory.getLogger(CourseController.class);

	@Autowired
	private CourseService courseService;



	@LogAnnotation
	@RequestMapping(value = "/addCoursePage1",method = RequestMethod.GET)
	public String addCoursePage1(String name) {
		logger.info("addCoursePage1");
		return "manage/add-course-1";
	}

	@LogAnnotation
	@RequestMapping(value = "/addCoursePage2",method = RequestMethod.GET)
	public String addCoursePage2(String name) {
		logger.info("addCoursePage2");
		return "manage/add-course-2";
	}

	@LogAnnotation
	@RequestMapping(value = "/addCoursePage3",method = RequestMethod.GET)
	public String addCoursePage3(String name) {
		logger.info("addCoursePage3");
		return "manage/add-course-3";
	}

	@LogAnnotation
	@RequestMapping(value = "/uploadCourseImg",method = RequestMethod.POST)
	@ResponseBody
	public Result uploadCourseImg(@RequestParam("file") MultipartFile file) {
		logger.info("filename:{},size:{}",file.getName(),file.getSize());
		return courseService.uploadCourseImg(file);
	}

	@LogAnnotation
	@RequestMapping(value = "/loadCourseImg",method = RequestMethod.GET)
	@ResponseBody
	public byte[] loadCourseImg(String uuid) {
		logger.info("uuid:{}",uuid);
		return courseService.loadCourseImg(uuid);
	}


	@LogAnnotation
	@RequestMapping(value = "/addCourse",method = RequestMethod.POST)
	@ResponseBody
	public Result addCourse(CourseDto courseDto) {
		logger.info("courseDto:{}",courseDto.toString());
		return courseService.addCourse(courseDto);
	}

	@LogAnnotation
	@RequestMapping(value = "/coursePage",method = RequestMethod.GET)
	public String coursePage(Model model) {
		logger.info("coursePage");

		CourseListDto courseListDto = new CourseListDto();
		courseListDto.setPage(0);
		courseListDto.setPageSize(10);
		Result result = courseService.courseList(courseListDto);
		model.addAttribute("result",result);

		return "manage/course";
	}

	@LogAnnotation
	@RequestMapping(value = "/courseList",method = RequestMethod.POST)
	@ResponseBody
	public Result courseList(CourseListDto courseListDto) {
		logger.info("courseList:{}",courseListDto.toString());
		return courseService.courseList(courseListDto);
	}

}

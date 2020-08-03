package com.example.demo.controller;

import com.example.demo.log.annotation.LogAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/course")
public class CourseController {
	
	private static Logger logger = LoggerFactory.getLogger(CourseController.class);


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



}

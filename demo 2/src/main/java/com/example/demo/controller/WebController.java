package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.log.annotation.LogAnnotation;
import com.example.demo.service.DocDealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class WebController {
	
	private static Logger logger = LoggerFactory.getLogger(WebController.class);

	@RequestMapping(value = "/home",method = RequestMethod.GET)
	public String home(){
		logger.info("enter home");
		return "home";
	}

	@RequestMapping(value = "/self-course",method = RequestMethod.GET)
	public String selfCourse(){
		logger.info("selfCourse");
		return "self-course";
	}

	@RequestMapping(value = "/free-course",method = RequestMethod.GET)
	public String freeCourse(){
		logger.info("freeCourse");
		return "free-course";
	}

	@RequestMapping(value = "/free-course-detail",method = RequestMethod.GET)
	public String freeCourseDetail(){
		logger.info("freeCourseDetail");
		return "free-course-detail";
	}

	@RequestMapping(value = "/self-course-detail",method = RequestMethod.GET)
	public String selfCourseDetail(){
		logger.info("selfCourseDetail");
		return "self-course-detail";
	}
}

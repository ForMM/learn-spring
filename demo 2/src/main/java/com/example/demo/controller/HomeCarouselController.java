package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.controller.dto.course.CourseDto;
import com.example.demo.controller.dto.course.CourseListDto;
import com.example.demo.log.annotation.LogAnnotation;
import com.example.demo.service.CourseService;
import com.example.demo.service.HomeCarouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/home")
public class HomeCarouselController {
	
	private static Logger logger = LoggerFactory.getLogger(HomeCarouselController.class);

	@Autowired
	private HomeCarouseService homeCarouseService;

	@LogAnnotation
	@RequestMapping(value = "/addHomeCarouseImgPage",method = RequestMethod.GET)
	public String addHomeCarouseImgPage(Model model) {
		logger.info("addHomeCarouseImgPage");
		return "manage/add-home-carousel";
	}

	@LogAnnotation
	@RequestMapping(value = "/uploadCarouseImg",method = RequestMethod.POST)
	@ResponseBody
	public Result uploadCourseImg(@RequestParam("file") MultipartFile file) {
		logger.info("filename:{},size:{},type:{}",file.getName(),file.getSize());
		return homeCarouseService.uploadCarouseImg(file);
	}

	@LogAnnotation
	@RequestMapping(value = "/loadCarouseImg",method = RequestMethod.GET)
	@ResponseBody
	public byte[] loadCourseImg(String uuid) {
		logger.info("uuid:{}",uuid);
		return homeCarouseService.loadCarouseImg(uuid);
	}

	@LogAnnotation
	@RequestMapping(value = "/carouseImgList",method = RequestMethod.POST)
	@ResponseBody
	public Result carouseImgList() {
		logger.info("carouseImgList");
		return homeCarouseService.homeCarouselImgList();
	}


}

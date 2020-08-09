package com.example.demo.service;

import com.example.demo.common.Result;
import com.example.demo.controller.dto.course.CourseDto;
import com.example.demo.controller.dto.course.CourseListDto;
import org.springframework.web.multipart.MultipartFile;

public interface HomeCarouseService {
	public Result uploadCarouseImg(MultipartFile file);
	public byte[] loadCarouseImg(String uuid);
	public Result homeCarouselImgList();
	public Result courseTypeImg(String type);
}

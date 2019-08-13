package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.common.Result;
import com.example.demo.controller.vo.EncryptVo;
import com.example.demo.log.annotation.LogAnnotation;
import com.example.demo.service.DigestService;

@Controller
@RequestMapping("/digest")
public class DigestController {
	private static Logger logger = LoggerFactory.getLogger(DigestController.class);
	
	@Autowired
	private DigestService digestService;
	
	@ResponseBody
	@LogAnnotation
	@RequestMapping(value = "/md5",method = RequestMethod.POST)
	public Result<EncryptVo> md5(String name) {
		return digestService.Md5Encypt(name);
	}
	
	@LogAnnotation
	@RequestMapping(value = "/sha256",method = RequestMethod.POST)
	public void sha256(String name,String idcard) {
		logger.info("sha256 hello world!");
	}
}


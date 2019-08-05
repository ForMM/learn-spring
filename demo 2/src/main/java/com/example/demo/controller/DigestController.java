package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.log.annotation.LogAnnotation;

@Controller
@RequestMapping("/digest")
public class DigestController {
	private static Logger logger = LoggerFactory.getLogger(DigestController.class);
	
	@RequestMapping(value = "/md5",method = RequestMethod.POST)
	public void md5(String name) {
		logger.info("hello world"+name);
	}
	
	@LogAnnotation
	@RequestMapping(value = "/sha256",method = RequestMethod.POST)
	public void sha256(String name,String idcard) {
		logger.info("sha256 hello world!");
	}
}


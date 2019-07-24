package com.example.demo.contoller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/digest")
public class DigestController {
	
	private final static Logger logger = LoggerFactory.getLogger(DigestController.class);

	
	@RequestMapping(value = "/md5",method = RequestMethod.POST)
	public void md5(String name) {
		System.out.println("hello world,"+name);
	};
	
	@RequestMapping(value = "/htmlToPdf",method = RequestMethod.POST)
	public void sha256(String name,String idcard) {
		System.out.println("hello world:"+name+",idcard:"+idcard);
		
		logger.info("hello world!");
	};
}


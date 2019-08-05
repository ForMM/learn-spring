package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.common.Result;
import com.example.demo.log.annotation.LogAnnotation;
import com.example.demo.service.DocDealService;

@Controller
@RequestMapping("/doc")
public class DocDealController {
	
	private static Logger logger = LoggerFactory.getLogger(DocDealController.class);

	@Autowired
	private DocDealService docDealService;
	
	@LogAnnotation
	@RequestMapping(value = "/generatePdf",method = RequestMethod.POST)
	public Result<Object> generatePdf(String name) {
		logger.info("generatePdf hello world{}"+name);
		Result<Object> generatePdf = docDealService.generatePdf();
		return generatePdf;
	}
	
	@RequestMapping(value = "/htmlToPdf",method = RequestMethod.POST)
	public void htmlToPdf(String name,String idcard) {
		System.out.println("hello world:"+name+",idcard:"+idcard);
		docDealService.htmlToPdf(name,idcard);
	}
}

package com.example.demo.controller;

import com.example.demo.util.CsvPojo.DataAndTypeCsv;
import com.example.demo.util.CsvUtil;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/doc")
public class DocDealController {
	
	private static Logger logger = LoggerFactory.getLogger(DocDealController.class);

	@Autowired
	private DocDealService docDealService;
	
	@LogAnnotation
	@RequestMapping(value = "/generatePdf",method = RequestMethod.POST)
	public void generatePdf(String name) {
		docDealService.generatePdf();
	}
	
	@LogAnnotation
	@ResponseBody
	@RequestMapping(value = "/htmlToPdf",method = RequestMethod.GET)
	public Result<Object> htmlToPdf(String name,String idcard) {
		Result<Object> htmlToPdf = docDealService.htmlToPdf(name,idcard);
		return htmlToPdf;
	}

	@LogAnnotation
	@RequestMapping(value = "/csvToTxt",method = RequestMethod.POST)
	public void csvToTxt(MultipartFile file) {


	}
}

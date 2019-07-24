package com.example.demo.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.service.DocDealService;

@Controller
@RequestMapping("/doc")
public class DocDealController {

	@Autowired
	private DocDealService docDealService;
	
	
	@RequestMapping(value = "/generatePdf",method = RequestMethod.POST)
	public void generatePdf(String name) {
		System.out.println("hello world,"+name);
		docDealService.generatePdf();
	};
	
	@RequestMapping(value = "/htmlToPdf",method = RequestMethod.POST)
	public void htmlToPdf(String name,String idcard) {
		System.out.println("hello world:"+name+",idcard:"+idcard);
		docDealService.htmlToPdf(name,idcard);
	};
}

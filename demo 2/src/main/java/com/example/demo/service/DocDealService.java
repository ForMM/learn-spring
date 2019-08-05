package com.example.demo.service;

import com.example.demo.common.Result;

public interface DocDealService {
	
	public Result<Object> generatePdf();
	
	public String htmlToPdf(String name,String idcard);
	
}

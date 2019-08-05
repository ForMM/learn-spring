package com.example.demo.service;

import com.example.demo.common.Result;

public interface DocDealService {
	
	public String generatePdf();
	
	public Result<Object> htmlToPdf(String name,String idcard);
	
}

package com.example.demo.controller;

import com.example.demo.controller.dto.AccountDto;
import com.example.demo.validator.annotation.ObjectValidatorAnn;
import com.example.demo.validator.annotation.StringValidatorAnn;
import com.example.demo.validator.annotation.ValidatorAnn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.common.Result;
import com.example.demo.log.annotation.LogAnnotation;
import com.example.demo.service.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	private static Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountService accountService;

	@LogAnnotation
	@ResponseBody
	@ValidatorAnn
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	public Result addAccount(@ObjectValidatorAnn AccountDto accountDto) {
		return accountService.addAccount(accountDto);
	}
	
//	@LogAnnotation
//	@ResponseBody
//	@ValidatorAnn
//	@RequestMapping(value = "/queryOne",method = RequestMethod.POST)
//	public Result queryOne(@StringValidatorAnn(notEmpty=true,maxLength = 10) String account) {
//		return accountService.queryOne(account);
//	}
//
//	@LogAnnotation
//	@ResponseBody
//	@RequestMapping(value = "/queryList",method = RequestMethod.POST)
//	public Result queryAccountList(String account) {
//		return accountService.queryOne(account);
//	}
	
}

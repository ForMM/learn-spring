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
import com.example.demo.service.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	private static Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountService accountService;
	
	@LogAnnotation
	@ResponseBody
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	public Result addAccount(String account,String password) {
		return accountService.addAccount(account, password);
	}
	
}

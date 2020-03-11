package com.example.demo.service;

import com.example.demo.common.Result;
import com.example.demo.controller.dto.AccountDto;

public interface AccountService {
	
	public Result addAccount(AccountDto accountDto);
	
	public Result queryOne(String account);
}

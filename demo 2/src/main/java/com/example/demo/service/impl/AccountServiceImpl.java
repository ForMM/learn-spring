package com.example.demo.service.impl;

import java.util.Date;
import java.util.List;

import com.example.demo.controller.dto.AccountDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.Result;
import com.example.demo.dao.AccountMapper;
import com.example.demo.dao.entity.Account;
import com.example.demo.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	private static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Autowired
	private AccountMapper accountMapper;
	
	@Override
	public Result addAccount(AccountDto accountDto) {
		Result result = new Result<>();
		result.setCode(1);
		result.setMsg("addAccount");
		
		Account adAccount = new Account();
		adAccount.setAccount(accountDto.getEmail());
		adAccount.setPassword(accountDto.getMobile());
		adAccount.setEmail(accountDto.getEmail());
		adAccount.setCreateTime(new Date());
		
		List<Account> selectAll = accountMapper.selectAll();
		result.setData(selectAll);
		return result;
	}

//	@Override
//	public Result queryOne(String account) {
//		Result result = new Result<>();
//		result.setCode(1);
//		result.setMsg("queryOne");
//		Account selectByParam = null;
//		try {
//			selectByParam = accountMapper.selectByParam(account);
//		}catch (Exception e) {
//			logger.error("",e);
//		}
//
//		result.setData(selectByParam);
//		return result;
//	}


}

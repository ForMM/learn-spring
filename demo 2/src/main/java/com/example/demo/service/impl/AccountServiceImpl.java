package com.example.demo.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.Result;
import com.example.demo.dao.AccountMapper;
import com.example.demo.dao.entity.Account;
import com.example.demo.service.AccountService;
import com.example.demo.util.encrpyt.MD5Util;

@Service
public class AccountServiceImpl implements AccountService {

	private static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Autowired
	private AccountMapper accountMapper;
	
	@Override
	public Result addAccount(String account, String password) {
		Result result = new Result<>();
		result.setStatus(1);
		result.setMsg("addAccount");
		
		Account adAccount = new Account();
		adAccount.setAccount(account);
		adAccount.setPassword(password);
		adAccount.setEmail(account);
		adAccount.setCreateTime(new Date());
		
		try {
			accountMapper.insert(adAccount);
		}catch (Exception e) {
			logger.error("addAccount error",e);
		}
		
		
		logger.info("addAccount success:account={}",account);
		return result;
	}

	@Override
	public Result queryOne(String account) {
		Result result = new Result<>();
		result.setStatus(1);
		result.setMsg("queryOne");
		Account selectByParam = null;
		try {
			selectByParam = accountMapper.selectByParam(account);
		}catch (Exception e) {
			logger.error("",e);
		}
		
		result.setData(selectByParam);
		return result;
	}


}

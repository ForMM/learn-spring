package com.example.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.example.demo.common.Result;
import com.example.demo.controller.vo.EncryptVo;
import com.example.demo.service.DigestService;
import com.example.demo.util.encrpyt.MD5Util;

@Service
public class DigestServiceImpl implements DigestService {
	private static Logger logger = LoggerFactory.getLogger(DigestServiceImpl.class);

	@Override
	public Result<EncryptVo> Md5Encypt(String param) {
		Result<EncryptVo> result = new Result<>();
		result.setStatus(1);
		result.setMsg("md5加密");
		
		EncryptVo vo = new EncryptVo();
		vo.setEncryptStr(MD5Util.getMd5Simple(param.getBytes()));
		
		
		
		result.setData(vo);
		return result;
	}

	

}

package com.example.demo.service;

import com.example.demo.common.Result;
import com.example.demo.controller.vo.EncryptVo;

public interface DigestService {
	
	public Result<EncryptVo> Md5Encypt(String param);
	
}

package com.example.demo;

import org.bouncycastle.util.encoders.Hex;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.util.encrpyt.sm.SM3Util;

@SpringBootTest
public class SM3test {
	
	private static Logger logger = LoggerFactory.getLogger(SM3test.class);

	@Test
	public void testSignAndVerify() {
		String aa = "hhhhhhhhh爱情";
		byte[] hash = SM3Util.hash(aa.getBytes());
		logger.info("sm3 hash:{}",Hex.toHexString(hash));
		
		boolean verify = SM3Util.verify(aa.getBytes(), hash);
		if(!verify) {
			Assert.fail("验签失败");
		}
		
		String aadd =null;
		aadd.toLowerCase();
		
	}

}

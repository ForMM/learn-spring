package com.example.demo;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.util.DStringUtils;

@SpringBootTest
public class DStringUtilTest {
	
	private static Logger logger = LoggerFactory.getLogger(DStringUtilTest.class);

	@Test
	public void testSignAndVerify() {
		boolean containsChinese = DStringUtils.isContainsChinese("hhhhhhhhh爱情");
		boolean chineseAll = DStringUtils.isChineseAll("爱情");
		
		boolean containsEnglish = DStringUtils.isContainsEnglish("爱情");
		boolean englishAll = DStringUtils.isEnglishAll("aa");
		
		logger.info("isContainsChinese:{}",containsChinese);
		logger.info("isChineseAll:{}",chineseAll);
		logger.info("containsEnglish:{}",containsEnglish);
		logger.info("englishAll:{}",englishAll);
	}

}

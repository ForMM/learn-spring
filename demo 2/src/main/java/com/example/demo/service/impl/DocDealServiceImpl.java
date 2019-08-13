package com.example.demo.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.example.demo.common.Result;
import com.example.demo.service.DocDealService;
import freemarker.template.Template;

@Service
public class DocDealServiceImpl implements DocDealService {

	private static Logger logger = LoggerFactory.getLogger(DocDealServiceImpl.class);
	
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	@Autowired
	private HttpServletResponse response;

	@Override
	public String generatePdf() {
		String fileRealName = "hello";

		InputStream inStream = null;
		OutputStream out = null;
		File outFile = null;
		try {
			Map<String, String> map = new HashMap<>();
			map.put("param1", "哈哈");
			map.put("param2", "解决");
			map.put("param3", "人生");
			map.put("param4", "人生");
			map.put("param5", "人生");
			map.put("param6", "人生");
			map.put("param7", "人生");
			map.put("param8", "人生");
			map.put("param9", "人生");
			
			
			Template tpl = this.freeMarkerConfigurer.getConfiguration().getTemplate("testdoc.ftl");
			String sysTemp = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator"); // 临时文件路径
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			String tempfileName = sysTemp + uuid + ".doc";
			
			
			outFile = new File(tempfileName);
			Writer w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));
			tpl.process(map, w);
			w.close();
			inStream = new FileInputStream(outFile);
			response.setHeader("Content-Disposition", "attachment;filename=" + fileRealName + ".doc");
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/msword");
			out = response.getOutputStream();
			byte[] buffer = new byte[1024]; // 缓冲区
			int bytesToRead = -1;
			// 通过循环将读入的Word文件的内容输出到浏览器中
			while ((bytesToRead = inStream.read(buffer)) != -1) {
				out.write(buffer, 0, bytesToRead);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (null != inStream) {
				try {
					inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != outFile) {
				outFile.delete();
			}
		}

		return null;
	}

	@Override
	public Result<Object> htmlToPdf(String name, String idcard) {
		Result<Object> result = new Result<Object>();
		
		result.setStatus(1);
		result.setMsg("aaaa");
		
		return result;
		
		
	}

}

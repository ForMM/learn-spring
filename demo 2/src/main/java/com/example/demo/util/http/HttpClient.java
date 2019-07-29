package com.example.demo.util.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.InflaterInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.controller.DigestController;

public class HttpClient {
	private final static Logger logger = LoggerFactory.getLogger(HttpClient.class);
	HttpURLConnection httpClient = null;
	InputStream inputStream = null;
	BufferedReader br =null;
	String result=null;
	
	
	public String doGet(String httpurl) {
		try {
			URL url = new URL(httpurl);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			//连接服务器超时时间
			connection.setConnectTimeout(15000);
			//读取远程地址数据返回时间
			connection.setReadTimeout(60000);
			connection.connect();
			
			if(connection.getResponseCode() == 200) {
				inputStream = connection.getInputStream();
				new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
			}
			
			
			
		} catch (MalformedURLException e) {
			logger.error("doGet have MalformedURLException problem!",e);
		} catch (IOException e) {
			logger.error("doGet have IOException problem!",e);
		}
		
		
		
		
		return "";
	}
	
	
}

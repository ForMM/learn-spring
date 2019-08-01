package com.example.demo.util.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtil {
	private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
	
	public static String doGet(String httpUrl) {
		String result="";
		RequestConfig requestConfig = RequestConfig.custom().
			    setConnectionRequestTimeout(1000).setConnectTimeout(1000).setSocketTimeout(1000).build();
		HttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
		HttpGet httpGet = new HttpGet(httpUrl); 
		try {
			HttpResponse httpResponse = client.execute(httpGet);
			result = EntityUtils.toString(httpResponse.getEntity());
		} catch (ClientProtocolException e) {
			logger.error("HttpClientUtil doGet exception",e);
		} catch (IOException e) {
			logger.error("HttpClientUtil doGet IOException",e);
		}
		return result;
	}
}

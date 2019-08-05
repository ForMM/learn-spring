package com.example.demo.util.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtil {
	private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

	public static String doGet(String httpUrl) {
		String result = "";
		HttpClient client = null;
		HttpResponse httpResponse = null;
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(1000).setConnectTimeout(1000)
				.setSocketTimeout(1000).build();
		client = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
		HttpGet httpGet = new HttpGet(httpUrl);
		try {
			httpResponse = client.execute(httpGet);
			result = EntityUtils.toString(httpResponse.getEntity());
		} catch (ClientProtocolException e) {
			logger.error("HttpClientUtil doGet exception", e);
		} catch (IOException e) {
			logger.error("HttpClientUtil doGet IOException", e);
		}finally {
			httpGet.releaseConnection();
		}
		return result;
	}
	
	public static String doPost(String httpUrl,Map<String,Object> param) {
		String result="";
		HttpClient client = null;
		HttpResponse httpResponse = null;
		RequestConfig requestConfig = getRequestConfig();
		client = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
		HttpPost httpPost = new HttpPost(httpUrl);
		try {
			httpResponse = client.execute(httpPost);
			result = EntityUtils.toString(httpResponse.getEntity());
		} catch (ClientProtocolException e) {
			logger.error("HttpClientUtil doGet exception", e);
		} catch (IOException e) {
			logger.error("HttpClientUtil doGet IOException", e);
		}finally {
			httpPost.releaseConnection();
		}
		
		
		
		return result;
	}
	
	private static RequestConfig getRequestConfig() {
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(1000).setConnectTimeout(1000)
				.setSocketTimeout(1000).build();
		return requestConfig;
	}
}

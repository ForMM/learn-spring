package com.example.demo.util.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
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

	private HttpClientUtil() {};
	
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
		httpPost.setHeader("Content-Type", "application/json;charset=utf8");
		try {
			httpResponse = client.execute(httpPost);
			logger.info(String.format("响应状态:%s",httpResponse));
			if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result = EntityUtils.toString(httpResponse.getEntity());
			}else {
				StringBuffer errorMsg = new StringBuffer();
				errorMsg.append("http status:");
				errorMsg.append(httpResponse.getStatusLine().getStatusCode());
				errorMsg.append(httpResponse.getStatusLine().getReasonPhrase());
				errorMsg.append(",header:");
				Header[] allHeaders = httpResponse.getAllHeaders();
				for(Header header:allHeaders) {
					errorMsg.append(header.getName()).append(":").append(header.getValue());
				}
				logger.info("httpResponse error:{}",errorMsg);
			}
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
		return RequestConfig.custom().setConnectionRequestTimeout(1000).setConnectTimeout(1000)
				.setSocketTimeout(1000).build();
	}
	
	
	public static void main(String[] args) {
		String doGet = HttpClientUtil.doGet("https://testapi.fadada.com:8443/api/syncPerson_auto.api");
		logger.info("doGet result:{}"+doGet);
		
		Map<String,Object> param = new HashMap<String, Object>();
		String doPost = HttpClientUtil.doPost("https://testapi.fadada.com:8443/api/syncPerson_auto.api",null);
		logger.info("doPost result:{}"+doPost);
	}
	
}

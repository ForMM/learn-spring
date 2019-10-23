package com.example.demo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.util.http.HttpClientUtil;
import sun.misc.BASE64Encoder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTengxun {

	private static Logger logger = LoggerFactory.getLogger(TestTengxun.class);
	// 云市场分配的密钥Id
	private static String secretId = "AKIDm6I1799TQKkzad53nBSP2v2r0aDxrXDqj5F";
	// 云市场分配的密钥Key
	private static String secretKey = "HhpJ8tP76khKMZOiGst55Xc0NSL1ME7B66Q6bA4F";
	private static String source = "market";

	public static String calcAuthorization(String source, String secretId, String secretKey, String datetime)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
		String signStr = "x-date: " + datetime + "\n" + "x-source: " + source;
		Mac mac = Mac.getInstance("HmacSHA1");
		Key sKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), mac.getAlgorithm());
		mac.init(sKey);
		byte[] hash = mac.doFinal(signStr.getBytes("UTF-8"));
		String sig = new BASE64Encoder().encode(hash);

		String auth = "hmac id=\"" + secretId + "\", algorithm=\"hmac-sha1\", headers=\"x-date x-source\", signature=\""
				+ sig + "\"";
		return auth;
	}

	public static String urlencode(Map<?, ?> map) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<?, ?> entry : map.entrySet()) {
			if (sb.length() > 0) {
				sb.append("&");
			}
			sb.append(String.format("%s=%s", URLEncoder.encode(entry.getKey().toString(), "UTF-8"),
					URLEncoder.encode(entry.getValue().toString(), "UTF-8")));
		}
		return sb.toString();
	}

	/**
	 * 注册账号
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	public static void testAccountRegister() throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {

		Calendar cd = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		String datetime = sdf.format(cd.getTime());
		// 签名
		String auth = calcAuthorization(source, secretId, secretKey, datetime);
		String httpUrl = "http://service-i7iwxtym-1255747603.gz.apigw.tencentcs.com/release/api2/account_register.api";

		// 请求头
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("X-Source", source);
		headers.put("X-Date", datetime);
		headers.put("Authorization", auth);

		
		// body参数
		Map<String, String> bodyParams = new HashMap<String, String>();
		bodyParams.put("account_type", "2");
		bodyParams.put("app_id", "000001");
		bodyParams.put("msg_digest", "MDJFRkI4RDRFQTlGRkNBRTJCRkNGMjRCN0NBOTVGOUFBNzM4NUMwQw==");
		bodyParams.put("open_id", "afdafafsa8888555555111");
		bodyParams.put("timestamp", "123123");
		bodyParams.put("v", "2.0");

		String sendHttpPost = HttpClientUtil.sendHttpPost(httpUrl, bodyParams, headers);
		logger.info("sendHttpPost result:{}", sendHttpPost);
	}

	
	/**
	 * 测试企业实名认证
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static void testCompanyVerify() throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {

		Calendar cd = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		String datetime = sdf.format(cd.getTime());
		// 签名
		String auth = calcAuthorization(source, secretId, secretKey, datetime);
		String httpUrl = "http://service-fq6jif1e-1255747603.gz.apigw.tencentcs.com/release/api2/get_company_verify_url.api";

		// 请求头
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("X-Source", source);
		headers.put("X-Date", datetime);
		headers.put("Authorization", auth);

		// body参数
        Map<String, String> bodyParams = new HashMap<String, String>();
        bodyParams.put("app_id", "000001");
        bodyParams.put("msg_digest", "RTAyNjQ2RUFBQzdEMTkyNzU3RDJERDRBNjQxQzFFMjgxNERCOTdDOQ==");
        bodyParams.put("v", "2.0");
        bodyParams.put("timestamp", "123123");
        
        
        JSONObject legalInfo = new JSONObject();
        legalInfo.put("legal_name", "测试majj");
        legalInfo.put("legal_id", "110101199003071778");
        legalInfo.put("legal_mobile", "18664556512");
        legalInfo.put("legal_id_front_pah", "https://gss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/b812c8fcc3cec3fd84e2d8d4df88d43f869427b6.jpg");
        bodyParams.put("agent_info", "");
        bodyParams.put("legal_info", JSON.toJSONString(legalInfo));
        
        JSONObject bankInfo = new JSONObject();
        bankInfo.put("bank_name", "招商银行");
        bankInfo.put("bank_id", "2312344545445");
        bankInfo.put("subbranch_name", "高新园");
        bodyParams.put("bank_info", "");
        
        //
        JSONObject companyInfo = new JSONObject();
        companyInfo.put("company_name", "测试腾讯云企业");
        companyInfo.put("credit_no", "2312344545445");
        companyInfo.put("credit_image_path", "https://gss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/b812c8fcc3cec3fd84e2d8d4df88d43f869427b6.jpg");
        bodyParams.put("company_info", JSON.toJSONString(companyInfo));
        
        bodyParams.put("company_principal_type", "");
        bodyParams.put("customer_id", "B9BE8C127CED774B756370D0AE9E3A9F");
       
        bodyParams.put("m_verified_way", "");
        bodyParams.put("notify_url", "www.baidu.com");
        bodyParams.put("option", "");
        bodyParams.put("page_modify", "1");
        bodyParams.put("result_type", "");
        bodyParams.put("return_url", "");
        bodyParams.put("verified_way", "");
        bodyParams.put("cert_flag", "1");

		String sendHttpPost = HttpClientUtil.sendHttpPost(httpUrl, bodyParams, headers);
		logger.info("sendHttpPost result:{}", sendHttpPost);
	}
	
	/**
	 * 测试个人认证
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static void testPersonVerify() throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {

		Calendar cd = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		String datetime = sdf.format(cd.getTime());
		// 签名
		String auth = calcAuthorization(source, secretId, secretKey, datetime);
		String httpUrl = "http://service-fq6jif1e-1255747603.gz.apigw.tencentcs.com/release/api2/get_company_verify_url.api";

		// 请求头
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("X-Source", source);
		headers.put("X-Date", datetime);
		headers.put("Authorization", auth);

		// body参数
        Map<String, String> bodyParams = new HashMap<String, String>();
        bodyParams.put("app_id", "000001");
        bodyParams.put("msg_digest", "RTAyNjQ2RUFBQzdEMTkyNzU3RDJERDRBNjQxQzFFMjgxNERCOTdDOQ==");
        bodyParams.put("v", "2.0");
        bodyParams.put("timestamp", "123123");
        
        
        JSONObject legalInfo = new JSONObject();
        legalInfo.put("legal_name", "测试majj");
        legalInfo.put("legal_id", "110101199003071778");
        legalInfo.put("legal_mobile", "18664556512");
        legalInfo.put("legal_id_front_pah", "https://gss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/b812c8fcc3cec3fd84e2d8d4df88d43f869427b6.jpg");
        bodyParams.put("agent_info", "");
        bodyParams.put("legal_info", JSON.toJSONString(legalInfo));
        
        JSONObject bankInfo = new JSONObject();
        bankInfo.put("bank_name", "招商银行");
        bankInfo.put("bank_id", "2312344545445");
        bankInfo.put("subbranch_name", "高新园");
        bodyParams.put("bank_info", "");
        
        //
        JSONObject companyInfo = new JSONObject();
        companyInfo.put("company_name", "测试腾讯云企业");
        companyInfo.put("credit_no", "2312344545445");
        companyInfo.put("credit_image_path", "https://gss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/b812c8fcc3cec3fd84e2d8d4df88d43f869427b6.jpg");
        bodyParams.put("company_info", JSON.toJSONString(companyInfo));
        
        bodyParams.put("company_principal_type", "");
        bodyParams.put("customer_id", "B9BE8C127CED774B756370D0AE9E3A9F");
       
        bodyParams.put("m_verified_way", "");
        bodyParams.put("notify_url", "www.baidu.com");
        bodyParams.put("option", "");
        bodyParams.put("page_modify", "1");
        bodyParams.put("result_type", "");
        bodyParams.put("return_url", "");
        bodyParams.put("verified_way", "");
        bodyParams.put("cert_flag", "1");

		String sendHttpPost = HttpClientUtil.sendHttpPost(httpUrl, bodyParams, headers);
		logger.info("sendHttpPost result:{}", sendHttpPost);
	}
	
	
	public static void testUploadDoc() throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {

		Calendar cd = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		String datetime = sdf.format(cd.getTime());
		// 签名
		String auth = calcAuthorization(source, secretId, secretKey, datetime);
		String httpUrl = "http://service-fq6jif1e-1255747603.gz.apigw.tencentcs.com/release/api2/get_company_verify_url.api";

		// 请求头
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("X-Source", source);
		headers.put("X-Date", datetime);
		headers.put("Authorization", auth);

		// body参数
        Map<String, String> bodyParams = new HashMap<String, String>();
        bodyParams.put("app_id", "000001");
        bodyParams.put("msg_digest", "RTAyNjQ2RUFBQzdEMTkyNzU3RDJERDRBNjQxQzFFMjgxNERCOTdDOQ==");
        bodyParams.put("v", "2.0");
        bodyParams.put("timestamp", "123123");
        
        
        JSONObject legalInfo = new JSONObject();
        legalInfo.put("legal_name", "测试majj");
        legalInfo.put("legal_id", "110101199003071778");
        legalInfo.put("legal_mobile", "18664556512");
        legalInfo.put("legal_id_front_pah", "https://gss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/b812c8fcc3cec3fd84e2d8d4df88d43f869427b6.jpg");
        bodyParams.put("agent_info", "");
        bodyParams.put("legal_info", JSON.toJSONString(legalInfo));
        
        JSONObject bankInfo = new JSONObject();
        bankInfo.put("bank_name", "招商银行");
        bankInfo.put("bank_id", "2312344545445");
        bankInfo.put("subbranch_name", "高新园");
        bodyParams.put("bank_info", "");
        
        //
        JSONObject companyInfo = new JSONObject();
        companyInfo.put("company_name", "测试腾讯云企业");
        companyInfo.put("credit_no", "2312344545445");
        companyInfo.put("credit_image_path", "https://gss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/b812c8fcc3cec3fd84e2d8d4df88d43f869427b6.jpg");
        bodyParams.put("company_info", JSON.toJSONString(companyInfo));
        
        bodyParams.put("company_principal_type", "");
        bodyParams.put("customer_id", "B9BE8C127CED774B756370D0AE9E3A9F");
       
        bodyParams.put("m_verified_way", "");
        bodyParams.put("notify_url", "www.baidu.com");
        bodyParams.put("option", "");
        bodyParams.put("page_modify", "1");
        bodyParams.put("result_type", "");
        bodyParams.put("return_url", "");
        bodyParams.put("verified_way", "");
        bodyParams.put("cert_flag", "1");

		String sendHttpPost = HttpClientUtil.sendHttpPost(httpUrl, bodyParams, headers);
		logger.info("sendHttpPost result:{}", sendHttpPost);
	}
	
	/**
	 * 测试手动签
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static void testExtsign() throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {

		Calendar cd = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		String datetime = sdf.format(cd.getTime());
		// 签名
		String auth = calcAuthorization(source, secretId, secretKey, datetime);
		String httpUrl = "http://service-fq6jif1e-1255747603.gz.apigw.tencentcs.com/release/api2/extsign.api";

		// 请求头
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("X-Source", source);
		headers.put("X-Date", datetime);
		headers.put("Authorization", auth);

		// body参数
        Map<String, String> bodyParams = new HashMap<String, String>();
        bodyParams.put("app_id", "000001");
        bodyParams.put("msg_digest", "RTAyNjQ2RUFBQzdEMTkyNzU3RDJERDRBNjQxQzFFMjgxNERCOTdDOQ==");
        bodyParams.put("v", "2.0");
        bodyParams.put("timestamp", "123123");
        
        
        JSONObject legalInfo = new JSONObject();
        legalInfo.put("legal_name", "测试majj");
        legalInfo.put("legal_id", "110101199003071778");
        legalInfo.put("legal_mobile", "18664556512");
        legalInfo.put("legal_id_front_pah", "https://gss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/b812c8fcc3cec3fd84e2d8d4df88d43f869427b6.jpg");
        bodyParams.put("agent_info", "");
        bodyParams.put("legal_info", JSON.toJSONString(legalInfo));
        
        JSONObject bankInfo = new JSONObject();
        bankInfo.put("bank_name", "招商银行");
        bankInfo.put("bank_id", "2312344545445");
        bankInfo.put("subbranch_name", "高新园");
        bodyParams.put("bank_info", "");
        
        //
        JSONObject companyInfo = new JSONObject();
        companyInfo.put("company_name", "测试腾讯云企业");
        companyInfo.put("credit_no", "2312344545445");
        companyInfo.put("credit_image_path", "https://gss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/b812c8fcc3cec3fd84e2d8d4df88d43f869427b6.jpg");
        bodyParams.put("company_info", JSON.toJSONString(companyInfo));
        
        bodyParams.put("company_principal_type", "");
        bodyParams.put("customer_id", "B9BE8C127CED774B756370D0AE9E3A9F");
       
        bodyParams.put("m_verified_way", "");
        bodyParams.put("notify_url", "www.baidu.com");
        bodyParams.put("option", "");
        bodyParams.put("page_modify", "1");
        bodyParams.put("result_type", "");
        bodyParams.put("return_url", "");
        bodyParams.put("verified_way", "");
        bodyParams.put("cert_flag", "1");

		String sendHttpPost = HttpClientUtil.sendHttpPost(httpUrl, bodyParams, headers);
		logger.info("sendHttpPost result:{}", sendHttpPost);
	}
	
	
	public static void main(String[] args)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
		//注册账号（企业）
		testAccountRegister(); //B2F76DF20FB8CDBB313272FD0768B93E
		//获取企业实名认证地址
		testCompanyVerify();
		
		
	}

}

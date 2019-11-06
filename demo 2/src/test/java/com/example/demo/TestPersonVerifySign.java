package com.example.demo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fadada.sdk.client.authForfadada.PersonVerifySign;
import com.fadada.sdk.client.request.PersonVerifySignReq;
import org.junit.Test;

public class TestPersonVerifySign {

	@Test
	public void TestPersonVerifySign() {
		PersonVerifySign personVerifySign = new PersonVerifySign("988888","","2.0","");
		PersonVerifySignReq req = new PersonVerifySignReq();
		req.setCustomer_id("8C8EB55A52191677B2B09C95310E8118");

		long l = System.currentTimeMillis();
		req.setTransaction_id(l+"");
		req.setContract_id("AutoCid_91024_110211_IYCFQYMIKX");
		req.setSign_keyword("委托方签字");
		req.setKeyword_strategy("1");

		JSONObject jo = new JSONObject();
		jo.put("pagenum",1);
		jo.put("x",100);
		jo.put("y",200);
		JSONObject jo1 = new JSONObject();
		jo1.put("pagenum",2);
		jo1.put("x",100);
		jo1.put("y",200);

		JSONArray ja = new JSONArray();
		ja.add(jo);
		ja.add(jo1);
//		req.setSignature_positions(ja.toJSONString());

		req.setNotify_url("");
		req.setReturn_url("");


		String page_modify = "1";
		String customer_name = "";
		String customer_ident_type = "0";
		String customer_ident_no = "430223199112101512";
		String  mobile = "";
		String  ident_front_path = "";
		String  verified_notify_url = "";
		req.setPage_modify(page_modify);
		req.setCustomer_name(customer_name);
		req.setCustomer_ident_type(customer_ident_type);
		req.setCustomer_ident_no(customer_ident_no);
		req.setMobile(mobile);
		req.setIdent_front_path(ident_front_path);
		req.setVerified_notify_url(verified_notify_url);

		String ss = personVerifySign.invoke(req);
		System.out.println(ss);



	}

}

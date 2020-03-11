package com.example.demo.config;

import com.fadada.utils.storage.FOSClient;
import com.fadada.utils.storage.meituan.StorageService;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

//@WebServlet(urlPatterns = "/get_contract_image", asyncSupported = true)
@Component
public class GetObjectServlet extends HttpServlet {
	private static final long serialVersionUID = 4319317957876874233L;
	private static Logger logger = Logger.getLogger(GetObjectServlet.class);

	@PostConstruct
	public void init(){
		System.out.println("asdfasdfasdfasdfasf");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String uuid = req.getParameter("uuid");
		logger.info("uuid=" + uuid);
		if (StringUtils.isBlank(uuid)) {
			return;
		}
		HttpClient client = null;
		OutputStream os = null;
		try {
			os = resp.getOutputStream();
			byte[] data = FOSClient.getObjectAsByte(FOSClient.BUCKET_PDF, uuid);
            logger.info("=====图片字节大小为：" + data.length);
			os.write(data);
			os.flush();
		} catch (Exception e) {
			logger.error("程序异常", e);
		} finally {
			if (client != null) {
				client.getConnectionManager().shutdown();
			}
			if (os != null) {
				os.close();
			}
		}
	}
}

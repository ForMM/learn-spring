package com.example.demo.config;

import com.fadada.servlet.*;
import com.fadada.servlet.GetObjectServlet;
import com.fadada.utils.storage.FOSClient;
import com.fadada.utils.storage.meituan.StorageService;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class ServletConfiguration{

//	@PostConstruct
//	public void init(){
//		System.out.println("FOSClient init");
//		StorageService service=new StorageServiceImpl();
//		FOSClient.setFosService(service);
//	}

	@Bean
	public GetObjectServlet contractImgServlet() {
		return new GetObjectServlet();
	}

	@Bean
	public ServletRegistrationBean contractImgServletRegistrationBean(GetObjectServlet getObjectServlet) {
		ServletRegistrationBean registration = new ServletRegistrationBean(getObjectServlet);
		registration.setEnabled(true);
		registration.addUrlMappings("/get_contract_image");
		return registration;
	}
	
	@Bean
	public ExtSignLocationServlet extSignLocationServlet() {
		return new ExtSignLocationServlet();
	}

	@Bean
	public ServletRegistrationBean extSignLocationServletRegistrationBean(ExtSignLocationServlet extSignLocationServlet) {
		ServletRegistrationBean registration = new ServletRegistrationBean(extSignLocationServlet);
		registration.setEnabled(true);
		registration.addUrlMappings("/sign_keyword_locations");
		return registration;
	}
	
	@Bean
	public ExtSignServlet extSignServlet() {
		return new ExtSignServlet();
	}

	@Bean
	public ServletRegistrationBean extSignServletRegistrationBean(ExtSignServlet extSignServlet) {
		ServletRegistrationBean registration = new ServletRegistrationBean(extSignServlet);
		registration.setEnabled(true);
		registration.addUrlMappings("/post_sign");
		return registration;
	}

	@Bean
	public PostSignServlet postSignServlet() {
		return new PostSignServlet();
	}

	@Bean
	public ServletRegistrationBean postSignServletRegistrationBean(PostSignServlet postSignServlet) {
		ServletRegistrationBean registration = new ServletRegistrationBean(postSignServlet);
		registration.setEnabled(true);
		registration.addUrlMappings("/post_sign_ukey");
		return registration;
	}
	
	@Bean
	public PreSignServlet preSignServlet() {
		return new PreSignServlet();
	}

	@Bean
	public ServletRegistrationBean PreSignServletRegistrationBean(PreSignServlet preSignServlet) {
		ServletRegistrationBean registration = new ServletRegistrationBean(preSignServlet);
		registration.setEnabled(true);
		registration.addUrlMappings("/pre_sign");
		return registration;
	}

	@Bean
	public PdfToImgServlet pdfToImgServlet() {
		return new PdfToImgServlet();
	}

	@Bean
	public ServletRegistrationBean pdfToImgServletRegistrationBean(PdfToImgServlet pdfToImgServlet) {
		ServletRegistrationBean registration = new ServletRegistrationBean(pdfToImgServlet);
		registration.setEnabled(true);
		registration.addUrlMappings("/pdf_to_img");
		return registration;
	}

}

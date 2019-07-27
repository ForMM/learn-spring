package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.log.SyslogInterceptor;

@Configuration
public class SyslogConfiguration implements WebMvcConfigurer{

	@Autowired
	private SyslogInterceptor logIntegerceptor;
	
	/**
	 * 实例化webMvcConfigurer接口
	 * @return
	 */
//	@Bean
//    public WebMvcConfigurer webMvcConfigurer() {
//        return new WebMvcConfigurer() {
//            /**
//             * 添加拦截器
//             * @param registry
//             */
//            @Override
//            public void addInterceptors(InterceptorRegistry registry) {
//                registry.addInterceptor(logIntegerceptor).addPathPatterns("/**");
//            }
//        };
//    }
	
	/**
     * 重写添加拦截器方法并添加配置拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
         registry.addInterceptor(logIntegerceptor).addPathPatterns("/**");
    }
	
	
}

package com.example.demo.log.interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class SyslogInterceptor extends HandlerInterceptorAdapter{
	   private static String mdcKeyProName = "userName";
	   private static String mdcKeyReqId = "reqId";
	   
	   @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	        MDC.put(mdcKeyProName,"demo");
	        MDC.put(mdcKeyReqId, UUID.randomUUID().toString().replace("-", ""));
	        return super.preHandle(request, response, handler);
	    }

	    @Override
	    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

	        super.postHandle(request, response, handler, modelAndView);
	    }

	    @Override
	    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
	        MDC.remove(mdcKeyProName);
	        MDC.remove(mdcKeyReqId);
	        super.afterCompletion(request, response, handler, ex);
	    }

	    @Override
	    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	        super.afterConcurrentHandlingStarted(request, response, handler);
	    }
	   
	   
}

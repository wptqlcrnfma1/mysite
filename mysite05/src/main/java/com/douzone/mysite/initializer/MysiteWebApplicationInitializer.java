package com.douzone.mysite.initializer;

import javax.servlet.Filter;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.douzone.mysite.config.AppConfig;
import com.douzone.mysite.config.WebConfig;

public class MysiteWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {AppConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		
		return new Class<?>[] {WebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		//Servlet Mapping
		return new String[] {"/"};
	}

	@Override
	protected Filter[] getServletFilters() {
		//filters
		return new Filter[] {new CharacterEncodingFilter("UTF-8", true)};
	}

	@Override
	protected FrameworkServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
		// 내가 직접 만들어서 DispatcherServlet을 던지는 매소드
		// 톰캣이 만들어 주므로 안써도 상관없지만 한번 만들어보자
		
		DispatcherServlet dispatcherServlet = (DispatcherServlet)super.createDispatcherServlet(servletAppContext);
		//그냥 부모꺼를 쓰면 되지만 globalexceptionHandler가 없으면 에러를 던지는 의미인 47줄을 추가하기위해 dispatcherServlet을 생성
		
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true); // Exception Handler가 없으면 exception 던진다.
		
		return dispatcherServlet;
	}
	
	

}

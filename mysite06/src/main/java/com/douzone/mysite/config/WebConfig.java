package com.douzone.mysite.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.douzone.security.AuthInterceptor;
import com.douzone.security.AuthUserHandlerMethodArgumentResolver;
import com.douzone.security.LoginInterceptor;
import com.douzone.security.LogoutInterceptor;


//properties들을 기존(mysite05)에 있던 기존 com 패키지들 깔끔하게 수정됨


@Configuration
@PropertySource("classpath:com/douzone/mysite/config/config.properties")// 패키지는 '.' path는 '/' env 사용하기 위해
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private Environment env; //controller에서도 주입 후 사용가능 (전역/싱글톤)

	
	// Argument Resolver
	@Bean
	public HandlerMethodArgumentResolver authUserHandlerMethodArgumentResolver() {
		return new AuthUserHandlerMethodArgumentResolver();
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(authUserHandlerMethodArgumentResolver()); // 메소드 사용 주입
		WebMvcConfigurer.super.addArgumentResolvers(resolvers);
	}

	// interceptors
	@Bean
	public HandlerInterceptor loginInterceptor() {
		return new LoginInterceptor();
	}

	@Bean
	public HandlerInterceptor logoutInterceptor() {
		return new LogoutInterceptor();
	}

	@Bean
	public HandlerInterceptor authInterceptor() {
		return new AuthInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
		.addInterceptor(loginInterceptor())
		.addPathPatterns(env.getProperty("security.auth-url"));
	
	registry
		.addInterceptor(logoutInterceptor())
		.addPathPatterns(env.getProperty("security.logout-url"));
	
	registry
		.addInterceptor(authInterceptor())
		.addPathPatterns("/**")
		.excludePathPatterns(env.getProperty("security.auth-url"))
		.excludePathPatterns(env.getProperty("security.logout-url"))
		.excludePathPatterns("/assets/**");
	}
	
	//fileuplaodConfig
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(env.getProperty("fileupload.resourceMapping")).addResourceLocations("file:" + env.getProperty("fileupload.uploadLocation"));
	}
}

package com.douzone.mysite.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.douzone.mysite.config.app.DBConfig;
import com.douzone.mysite.config.app.MyBatisConfig;

@Configuration
@EnableAspectJAutoProxy //@Aspect 갖고 있는거 처리
@ComponentScan({"com.douzone.mysite.service","com.douzone.mysite.repository", "com.douzone.mysite.aspect"})
@Import({DBConfig.class,MyBatisConfig.class})
public class AppConfig {
	//쭉 적으면 되는데 클래스를 모아서 던진다
	
}

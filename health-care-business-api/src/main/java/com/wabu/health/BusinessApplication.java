package com.wabu.health;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * api 启动类（注意：只能放在com.wabu.loan，否则spring boot无法扫描到service,model）
 * @author Administrator
 *
 */
@SpringBootApplication
public class BusinessApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BusinessApplication.class);
	}

	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
		factory.setPort(8080);
		return factory;
	}
	

	@Bean
	public DispatcherServlet dispatcherServlet() {
		return new DispatcherServlet();
	}
	/*
	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		return new ServletRegistrationBean(dispatcherServlet(), "/api/v1/*");
	}
	
	
	@Bean
	public MultipartResolver multipartResolver() {
		return new CommonsMultipartResolver();
	}
*/
	public static void main(String[] args) {
		SpringApplication.run(BusinessApplication.class, args);

	}

}

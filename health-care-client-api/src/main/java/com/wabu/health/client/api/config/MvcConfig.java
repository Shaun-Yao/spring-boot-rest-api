package com.wabu.health.client.api.config;

import java.util.List;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.wabu.health.client.api.interceptor.AuthorizationInterceptor;
import com.wabu.health.client.api.resolver.CurrentUserMethodArgumentResolver;

/**
 * 配置类，增加自定义拦截器和解析器
 * @author yao
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    @Autowired
    private CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(authorizationInterceptor);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserMethodArgumentResolver);
    }
    
    
    @Bean
	public Validator validator() {
		return new LocalValidatorFactoryBean();
	}
	
}

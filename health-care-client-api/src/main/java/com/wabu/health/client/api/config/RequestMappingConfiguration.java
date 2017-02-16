package com.wabu.health.client.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wabu.health.client.api.mapping.ApiVersionRequestMappingHandlerMapping;

/**
 * 初始化ApiVersionRequestMappingHandlerMapping
 * 原本可以使用MvcConfig继承WebMvcConfigurationSupport 
 * 重写 requestMappingHandlerMapping方法来实现，
 * 但是继承WebMvcConfigurationSupport导致swagger文档无法访问
 * 所以单独配置RequestMappingConfiguration
 * @author yao
 *
 */
@Configuration
public class RequestMappingConfiguration {
    
    @Bean
    public ApiVersionRequestMappingHandlerMapping customMappingHandlerMapping() {
        ApiVersionRequestMappingHandlerMapping handlerMapping = new ApiVersionRequestMappingHandlerMapping("v");
        handlerMapping.setOrder(-1);
        return handlerMapping;
    }
}

package com.wabu.health.business.api.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import com.wabu.health.business.api.annotation.CurrentUser;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public static final String SWAGGER_SCAN_BASE_PACKAGE = "com.wabu.health.business.api.controller";
    public static final String VERSION = "v1";

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("医护无忧")
                .description("医护无忧商家端API说明文档")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .termsOfServiceUrl("")
                .version(VERSION)
                .contact(new Contact("yao", "", "349638972@qq.com"))
                .build();
    }

    @Bean
    public Docket customImplementation() {
    	List<ResponseMessage> error = new ArrayList<>();
    	error.add(new ResponseMessageBuilder().code(500).message("服务器内部错误")
                .responseModel(new ModelRef("Error")).build());
    	Set<String> produces = new HashSet<>();
    	produces.add(MediaType.APPLICATION_JSON_VALUE);
    	produces.add(MediaType.TEXT_HTML_VALUE);
    	
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
                .build()
                //.directModelSubstitute(LocalDate.class, String.class)
                //.directModelSubstitute(Date.class, String.class)
                //.globalResponseMessage(RequestMethod.GET, error)
                //.globalResponseMessage(RequestMethod.POST, error)
                .ignoredParameterTypes(CurrentUser.class)// 忽略有CurrentUser注解的参数，当前用户是自动注入的
                //.produces(produces)
                .apiInfo(apiInfo());
		        // 如果需要鉴权，请放开以下注释行->
		        // .securitySchemes(newArrayList(apiKey()))
		        // .securityContexts(newArrayList(securityContext()))
		        // <-如果需要鉴权，请放开以上注释行
		        // 如果需要全局性参数，请放开以下注释行->
		        // .globalOperationParameters(
		        // newArrayList(new ParameterBuilder()
		        // .name("someGlobalParameter")
		        // .description(
		        // "Description of someGlobalParameter")
		        // .modelRef(new ModelRef("string"))
		        // .parameterType("query").required(true).build()));
		        // <-如果需要全局性参数，请放开以上注释行
		        // 如果需要UrlTemplating，请放开以下注释行->
		        // .enableUrlTemplating(true);
		        // <-如果需要UrlTemplating，请放开以上注释行
    }
}

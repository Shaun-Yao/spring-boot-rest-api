package com.wabu.health.business.api.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在Controller的方法上使用此注解，该方法在映射时会检查用户是否登录，未登录返回401错误
 * @see com.wabu.loan.api.business.interceptor.AuthorizationInterceptor
 * @author yao
 */
@Retention(RetentionPolicy.RUNTIME)  
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface TokenRequired {
}

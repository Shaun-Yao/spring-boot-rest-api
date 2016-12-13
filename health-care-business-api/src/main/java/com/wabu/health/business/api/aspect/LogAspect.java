package com.wabu.health.business.api.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LogAspect {


    @Pointcut("execution(* com.wabu.loan.api.business.controller..*Controller.*(..))")
    public void log(){}

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        log.info("URL: {} {}", request.getMethod(), request.getRequestURL().toString());
        //log.info("IP : " + request.getRemoteAddr());
        //log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        
        log.info("ARGS: {}", joinPoint.getArgs());

    }

    @AfterReturning(returning = "ret", pointcut = "log()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容(发生异常则不会打印返回内容，由捕捉异常代码打印日志)
    	//ObjectMapper mapper = new ObjectMapper();
        //String retJson = mapper.writeValueAsString(ret);
        log.info("RESPONSE: {}", ret);
    }
}

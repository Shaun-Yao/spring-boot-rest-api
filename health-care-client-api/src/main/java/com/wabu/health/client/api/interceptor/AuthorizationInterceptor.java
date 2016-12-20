package com.wabu.health.client.api.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wabu.health.client.api.annotation.TokenRequired;
import com.wabu.health.client.api.authorization.Token;
import com.wabu.health.client.api.authorization.TokenManager;
import com.wabu.health.client.api.config.Constants;

/**
 * 自定义拦截器，判断此次请求是否有权限
 * @author yao
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenManager manager;

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        
        HandlerMethod handlerMethod = (HandlerMethod) handler;  
        Method method = handlerMethod.getMethod();
        boolean hasClassAnnotation = method.getDeclaringClass().isAnnotationPresent(TokenRequired.class);//类上有@Authorization
        boolean hasMethodAnnotation = method.isAnnotationPresent(TokenRequired.class);//方法上有@Authorization
     
        if (hasClassAnnotation || hasMethodAnnotation) {  
        	//从header中得到token
            String authorization = request.getHeader(Constants.AUTHORIZATION);
            //验证token
            Token model = manager.get(authorization);
            if (manager.check(model)) {
                //如果token验证成功，将token对应的用户id存在request中，便于之后注入
                request.setAttribute(Constants.CURRENT_USER_ID, model.getUserId());
                return true;
            }
            //如果验证token失败，并且方法注明了Authorization，返回401错误
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        
        return true;
    }
}

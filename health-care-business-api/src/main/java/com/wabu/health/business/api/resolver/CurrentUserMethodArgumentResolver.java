package com.wabu.health.business.api.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import com.wabu.health.business.api.config.Constants;

/**
 * 增加方法注入，将含有CurrentUser注解的方法参数注入当前登录用户
 * 
 * @author yao
 */
@Component
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

//	@Autowired
//	private BusinessRepository businessRepository;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// 如果参数类型是User并且有CurrentUser注解则支持
//		if (parameter.getParameterType().isAssignableFrom(Business.class)
//				&& parameter.hasParameterAnnotation(CurrentUser.class)) {
//			return true;
//		}
		return false;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		// 取出鉴权时存入的登录用户Id
		String currentUserId = (String) webRequest.getAttribute(Constants.CURRENT_USER_ID, RequestAttributes.SCOPE_REQUEST);
		if (currentUserId != null) {
			// 从数据库中查询并返回
			//return businessRepository.findOne(currentUserId);
		}
		throw new MissingServletRequestPartException(Constants.CURRENT_USER_ID);
	}
}

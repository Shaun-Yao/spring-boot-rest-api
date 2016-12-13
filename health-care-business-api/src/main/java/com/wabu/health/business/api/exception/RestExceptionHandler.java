package com.wabu.health.business.api.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wabu.health.business.api.config.ResultModel;
import com.wabu.health.business.api.config.ResultStatus;

import lombok.extern.slf4j.Slf4j;

/**
 * 自定义ExceptionHandler，专门处理Restful异常.
 * 
 * @author yao
 */
// 会被Spring-MVC自动扫描，但又不属于Controller的annotation。
@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * 处理RestException.
	 */
	@ExceptionHandler(value = { RestException.class })
	public final ResponseEntity<?> handleException(RestException ex, WebRequest request) {
		//System.out.println("===handleRestException===");
		HttpHeaders headers = new HttpHeaders();
		
		//headers.setContentType(MediaType.parseMediaType(MediaTypes.TEXT_PLAIN_UTF_8));
		return handleExceptionInternal(ex, ex.getMessage(), headers, ex.status, request);
	}

	/**
	 * 处理JSR311 Validation异常.
	 * @throws JsonProcessingException
	 */
	@ExceptionHandler(value = { ConstraintViolationException.class })
	public final ResponseEntity<?> handleException(ConstraintViolationException ex, WebRequest request) {
		//System.out.println("===handleConstraintViolationException===");
//		Map<String, String> errors = BeanValidators.extractPropertyAndMessage(ex.getConstraintViolations());
//		ObjectMapper mapper = new ObjectMapper();
//		String body = mapper.writeValueAsString(errors);
//		HttpHeaders headers = new HttpHeaders();
		//headers.setContentType(MediaType.parseMediaType(MediaTypes.TEXT_PLAIN_UTF_8));
		//return handleExceptionInternal(ex, body, headers, HttpStatus.BAD_REQUEST, request);
		return new ResponseEntity<>(ResultModel.error(ResultStatus.PARAM_ERROR), HttpStatus.BAD_REQUEST);
	}

	/**
	 * 处理Exception.
	 * 本应该try catch 到异常然后 throw new RestException
	 * 但是有些被忽略的异常先打印到日志
	 * @return 
	 */
	@ExceptionHandler(value = { Exception.class })
	public final ResponseEntity<?> handleException(RuntimeException ex, WebRequest request) {
		log.error("捕获到异常", ex);
		//HttpHeaders headers = new HttpHeaders();
		//headers.setContentType(MediaType.parseMediaType(MediaTypes.TEXT_PLAIN_UTF_8));
		return new ResponseEntity<>(ResultModel.error(ResultStatus.SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
		//return handleExceptionInternal(ex, "服务器内部错误！", headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}


}

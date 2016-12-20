package com.wabu.health.client.api.controller;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wabu.health.model.Client;
import com.wabu.health.service.ClientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"Client related"})
@RestController
@RequestMapping(value = "/client")
public class ClientController {

	@Autowired
	private ClientService clientService;

	@Autowired
	private Validator validator;
	
	/**
	 * 用户注册
	 * 
	 * @param business
	 */
	@ApiOperation(value = "用户注册", notes = "用户注册")
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void add(@ModelAttribute Client client) {
		
		// 调用JSR303 Bean Validator进行校验, 异常将由RestExceptionHandler统一处理.
		//BeanValidators.validateWithException(validator, business);
		
		
		clientService.add(client);

	}

}

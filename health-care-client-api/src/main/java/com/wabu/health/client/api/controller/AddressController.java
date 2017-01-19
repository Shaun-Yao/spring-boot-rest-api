package com.wabu.health.client.api.controller;

import java.util.List;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wabu.health.client.api.annotation.CurrentUser;
import com.wabu.health.model.Address;
import com.wabu.health.model.Client;
import com.wabu.health.service.AddressService;
import com.wabu.health.service.ClientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"Address related"})
@RestController
@RequestMapping(value = "/addresses")
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@Autowired
	private ClientService clientService;

	@Autowired
	private Validator validator;
	
	
	
	@ApiOperation(value = "查询用户关联所有地址", notes = "查询用户关联所有地址")
    //@ApiResponses(value = {@ApiResponse(code = 404, message = "商家不存在") })
	//@ApiImplicitParam(name = "authorization", value = "授权参数", required = true, dataType = "string", paramType = "header")
    @RequestMapping(method = RequestMethod.GET)
	//@TokenRequired
	public ResponseEntity<List<Address>> list() {
		
		List<Address> addresss = addressService.findAll();
		return new ResponseEntity<List<Address>>(addresss, HttpStatus.OK);
		
	}
	
	/**
	 * 添加患者
	 * @param address
	 */
	@ApiOperation(value = "添加服务地址", notes = "添加服务地址")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void add(@RequestBody Address address, @CurrentUser Client client) {
		
		// 调用JSR303 Bean Validator进行校验, 异常将由RestExceptionHandler统一处理.
		//BeanValidators.validateWithException(validator, serviceItems);
		Client currentUser = clientService.findOne("1");
		address.setClient(currentUser);
		addressService.add(address);

	}

}

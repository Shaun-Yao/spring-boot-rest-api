package com.wabu.health.business.api.controller;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wabu.health.model.Order;
import com.wabu.health.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"Order related"})//2.6.0起中文路径无法展开api
@RestController
@RequestMapping(value = "/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private Validator validator;
	
//	@Autowired
//	private FileUtil fileUtil;
	
	/**
	 * 根据id查找商家
	 * @param id 商家id
	 * @return
	 */
	/*@ApiOperation(value = "根据id查找商家", notes = "根据id查找商家", response = Business.class)
//    @ApiResponses(value = {@ApiResponse(code = 404, message = "商家不存在") })
	@ApiImplicitParam(name = "authorization", value = "授权参数", required = true, dataType = "string", paramType = "header")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@TokenRequired
	public ResponseEntity<?> get(
			@ApiParam(value = "商家id" ,required = true ) @PathVariable("id") String id) {
		Business business = businessService.findOne(id);
		if (business == null) {
			return new ResponseEntity<>(ResultModel.error(ResultStatus.USER_NOT_FOUND), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Business>(business, HttpStatus.OK);
	}*/
	
	/**
	 * 商家注册
	 * @param serviceItems
	 */
	@ApiOperation(value = "提交订单", notes = "提交订单")
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void add(@RequestBody Order order) {
		
		// 调用JSR303 Bean Validator进行校验, 异常将由RestExceptionHandler统一处理.
		//BeanValidators.validateWithException(validator, serviceItems);
		//order.setServiceItems(new InjectionInfusion());
		orderService.add(order);

	}

}

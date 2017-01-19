package com.wabu.health.client.api.controller;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wabu.health.client.api.resource.OrderResource;
import com.wabu.health.client.api.resource.OrderResourceAssembler;
import com.wabu.health.enums.OrderStatus;
import com.wabu.health.model.Client;
import com.wabu.health.model.Order;
import com.wabu.health.service.ClientService;
import com.wabu.health.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = { "Order related" }) // 2.6.0起中文路径无法展开api
@RestController
@RequestMapping(value = "/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ClientService clientService;

	@Autowired
	private Validator validator;
	
	@Autowired
	private PagedResourcesAssembler<Order> parAssembler;
	
	
	/**
	 * 根据订单状态查找订单
	 * @return
	 */ 
	@ApiOperation(value = "根据订单状态查找订单", notes = "参数status值为空，或者不传则表示查询全部订单")
	@GetMapping
	public ResponseEntity<PagedResources<OrderResource>> list(@RequestParam(required = false) OrderStatus status,
			@ApiParam(value = "页码, 从1开始", required = true, defaultValue = "1") @RequestParam int page, 
			@ApiParam(value = "每页记录条数", required = true, defaultValue = "10")@RequestParam int size,
			@ApiParam(value = "排序字段,多字段以\",\"号隔开，\"-\"号表示降序，默认为升序", 
			required = true, defaultValue = "serviceTime,-servicePackage") @RequestParam String sort) {
		Page<Order> orders = orderService.findAll(status, page, size, sort);
		OrderResourceAssembler orderResourceAssembler = new OrderResourceAssembler(this.getClass(), OrderResource.class);
		return new ResponseEntity<>(parAssembler.toResource(orders, orderResourceAssembler), HttpStatus.OK);
	}

	/**
	 * 保存订单
	 * 
	 * @param serviceItems
	 */
	@ApiOperation(value = "提交订单", notes = "提交订单")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void add(@RequestBody Order order) {

		// 调用JSR303 Bean Validator进行校验, 异常将由RestExceptionHandler统一处理.
		// BeanValidators.validateWithException(validator, serviceItems);
		// order.setServiceItems(new InjectionInfusion());
		Client currentUser = clientService.findOne("1");
		order.setClient(currentUser);
		orderService.add(order);

	}

}

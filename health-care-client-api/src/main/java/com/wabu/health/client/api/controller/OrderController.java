package com.wabu.health.client.api.controller;

import java.util.List;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
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

	// @Autowired
	// private FileUtil fileUtil;

	/**
	 * 查找全部订单
	 * @return
	 */
	/*@ApiOperation(value = "查找全部订单单", notes = "查找全部订单", response = OrderResource.class)
	@GetMapping
	public ResponseEntity<List<OrderResource>> list() {
		List<Order> orders = orderService.findAll();
		List<OrderResource> orderResources = new OrderResourceAssembler(this.getClass(),
				OrderResource.class).toResources(orders);
		return new ResponseEntity<List<OrderResource>>(orderResources, HttpStatus.OK);
	}*/
	
	/**
	 * 根据订单状态查找订单
	 * @return
	 */ 
	@ApiOperation(value = "根据订单状态查找订单", notes = "根据订单状态查找订单", response = OrderResource.class)
	@GetMapping
	public ResponseEntity<List<OrderResource>> list(@RequestParam(required = false) OrderStatus status) {
		List<Order> orders = orderService.findAll(status);
		List<OrderResource> orderResources = new OrderResourceAssembler(this.getClass(),
				OrderResource.class).toResources(orders);
		return new ResponseEntity<List<OrderResource>>(orderResources, HttpStatus.OK);
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

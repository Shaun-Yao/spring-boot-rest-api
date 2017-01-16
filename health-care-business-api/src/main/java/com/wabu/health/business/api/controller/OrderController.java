package com.wabu.health.business.api.controller;

import java.util.List;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wabu.health.business.api.resource.OrderResource;
import com.wabu.health.business.api.resource.OrderResourceAssembler;
import com.wabu.health.enums.OrderStatus;
import com.wabu.health.model.Order;
import com.wabu.health.service.BusinessService;
import com.wabu.health.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"Order related"})//2.6.0起中文路径无法展开api
@RestController
@RequestMapping(value = "/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private BusinessService businessService;
	
	@Autowired
	private Validator validator;
	
	/**
	 * 查找需求订单
	 * @param status 订单状态
	 * @param cursor 游标
	 * @param limit 查询条数
	 * @return
	 */
	@ApiOperation(value = "根据订单状态查找订单", notes = "商家查找需求订单status值应为2，即是付款成功状态", response = OrderResource.class)
	@GetMapping
	public ResponseEntity<List<OrderResource>> list(@RequestParam(required = false) OrderStatus status,
			@RequestParam(required = false) String cursor, @RequestParam int limit,
			Pageable pageable) {
//		List<Order> orders = orderService.findAll(status, cursor, limit, pageable);
//		List<OrderResource> orderResources = new OrderResourceAssembler(this.getClass(),
//				OrderResource.class).toResources(orders);
//		return new ResponseEntity<List<OrderResource>>(orderResources, HttpStatus.OK);
		return null;
	}
	
	/**
	 * 根据id查找订单
	 * @return
	 */ 
	@ApiOperation(value = "根据id查找订单", notes = "根据id查找订单", response = OrderResource.class)
	@GetMapping(value = "/{id}")
	public ResponseEntity<OrderResource> get(@PathVariable String id) {
		Order order = orderService.findOne(id);
		OrderResource orderResource = new OrderResourceAssembler(this.getClass(),
				OrderResource.class).toResource(order);
		return new ResponseEntity<OrderResource>(orderResource, HttpStatus.OK);
	}
	
	/**
	 * 抢单
	 * @return
	 */ 
	@ApiOperation(value = "抢单", notes = "抢单")
	@PutMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void rush(@PathVariable String id) {
		//TODO 抢单前先判断是否已经被抢
//		Business currentUser = businessService.findOne("1");
//		Order order = orderService.findOne(id);
//		order.setBusiness(currentUser);
//		orderService.update(order);
		orderService.rushOrder(id);
	}

}

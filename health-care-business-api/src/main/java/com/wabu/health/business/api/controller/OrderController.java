package com.wabu.health.business.api.controller;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
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
import io.swagger.annotations.ApiParam;

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
	
	@Autowired
	private PagedResourcesAssembler<Order> parAssembler;
	
	/**
	 * 查找需求订单
	 * @param status 订单状态
	 * @param cursor 游标
	 * @param limit 查询条数
	 * @return
	 */
	@ApiOperation(value = "查找需求订单", notes = "查找需求订单")
	@GetMapping
	public ResponseEntity<PagedResources<OrderResource>> list(
			@ApiParam(value = "页码, 从1开始", required = true, defaultValue = "1") @RequestParam int page, 
			@ApiParam(value = "每页记录条数", required = true, defaultValue = "10") @RequestParam int size,
			@ApiParam(value = "排序字段,多字段以\",\"号隔开，\"-\"号表示降序，默认为升序", 
			required = true, defaultValue = "serviceTime,-servicePackage") @RequestParam String sort) {
		
		Page<Order> orders = orderService.findAll(OrderStatus.付款成功, page, size, sort);//需求订单就是已经付款成功的订单
		OrderResourceAssembler orderResourceAssembler = new OrderResourceAssembler(this.getClass(), OrderResource.class);
		return new ResponseEntity<>(parAssembler.toResource(orders, orderResourceAssembler), HttpStatus.OK);
	}
	
	/**
	 * 根据id查找订单
	 * @return
	 */ 
	@ApiOperation(value = "根据id查找订单", notes = "根据id查找订单")
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

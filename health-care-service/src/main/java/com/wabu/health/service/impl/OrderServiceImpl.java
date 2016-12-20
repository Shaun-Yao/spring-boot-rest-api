package com.wabu.health.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wabu.health.enums.OrderStatus;
import com.wabu.health.model.Order;
import com.wabu.health.model.ServiceItems;
import com.wabu.health.repository.OrderRepository;
import com.wabu.health.service.OrderService;



@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public Order findOne(String id) {
		return orderRepository.findOne(id);
	}
	
	@Override
	@Transactional
	public void add(ServiceItems serviceItems) {
		//Date now = new Date();
		//serviceItems.setCreatedAt(now);
		
		Order order = new Order();
		order.setOrderStatus(OrderStatus.付款成功);//测试阶段直接到付款成功状态
		order.setServiceItems(serviceItems);
		//order.setCreatedAt(now);
		orderRepository.save(order);
	}

	@Override
	public void update(Order order) {
		orderRepository.save(order);
	}


	@Override
	public Page<Order> findPage(Pageable pageable) {
		return orderRepository.findAll(pageable);
	}


}

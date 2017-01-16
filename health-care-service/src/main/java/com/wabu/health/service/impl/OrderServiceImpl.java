package com.wabu.health.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wabu.health.enums.OrderStatus;
import com.wabu.health.model.Order;
import com.wabu.health.repository.BusinessRepository;
import com.wabu.health.repository.OrderRepository;
import com.wabu.health.service.OrderService;



@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private BusinessRepository businessRepository;
	
	@Override
	public Order findOne(String id) {
		return orderRepository.findOne(id);
	}
	
	@Override
	@Transactional
	public void add(Order order) {
		
		order.setOrderStatus(OrderStatus.付款成功);//测试阶段直接到付款成功状态
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

	@Override
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	@Override
	public Page<Order> findAll(OrderStatus status, int page, int size) {
		Pageable pageable = new PageRequest(page, size);
		if (status == null) {//无状态表示查询全部订单
			return orderRepository.findAll(pageable);
		}
		
		return orderRepository.findByOrderStatus(status, pageable);
	}

	@Override
	@Transactional
	public void rushOrder(String id) {
		//Business business = businessRepository.findOne("1");
		orderRepository.updateBusiness(id, "1");
	}


}

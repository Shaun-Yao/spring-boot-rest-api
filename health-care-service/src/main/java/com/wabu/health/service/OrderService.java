package com.wabu.health.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wabu.health.model.Order;
import com.wabu.health.model.ServiceItems;


public interface OrderService {
	Order findOne(String id);
	void add(ServiceItems serviceItems);
	void update(Order order);
	Page<Order> findPage(Pageable pageable);

}

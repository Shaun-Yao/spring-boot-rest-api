package com.wabu.health.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wabu.health.model.Order;



public interface OrderService {
	Order findOne(String id);
	void add(Order order);
	void update(Order order);
	Page<Order> findPage(Pageable pageable);
	List<Order> findAll();
}

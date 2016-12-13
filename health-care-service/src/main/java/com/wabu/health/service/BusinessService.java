package com.wabu.health.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wabu.health.model.Business;


public interface BusinessService {
	Business findOne(String id);
	void update(Business business);
	Business findByAccount(String account);
	void add(Business business);
	Page<Business> findPage(Pageable pageable);
	void pass(String id);

}

package com.wabu.health.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wabu.health.model.Address;



public interface AddressService {
	Address findOne(String id);
	void add(Address address);
	void update(Address address);
	Page<Address> findPage(Pageable pageable);
	List<Address> findAll(); 
}

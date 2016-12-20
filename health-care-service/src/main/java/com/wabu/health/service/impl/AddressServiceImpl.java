package com.wabu.health.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wabu.health.model.Address;
import com.wabu.health.repository.AddressRepository;
import com.wabu.health.service.AddressService;



@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressRepository addressRepository;
	
	@Override
	public Address findOne(String id) {
		return addressRepository.findOne(id);
	}
	
	@Override
	@Transactional
	public void add(Address address) {
		addressRepository.save(address);
	}

	@Override
	public void update(Address address) {
		addressRepository.save(address);
	}


	@Override
	public Page<Address> findPage(Pageable pageable) {
		return addressRepository.findAll(pageable);
	}

	@Override
	public List<Address> findAll() {
		return addressRepository.findAll();
	}


}

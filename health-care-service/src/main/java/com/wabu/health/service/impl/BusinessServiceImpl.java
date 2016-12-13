package com.wabu.health.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wabu.health.model.Business;
import com.wabu.health.repository.BusinessRepository;
import com.wabu.health.service.BusinessService;
import com.wabu.health.util.PasswordGenerator;



@Service
public class BusinessServiceImpl implements BusinessService {

	@Autowired
	BusinessRepository businessRepository;
	
	@Override
	public Business findOne(String id) {
		return businessRepository.findOne(id);
	}
	
	@Override
	@Transactional
	public void add(Business business) {
		final String plainPassword = business.getPlainPassword();
		final String salt = PasswordGenerator.next();
        final String hash = PasswordGenerator.hash(plainPassword, salt);
        business.setSalt(salt);
        business.setPassword(hash);
		business.setCreateTime(new Date());
		businessRepository.save(business);
	}

	@Override
	public void update(Business business) {
		businessRepository.save(business);
	}

	@Override
	public Business findByAccount(String account) {
		return businessRepository.findByAccount(account);
	}

	@Override
	public Page<Business> findPage(Pageable pageable) {
		return businessRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public void pass(String id) {
		businessRepository.setValidTrue(id);
	}

}

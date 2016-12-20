package com.wabu.health.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wabu.health.model.Client;


public interface ClientService {
	Client findOne(String id);
	void update(Client client);
	Client findByAccount(String account);
	void add(Client client);
	Page<Client> findPage(Pageable pageable);

}

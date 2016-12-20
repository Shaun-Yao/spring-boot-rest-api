package com.wabu.health.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wabu.health.model.Client;
import com.wabu.health.repository.ClientRepository;
import com.wabu.health.service.ClientService;
import com.wabu.health.util.PasswordGenerator;



@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientRepository clientRepository;
	
	@Override
	public Client findOne(String id) {
		return clientRepository.findOne(id);
	}
	
	@Override
	@Transactional
	public void add(Client client) {
		final String plainPassword = client.getPlainPassword();
		final String salt = PasswordGenerator.next();
        final String hash = PasswordGenerator.hash(plainPassword, salt);
        client.setSalt(salt);
        client.setPassword(hash);
		clientRepository.save(client);
	}

	@Override
	public void update(Client client) {
		clientRepository.save(client);
	}

	@Override
	public Client findByAccount(String account) {
		return clientRepository.findByAccount(account);
	}

	@Override
	public Page<Client> findPage(Pageable pageable) {
		return clientRepository.findAll(pageable);
	}

}

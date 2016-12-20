package com.wabu.health.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wabu.health.model.Client;


@Repository
public interface ClientRepository extends JpaRepository<Client, String> {

	Client findByAccount(String account);
}

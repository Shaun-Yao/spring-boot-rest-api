package com.wabu.health.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wabu.health.model.Address;


@Repository
public interface AddressRepository extends JpaRepository<Address, String> {

	
}

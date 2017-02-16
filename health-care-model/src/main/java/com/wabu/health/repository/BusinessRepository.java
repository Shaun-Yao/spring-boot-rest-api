package com.wabu.health.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wabu.health.model.Business;


@Repository
public interface BusinessRepository extends JpaRepository<Business, String> {

	Business findByAccount(String account);

	@Modifying
	@Query("update Business business set business.valid = 1 where business.id = ?1")
	void setValidTrue(String id);

}

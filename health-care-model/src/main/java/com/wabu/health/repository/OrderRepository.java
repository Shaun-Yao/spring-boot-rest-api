package com.wabu.health.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wabu.health.enums.OrderStatus;
import com.wabu.health.model.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

	Page<Order> findByOrderStatus(OrderStatus orderStatus, Pageable pageable);

	@Modifying
	@Query("update Order o set o.business.id = :businessId where o.id = :id")
	void updateBusiness(@Param("id") String id, @Param("businessId") String businessId);

	
}

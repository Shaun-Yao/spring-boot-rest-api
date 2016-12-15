package com.wabu.health.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.wabu.health.enums.OrderStatus;

import lombok.Getter;
import lombok.Setter;

/**
 * 订单信息
 *
 */
@Getter
@Setter
@Entity
@Table(name = "HEALTH_ORDER")
public class Order extends BaseEntity {
	
	@OneToOne
	@JoinColumn(name = "SERVICE_ITEMS_ID", nullable = false)
	private ServiceItems serviceItems;// 服务项目

	
	//@ManyToOne(cascade = CascadeType.PERSIST)
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "CLIENT_ID", nullable = false)
//	private Client client; //用户
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "BUSINESS_ID", nullable = false)
//	private Business business; //接单人
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "ORDER_STATUS", nullable = false)
	private OrderStatus orderStatus;// 订单状态
	
	
}

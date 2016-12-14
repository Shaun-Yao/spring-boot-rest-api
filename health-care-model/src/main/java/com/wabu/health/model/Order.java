package com.wabu.health.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.wabu.health.enums.LoanBusinessStatus;
import com.wabu.health.enums.LoanClientStatus;

import lombok.Getter;
import lombok.Setter;

/**
 * 申请借款信息
 *
 */
@Getter
@Setter
@Entity
@Table(name = "HEALTH_ORDER")
public class Order extends BaseEntity {
	
	@OneToOne
	@Column(name = "SERVICEITEMS_ID", nullable = false)
	private ServiceItems serviceItems;// 服务项目

	
	//@ManyToOne(cascade = CascadeType.PERSIST)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIENT_ID", nullable = false)
	private Client client; //用户
	
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "CLIENT_STATUS", nullable = false)
	private LoanClientStatus clientStatus;// 贷款状态
	
	/*@JsonIgnore
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Data data;*/// 贷款人资料
	
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "BUSINESS_STATUS", nullable = false)
	private LoanBusinessStatus businessStatus;// 贷款状态
	
	
}

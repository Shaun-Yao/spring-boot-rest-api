package com.wabu.health.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

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
@Table(name = "LOAN_ORDER")
public class Order extends BaseEntity {
	
	
	@Column(name = "MONEY", nullable = false)
	private Integer money;// 订单金额

	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "LENDER_PROFILE_ID", nullable = false)
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
	
	
	@NotBlank
	@Column(name = "LATITUDE", nullable = false)
	private Double latitude;//订单位置纬度  
	
	
	@NotBlank
	@Column(name = "LONGITUDE", nullable = false)
    private Double longitude;//订单位置经度   
	
}

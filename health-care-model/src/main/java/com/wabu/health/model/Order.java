package com.wabu.health.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wabu.health.enums.OrderStatus;
import com.wabu.health.enums.ServiceType;

import io.swagger.annotations.ApiModelProperty;
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
	
	@ApiModelProperty(value = "服务类型", required = true)
	@Enumerated
	@Column(name = "SERVICETYPE", nullable = false)
	private ServiceType serviceType;// 服务类型
	
	/*
	 * 服务地址不与Address关联，防止用户修改或者删除地址后订单收货信息出错
	 */
	@ApiModelProperty(value = "服务地址，拼接完整地址作为参数", required = true)
	@Column(name = "ADDRESS", nullable = false)
	private String address;// 地址
	
	@ApiModelProperty(example = "2016-12-19 17:11:39")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "SERVICE_TIME", nullable = false, updatable = false)
	private Date serviceTime;// 服务时间

	/*
	 * 服务套餐不与ServicePackage关联，防止修改或者删除套餐后订单收货信息出错
	 */
	@ApiModelProperty(value = "服务套餐", required = true)
	@Column(name = "SERVICE_PACKAGE", nullable = false)
	private String servicePackage;// 服务套餐

	@ApiModelProperty(value = "描述", required = true)
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;// 描述

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID", nullable = false)
	private Client client;
	
//	TODO 保存时没有检查关联实体是否存在
	@ApiModelProperty(value = "患者", dataType = "com.wabu.health.model.NestedObject", required = true)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PATIENT_ID", nullable = false)
	private Patient patient;

	@ApiModelProperty(value = "是否有相应的护理工具", required = true)
	@Column(name = "HAS_TOOL", nullable = false, columnDefinition = "boolean default false")
	private boolean tool = false;// 是否有相应的护理工具

	@ApiModelProperty(value = "是否有必备药品")
	@Column(name = "HAS_ESSENTIAL_DRUGS", columnDefinition = "boolean default false")
	private boolean essentialDrugs = false;// 是否有必备药品
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BUSINESS_ID")
	private Business business; //接单人
	
	@JsonIgnore
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "ORDER_STATUS", nullable = false)
	private OrderStatus orderStatus;// 订单状态
	
	
}

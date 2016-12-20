package com.wabu.health.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wabu.health.enums.ServiceType;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 服务项目
 *
 */
@Getter
@Setter
@Entity
@Table(name = "HEALTH_SERVICE_ITEMS")
public class ServiceItems extends BaseEntity {
	
	@Enumerated
	@Column(name = "SERVICETYPE", nullable = false)
	private ServiceType serviceType;// 服务类型
	
	/*
	 * 服务地址不与Address关联，防止用户修改或者删除地址后订单收货信息出错
	 */
	@Column(name = "ADDRESS", nullable = false)
	private String address;// 地址
	
	@ApiModelProperty(example = "2016-12-19 17:11:39")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "SERVICE_TIME", nullable = false, updatable = false)
	private Date serviceTime;// 创建时间

	/*
	 * 服务套餐不与ServicePackage关联，防止修改或者删除套餐后订单收货信息出错
	 */
	@Column(name = "SERVICE_PACKAGE", nullable = false)
	private String servicePackage;// 服务套餐
	
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;// 描述

//	@JsonProperty(value = "patient.id")
//	@ApiModelProperty(dataType = "java.lang.String", example = "1")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PATIENT_ID", nullable = false)
	private Patient patient;
	
	@Column(name = "HAS_INFUSION_TOOL", nullable = false, columnDefinition = "boolean default false")
	private boolean infusionTool = false;// 是否有输液工具
	
	@Column(name = "HAS_ESSENTIAL_DRUGS", nullable = false, columnDefinition = "boolean default false")
	private boolean essentialDrugs = false;// 是否有必备药品
	
	@ApiModelProperty(hidden = true)
	@Column(name = "VALID", nullable = false, columnDefinition = "boolean default true")
	private boolean valid = true;// 是否有效
	
}

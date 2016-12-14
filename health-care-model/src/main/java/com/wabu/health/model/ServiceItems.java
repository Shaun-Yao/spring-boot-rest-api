package com.wabu.health.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

/**
 * 服务项目抽象类
 *
 */
@Getter
@Setter
@MappedSuperclass
public abstract class ServiceItems extends BaseEntity {
	
	/*
	 * 服务地址不与Address关联，防止用户修改或者删除地址后订单收货信息出错
	 */
	@Column(name = "ADDRESS", nullable = false)
	protected String address;// 地址
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "SERVICE_TIME", nullable = false, updatable = false)
	protected Date serviceTime;// 创建时间

	/*
	 * 服务套餐不与ServicePackage关联，防止修改或者删除套餐后订单收货信息出错
	 */
	@Column(name = "SERVICE_PACKAGE", nullable = false)
	protected String servicePackage;// 服务套餐
	
	@Column(name = "DESC", nullable = false)
	protected String desc;// 描述

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PATIENT_ID", nullable = false)
	protected Patient patient;
	
	@Column(name = "VALID", nullable = false, columnDefinition = "boolean default true")
	protected Boolean valid = true;// 是否有效
	
}

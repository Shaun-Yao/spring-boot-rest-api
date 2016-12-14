package com.wabu.health.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * 服务套餐
 *
 */
@Getter
@Setter
@Entity
@Table(name = "HEALTH_SERVICE_PACKAGE")
public class ServicePackage extends BaseEntity {
	
	@Column(name = "MONEY", nullable = false)
	private Integer money;// 金额

	@Column(name = "TIMES", nullable = false)
	private Integer times;// 次数

	@Column(name = "VALID", nullable = false, columnDefinition = "boolean default true")
	private Boolean valid = true;// 是否有效
	
}

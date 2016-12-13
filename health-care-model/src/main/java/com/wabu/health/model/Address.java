package com.wabu.health.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * 服务地址
 *
 */
@Getter
@Setter
@Entity
@Table(name = "HEALTH_ADDRESS")
public class Address extends BaseEntity {
	
	@Column(name = "AREA", nullable = false)
	private String area;// 地区/街道

	@Column(name = "ADDRESS", nullable = false)
	private String address;// 楼层门牌信息

	@ManyToOne
    @JoinColumn(name = "CLIENT_ID", nullable = false, updatable = false)
	private Client client;//关联用户
	
	@Column(name = "VALID", nullable = false, columnDefinition = "boolean default true")
	private Boolean valid = true;// 是否有效

	
}

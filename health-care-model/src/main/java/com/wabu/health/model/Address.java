package com.wabu.health.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
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
	
	@ApiModelProperty(value = "地区/街道", required = true)
	@Column(name = "AREA", nullable = false)
	private String area;// 地区/街道

	@ApiModelProperty(value = "楼层门牌信息", required = true)
	@Column(name = "ADDRESS", nullable = false)
	private String address;// 楼层门牌信息

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID", nullable = false, updatable = false)
	private Client client;//关联用户

	@JsonIgnore
	@Column(name = "VALID", nullable = false, columnDefinition = "boolean default true")
	private Boolean valid = true;// 是否有效

	
}

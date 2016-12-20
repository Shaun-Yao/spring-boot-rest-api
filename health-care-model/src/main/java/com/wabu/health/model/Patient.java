package com.wabu.health.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wabu.health.enums.Gender;
import com.wabu.health.enums.Relationship;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 患者
 *
 */
@Getter
@Setter
@Entity
@Table(name = "HEALTH_PATIENT")
public class Patient extends BaseEntity {

	@ApiModelProperty(value = "姓名", required = true)
	@Column(name = "NAME", nullable = false)
	private String name;// 姓名

	@ApiModelProperty(value = "身份证", required = true)
	@Column(name = "ID_CARD", nullable = false)
	private String idCard;// 身份证号

	@ApiModelProperty(value = "性别", required = true)
	@Enumerated
	@Column(name = "GENDER", nullable = false)
	private Gender gender;// 性别

	@ApiModelProperty(value = "与患者关系", required = true)
	@Enumerated
	@Column(name = "RELATIONSHIP", nullable = false)
	private Relationship relationship;// 与患者关系
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID", nullable = false, updatable = false)
	private Client client;//关联用户
	
	@JsonIgnore
	@Column(name = "VALID", nullable = false, columnDefinition = "boolean default true")
	private boolean valid = true;// 是否有效

	
}

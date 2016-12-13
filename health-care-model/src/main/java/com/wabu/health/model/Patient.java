package com.wabu.health.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wabu.health.enums.Gender;
import com.wabu.health.enums.Relationship;

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
	
	@Column(name = "NAME", nullable = false)
	private String name;// 姓名

	@Column(name = "ID_CARD", nullable = false)
	private String idCard;// 身份证号

	@Enumerated
	@Column(name = "GENDER", nullable = false)
	private Gender gender;// 性别
	
	@Enumerated
	@Column(name = "RELATIONSHIP", nullable = false)
	private Relationship relationship;// 性别
	
	@ManyToOne
    @JoinColumn(name = "CLIENT_ID", nullable = false, updatable = false)
	private Client client;//关联用户
	
	@Column(name = "VALID", nullable = false, columnDefinition = "boolean default true")
	private Boolean valid = true;// 是否有效

	
}

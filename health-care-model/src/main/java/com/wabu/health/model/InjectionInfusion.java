package com.wabu.health.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * 打针输液
 *
 */
@Getter
@Setter
@Entity
@Table(name = "HEALTH_INJECTION_INFUSION")
public class InjectionInfusion extends ServiceItems {
	
	
	@Column(name = "HAS_INFUSION_TOOL", nullable = false, columnDefinition = "boolean default false")
	private boolean infusionTool = false;// 是否有输液工具
	
	@Column(name = "HAS_ESSENTIAL_DRUGS", nullable = false, columnDefinition = "boolean default false")
	private boolean essentialDrugs = false;// 是否有必备药品
	

}

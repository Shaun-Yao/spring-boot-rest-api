package com.wabu.health.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

/**
 * 商家
 *
 */
@Getter
@Setter
@Entity
@Table(name = "LOAN_BUSINESS")
public class Client extends BaseEntity {

	 /**
     * 手机号码: 
     * 13[0-9], 14[5,7], 15[0, 1, 2, 3, 5, 6, 7, 8, 9], 17[0, 1, 6, 7, 8], 18[0-9]
     * 移动号段: 134,135,136,137,138,139,147,150,151,152,157,158,159,170,178,182,183,184,187,188
     * 联通号段: 130,131,132,145,152,155,156,170,171,176,185,186
     * 电信号段: 133,134,153,170,177,180,181,189
     */
	@NotBlank
	@ApiModelProperty(value = "手机号码（正则：^1(3[0-9]|4[57]|5[0-35-9]|7[01678]|8[0-9])\\d{8}）", required = true)
	@Pattern(regexp = "^1(3[0-9]|4[57]|5[0-35-9]|7[01678]|8[0-9])\\d{8}")
	@Column(name = "ACCOUNT", nullable = false)
	private String account;// 账号
	
	
	// 不持久化到数据库，也不显示在Restful接口的属性.
	@Transient
	@JsonProperty(access = Access.WRITE_ONLY)
	@ApiModelProperty(value = "密码（6-16位字符）", required = true)
	@NotBlank
	@Size(min = 6, max = 16)
	private String plainPassword;
	
	
	@JsonIgnore
	@Column(name = "PASSWORD", nullable = false)
	private String password;// 密码
	
	
	@JsonIgnore
	@Column(name = "SALT", nullable = false)
	private String salt;// 盐值

	
	@NotBlank
	@ApiModelProperty(value = "用户昵称（3-16位字符）", required = true)
	@Size(min = 3, max = 16)
	@Column(name = "NAME", nullable = false)
	private String name;
	

	@ApiParam(hidden = true)
	@Column(name = "VALID", nullable = false, columnDefinition = "boolean default false")
	private boolean valid = false;// 是否有效
}

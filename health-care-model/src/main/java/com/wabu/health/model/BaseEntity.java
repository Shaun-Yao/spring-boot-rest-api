package com.wabu.health.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

	@ApiParam(hidden = true)
	@Id @GenericGenerator(name = "generator", strategy = "uuid2")
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	protected String id;
	

	@ApiParam(hidden = true)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "CREATE_TIME", nullable = false, updatable = false)
	protected Date createTime;// 创建时间

}

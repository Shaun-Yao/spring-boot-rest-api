package com.wabu.health.business.api.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.Date;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import com.wabu.health.business.api.controller.PatientController;
import com.wabu.health.enums.OrderStatus;
import com.wabu.health.enums.ServiceType;
import com.wabu.health.model.Order;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OrderResource extends ResourceSupport {

	private ServiceType serviceType;// 服务类型
	
	private String address;// 地址
	
	private Date serviceTime;// 服务时间

	private String servicePackage;// 服务套餐

	private String description;// 描述

	private boolean tool = false;// 是否有相应的护理工具

	private boolean essentialDrugs = false;// 是否有必备药品
	
	private OrderStatus orderStatus;
	
	public OrderResource(Order order) {
		this.serviceType = order.getServiceType();
		this.address = order.getAddress();
		this.serviceTime = order.getServiceTime();
		this.servicePackage = order.getServicePackage();
		this.description = order.getDescription();
		this.tool = order.isTool();
		this.essentialDrugs = order.isEssentialDrugs();
		this.orderStatus = order.getOrderStatus();
		Link link = linkTo(PatientController.class).slash(order.getPatient().getId()).withRel("patient");
		add(link);
	}
}

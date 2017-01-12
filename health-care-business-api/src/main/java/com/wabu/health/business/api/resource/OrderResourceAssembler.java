package com.wabu.health.business.api.resource;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.wabu.health.model.Order;

public class OrderResourceAssembler extends ResourceAssemblerSupport<Order, OrderResource> {


	public OrderResourceAssembler(Class<?> controllerClass, Class<OrderResource> resourceType) {
		super(controllerClass, resourceType);
	}

	@Override
	public OrderResource toResource(Order order) {
		OrderResource orderResource = createResourceWithId(order.getId(), order);
		return orderResource;
	}

	@Override
	protected OrderResource instantiateResource(Order order) {
		return new OrderResource(order);
	}
	
}

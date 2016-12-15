package com.wabu.health.enums;

/**
 * 客户端订单状态
 * @author yao
 *
 */
public enum OrderStatus {
	
	订单已提交(0), 待付款(1), 付款成功(2), 待服务(3), 待评价(4);

	private final int code;

	private OrderStatus(int code) {
		this.code = code;
	}

	public static OrderStatus fromCode(int code) {
		switch (code) {
		case 0:
			return 待付款;
		case 1:
			return 待服务;

		default:
			return 待评价;
		}
	}

	public int getCode() {
		return code;
	}
}

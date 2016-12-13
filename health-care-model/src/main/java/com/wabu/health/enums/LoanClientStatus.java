package com.wabu.health.enums;

/**
 * 贷款客户端状态
 * @author yao
 *
 */
public enum LoanClientStatus {
	
	申请(0), 商家接单(1), 提交资料(2), 审核中(3), 审核通过(3);

	private final int code;

	private LoanClientStatus(int code) {
		this.code = code;
	}

	public static LoanClientStatus fromCode(int code) {
		switch (code) {
		case 0:
			return 申请;
		case 1:
			return 商家接单;
		case 2:
			return 提交资料;
		case 3:
			return 审核中;

		default:
			return 审核通过;
		}
	}

	public int getCode() {
		return code;
	}
}

package com.wabu.health.enums;

/**
 * 贷款商家状态
 * @author yao
 *
 */
public enum LoanBusinessStatus {
	
	抢单成功(0), 提出资料要求(1), 资料审核(2), 约定面谈(3);

	private final int code;

	private LoanBusinessStatus(int code) {
		this.code = code;
	}

	public static LoanBusinessStatus fromCode(int code) {
		switch (code) {
		case 0:
			return 抢单成功;
		case 1:
			return 提出资料要求;
		case 2:
			return 资料审核;

		default:
			return 约定面谈;
		}
	}

	public int getCode() {
		return code;
	}
}

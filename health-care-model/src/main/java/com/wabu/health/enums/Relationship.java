package com.wabu.health.enums;

/**
 * 与患者关系
 * @author yao
 *
 */
public enum Relationship {
	
	男(0), 女(1);

	private final int code;

	private Relationship(int code) {
		this.code = code;
	}

	public static Relationship fromCode(int code) {
		switch (code) {
		case 0:
			return 男;

		default:
			return 女;
		}
	}

	public int getCode() {
		return code;
	}
}

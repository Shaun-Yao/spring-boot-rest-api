package com.wabu.health.enums;

/**
 * 性别
 * @author yao
 *
 */
public enum Gender {
	
	男(0), 女(1);

	private final int code;

	private Gender(int code) {
		this.code = code;
	}

	public static Gender fromCode(int code) {
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

package com.wabu.health.enums;

/**
 * 与患者关系
 * @author yao
 *
 */
public enum Relationship {
	
	自己(0), 爸爸(1), 妈妈(2), 亲戚(3), 朋友(4), 孩子(5), 其它(6);

	private final int code;

	private Relationship(int code) {
		this.code = code;
	}

	public static Relationship fromCode(int code) {
		switch (code) {
		case 0:
			return 自己;
		case 1:
			return 爸爸;
		case 2:
			return 妈妈;
		case 3:
			return 亲戚;
		case 4:
			return 朋友;
		case 5:
			return 孩子;

		default:
			return 其它;
		}
	}

	public int getCode() {
		return code;
	}
}

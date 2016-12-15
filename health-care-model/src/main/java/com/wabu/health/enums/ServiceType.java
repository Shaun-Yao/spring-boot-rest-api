package com.wabu.health.enums;

/**
 * 服务类型
 * @author yao
 *
 */
public enum ServiceType {
	
	打针输液(0), 孕妇护理(1), 静脉采血(2), PICC换药(3), 普通换药(4), 压疮护理(5);

	private final int code;

	private ServiceType(int code) {
		this.code = code;
	}

	public static ServiceType fromCode(int code) {
		switch (code) {
		case 0:
			return 打针输液;
		case 1:
			return 孕妇护理;
		case 2:
			return 静脉采血;
		case 3:
			return PICC换药;
		case 4:
			return 普通换药;

		default:
			return 压疮护理;
		}
	}

	public int getCode() {
		return code;
	}
}

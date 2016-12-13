package com.wabu.health.business.api.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 自定义错误请求状态码
 * @author yao
 */
@AllArgsConstructor
public enum ResultStatus {
    USERNAME_OR_PASSWORD_ERROR(-1001, "用户名或密码错误"),
    USER_INVALID(-1002, "用户暂未通过审核"),
    USER_NOT_FOUND(-1003, "用户不存在"),
	
    PARAM_ERROR(-1400, "参数错误，请参考API文档"),
	SERVER_ERROR(-1500, "服务器错误");

    /**
     * 返回码
     */
	@Getter @Setter
    private int code;

    /**
     * 返回结果描述
     */
	@Getter @Setter
    private String message;

}

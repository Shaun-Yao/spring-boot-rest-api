package com.wabu.health.business.api.config;

import lombok.Getter;

/**
 * 自定义返回结果
 * @author yao
 */
public class ResultModel {

    /**
     * 返回码
     */
	@Getter
    private int code;

    /**
     * 返回结果描述
     */
	@Getter
    private String message;


    public ResultModel(ResultStatus status) {
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public static ResultModel error(ResultStatus error) {
        return new ResultModel(error);
    }
}

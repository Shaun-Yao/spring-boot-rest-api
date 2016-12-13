package com.wabu.health.business.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wabu.health.business.api.annotation.CurrentUser;
import com.wabu.health.business.api.annotation.TokenRequired;
import com.wabu.health.business.api.authorization.Token;
import com.wabu.health.business.api.authorization.TokenManager;
import com.wabu.health.business.api.config.ResultModel;
import com.wabu.health.business.api.config.ResultStatus;
import com.wabu.health.model.Business;
import com.wabu.health.service.BusinessService;
import com.wabu.health.util.PasswordGenerator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(tags = { "登录相关" })
@RestController
@RequestMapping(value = "/login")
@Slf4j
public class LoginController {

	@Autowired
	private BusinessService businessService;

	@Autowired
	private TokenManager tokenManager;

	/**
	 * 商家登录
	 * 
	 * @param account
	 * @param password
	 * @return 成功则返回token, 失败则返回404
	 */
	@ApiOperation(value = "登录", 
			notes = "成功即返回userId,token字段，userId_token组成authorization，后面的请求需要权限都传authorization参数", response = Token.class)
//	@ApiResponses(value = { @ApiResponse(code = 404, message = "用户名或密码错误"),
//			@ApiResponse(code = 403, message = "商家暂未审核通过") })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestParam String account, @RequestParam String password) {

		Business business = businessService.findByAccount(account);

		if (business != null) {
			if (!business.isValid()) {// 商家无效
				return new ResponseEntity<>(ResultModel.error(ResultStatus.USER_INVALID), HttpStatus.FORBIDDEN);
			}

			String salt = business.getSalt();
			String dbPassword = business.getPassword();
			if (PasswordGenerator.verify(password, salt, dbPassword)) {
				// 生成一个token，保存用户登录状态
				Token token = tokenManager.create(business.getId());
				return new ResponseEntity<Token>(token, HttpStatus.OK);
				//return new ResponseEntity<>(ResultModel.ok(token), HttpStatus.OK);
			}
		}

		// 排除商家无效，成功返回的情况，其它的就是用户名或密码错误
//		String message = "用户名或密码错误！";
//		log.warn(message);
		//throw new RestException(HttpStatus.NOT_FOUND, message);
		return new ResponseEntity<>(ResultModel.error(ResultStatus.USERNAME_OR_PASSWORD_ERROR), HttpStatus.NOT_FOUND);
	}

	/**
	 * 商家登出
	 * 
	 * @param business
	 */
	@ApiOperation(value = "退出登录", notes = "退出登录")
	@ApiImplicitParam(name = "authorization", value = "授权参数", required = true, dataType = "string", paramType = "header")
    @RequestMapping(method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@TokenRequired
	public void logout(@CurrentUser Business business) {
		tokenManager.delete(business.getId());
	}

}

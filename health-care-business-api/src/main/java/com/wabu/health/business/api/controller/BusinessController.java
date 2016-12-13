package com.wabu.health.business.api.controller;

import java.io.IOException;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wabu.health.business.api.annotation.TokenRequired;
import com.wabu.health.business.api.config.ResultModel;
import com.wabu.health.business.api.config.ResultStatus;
import com.wabu.health.model.Business;
import com.wabu.health.service.BusinessService;
import com.wabu.health.util.BeanValidators;
import com.wabu.health.util.FileUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = {"商家相关"})
@RestController
@RequestMapping(value = "/businesses")
public class BusinessController {

	@Autowired
	private BusinessService businessService;

	@Autowired
	private Validator validator;
	
	@Autowired
	private FileUtil fileUtil;
	
	/**
	 * 根据id查找商家
	 * @param id 商家id
	 * @return
	 */
	@ApiOperation(value = "根据id查找商家", notes = "根据id查找商家", response = Business.class)
//    @ApiResponses(value = {@ApiResponse(code = 404, message = "商家不存在") })
	@ApiImplicitParam(name = "authorization", value = "授权参数", required = true, dataType = "string", paramType = "header")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@TokenRequired
	public ResponseEntity<?> get(
			@ApiParam(value = "商家id" ,required = true ) @PathVariable("id") String id) {
		Business business = businessService.findOne(id);
		if (business == null) {
			return new ResponseEntity<>(ResultModel.error(ResultStatus.USER_NOT_FOUND), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Business>(business, HttpStatus.OK);
	}
	
	/**
	 * 商家注册
	 * 
	 * @param business
	 * @throws IOException 由全局异常统一处理
	 */
	@ApiOperation(value = "商家注册", notes = "商家注册")
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void add(@ModelAttribute Business business, 
			@ApiParam(value = "营业执照", required = true) @RequestParam MultipartFile licenseFile) throws IOException {
		
		// 调用JSR303 Bean Validator进行校验, 异常将由RestExceptionHandler统一处理.
		BeanValidators.validateWithException(validator, business);
		
		String license = fileUtil.saveImage(licenseFile);
		//business.setLicense(license);
		
		businessService.add(business);

	}

}

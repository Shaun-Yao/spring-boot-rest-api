package com.wabu.health.client.api.controller;

import java.util.List;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wabu.health.client.api.annotation.CurrentUser;
import com.wabu.health.model.Client;
import com.wabu.health.model.Patient;
import com.wabu.health.service.ClientService;
import com.wabu.health.service.PatientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"Patient related"})//2.6.1中文路径无法展开api
@RestController
@RequestMapping(value = "/patient")
public class PatientController {

	@Autowired
	private PatientService patientService;
	
	@Autowired
	private ClientService clientService;

	@Autowired
	private Validator validator;
	
	
	
	@ApiOperation(value = "查询用户关联所有患者", notes = "查询用户关联所有患者", response = Patient.class)
    //@ApiResponses(value = {@ApiResponse(code = 404, message = "商家不存在") })
	//@ApiImplicitParam(name = "authorization", value = "授权参数", required = true, dataType = "string", paramType = "header")
    @RequestMapping(method = RequestMethod.GET)
	//@TokenRequired
	public ResponseEntity<List<Patient>> list() {
		
		List<Patient> patients = patientService.findAll();
		return new ResponseEntity<List<Patient>>(patients, HttpStatus.OK);
		
	}
	
	/**
	 * 添加患者
	 * @param patient
	 */
	@ApiOperation(value = "添加患者", notes = "添加患者")
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void add(@RequestBody Patient patient, @CurrentUser Client client) {
		
		// 调用JSR303 Bean Validator进行校验, 异常将由RestExceptionHandler统一处理.
		//BeanValidators.validateWithException(validator, serviceItems);
		Client currentUser = clientService.findOne("1");
		patient.setClient(currentUser);
		patientService.add(patient);

	}

}

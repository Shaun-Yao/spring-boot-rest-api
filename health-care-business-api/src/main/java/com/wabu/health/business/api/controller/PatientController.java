package com.wabu.health.business.api.controller;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wabu.health.model.Patient;
import com.wabu.health.service.PatientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"Patient related"})//2.6.1中文路径无法展开api
@RestController
@RequestMapping(value = "/patients")
public class PatientController {

	@Autowired
	private PatientService patientService;

	@Autowired
	private Validator validator;
	
	
	@ApiOperation(value = "根据id查找患者", notes = "根据id查找患者", response = Patient.class)
    @GetMapping(value = "/{id}")
	public ResponseEntity<Patient> get(@PathVariable String id) {
		
		Patient patient = patientService.findOne(id);
		return new ResponseEntity<Patient>(patient, HttpStatus.OK);
		
	}
	

}

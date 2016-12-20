package com.wabu.health.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wabu.health.model.Patient;



public interface PatientService {
	Patient findOne(String id);
	void add(Patient patient);
	void update(Patient patient);
	Page<Patient> findPage(Pageable pageable);
	List<Patient> findAll(); 
}

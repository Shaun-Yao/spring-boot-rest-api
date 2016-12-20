package com.wabu.health.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wabu.health.model.Patient;
import com.wabu.health.repository.PatientRepository;
import com.wabu.health.service.PatientService;



@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	PatientRepository patientRepository;
	
	@Override
	public Patient findOne(String id) {
		return patientRepository.findOne(id);
	}
	
	@Override
	@Transactional
	public void add(Patient patient) {
		patientRepository.save(patient);
	}

	@Override
	public void update(Patient patient) {
		patientRepository.save(patient);
	}


	@Override
	public Page<Patient> findPage(Pageable pageable) {
		return patientRepository.findAll(pageable);
	}

	@Override
	public List<Patient> findAll() {
		return patientRepository.findAll();
	}


}

package com.healthins.serviceint;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.healthins.dto.RegistrationDto;
import com.healthins.model.RegistrationModel;

public interface HealthInsurenceService {

	
	ResponseEntity<?> saveHealthInsurence(RegistrationDto health);
	ResponseEntity<?> findByMobileNo(String mobileNo);
	ResponseEntity<?> findByEmail(String email);
	ResponseEntity<?> generateOtp();
	//ResponseEntity<?> findByMobileNo1(String mobileNo);
	ResponseEntity<?> fetchByMobileNo(String mobileNo);
	List<RegistrationModel> getAllUsers();

}
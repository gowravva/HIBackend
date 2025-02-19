package com.healthins.serviceint;

import org.springframework.http.ResponseEntity;

import com.healthins.dto.RelationDto;


public interface RelationService {
	    ResponseEntity<?> saveHealthIns(RelationDto relation);
	    ResponseEntity<?> getAllRelations();
		ResponseEntity<?> checkCustomerDetails(RelationDto relation);
		ResponseEntity<?> getAllRelations(String customerId);
		ResponseEntity<?> customerDetails(String customerId);
		ResponseEntity<?> saveHealthins(RelationDto relation);
		
		
	   
	}





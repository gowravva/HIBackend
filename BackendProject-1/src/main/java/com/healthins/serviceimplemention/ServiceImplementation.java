package com.healthins.serviceimplemention;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.healthins.dto.RegistrationDto;
import com.healthins.healthinsurance.otp.Otpservice;
import com.healthins.model.RegistrationModel;
import com.healthins.repo.HealthInsurenceRepository;
import com.healthins.serviceint.HealthInsurenceService;

@Service
public class ServiceImplementation implements HealthInsurenceService {

    @Autowired
    private HealthInsurenceRepository repository;

    @Autowired
    private Otpservice otpService;

    @Override
    public ResponseEntity<?> saveHealthInsurence(RegistrationDto dto) {
        Optional<RegistrationModel> data = repository.findByMobileNoOrEmail(dto.getMobileNo(),dto.getEmail());

        if (data.isEmpty()) {
            // Generate customer ID based on timestamp
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            String customerId = now.format(formatter);

            // Save new registration data
            RegistrationModel insuranceModel = new RegistrationModel();
            insuranceModel.setFullName(dto.getFullName());
            insuranceModel.setMobileNo(dto.getMobileNo());
            insuranceModel.setEmail(dto.getEmail());
            insuranceModel.setGender(dto.getGender());
            insuranceModel.setCustomerId(customerId);
            repository.save(insuranceModel);

            return ResponseEntity.status(HttpStatus.CREATED).body("Data stored successfully. ");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Data already exists for the provided mobile number or email");
        }
    }

    @Override
    public ResponseEntity<?> fetchByMobileNo(String mobileNo) {
        Optional<RegistrationModel> data = repository.findByMobileNo(mobileNo);
        if (data.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data found for the provided mobile number: " + mobileNo);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body( "Data is found. Details: " + data.get());
        }
    }

    @Override
    public ResponseEntity<?> findByMobileNo(String mobileNo) {
        Optional<RegistrationModel> data = repository.findByMobileNo(mobileNo);
        if (data.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No data found for the provided mobile number: " + mobileNo);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(data.get()); // Return the actual data
        }
    }

    @Override
    public ResponseEntity<?> findByEmail(String email) {
        Optional<RegistrationModel> data = repository.findByEmail(email); // Fetch the record by email
        if (data.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No data found for the provided email ID: " + email);
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Data already exists for the provided email ID: " + email + ". Details: " + data.get());
        }
    }
   

    @Override
    public ResponseEntity<?> generateOtp() {
        String otp = otpService.generateOtp();
        return ResponseEntity.status(HttpStatus.OK).body("OTP generated: " + otp);
    }

@Override 
public List<RegistrationModel> getAllUsers() {
    return repository.findAll();
}
}

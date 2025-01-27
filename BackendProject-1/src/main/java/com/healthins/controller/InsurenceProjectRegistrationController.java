package com.healthins.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.healthins.dto.RegistrationDto;
import com.healthins.model.RegistrationModel;
import com.healthins.otp.EmailOtp; // Import your EmailOtp service
import com.healthins.serviceint.HealthInsurenceService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/register")
@CrossOrigin(origins="*")
public class InsurenceProjectRegistrationController {

	
    @Autowired
    private HealthInsurenceService service;

    @Autowired
    private EmailOtp emailService; // Autowire EmailOtp service

    @PostMapping("/data")
    public ResponseEntity<?> post(@RequestBody RegistrationDto health) {
        return service.saveHealthInsurence(health);
    }

    @GetMapping("/get/{mobileNo}")
    public ResponseEntity<?> findByMobileNo(@PathVariable String mobileNo) {
        return service.findByMobileNo(mobileNo);
    }

    @GetMapping("/fetch/{mobileNo}")
    public ResponseEntity<?> fetchByMobileNo(@PathVariable String mobileNo) {
        return service.findByMobileNo(mobileNo);
    }

    @GetMapping("/call/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email) {
        return service.findByEmail(email);
    }

    @GetMapping("/send-otp")
    public String sendOtp(@RequestParam String email) {
        String otp = emailService.generateOTP();
        try {
            emailService.sendOtpEmail(email, otp);
            return "OTP sent to " + email;
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Failed to send OTP. Please try again.";
        }
    }
    
    @GetMapping("/users")
    public List<RegistrationModel> getAllUsers() {
        return service.getAllUsers();
    }
}

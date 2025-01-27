package com.healthins.healthinsurance.otp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

@Service
public class Otpservice {
    public String generateOtp() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mmss");
        return now.format(formatter); // Generates a 4-digit OTP based on current time
    }
}

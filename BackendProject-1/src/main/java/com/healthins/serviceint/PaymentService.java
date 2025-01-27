package com.healthins.serviceint;
import org.springframework.http.ResponseEntity;
import com.healthins.model.PaymentModel;

public interface PaymentService {
	    ResponseEntity<?> savePayment(PaymentModel payment);
	    ResponseEntity<?> getPaymentById(Long id);
		ResponseEntity<?> getAllPayments();
		ResponseEntity<?> updatePayment(Long id, PaymentModel payment);
		void deletePayment(Long id);
		
	}




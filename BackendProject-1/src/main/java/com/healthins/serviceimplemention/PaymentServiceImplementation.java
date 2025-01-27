package com.healthins.serviceimplemention;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.healthins.model.PaymentModel;
import com.healthins.repo.PaymentRepo;
import com.healthins.serviceint.PaymentService;

@Service
public class PaymentServiceImplementation implements PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    @Override
    public ResponseEntity<?> savePayment(PaymentModel payment) {
        PaymentModel savedPayment = paymentRepo.save(payment);
        return ResponseEntity.ok(savedPayment);
    }

    @Override
    public ResponseEntity<?> getPaymentById(Long id) {
        Optional<PaymentModel> payment = paymentRepo.findById(id);
        return payment.map(ResponseEntity::ok)
                      .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
    }

    @Override
    public ResponseEntity<?> getAllPayments() {
        List<PaymentModel> payments = paymentRepo.findAll();
        return ResponseEntity.ok(payments);
    }

    @Override
    public ResponseEntity<?> updatePayment(Long id, PaymentModel payment) {
        PaymentModel existingPayment = paymentRepo.findById(id)
                                                  .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));

        // Update the fields
        existingPayment.setCustomerId(payment.getCustomerId());
        existingPayment.setPaymentId(payment.getPaymentId());
        existingPayment.setPremiumAmount(payment.getPremiumAmount());
        existingPayment.setSumAssuredAmount(payment.getSumAssuredAmount());
        existingPayment.setYear(payment.getYear());
        existingPayment.setDiseaseAmount(payment.getDiseaseAmount());
        existingPayment.setDiscountAmount(payment.getDiscountAmount());

        PaymentModel updatedPayment = paymentRepo.save(existingPayment);
        return ResponseEntity.ok(updatedPayment);
    }

    @Override
    public void deletePayment(Long id) {
        if (!paymentRepo.existsById(id)) {
            throw new RuntimeException("Payment not found with id: " + id);
        }
        paymentRepo.deleteById(id);
    }
}

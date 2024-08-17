package com.example.paymentservice.service;

import com.example.paymentservice.dao.PaymentRepository;
import com.example.paymentservice.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment processPayment(Payment payment) {
        Optional<Payment> existingPayment = paymentRepository.findByIdempotencyKey(payment.getIdempotencyKey());
        if (existingPayment.isPresent()) {
            return existingPayment.get();
        }
        payment.setPaymentTime(LocalDateTime.now());
        payment.setStatus("PAID");
        return paymentRepository.save(payment);
    }

    public Payment getPaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found with ID: " + paymentId));
    }

    public Payment updatePaymentStatus(Long paymentId, String status) {
        Payment payment = getPaymentById(paymentId);
        payment.setStatus(status);
        return paymentRepository.save(payment);
    }

    public void deletePayment(Long paymentId) {
        paymentRepository.deleteById(paymentId);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}

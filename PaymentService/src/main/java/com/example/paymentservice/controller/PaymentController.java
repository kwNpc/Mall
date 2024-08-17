package com.example.paymentservice.controller;

import com.example.paymentservice.dto.PaymentRequest;
import com.example.paymentservice.entity.Payment;
import com.example.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // 创建支付请求
    @PostMapping
    public Payment makePayment(@RequestBody PaymentRequest request) {
        Payment payment = new Payment();
        payment.setOrderId(request.getOrderId());
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setAmount(request.getAmount());
        payment.setIdempotencyKey(request.getIdempotencyKey());
        return paymentService.processPayment(payment);
    }

    // 查询支付状态
    @GetMapping("/{paymentId}")
    public Payment getPayment(@PathVariable Long paymentId) {
        return paymentService.getPaymentById(paymentId);
    }

    // 更新支付状态
    @PutMapping("/{paymentId}")
    public Payment updatePaymentStatus(@PathVariable Long paymentId, @RequestBody Map<String, String> updateRequest) {
        String newStatus = updateRequest.get("status");
        return paymentService.updatePaymentStatus(paymentId, newStatus);
    }

    // 删除支付记录
    @DeleteMapping("/{paymentId}")
    public ResponseEntity<String> deletePayment(@PathVariable Long paymentId) {
        paymentService.deletePayment(paymentId);
        return ResponseEntity.ok("Payment with ID " + paymentId + " has been deleted successfully.");
    }

    // 查询所有支付记录
    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }
}

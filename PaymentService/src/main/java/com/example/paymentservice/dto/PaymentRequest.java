package com.example.paymentservice.dto;

public class PaymentRequest {
    private Long orderId;
    private String paymentMethod;
    private Double amount;
    private String idempotencyKey;

    // Getters and Setters

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public void setIdempotencyKey(String idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
    }

    @Override
    public String toString() {
        return "PaymentRequest{" +
                "orderId=" + orderId +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", amount=" + amount +
                ", idempotencyKey='" + idempotencyKey + '\'' +
                '}';
    }
}

package com.upb.agripos.model.payment;

public interface PaymentMethod {
    boolean process(double amount);
    String getName();
    String getDescription();
}

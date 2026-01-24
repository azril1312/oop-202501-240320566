package com.upb.agripos.model.payment;

public class CashPayment implements PaymentMethod {
    private double amount;

    public CashPayment(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean process(double totalBill) {
        if (amount >= totalBill) {
            return true;
        }
        return false;
    }

    @Override
    public String getName() {
        return "Tunai";
    }

    @Override
    public String getDescription() {
        return "Pembayaran Tunai";
    }

    public double getChange(double totalBill) {
        return amount - totalBill;
    }
}

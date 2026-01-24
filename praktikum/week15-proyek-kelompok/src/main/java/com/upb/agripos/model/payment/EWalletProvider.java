package com.upb.agripos.model.payment;

public enum EWalletProvider {
    GCASH("GCash", 5000),
    GRABPAY("GrabPay", 10000),
    PAYPAL("PayPal", 15000),
    OVO("OVO", 8000),
    GOPAY("GoPay", 12000);
    
    private String displayName;
    private double defaultBalance;
    
    EWalletProvider(String displayName, double defaultBalance) {
        this.displayName = displayName;
        this.defaultBalance = defaultBalance;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public double getDefaultBalance() {
        return defaultBalance;
    }
}

package com.upb.agripos.model.payment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EWalletPayment implements PaymentMethod {
    private EWalletProvider provider;
    private double balance;
    private String accountNumber;
    private List<TransactionHistory> history;
    
    public EWalletPayment(EWalletProvider provider, double balance) {
        this.provider = provider;
        // Saldo unlimited (menggunakan max double)
        this.balance = Double.MAX_VALUE;
        this.accountNumber = generateAccountNumber();
        this.history = new ArrayList<>();
    }
    
    public EWalletPayment(String providerName, double balance) {
        this(EWalletProvider.valueOf(providerName.toUpperCase()), Double.MAX_VALUE);
    }

    @Override
    public boolean process(double totalBill) {
        if (balance >= totalBill) {
            balance -= totalBill;
            // Record transaction history
            history.add(new TransactionHistory(
                generateTransactionId(),
                totalBill,
                "Pembayaran Transaksi",
                LocalDateTime.now(),
                balance
            ));
            return true;
        }
        return false;
    }

    @Override
    public String getName() {
        return "E-Wallet";
    }

    @Override
    public String getDescription() {
        return "Pembayaran via " + provider.getDisplayName();
    }
    
    public EWalletProvider getProvider() {
        return provider;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public List<TransactionHistory> getHistory() {
        return history;
    }
    
    public void topUp(double amount) {
        balance += amount;
        history.add(new TransactionHistory(
            generateTransactionId(),
            amount,
            "Top Up",
            LocalDateTime.now(),
            balance
        ));
    }
    
    private String generateAccountNumber() {
        return provider.toString().charAt(0) + String.format("%08d", (int)(Math.random() * 100000000));
    }
    
    private String generateTransactionId() {
        return provider.toString().substring(0, 2).toUpperCase() + 
               System.currentTimeMillis() % 1000000;
    }
    
    // Inner class untuk history transaksi
    public static class TransactionHistory {
        private String transactionId;
        private double amount;
        private String description;
        private LocalDateTime timestamp;
        private double balanceAfter;
        
        public TransactionHistory(String transactionId, double amount, String description,
                                LocalDateTime timestamp, double balanceAfter) {
            this.transactionId = transactionId;
            this.amount = amount;
            this.description = description;
            this.timestamp = timestamp;
            this.balanceAfter = balanceAfter;
        }
        
        public String getTransactionId() { return transactionId; }
        public double getAmount() { return amount; }
        public String getDescription() { return description; }
        public LocalDateTime getTimestamp() { return timestamp; }
        public double getBalanceAfter() { return balanceAfter; }
        public String getFormattedTimestamp() {
            return timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
    }
}

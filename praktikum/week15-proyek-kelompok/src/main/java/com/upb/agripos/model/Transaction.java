package com.upb.agripos.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private int id;
    private double total;
    private String paymentMethod;
    private LocalDateTime createdAt;
    private List<CartItem> items;
    private double paymentAmount;
    private double change;

    public Transaction(double total) {
        this.total = total;
        this.paymentMethod = "Tunai";
        this.createdAt = LocalDateTime.now();
        this.items = new ArrayList<>();
    }

    public Transaction(int id, double total, String paymentMethod, LocalDateTime createdAt) {
        this.id = id;
        this.total = total;
        this.paymentMethod = paymentMethod;
        this.createdAt = createdAt;
        this.items = new ArrayList<>();
    }

    public int getId() { return id; }
    public double getTotal() { return total; }
    public String getPaymentMethod() { return paymentMethod; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public List<CartItem> getItems() { return items; }
    public double getPaymentAmount() { return paymentAmount; }
    public double getChange() { return change; }

    public void setTotal(double total) { this.total = total; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public void setItems(List<CartItem> items) { this.items = items; }
    public void setPaymentAmount(double paymentAmount) { this.paymentAmount = paymentAmount; }
    public void setChange(double change) { this.change = change; }
    
    public void addItem(CartItem item) {
        this.items.add(item);
    }
    
    public String getFormattedDate() {
        return createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}


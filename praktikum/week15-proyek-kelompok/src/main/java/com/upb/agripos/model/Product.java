package com.upb.agripos.model;

public class Product {
    private int id;
    private String code;
    private String name;
    private String category;
    private double price;
    private int stock;

    public Product(String code, String name, String category, double price, int stock) {
        this.code = code;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    public Product(int id, String code, String name, String category, double price, int stock) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    public int getId() { return id; }
    public String getCode() { return code; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    public void setStock(int stock) { this.stock = stock; }
    public void setPrice(double price) { this.price = price; }
    public void setName(String name) { this.name = name; }
    public void setId(int id) { this.id = id; }
    public void setCode(String code) { this.code = code; }
    public void setCategory(String category) { this.category = category; }

    @Override
    public String toString() {
        return code + " - " + name + " (" + category + ") - Rp" + price;
    }
}
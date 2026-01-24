package com.upb.agripos.service;

import com.upb.agripos.model.Cart;
import com.upb.agripos.model.CartItem;
import com.upb.agripos.model.Product;

public class CartService {
    private Cart cart = new Cart();

    public void add(Product product, int qty) {
        cart.addItem(new CartItem(product, qty));
    }

    public Cart getCart() {
        return cart;
    }
}
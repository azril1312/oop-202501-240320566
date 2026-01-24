package com.upb.agripos.dao;

import com.upb.agripos.model.Product;
import java.util.List;

public interface ProductDAO {

    void insert(Product product);

    List<Product> findAll();

    Product findById(int id);

    void update(Product product);

    void delete(int id);
}
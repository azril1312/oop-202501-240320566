package com.upb.agripos.service;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.dao.ProductDAOImpl;
import com.upb.agripos.model.Product;
import java.util.List;
import java.util.ArrayList;

public class ProductService {
    private ProductDAO dao = new ProductDAOImpl();

    public List<Product> getAllProducts() {
        try {
            // Hanya ambil dari database
            List<Product> dbProducts = dao.findAll();
            return dbProducts.isEmpty() ? new ArrayList<>() : dbProducts;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    public void addProduct(Product product) {
        // Simpan langsung ke database
        dao.insert(product);
        System.out.println("Product added: " + product.getName());
        System.out.println("📊 Total produk di database: " + dao.findAll().size());
    }
    
    public void updateProduct(Product product) {
        // Update langsung di database
        dao.update(product);
        System.out.println("Product updated: " + product.getName());
    }
    
    public void deleteProduct(int id) {
        // Hapus langsung dari database
        dao.delete(id);
        System.out.println("Product deleted with id: " + id);
        System.out.println("📊 Total produk di database: " + dao.findAll().size());
    }
    
    public ProductDAO getDAO() {
        return dao;
    }

    public Product getProductById(int id) {
        try {
            return dao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
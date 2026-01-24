package com.upb.agripos.service;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.dao.ProductDAOImpl;
import com.upb.agripos.model.Product;
import java.util.List;
import java.util.ArrayList;

public class ProductService {
    private ProductDAO dao = new ProductDAOImpl();
    // Shared in-memory storage untuk products yang ditambah di admin
    private static List<Product> memoryStorage = new ArrayList<>();
    private static int nextId = 6;
    
    static {
        // Inisialisasi dengan dummy data
        memoryStorage.add(new Product(1, "P001", "Beras Premium", "Pangan", 50000, 100));
        memoryStorage.add(new Product(2, "P002", "Pupuk Urea", "Pupuk", 30000, 150));
        memoryStorage.add(new Product(3, "P003", "Benih Padi", "Benih", 25000, 80));
        memoryStorage.add(new Product(4, "P004", "Pestisida Organik", "Pestisida", 45000, 60));
        memoryStorage.add(new Product(5, "P005", "Bibit Jagung", "Benih", 35000, 120));
    }

    public List<Product> getAllProducts() {
        try {
            List<Product> dbProducts = dao.findAll();
            List<Product> result = new ArrayList<>(memoryStorage);
            result.addAll(dbProducts);
            return result.isEmpty() ? memoryStorage : result;
        } catch (Exception e) {
            e.printStackTrace();
            return memoryStorage;
        }
    }
    
    public void addProduct(Product product) {
        // Simpan ke database terlebih dahulu
        dao.insert(product);
        
        // Kemudian ke memory storage
        product.setId(nextId++);
        memoryStorage.add(product);
        System.out.println("Product added: " + product.getName());
        System.out.println("📊 Total produk di database: " + dao.findAll().size());
    }
    
    public void updateProduct(Product product) {
        // Update di database
        dao.update(product);
        
        // Update di memory storage
        for (int i = 0; i < memoryStorage.size(); i++) {
            if (memoryStorage.get(i).getId() == product.getId()) {
                memoryStorage.set(i, product);
                System.out.println("Product updated: " + product.getName());
                return;
            }
        }
    }
    
    public void deleteProduct(int id) {
        // Hapus dari database
        dao.delete(id);
        
        // Hapus dari memory storage
        memoryStorage.removeIf(p -> p.getId() == id);
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
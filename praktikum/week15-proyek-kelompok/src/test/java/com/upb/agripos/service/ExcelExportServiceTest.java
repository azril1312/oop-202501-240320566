package com.upb.agripos.service;

import com.upb.agripos.model.Product;
import com.upb.agripos.model.Transaction;
import com.upb.agripos.model.CartItem;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class untuk ExcelExportService
 * Memastikan Excel export berfungsi dengan baik
 */
public class ExcelExportServiceTest {
    
    private ExcelExportService excelExportService = new ExcelExportService();
    
    /**
     * Test export transactions to Excel
     */
    @Test
    public void testExportTransactionsToExcel() {
        // Prepare test data
        List<Transaction> transactions = createSampleTransactions();
        String testFilePath = System.getProperty("java.io.tmpdir") + 
                             File.separator + "test_laporan.xlsx";
        
        // Execute export
        boolean result = excelExportService.exportTransactionsToExcel(
            transactions, 
            testFilePath
        );
        
        // Verify result
        assertTrue(result, "Export should return true");
        assertTrue(Files.exists(Paths.get(testFilePath)), 
                  "File should exist after export");
        
        // Clean up
        new File(testFilePath).delete();
    }
    
    /**
     * Test export summary to Excel
     */
    @Test
    public void testExportSummaryToExcel() {
        // Prepare test data
        List<Transaction> transactions = createSampleTransactions();
        String testFilePath = System.getProperty("java.io.tmpdir") + 
                             File.separator + "test_ringkasan.xlsx";
        
        // Execute export
        boolean result = excelExportService.exportSummaryToExcel(
            transactions, 
            testFilePath
        );
        
        // Verify result
        assertTrue(result, "Summary export should return true");
        assertTrue(Files.exists(Paths.get(testFilePath)), 
                  "Summary file should exist after export");
        
        // Clean up
        new File(testFilePath).delete();
    }
    
    /**
     * Helper method untuk membuat sample transaction data
     */
    private List<Transaction> createSampleTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        
        // Transaction 1 - Tunai
        Transaction trans1 = new Transaction(150000);
        trans1.setPaymentMethod("Tunai");
        
        Product product1 = new Product("PROD-001", "Beras Premium", "Bahan Pangan", 75000, 50);
        CartItem item1 = new CartItem(product1, 2);
        trans1.addItem(item1);
        
        transactions.add(trans1);
        
        // Transaction 2 - E-Wallet
        Transaction trans2 = new Transaction(100000);
        trans2.setPaymentMethod("E-Wallet");
        
        Product product2 = new Product("PROD-002", "Pupuk Urea", "Pupuk", 50000, 100);
        CartItem item2 = new CartItem(product2, 2);
        trans2.addItem(item2);
        
        transactions.add(trans2);
        
        // Transaction 3 - Tunai
        Transaction trans3 = new Transaction(200000);
        trans3.setPaymentMethod("Tunai");
        
        Product product3 = new Product("PROD-003", "Benih Jagung", "Benih", 100000, 30);
        CartItem item3 = new CartItem(product3, 2);
        trans3.addItem(item3);
        
        transactions.add(trans3);
        
        return transactions;
    }
}

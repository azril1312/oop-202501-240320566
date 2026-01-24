package com.upb.agripos.service;

import com.upb.agripos.dao.TransactionDAO;
import com.upb.agripos.dao.TransactionDAOImpl;
import com.upb.agripos.model.Transaction;
import com.upb.agripos.model.CartItem;
import java.util.ArrayList;
import java.util.List;

public class TransactionService {
    private TransactionDAO dao = new TransactionDAOImpl();
    // Static storage untuk history transaksi
    private static List<Transaction> transactionHistory = new ArrayList<>();

    public void saveTransaction(Transaction transaction) {
        try {
            dao.save(transaction);
            // Tambah ke history (in-memory)
            transactionHistory.add(transaction);
            System.out.println("Transaksi berhasil disimpan: Rp " + transaction.getTotal());
        } catch (Exception e) {
            System.out.println("Gagal menyimpan transaksi ke database");
            e.printStackTrace();
        }
    }

    public Transaction createTransaction(double total, String paymentMethod) {
        Transaction transaction = new Transaction(total);
        transaction.setPaymentMethod(paymentMethod);
        return transaction;
    }
    
    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }
    
    public void addItemsToTransaction(Transaction transaction, List<CartItem> items) {
        for (CartItem item : items) {
            transaction.addItem(item);
        }
    }
}

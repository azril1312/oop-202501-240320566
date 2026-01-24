package com.upb.agripos.dao;

import com.upb.agripos.config.DatabaseConfig;
import com.upb.agripos.model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TransactionDAOImpl implements TransactionDAO {

    @Override
    public void save(Transaction transaction) {

        String sql = """
            INSERT INTO transactions(total, created_at)
            VALUES(?, NOW())
        """;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, transaction.getTotal());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
package com.upb.agripos.view;

import com.upb.agripos.model.User;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashboardView extends VBox {
    
    public DashboardView(Stage stage, User user) {
        setSpacing(10);
        setPrefWidth(400);
        setPrefHeight(300);

        Button btnLogout = new Button("Logout");
        btnLogout.setStyle("-fx-padding: 10; -fx-font-size: 14;");
        btnLogout.setOnAction(e -> 
            stage.setScene(new Scene(new LoginView(stage), 400, 300))
        );

        // Menu berdasarkan role
        if (user.getRole().equals("kasir")) {
            // Menu untuk Kasir
            Button btnTransaksi = new Button("Transaksi Baru");
            btnTransaksi.setStyle("-fx-padding: 10; -fx-font-size: 14;");
            btnTransaksi.setOnAction(e ->
                stage.setScene(new Scene(new KasirView(stage, user), 600, 500))
            );

            Button btnLaporanKasir = new Button("Laporan Kasirku");
            btnLaporanKasir.setStyle("-fx-padding: 10; -fx-font-size: 14;");
            btnLaporanKasir.setOnAction(e ->
                stage.setScene(new Scene(new ReportView(stage, user), 600, 500))
            );

            getChildren().addAll(
                new javafx.scene.control.Label("Dashboard Kasir - " + user.getUsername()),
                btnTransaksi,
                btnLaporanKasir,
                btnLogout
            );
        } else if (user.getRole().equals("admin")) {
            // Menu untuk Admin
            Button btnManajemenProduk = new Button("Manajemen Produk");
            btnManajemenProduk.setStyle("-fx-padding: 10; -fx-font-size: 14;");
            btnManajemenProduk.setOnAction(e ->
                stage.setScene(new Scene(new ProductManagementView(stage, user), 800, 600))
            );

            Button btnLaporanAdmin = new Button("Laporan Penjualan");
            btnLaporanAdmin.setStyle("-fx-padding: 10; -fx-font-size: 14;");
            btnLaporanAdmin.setOnAction(e ->
                stage.setScene(new Scene(new ReportView(stage, user), 800, 600))
            );

            getChildren().addAll(
                new javafx.scene.control.Label("Dashboard Admin - " + user.getUsername()),
                btnManajemenProduk,
                btnLaporanAdmin,
                btnLogout
            );
        }
    }
}

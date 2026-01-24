package com.upb.agripos.view;

import com.upb.agripos.model.CartItem;
import com.upb.agripos.model.User;
import com.upb.agripos.model.Transaction;
import com.upb.agripos.service.CartService;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StrukView extends VBox {

    public StrukView(Stage stage, CartService cs, User user) {
        this(stage, cs, user, null);
    }
    
    public StrukView(Stage stage, CartService cs, User user, Transaction transaction) {
        setSpacing(10);
        setStyle("-fx-padding: 10;");

        TextArea area = new TextArea();
        area.setEditable(false);
        area.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 11;");

        StringBuilder sb = new StringBuilder();
        sb.append("═══════════════════════════════════\n");
        sb.append("       STRUK PEMBAYARAN AGRIPOS\n");
        sb.append("═══════════════════════════════════\n");
        
        if (transaction != null) {
            sb.append("Tanggal  : ").append(transaction.getFormattedDate()).append("\n");
            sb.append("Metode   : ").append(transaction.getPaymentMethod()).append("\n");
            sb.append("───────────────────────────────────\n");
        }
        
        sb.append("\nDETAIL PEMBELIAN:\n");
        sb.append("───────────────────────────────────\n");
        sb.append("No | Barang            | Qty | Harga    | Subtotal\n");
        sb.append("───────────────────────────────────\n");
        
        int itemNo = 1;
        for (CartItem i : cs.getCart().getItems()) {
            sb.append(String.format("%d | %-17s | %3d | %8.0f | %10.0f\n", 
                itemNo++,
                i.getProduct().getName(), 
                i.getQty(),
                i.getProduct().getPrice(),
                i.getSubtotal()));
        }
        
        sb.append("───────────────────────────────────\n");
        sb.append(String.format("TOTAL             : Rp %10.0f\n", cs.getCart().getTotal()));
        
        if (transaction != null && transaction.getChange() > 0) {
            sb.append(String.format("Dibayar           : Rp %10.0f\n", transaction.getPaymentAmount()));
            sb.append(String.format("KEMBALIAN         : Rp %10.0f\n", transaction.getChange()));
        } else if (transaction != null) {
            sb.append(String.format("Dibayar           : Rp %10.0f\n", transaction.getPaymentAmount()));
        }
        
        sb.append("═══════════════════════════════════\n");
        sb.append("\n    Terima kasih atas pembelian Anda!\n");
        sb.append("        Kunjungi kembali kami\n");
        sb.append("\n═══════════════════════════════════");

        area.setText(sb.toString());
        area.setPrefHeight(400);

        Button selesai = new Button("Selesai");
        selesai.setStyle("-fx-padding: 10; -fx-font-size: 14;");
        selesai.setOnAction(e ->
            stage.setScene(new Scene(new DashboardView(stage, user), 400, 300))
        );

        getChildren().addAll(area, selesai);
    }
}
package com.upb.agripos.view;

import com.upb.agripos.model.User;
import com.upb.agripos.model.Product;
import com.upb.agripos.model.Transaction;
import com.upb.agripos.model.payment.CashPayment;
import com.upb.agripos.model.payment.EWalletPayment;
import com.upb.agripos.model.payment.PaymentMethod;
import com.upb.agripos.service.CartService;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.service.TransactionService;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class KasirView extends VBox {

    public KasirView(Stage stage, User user) {
        setSpacing(10);

        ProductService ps = new ProductService();
        CartService cs = new CartService();
        TransactionService ts = new TransactionService();
        
        // Bagian 2: Tampil Keranjang (deklarasi awal)
        TextArea cartDisplay = new TextArea();
        cartDisplay.setEditable(false);
        cartDisplay.setPrefHeight(150);

        // Bagian 1: Pilih Produk
        ComboBox<Product> combo = new ComboBox<>();
        combo.getItems().addAll(ps.getAllProducts());
        combo.setPrefWidth(250);

        TextField qty = new TextField();
        qty.setPromptText("Qty");
        qty.setPrefWidth(80);

        Button add = new Button("Tambah ke Keranjang");
        add.setOnAction(e -> {
            try {
                if (combo.getValue() != null && !qty.getText().isEmpty()) {
                    cs.add(combo.getValue(), Integer.parseInt(qty.getText()));
                    qty.clear();
                    updateCartDisplay(cartDisplay, cs);
                }
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Jumlah harus angka");
            }
        });
        
        Button refreshProducts = new Button("Refresh Produk");
        refreshProducts.setOnAction(e -> {
            combo.getItems().clear();
            combo.getItems().addAll(ps.getAllProducts());
            System.out.println("Product list refreshed: " + ps.getAllProducts().size() + " items");
        });

        HBox selectBox = new HBox(5, combo, qty, add, refreshProducts);
        selectBox.setStyle("-fx-padding: 10;");

        // Bagian 3: Pilih Metode Pembayaran
        ComboBox<String> paymentMethod = new ComboBox<>();
        paymentMethod.getItems().addAll("Tunai", "E-Wallet");
        paymentMethod.setValue("Tunai");
        paymentMethod.setPrefWidth(150);

        TextField paymentAmount = new TextField();
        paymentAmount.setPromptText("Jumlah Pembayaran");
        paymentAmount.setPrefWidth(150);

        Button bayar = new Button("Proses Pembayaran");
        bayar.setOnAction(e -> {
            try {
                double total = cs.getCart().getTotal();
                String method = paymentMethod.getValue();
                
                if ("Tunai".equals(method)) {
                    double amount = Double.parseDouble(paymentAmount.getText());
                    PaymentMethod payment = new CashPayment(amount);
                    if (payment.process(total)) {
                        Transaction trans = ts.createTransaction(total, method);
                        trans.setItems(cs.getCart().getItems());
                        trans.setPaymentAmount(amount);
                        trans.setChange(amount - total);
                        ts.saveTransaction(trans);
                        stage.setScene(new Scene(new StrukView(stage, cs, user, trans), 400, 500));
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Uang tidak cukup");
                    }
                } else {
                    // Buka E-Wallet Payment dialog
                    Stage ewalletStage = new Stage();
                    ewalletStage.setTitle("Pembayaran E-Wallet");
                    ewalletStage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
                    ewalletStage.initOwner(stage);
                    
                    EWalletPaymentView ewalletView = new EWalletPaymentView(total, stage);
                    ewalletStage.setScene(new Scene(ewalletView, 500, 450));
                    ewalletStage.showAndWait();
                    
                    if (ewalletView.isConfirmed()) {
                        Transaction trans = ts.createTransaction(total, method);
                        trans.setItems(cs.getCart().getItems());
                        trans.setPaymentAmount(total);
                        trans.setChange(0); // No change for e-wallet
                        ts.saveTransaction(trans);
                        stage.setScene(new Scene(new StrukView(stage, cs, user, trans), 400, 500));
                    }
                }
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Format nominal tidak valid!");
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Error: " + ex.getMessage());
            }
        });

        HBox paymentBox = new HBox(5, new Label("Metode:"), paymentMethod,
            new Label("Jumlah:"), paymentAmount, bayar);
        paymentBox.setStyle("-fx-padding: 10;");

        Button btnKembali = new Button("Kembali");
        btnKembali.setOnAction(e ->
            stage.setScene(new Scene(new DashboardView(stage, user), 400, 300))
        );

        getChildren().addAll(selectBox, cartDisplay, paymentBox, btnKembali);
        updateCartDisplay(cartDisplay, cs);
    }

    private void updateCartDisplay(TextArea area, CartService cs) {
        StringBuilder sb = new StringBuilder("=== KERANJANG ===\n");
        for (var item : cs.getCart().getItems()) {
            sb.append(item.getProduct().getName()).append(" x ").append(item.getQty())
              .append(" = Rp").append(item.getSubtotal()).append("\n");
        }
        sb.append("TOTAL: Rp").append(cs.getCart().getTotal());
        area.setText(sb.toString());
    }

    private void showAlert(Alert.AlertType type, String message) {
        new Alert(type, message).show();
    }
}
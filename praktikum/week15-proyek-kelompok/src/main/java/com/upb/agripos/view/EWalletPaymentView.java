package com.upb.agripos.view;

import com.upb.agripos.model.payment.EWalletPayment;
import com.upb.agripos.model.payment.EWalletProvider;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EWalletPaymentView extends VBox {
    private EWalletPayment selectedPayment;
    private boolean confirmed = false;
    
    public EWalletPaymentView(double totalBill, Stage primaryStage) {
        setSpacing(10);
        setStyle("-fx-padding: 15;");
        setPrefWidth(500);
        setPrefHeight(400);
        
        // Title
        Label title = new Label("PEMBAYARAN E-WALLET");
        title.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");
        
        // Total Bill
        Label billLabel = new Label(String.format("Total Pembayaran: Rp %,.0f", totalBill));
        billLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: #2e7d32;");
        
        // Provider Selection
        ComboBox<EWalletProvider> providerCombo = new ComboBox<>();
        providerCombo.getItems().addAll(EWalletProvider.values());
        providerCombo.setValue(EWalletProvider.GCASH);
        providerCombo.setPrefWidth(250);
        
        Label providerLabel = new Label("Pilih Provider:");
        HBox providerBox = new HBox(10, providerLabel, providerCombo);
        
        // Account Info Display
        TextArea accountInfo = new TextArea();
        accountInfo.setEditable(false);
        accountInfo.setPrefHeight(150);
        accountInfo.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 11;");
        
        // Amount Input
        TextField amountField = new TextField();
        amountField.setPromptText("Nominal Pembayaran");
        amountField.setPrefWidth(250);
        amountField.setText(String.valueOf((long)totalBill));
        
        Label amountLabel = new Label("Nominal:");
        HBox amountBox = new HBox(10, amountLabel, amountField);
        
        // Update account info when provider changes
        providerCombo.setOnAction(e -> {
            EWalletProvider provider = providerCombo.getValue();
            EWalletPayment payment = new EWalletPayment(provider, provider.getDefaultBalance());
            selectedPayment = payment;
            
            StringBuilder sb = new StringBuilder();
            sb.append("═════════════════════════════════════\n");
            sb.append(String.format("  Provider        : %s\n", provider.getDisplayName()));
            sb.append(String.format("  Nomor Rekening  : %s\n", payment.getAccountNumber()));
            sb.append(String.format("  Saldo Tersedia  : UNLIMITED\n"));
            sb.append("─────────────────────────────────────\n");
            sb.append(String.format("  Total Tagihan   : Rp %,.0f\n", totalBill));
            sb.append("═════════════════════════════════════");
            accountInfo.setText(sb.toString());
        });
        
        // Trigger initial display
        providerCombo.fireEvent(new javafx.event.ActionEvent());
        
        // Payment Method Details
        Label methodTitle = new Label("Detail Metode Pembayaran:");
        methodTitle.setStyle("-fx-font-weight: bold;");
        
        // Buttons
        Button confirmBtn = new Button("Konfirmasi Pembayaran");
        confirmBtn.setPrefWidth(200);
        confirmBtn.setStyle("-fx-padding: 10; -fx-font-size: 12;");
        confirmBtn.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (selectedPayment.process(totalBill)) {
                    confirmed = true;
                    showSuccessAlert(selectedPayment, totalBill);
                    ((Stage)this.getScene().getWindow()).close();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Saldo tidak cukup untuk transaksi ini!");
                }
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Masukkan nominal yang valid!");
            }
        });
        
        Button cancelBtn = new Button("Batal");
        cancelBtn.setPrefWidth(200);
        cancelBtn.setStyle("-fx-padding: 10; -fx-font-size: 12;");
        cancelBtn.setOnAction(e -> {
            confirmed = false;
            ((Stage)this.getScene().getWindow()).close();
        });
        
        HBox buttonBox = new HBox(10, confirmBtn, cancelBtn);
        buttonBox.setStyle("-fx-padding: 10;");
        
        // Features info
        Label featuresLabel = new Label("Fitur:");
        featuresLabel.setStyle("-fx-font-weight: bold;");
        TextArea features = new TextArea();
        features.setEditable(false);
        features.setPrefHeight(80);
        features.setText("✓ Pembayaran instant\n✓ Aman & terenkripsi\n✓ Gratis transaksi\n✓ Riwayat transaksi tercatat");
        features.setStyle("-fx-control-inner-background: #f0f0f0; -fx-font-size: 11;");
        
        getChildren().addAll(
            title,
            billLabel,
            new Separator(),
            providerBox,
            methodTitle,
            accountInfo,
            amountBox,
            featuresLabel,
            features,
            new Separator(),
            buttonBox
        );
    }
    
    public EWalletPayment getSelectedPayment() {
        return selectedPayment;
    }
    
    public boolean isConfirmed() {
        return confirmed;
    }
    
    private void showSuccessAlert(EWalletPayment payment, double amount) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pembayaran Berhasil");
        alert.setHeaderText("Transaksi E-Wallet Sukses");
        
        StringBuilder content = new StringBuilder();
        content.append("Provider: ").append(payment.getProvider().getDisplayName()).append("\n");
        content.append(String.format("Jumlah: Rp %,.0f\n", amount));
        content.append(String.format("Saldo Sisa: Rp %,.0f\n", payment.getBalance()));
        content.append("\nTerima kasih telah berbelanja!");
        
        alert.setContentText(content.toString());
        alert.showAndWait();
    }
    
    private void showAlert(Alert.AlertType type, String message) {
        new Alert(type, message).show();
    }
}

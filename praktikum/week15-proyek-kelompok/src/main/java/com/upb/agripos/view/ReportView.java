package com.upb.agripos.view;

import com.upb.agripos.model.User;
import com.upb.agripos.model.Transaction;
import com.upb.agripos.model.CartItem;
import com.upb.agripos.service.TransactionService;
import com.upb.agripos.service.ExcelExportService;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.awt.Desktop;
import javafx.scene.Parent;

public class ReportView extends VBox {
    private TransactionService ts = new TransactionService();
    private ExcelExportService excelExportService = new ExcelExportService();
    
    public ReportView(Stage stage, User user) {
        setSpacing(10);
        setPrefWidth(900);
        setPrefHeight(700);
        setStyle("-fx-padding: 10;");
        
        // Judul
        Label title = new Label("LAPORAN PENJUALAN DETAIL");
        title.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
        
        // Filter tanggal
        DatePicker dpStart = new DatePicker(LocalDate.now().minusDays(7));
        DatePicker dpEnd = new DatePicker(LocalDate.now());
        ComboBox<String> cbPaymentFilter = new ComboBox<>();
        cbPaymentFilter.getItems().addAll("Semua", "Tunai", "E-Wallet");
        cbPaymentFilter.setValue("Semua");
        
        Button btnFilter = new Button("Tampilkan");
        btnFilter.setStyle("-fx-padding: 8;");
        
        Button btnRefresh = new Button("Refresh");
        btnRefresh.setStyle("-fx-padding: 8;");
        
        HBox filterBox = new HBox(10,
            new Label("Dari:"), dpStart,
            new Label("Sampai:"), dpEnd,
            new Label("Metode:"), cbPaymentFilter,
            btnFilter,
            btnRefresh
        );
        filterBox.setStyle("-fx-padding: 10; -fx-border-color: #cccccc; -fx-border-radius: 5;");
        
        // Tabel Laporan Detail
        TableView<TransactionReport> table = new TableView<>();
        table.setPrefHeight(300);
        
        TableColumn<TransactionReport, Integer> colNo = new TableColumn<>("No");
        colNo.setCellFactory(column -> new TableCell<TransactionReport, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow() == null) {
                    setText(null);
                } else {
                    setText(String.valueOf(getTableRow().getIndex() + 1));
                }
            }
        });
        colNo.setPrefWidth(40);
        
        TableColumn<TransactionReport, String> colDate = new TableColumn<>("Tanggal");
        colDate.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDate()));
        colDate.setPrefWidth(100);
        
        TableColumn<TransactionReport, String> colPayment = new TableColumn<>("Metode Pembayaran");
        colPayment.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getPaymentMethod()));
        colPayment.setPrefWidth(120);
        
        TableColumn<TransactionReport, String> colDetails = new TableColumn<>("Detail");
        colDetails.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDetails()));
        colDetails.setPrefWidth(200);
        
        TableColumn<TransactionReport, Double> colAmount = new TableColumn<>("Jumlah (Rp)");
        colAmount.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getAmount()).asObject());
        colAmount.setPrefWidth(120);
        colAmount.setStyle("-fx-alignment: CENTER-RIGHT;");
        
        table.getColumns().addAll(colNo, colDate, colPayment, colDetails, colAmount);
        
        // Load real transactions from service
        loadRealTransactions(table);
        
        btnRefresh.setOnAction(e -> {
            table.getItems().clear();
            loadRealTransactions(table);
            showAlert(Alert.AlertType.INFORMATION, "Data laporan diperbarui!");
        });
        
        btnFilter.setOnAction(e -> {
            table.getItems().clear();
            applyFilter(table, dpStart.getValue(), dpEnd.getValue(), cbPaymentFilter.getValue());
            String filterPeriod = "Periode: " + dpStart.getValue() + " s/d " + dpEnd.getValue();
            String filterMethod = "Metode: " + cbPaymentFilter.getValue();
            showAlert(Alert.AlertType.INFORMATION, filterPeriod + "\n" + filterMethod);
        });
        
        // Summary Panel
        VBox summaryBox = createSummaryPanel(table);
        
        // Detail View Section
        TextArea detailView = new TextArea();
        detailView.setEditable(false);
        detailView.setPrefHeight(150);
        detailView.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 11;");
        
        table.setOnMouseClicked(e -> {
            TransactionReport selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                detailView.setText(generateDetailReport(selected));
            }
        });
        
        Button btnExport = new Button("Export Laporan");
        btnExport.setStyle("-fx-padding: 10;");
        btnExport.setOnAction(e -> handleExportExcel(table));
        
        Button btnBack = new Button("Kembali");
        btnBack.setStyle("-fx-padding: 10;");
        btnBack.setOnAction(e ->
            stage.setScene(new Scene(new DashboardView(stage, user), 400, 300))
        );
        
        HBox buttonBox = new HBox(10, btnExport, btnBack);
        buttonBox.setStyle("-fx-padding: 10;");
        
        getChildren().addAll(
            title,
            filterBox,
            new Label("Data Penjualan Detail:"),
            table,
            new Label("Ringkasan Transaksi:"),
            summaryBox,
            new Label("Detail Transaksi (Klik untuk detail):"),
            detailView,
            buttonBox
        );
    }
    
    private void loadRealTransactions(TableView<TransactionReport> table) {
        // Load dari TransactionService history
        java.util.List<Transaction> transactions = ts.getTransactionHistory();
        
        if (transactions.isEmpty()) {
            // Fallback ke dummy data jika belum ada transaksi
            loadDummyData(table);
            return;
        }
        
        for (Transaction trans : transactions) {
            StringBuilder details = new StringBuilder();
            int itemNo = 1;
            for (CartItem item : trans.getItems()) {
                if (details.length() > 0) details.append(" | ");
                details.append(itemNo++).append(". ").append(item.getProduct().getName()).append(" x").append(item.getQty());
            }
            
            String detailStr = details.length() > 0 ? details.toString() : "Transaksi";
            table.getItems().add(new TransactionReport(
                trans.getFormattedDate(),
                trans.getPaymentMethod(),
                trans.getTotal(),
                detailStr,
                trans
            ));
        }
    }
    
    private void loadDummyData(TableView<TransactionReport> table) {
        table.getItems().addAll(
            new TransactionReport(LocalDate.now().toString(), "Tunai", 150000, "Beras 2kg + Pupuk"),
            new TransactionReport(LocalDate.now().toString(), "E-Wallet", 75000, "Benih Padi 1kg"),
            new TransactionReport(LocalDate.now().minusDays(1).toString(), "Tunai", 200000, "Pestisida + Pupuk Urea"),
            new TransactionReport(LocalDate.now().minusDays(1).toString(), "E-Wallet", 50000, "Bibit Jagung 2kg"),
            new TransactionReport(LocalDate.now().minusDays(2).toString(), "Tunai", 100000, "Beras Premium 5kg")
        );
    }
    
    private void applyFilter(TableView<TransactionReport> table, LocalDate startDate, LocalDate endDate, String paymentMethod) {
        // Load dari TransactionService history
        java.util.List<Transaction> transactions = ts.getTransactionHistory();
        
        if (transactions.isEmpty()) {
            // Fallback ke dummy data jika belum ada transaksi
            loadDummyData(table);
            // Filter dummy data
            filterTableData(table, startDate, endDate, paymentMethod);
            return;
        }
        
        for (Transaction trans : transactions) {
            StringBuilder details = new StringBuilder();
            int itemNo = 1;
            for (CartItem item : trans.getItems()) {
                if (details.length() > 0) details.append(" | ");
                details.append(itemNo++).append(". ").append(item.getProduct().getName()).append(" x").append(item.getQty());
            }
            
            String detailStr = details.length() > 0 ? details.toString() : "Transaksi";
            table.getItems().add(new TransactionReport(
                trans.getFormattedDate(),
                trans.getPaymentMethod(),
                trans.getTotal(),
                detailStr,
                trans
            ));
        }
        
        // Filter hasil berdasarkan payment method
        filterTableData(table, startDate, endDate, paymentMethod);
    }
    
    private void filterTableData(TableView<TransactionReport> table, LocalDate startDate, LocalDate endDate, String paymentMethod) {
        // Filter hanya berdasarkan payment method
        // Jika "Semua" dipilih, tampilkan semua
        if (!paymentMethod.equals("Semua")) {
            table.getItems().removeIf(tr -> !tr.getPaymentMethod().equals(paymentMethod));
        }
    }
    
    private VBox createSummaryPanel(TableView<TransactionReport> table) {
        VBox summaryBox = new VBox(5);
        summaryBox.setStyle("-fx-border-color: #cccccc; -fx-border-radius: 5; -fx-padding: 10; -fx-background-color: #f9f9f9;");
        
        Label totalLabel = new Label();
        Label tunaiLabel = new Label();
        Label ewalletLabel = new Label();
        Label countLabel = new Label();
        
        table.getItems().addListener((javafx.collections.ListChangeListener.Change<? extends TransactionReport> c) -> {
            updateSummary(table, totalLabel, tunaiLabel, ewalletLabel, countLabel);
        });
        
        updateSummary(table, totalLabel, tunaiLabel, ewalletLabel, countLabel);
        
        summaryBox.getChildren().addAll(totalLabel, tunaiLabel, ewalletLabel, countLabel);
        return summaryBox;
    }
    
    private void updateSummary(TableView<TransactionReport> table, Label totalLabel, Label tunaiLabel, 
                              Label ewalletLabel, Label countLabel) {
        double total = 0;
        double tunai = 0;
        double ewallet = 0;
        int count = 0;
        
        for (TransactionReport tr : table.getItems()) {
            total += tr.getAmount();
            count++;
            if ("Tunai".equals(tr.getPaymentMethod())) {
                tunai += tr.getAmount();
            } else {
                ewallet += tr.getAmount();
            }
        }
        
        totalLabel.setText(String.format("Total Penjualan: Rp %,.0f", total));
        totalLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 13;");
        
        tunaiLabel.setText(String.format("├─ Tunai: Rp %,.0f (%.1f%%)", tunai, count > 0 ? (tunai/total)*100 : 0));
        ewalletLabel.setText(String.format("└─ E-Wallet: Rp %,.0f (%.1f%%)", ewallet, count > 0 ? (ewallet/total)*100 : 0));
        countLabel.setText(String.format("Total Transaksi: %d", count));
    }
    
    private String generateDetailReport(TransactionReport tr) {
        StringBuilder sb = new StringBuilder();
        sb.append("═══════════════════════════════════════════════════════\n");
        sb.append("                  DETAIL TRANSAKSI\n");
        sb.append("═══════════════════════════════════════════════════════\n");
        sb.append(String.format("Tanggal       : %s\n", tr.getDate()));
        sb.append(String.format("Metode        : %s\n", tr.getPaymentMethod()));
        sb.append("───────────────────────────────────────────────────────\n");
        sb.append("DETAIL BARANG:\n");
        sb.append("───────────────────────────────────────────────────────\n");
        
        // Jika ada Transaction object dengan detail items
        if (tr.getTransaction() != null && !tr.getTransaction().getItems().isEmpty()) {
            int itemNo = 1;
            for (CartItem item : tr.getTransaction().getItems()) {
                sb.append(String.format("%d. %-25s x %3d @ Rp %8.0f\n", 
                    itemNo++,
                    item.getProduct().getName(),
                    item.getQty(),
                    item.getProduct().getPrice()));
                sb.append(String.format("   Subtotal: Rp %10.0f\n", item.getSubtotal()));
            }
        } else {
            // Fallback ke detail string
            sb.append(String.format("Detail        : %s\n", tr.getDetails()));
        }
        
        sb.append("───────────────────────────────────────────────────────\n");
        sb.append(String.format("Total Jumlah  : Rp %,.0f\n", tr.getAmount()));
        sb.append("───────────────────────────────────────────────────────\n");
        sb.append("Status        : SELESAI\n");
        sb.append("═══════════════════════════════════════════════════════");
        return sb.toString();
    }
    
    private void showAlert(Alert.AlertType type, String message) {
        new Alert(type, message).show();
    }
    
    // Inner class untuk data laporan
    public static class TransactionReport {
        private String date;
        private String paymentMethod;
        private double amount;
        private String details;
        private Transaction transaction;
        
        public TransactionReport(String date, String paymentMethod, double amount, String details) {
            this.date = date;
            this.paymentMethod = paymentMethod;
            this.amount = amount;
            this.details = details;
            this.transaction = null;
        }
        
        public TransactionReport(String date, String paymentMethod, double amount, String details, Transaction transaction) {
            this.date = date;
            this.paymentMethod = paymentMethod;
            this.amount = amount;
            this.details = details;
            this.transaction = transaction;
        }
        
        public String getDate() { return date; }
        public String getPaymentMethod() { return paymentMethod; }
        public double getAmount() { return amount; }
        public String getDetails() { return details; }
        public Transaction getTransaction() { return transaction; }
    }
    
    /**
     * Handle Excel export dengan file chooser dialog
     */
    private void handleExportExcel(TableView<TransactionReport> table) {
        if (table.getItems().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Tidak ada data untuk di-export!");
            return;
        }
        
        // Create file chooser dialog
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Simpan Laporan Penjualan");
        fileChooser.setInitialFileName("Laporan_Penjualan_" + LocalDate.now() + ".xlsx");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Excel Files (*.xlsx)", "*.xlsx"),
            new FileChooser.ExtensionFilter("All Files (*.*)", "*.*")
        );
        
        // Set initial directory to Documents
        String userHome = System.getProperty("user.home");
        fileChooser.setInitialDirectory(new File(userHome + "\\Documents"));
        
        // Show save dialog
        Window window = this.getScene() != null ? this.getScene().getWindow() : null;
        File selectedFile = fileChooser.showSaveDialog(window);
        
        if (selectedFile != null) {
            // Convert table data to transactions
            java.util.List<Transaction> transactionsToExport = new java.util.ArrayList<>();
            for (TransactionReport tr : table.getItems()) {
                if (tr.getTransaction() != null) {
                    transactionsToExport.add(tr.getTransaction());
                }
            }
            
            // If no transactions object available, still export the table data
            if (transactionsToExport.isEmpty()) {
                // Create dummy transactions from report data (fallback)
                for (TransactionReport tr : table.getItems()) {
                    Transaction trans = new Transaction(tr.getAmount());
                    trans.setPaymentMethod(tr.getPaymentMethod());
                    transactionsToExport.add(trans);
                }
            }
            
            // Perform export
            boolean success = excelExportService.exportTransactionsToExcel(
                transactionsToExport, 
                selectedFile.getAbsolutePath()
            );
            
            if (success) {
                showAlert(Alert.AlertType.INFORMATION, 
                    "Laporan berhasil di-export!\nFile: " + selectedFile.getName() + 
                    "\nLokasi: " + selectedFile.getAbsolutePath());
                
                // Optionally open the file
                try {
                    Desktop.getDesktop().open(selectedFile);
                } catch (Exception ex) {
                    // File saved but couldn't auto-open
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "Gagal mengexport laporan!");
            }
        }
    }
}

package com.upb.agripos.view;

import com.upb.agripos.model.Product;
import com.upb.agripos.model.User;
import com.upb.agripos.service.ProductService;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProductManagementView extends VBox {
    private Product selectedProduct = null;
    
    public ProductManagementView(Stage stage, User user) {
        setSpacing(10);
        setPrefWidth(900);
        setPrefHeight(700);
        setStyle("-fx-padding: 10;");
        
        ProductService ps = new ProductService();
        
        // Form Tambah/Edit Produk
        Label formTitle = new Label("FORM PRODUK");
        formTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
        
        TextField tfCode = new TextField();
        tfCode.setPromptText("Kode Produk");
        tfCode.setPrefWidth(100);
        
        TextField tfName = new TextField();
        tfName.setPromptText("Nama Produk");
        tfName.setPrefWidth(120);
        
        TextField tfCategory = new TextField();
        tfCategory.setPromptText("Kategori");
        tfCategory.setPrefWidth(100);
        
        TextField tfPrice = new TextField();
        tfPrice.setPromptText("Harga");
        tfPrice.setPrefWidth(80);
        
        TextField tfStock = new TextField();
        tfStock.setPromptText("Stok");
        tfStock.setPrefWidth(60);
        
        // Tabel Produk
        Label tableTitle = new Label("DAFTAR PRODUK");
        tableTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
        
        TableView<Product> table = new TableView<>();
        table.setPrefHeight(350);
        
        TableColumn<Product, String> colCode = new TableColumn<>("Kode");
        colCode.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCode()));
        colCode.setPrefWidth(80);
        
        TableColumn<Product, String> colName = new TableColumn<>("Nama");
        colName.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getName()));
        colName.setPrefWidth(150);
        
        TableColumn<Product, String> colCategory = new TableColumn<>("Kategori");
        colCategory.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCategory()));
        colCategory.setPrefWidth(100);
        
        TableColumn<Product, Double> colPrice = new TableColumn<>("Harga");
        colPrice.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
        colPrice.setPrefWidth(100);
        
        TableColumn<Product, Integer> colStock = new TableColumn<>("Stok");
        colStock.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getStock()).asObject());
        colStock.setPrefWidth(80);
        
        table.getColumns().addAll(colCode, colName, colCategory, colPrice, colStock);
        
        // Load data awal
        loadTableData(table, ps);
        
        // Event ketika produk di-klik di tabel
        table.setOnMouseClicked(e -> {
            Product selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                selectedProduct = selected;
                tfCode.setText(selected.getCode());
                tfName.setText(selected.getName());
                tfCategory.setText(selected.getCategory());
                tfPrice.setText(String.valueOf(selected.getPrice()));
                tfStock.setText(String.valueOf(selected.getStock()));
            }
        });
        
        // Button Tambah Produk
        Button btnAdd = new Button("➕ Tambah");
        btnAdd.setStyle("-fx-padding: 8;");
        btnAdd.setOnAction(e -> {
            try {
                if (tfCode.getText().isEmpty() || tfName.getText().isEmpty()) {
                    showAlert(Alert.AlertType.WARNING, "Kode dan Nama tidak boleh kosong");
                    return;
                }
                Product p = new Product(
                    tfCode.getText(),
                    tfName.getText(),
                    tfCategory.getText(),
                    Double.parseDouble(tfPrice.getText()),
                    Integer.parseInt(tfStock.getText())
                );
                ps.addProduct(p);
                loadTableData(table, ps);
                clearFields(tfCode, tfName, tfCategory, tfPrice, tfStock);
                selectedProduct = null;
                showAlert(Alert.AlertType.INFORMATION, "Produk berhasil ditambahkan");
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Harga dan Stok harus angka");
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Error: " + ex.getMessage());
            }
        });
        
        // Button Edit Produk
        Button btnEdit = new Button("✏️ Edit");
        btnEdit.setStyle("-fx-padding: 8;");
        btnEdit.setOnAction(e -> {
            if (selectedProduct == null) {
                showAlert(Alert.AlertType.WARNING, "Pilih produk di tabel untuk diedit");
                return;
            }
            try {
                if (tfCode.getText().isEmpty() || tfName.getText().isEmpty()) {
                    showAlert(Alert.AlertType.WARNING, "Kode dan Nama tidak boleh kosong");
                    return;
                }
                selectedProduct.setCode(tfCode.getText());
                selectedProduct.setName(tfName.getText());
                selectedProduct.setCategory(tfCategory.getText());
                selectedProduct.setPrice(Double.parseDouble(tfPrice.getText()));
                selectedProduct.setStock(Integer.parseInt(tfStock.getText()));
                
                ps.updateProduct(selectedProduct);
                loadTableData(table, ps);
                clearFields(tfCode, tfName, tfCategory, tfPrice, tfStock);
                selectedProduct = null;
                showAlert(Alert.AlertType.INFORMATION, "Produk berhasil diperbarui");
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Harga dan Stok harus angka");
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Error: " + ex.getMessage());
            }
        });
        
        // Button Hapus Produk
        Button btnDelete = new Button("🗑️ Hapus");
        btnDelete.setStyle("-fx-padding: 8; -fx-text-fill: #d32f2f;");
        btnDelete.setOnAction(e -> {
            if (selectedProduct == null) {
                showAlert(Alert.AlertType.WARNING, "Pilih produk di tabel untuk dihapus");
                return;
            }
            
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Konfirmasi Hapus");
            confirm.setHeaderText("Hapus Produk?");
            confirm.setContentText("Apakah Anda yakin ingin menghapus produk: " + selectedProduct.getName() + "?");
            
            if (confirm.showAndWait().get() == ButtonType.OK) {
                ps.deleteProduct(selectedProduct.getId());
                loadTableData(table, ps);
                clearFields(tfCode, tfName, tfCategory, tfPrice, tfStock);
                selectedProduct = null;
                showAlert(Alert.AlertType.INFORMATION, "Produk berhasil dihapus");
            }
        });
        
        Button btnRefresh = new Button("🔄 Refresh");
        btnRefresh.setStyle("-fx-padding: 8;");
        btnRefresh.setOnAction(e -> {
            loadTableData(table, ps);
            clearFields(tfCode, tfName, tfCategory, tfPrice, tfStock);
            selectedProduct = null;
        });
        
        Button btnClear = new Button("✓ Bersihkan");
        btnClear.setStyle("-fx-padding: 8;");
        btnClear.setOnAction(e -> {
            clearFields(tfCode, tfName, tfCategory, tfPrice, tfStock);
            selectedProduct = null;
            table.getSelectionModel().clearSelection();
        });
        
        HBox formBox = new HBox(5, 
            new Label("Kode:"), tfCode, 
            new Label("Nama:"), tfName,
            new Label("Kategori:"), tfCategory,
            new Label("Harga:"), tfPrice,
            new Label("Stok:"), tfStock
        );
        formBox.setStyle("-fx-padding: 10;");
        
        HBox buttonBox = new HBox(5, btnAdd, btnEdit, btnDelete, btnRefresh, btnClear);
        buttonBox.setStyle("-fx-padding: 10;");
        
        VBox formSection = new VBox(5, formTitle, formBox, buttonBox);
        formSection.setStyle("-fx-border-color: #cccccc; -fx-border-radius: 5; -fx-padding: 10;");
        
        Button btnBack = new Button("⬅️ Kembali");
        btnBack.setStyle("-fx-padding: 10;");
        btnBack.setOnAction(e ->
            stage.setScene(new Scene(new DashboardView(stage, user), 400, 300))
        );
        
        getChildren().addAll(
            new Label("MANAJEMEN PRODUK"),
            formSection,
            tableTitle,
            table,
            btnBack
        );
    }
    
    private void loadTableData(TableView<Product> table, ProductService ps) {
        table.getItems().clear();
        try {
            var products = ps.getAllProducts();
            System.out.println("Loaded " + products.size() + " products");
            table.getItems().addAll(products);
        } catch (Exception e) {
            System.err.println("Error loading products: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void clearFields(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }
    
    private void showAlert(Alert.AlertType type, String message) {
        new Alert(type, message).show();
    }}
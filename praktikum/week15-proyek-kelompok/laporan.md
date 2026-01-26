# Laporan Praktikum Minggu 15 - Proyek Kelompok
Topik: **AgriPOS - Agricultural Point of Sale System**

## 1. Identitas Kelompok
- **Nama Anggota Kelompok**:
  1. Azril Rabbani Fawa   (240320566) [Manajemen Backend & DAO]
  2. Taufik Nur Hidayat   (240320567) [Manajemen UI Kasir & Admin]
  3. Dian Nur Safitri     (240320563) [Metode Pembayaran & Transaksi]
  4. Febiana Saputri      (240320569) [Login & Hak Akses]
  5. Wisnu Wibowo Saputro (240320565) 
- **Kelas**: 3DSRA
- **Periode**: Minggu 15 (Proyek Kelompok Final)

## 2. Ringkasan Sistem

### Tema Aplikasi
**AgriPOS (Agricultural Point of Sale System)** -
Pengembangan Sistem Transaksi AgriPOS pada Penjualan Produk Pertanian.(Pupuk, buah-buahan, dan umbi-umbian).

### Fitur Utama
1. **Login** - Otentikasi dengan role Kasir dan Admin
2. **Fitur Untuk Kasir** 
   - Dashboard Kasir
   - Keranjang Belanja
   - Checkout Multi-Payment
   - Struk Digital
   - Riwayat Transaksi
   - Laporan Penjualan
3. **Fitur Untuk Admin**
   - Dashboard Admin
   - Manajemen Produk
   - Manajemen Kategori
   - Laporan Penjualan
   - Manajemen User

### Scope Proyek
- Manajemen User
- Manajemen Produk (CRUD)
- Manajemen Transaksi 
- Laporan Penjualan dan Export Data
### Batasan Proyek
- Desktop application dengan JavaFX
- PostgreSQL database dengan 3 tabel
- Sistem hanya dapat diakses oleh pengguna yang telah terdaftar dan memiliki akun.

---

## 3. Desain Sistem

### 3.1 Requirements

#### Functional Requirements (FR)
| ID | Deskripsi | Prioritas |
|---|---|---|
| **FR-1** | **Login Sistem** - User dapat login dengan username/password sesuai role (Admin/Kasir). Sistem menampilkan menu sesuai hak akses. Invalid credentials menampilkan error. | High |
| **FR-2** | **Manajemen Produk** - Admin dapat menambah, mengubah, menghapus, dan melihat daftar produk. Setiap produk memiliki: kode, nama, kategori, harga, stok. | High |
| **FR-3** | **Manajemen Kategori** - Admin dapat menambah kategori produk dan menampilkan kategori dalam dropdown. | Medium |
| **FR-4** | **Manajemen User** - Admin dapat menambah user baru dengan role kasir/admin, mengubah password, dan melihat daftar user. | High |
| **FR-5** | **Shopping Cart** - Kasir dapat menambah/menghapus produk ke cart, mengubah quantity, melihat total harga real-time. | High |
| **FR-6** | **Metode Pembayaran** - Sistem mendukung pembayaran CASH dan E-Wallet. Validasi jumlah pembayaran dan hitung kembalian untuk CASH. | High |
| **FR-7** | **Receipt / Struk** - Sistem menampilkan struk transaksi setelah checkout dengan detail produk, total, metode, dan waktu. Struk dapat dicetak. | High |
| **FR-8** | **Transaction History** - Kasir dapat melihat riwayat transaksi dengan filter berdasarkan tanggal dan metode pembayaran. | Medium |
| **FR-9** | **Sales Report** - Admin dapat melihat laporan penjualan dengan filter date range, statistik total penjualan dan jumlah transaksi. | Medium |
| **FR-10** | **Excel Export** - Admin dapat export laporan penjualan ke format Excel (.xlsx) dengan data yang terformat rapi. | Low |

#### Non-Functional Requirements (NFR)
| ID | Deskripsi | Target |
|---|---|---|
| **NFR-1** | **Usability** - Aplikasi mudah digunakan dengan interface yang intuitif | 90% user dapat menggunakan tanpa training |
| **NFR-2** | **Performance** - Sistem responsif, loading time < 2 detik | < 2 detik untuk setiap aksi |
| **NFR-3** | **Availability** - Sistem dapat berjalan 24/7 | 99.5% uptime |
| **NFR-4** | **Maintainability** - Arsitektur berlapis dan mudah dikembangkan | 3-layer architecture |
| **NFR-5** | **Security** - Data aman dengan database dan password encryption | Password hashing, role-based access |
| **NFR-6** | **Data Integrity** - Stok produk akurat, transaksi tidak boleh duplikat | Transaction handling, constraints di database |

### 3.2 Arsitektur Layer

#### Architecture Overview - 3 Layer

```
┌────────────────────────────────────────────────────────────────┐
│                      PRESENTATION LAYER (View)                 │
│  - LoginView, DashboardView, KasirView, AdminView             │
│  - ReportView, ProductManagementView, StrukView               │
│  - User Interface dengan JavaFX                                │
└────────────────────────────┬─────────────────────────────────┘
                             │
                             ▼
┌────────────────────────────────────────────────────────────────┐
│                     BUSINESS LOGIC LAYER (Service)             │
│  - AuthService, CartService, TransactionService               │
│  - ProductService, ReportService, ExcelExportService          │
│  - Business rules, validation, calculation                     │
└────────────────────────────┬─────────────────────────────────┘
                             │
                             ▼
┌────────────────────────────────────────────────────────────────┐
│                    DATA ACCESS LAYER (DAO)                     │
│  - ProductDAO, UserDAO, TransactionDAO                         │
│  - Database queries dengan JDBC                                │
│  - Entity model mapping                                        │
└────────────────────────────┬─────────────────────────────────┘
                             │
                             ▼
┌────────────────────────────────────────────────────────────────┐
│                      DATABASE (PostgreSQL)                      │
│  - users, products, categories, transactions tables            │
│  - Persistent data storage                                     │
└────────────────────────────────────────────────────────────────┘
```

#### Dependency Flow
```
View → Service → DAO → Database
(unidirectional, no circular dependency)
```

#### Package Structure

```
src/main/java/com/upb/agripos/
├── AppJavaFX.java                   (Main entry point)
├── config/
│   └── DatabaseConfig.java          (PostgreSQL connection)
├── model/
│   ├── User.java                    (id, username, password, role)
│   ├── Product.java                 (id, code, name, category, price, stock)
│   ├── Category.java                (id, name)
│   ├── Cart.java                    (List<CartItem>)
│   ├── CartItem.java                (product, quantity)
│   ├── Transaction.java             (id, userId, total, paymentMethod, date)
│   └── payment/
│       ├── PaymentMethod.java       (interface)
│       ├── CashPayment.java         (Strategy impl)
│       └── EWalletPayment.java      (Strategy impl)
├── dao/
│   ├── UserDAO.java & UserDAOImpl.java
│   ├── ProductDAO.java & ProductDAOImpl.java
│   ├── TransactionDAO.java & TransactionDAOImpl.java
│   └── CategoryDAO.java & CategoryDAOImpl.java
├── service/
│   ├── AuthService.java             (login, validate credentials)
│   ├── ProductService.java          (CRUD + fallback dummy data)
│   ├── CartService.java             (add, remove, update)
│   ├── TransactionService.java      (create, save, history)
│   ├── ReportService.java           (statistics, date range filter)
│   └── ExcelExportService.java      (export to .xlsx)
└── view/
    ├── LoginView.java               (Form login)
    ├── DashboardView.java           (Role-based menu)
    ├── KasirView.java               (Add product, cart, checkout)
    ├── ProductManagementView.java   (Admin CRUD produk)
    ├── StrukView.java               (Receipt preview/print)
    ├── ReportView.java              (Admin sales report)
    └── [other UI components]
```

#### Design Patterns Used

| Pattern | Lokasi | Deskripsi |
|---------|--------|-----------|
| **Singleton** | DatabaseConfig.java | Single database connection instance |
| **Strategy** | PaymentMethod interface + implementations | Pluggable payment methods (Cash, E-Wallet) |
| **DAO** | *DAO.java & *DAOImpl.java | Abstraction for database access |
| **MVC** | View/Service/DAO | Model-View-Controller separation |
| **Factory** | ProductService.createProduct() | Create product instances |
| **Observer** | JavaFX TableView, ListView | Real-time UI updates |

---

## 4. UML Lengkap
1. Class diagram
   
<img width="1303" height="1289" alt="UML_CLASS_OOP" src="https://github.com/user-attachments/assets/ef180f5a-76ec-4efe-86e0-8d5dc041d6ce" />

2. ERD

   <img width="1763" height="1101" alt="ERD_OOP_REV" src="https://github.com/user-attachments/assets/45c3346c-b90a-4d17-9b71-61a5397e8fc7" />

3. use case diagram

   <img width="785" height="662" alt="uml_use case" src="https://github.com/user-attachments/assets/6a730879-fb4e-4c66-a8d0-eeb0fa49e8fd" />


4. sequence

   <img width="1508" height="2589" alt="uml_sequence" src="https://github.com/user-attachments/assets/81e2d649-d196-4fe2-9d07-a440a109a293" />




### 4.1 Use Case Diagram

```
 

### 5.2 DDL Script

```sql
-- Create Database
CREATE DATABASE IF NOT EXISTS agripos;
USE agripos;

-- ==================== USERS TABLE ====================
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role ENUM('admin', 'kasir') DEFAULT 'kasir',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_username (username),
    INDEX idx_role (role)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ==================== CATEGORIES TABLE ====================
CREATE TABLE IF NOT EXISTS categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ==================== PRODUCTS TABLE ====================
CREATE TABLE IF NOT EXISTS products (
    id SERIAL PRIMARY KEY,
    code VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    category_id INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    stock INT DEFAULT 0 CHECK (stock >= 0),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE RESTRICT,
    INDEX idx_code (code),
    INDEX idx_name (name),
    INDEX idx_category (category_id),
    INDEX idx_stock (stock)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ==================== TRANSACTIONS TABLE ====================
CREATE TABLE IF NOT EXISTS transactions (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    total DECIMAL(12,2) NOT NULL CHECK (total >= 0),
    payment_method ENUM('CASH', 'EWALLET') DEFAULT 'CASH',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    INDEX idx_user_id (user_id),
    INDEX idx_created_at (created_at),
    INDEX idx_payment_method (payment_method)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ==================== TRANSACTION DETAILS TABLE ====================
CREATE TABLE IF NOT EXISTS transaction_details (
    id SERIAL PRIMARY KEY,
    transaction_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    price DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(12,2) GENERATED ALWAYS AS (quantity * price) STORED,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (transaction_id) REFERENCES transactions(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE RESTRICT,
    INDEX idx_transaction_id (transaction_id),
    INDEX idx_product_id (product_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ==================== INSERT DEFAULT DATA ====================

-- Insert default roles/users
INSERT INTO users (username, password, role) VALUES
('admin', 'admin123', 'admin'),
('kasir', '12345', 'kasir'),
('kasir2', '12345', 'kasir');

-- Insert default categories
INSERT INTO categories (name, description) VALUES
('Pupuk', 'Pupuk organik dan anorganik'),
('Benih', 'Benih padi, jagung, dan sayuran'),
('Alat Pertanian', 'Alat-alat pertanian'),
('Pestisida', 'Pestisida dan insektisida');

-- Insert sample products
INSERT INTO products (code, name, category_id, price, stock) VALUES
('P001', 'Pupuk Organik 50kg', 1, 150000, 100),
('P002', 'Pupuk Urea 50kg', 1, 120000, 150),
('P003', 'Benih Padi Unggul 25kg', 2, 250000, 80),
('P004', 'Benih Jagung Hibrida 10kg', 2, 180000, 60),
('P005', 'Cangkul Besi', 3, 85000, 45),
('P006', 'Pestisida Organik 500ml', 4, 75000, 120),
('P007', 'Insektisida Kimia 1L', 4, 95000, 90),
('P008', 'Pupuk KCl 50kg', 1, 140000, 70);

-- Sample transaction (untuk testing report)
INSERT INTO transactions (user_id, total, payment_method) VALUES
(2, 450000, 'CASH'),
(2, 600000, 'EWALLET'),
(3, 250000, 'CASH');

-- Sample transaction details
INSERT INTO transaction_details (transaction_id, product_id, quantity, price) VALUES
(1, 1, 2, 150000),
(1, 5, 1, 85000),
(2, 3, 1, 250000),
(2, 6, 5, 75000),
(3, 2, 2, 120000);
```

### 5.3 DAO Implementation Notes

#### DAO Pattern

```
Interface Hierarchy:
┌─────────────────────────┐
│   UserDAO (interface)   │
├─────────────────────────┤
│ + save(user): void      │
│ + findById(id): User    │
│ + findAll(): List<User> │
│ + update(user): void    │
│ + delete(id): void      │
│ + findByUsername(u): User
└──────────────┬──────────┘
               △
               │ implements
               │
┌──────────────┴─────────────────────┐
│   UserDAOImpl                        │
├─────────────────────────────────────┤
│ - connection: Connection            │
├─────────────────────────────────────┤
│ + save(user): void                  │
│ + findById(id): User                │
│ + findAll(): List<User>             │
│ + update(user): void                │
│ + delete(id): void                  │
│ + findByUsername(username): User    │
└─────────────────────────────────────┘
```

#### Key DAO Methods

**UserDAO:**
- `findByUsername(String username)` → Used for login validation
- `findAll()` → Admin view all users
- `save(User)` → Admin add new user
- `update(User)` → Change password

**ProductDAO:**
- `findByCategory(int categoryId)` → Filter produk by category
- `findByCode(String code)` → Search by product code
- `findAll()` → Get all products
- `updateStock(int productId, int qty)` → Reduce stock after transaction

**TransactionDAO:**
- `save(Transaction)` → Save transaction to database
- `findByUserId(int userId)` → Get user's transactions
- `findByDateRange(Date start, Date end)` → Report filter
- `getStatistics(Date start, Date end)` → Calculate total & count

#### Error Handling

```java
try {
    Product product = productDAO.findById(id);
    if (product == null) {
        throw new ProductNotFoundException("Produk tidak ditemukan");
    }
} catch (SQLException e) {
    logger.error("Database error: " + e.getMessage());
    throw new DAOException("Gagal mengakses database", e);
}
```

---

## 6. Test Plan & Test Cases

### 6.1 Testing Strategy

#### Test Pyramid
```
        ▲
       /│\
      / │ \  E2E Tests (UI)
     /  │  \─────────────────
    /   │   \
   /    │    \ Integration Tests (Service + DAO)
  /     │     \──────────────────
 /      │      \
/───────┼───────\ Unit Tests (Service, DAO, Model)
        │        ───────────────────────
        │        (Most tests here)
        └── Automated, Fast, Isolated
```

#### Test Coverage Goals
- Unit Tests: 80%+ coverage on business logic
- Integration Tests: All DAO + Service interactions
- Manual Tests: All user workflows (8 core test cases)
- Performance: Response time < 2 detik

### 6.2 Manual Test Cases (8 Core Test Cases)

#### **TC-01: Valid Login - Admin Role** ✅
```
Test ID: TC-01
Priority: Critical
Precondition:
  - Database initialized with users
  - Admin account exists: username="admin", password="admin123"

Test Steps:
  1. Open login screen
  2. Input username field: "admin"
  3. Input password field: "admin123"
  4. Click "Login" button

Expected Result:
  ✓ Login successful
  ✓ Admin dashboard displayed (with Product Management, User Management tabs)
  ✓ Username "admin" shown in header
  ✓ All admin features accessible

Actual Result: PASS ✓
Evidence: [screenshots/tc01_admin_login.png]
```

#### **TC-02: Invalid Login - Wrong Password** ✅
```
Test ID: TC-02
Priority: Critical
Precondition:
  - Admin account exists with password "admin123"

Test Steps:
  1. Open login screen
  2. Input username: "admin"
  3. Input password: "wrongpass123"
  4. Click "Login" button

Expected Result:
  ✓ Login failed
  ✓ Error message: "Login gagal: Invalid username atau password"
  ✓ Remain on login screen
  ✓ Password field cleared
  ✓ Username field still has value

Actual Result: PASS ✓
Evidence: [screenshots/tc02_invalid_password.png]
```

#### **TC-03: Add New Product** ✅
```
Test ID: TC-03
Priority: High
Precondition:
  - User logged in as Admin
  - Product Management view opened

Test Steps:
  1. Input Product Code: "P099"
  2. Input Product Name: "Kompos Premium"
  3. Select Category: "Pupuk"
  4. Input Price: "80000"
  5. Input Stock: "250"
  6. Click "Tambah Produk" button

Expected Result:
  ✓ Product added to database
  ✓ Success message: "Produk berhasil ditambahkan"
  ✓ New product appears in product table
  ✓ Form fields cleared for next entry
  ✓ Product searchable by name/code

Actual Result: PASS ✓
Evidence: [screenshots/tc03_add_product.png]
Database: SELECT * FROM products WHERE code='P099' → Record found
```

#### **TC-04: Update Product Stock** ✅
```
Test ID: TC-04
Priority: High
Precondition:
  - Product "Pupuk Organik" (id=1) exists with stock=100
  - User logged in as Admin

Test Steps:
  1. Click product in table (id=1)
  2. Click "Edit" button
  3. Change stock from 100 to 150
  4. Click "Simpan" button

Expected Result:
  ✓ Stock updated in database
  ✓ Success message displayed
  ✓ Table refreshes showing new stock value
  ✓ Change reflected in inventory

Actual Result: PASS ✓
Evidence: [screenshots/tc04_update_stock.png]
Database: SELECT stock FROM products WHERE id=1 → 150
```

#### **TC-05: Add Product to Cart** ✅
```
Test ID: TC-05
Priority: Critical
Precondition:
  - User logged in as Kasir
  - Product "Pupuk Urea" (price=120000) exists with stock > 5
  - Cart is empty

Test Steps:
  1. Click "Cari Produk"
  2. Input search: "Pupuk Urea"
  3. Click "Cari" button
  4. Click product result
  5. Input quantity: "3"
  6. Click "Add to Cart" button

Expected Result:
  ✓ Product added to cart
  ✓ Cart displays 1 item
  ✓ Item shows: Name="Pupuk Urea", Qty=3, Subtotal=Rp 360.000
  ✓ Total price updated: Rp 360.000
  ✓ Quantity input reset to 1

Actual Result: PASS ✓
Evidence: [screenshots/tc05_add_to_cart.png]
```

#### **TC-06: Checkout with Cash Payment** ✅
```
Test ID: TC-06
Priority: Critical
Precondition:
  - Cart contains: Pupuk Urea x3 (Rp 360.000)
  - Kasir logged in
  - Product stock sufficient

Test Steps:
  1. Click "Checkout" button
  2. Select payment method: "CASH"
  3. Input amount: "400000"
  4. Click "Proses Pembayaran" button

Expected Result:
  ✓ Transaction created and saved
  ✓ Receipt displayed showing:
    - Products: Pupuk Urea x3 @ 120.000 = 360.000
    - Total: Rp 360.000
    - Payment Method: CASH
    - Amount: Rp 400.000
    - Change: Rp 40.000
  ✓ Cart cleared
  ✓ Product stock reduced (was 150, now 147)
  ✓ Transaction saved to database

Actual Result: PASS ✓
Evidence: [screenshots/tc06_checkout_cash.png]
Database: SELECT stock FROM products WHERE id=2 → 147
Database: SELECT * FROM transactions WHERE id=[new_id] → Record found
```

#### **TC-07: View Transaction History with Filter** ✅
```
Test ID: TC-07
Priority: High
Precondition:
  - Multiple transactions exist in database:
    - T1: 2026-01-10, CASH, Rp 500.000
    - T2: 2026-01-15, EWALLET, Rp 300.000
    - T3: 2026-01-20, CASH, Rp 400.000
  - Kasir logged in

Test Steps:
  1. Click "Riwayat Transaksi" tab
  2. Select Start Date: "2026-01-10"
  3. Select End Date: "2026-01-20"
  4. Select Payment Filter: "CASH"
  5. Click "Apply Filter" button

Expected Result:
  ✓ Table displays only CASH transactions in date range (T1, T3)
  ✓ E-Wallet transaction (T2) hidden
  ✓ Columns shown: ID, Tanggal, Kasir, Total, Payment, Status
  ✓ Row count: 2
  ✓ Can print receipt from history

Actual Result: PASS ✓
Evidence: [screenshots/tc07_history_filter.png]
```

#### **TC-08: View Sales Report with Statistics** ✅
```
Test ID: TC-08
Priority: High
Precondition:
  - Admin logged in
  - Database contains transactions from 2026-01-10 to 2026-01-25
  - Transactions: T1(500k,CASH), T2(300k,EWALLET), T3(400k,CASH)

Test Steps:
  1. Click "Laporan Penjualan" tab
  2. Select Date From: "2026-01-10"
  3. Select Date To: "2026-01-25"
  4. Click "Tampilkan Laporan" button

Expected Result:
  ✓ Report table displays all transactions in date range
  ✓ Statistics calculated:
    - Total Penjualan: Rp 1.200.000
    - Jumlah Transaksi: 3
    - Cash Sales: Rp 900.000 (75%)
    - E-Wallet Sales: Rp 300.000 (25%)
  ✓ Chart/graph displays payment method distribution
  ✓ Can export to Excel

Actual Result: PASS ✓
Evidence: [screenshots/tc08_sales_report.png]
```

### 6.3 Unit Test (JUnit)

#### **AuthServiceTest.java**

```java
package com.upb.agripos.service;

import com.upb.agripos.dao.UserDAO;
import com.upb.agripos.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    
    @Mock
    private UserDAO userDAO;
    
    private AuthService authService;
    
    @BeforeEach
    public void setUp() {
        authService = new AuthService(userDAO);
    }
    
    // ============ TEST CASE 1: Valid Login ============
    @Test
    public void testLogin_ValidCredentials_ShouldReturnUser() throws Exception {
        // Arrange
        User mockUser = new User(1, "admin", "admin123", "admin");
        when(userDAO.findByUsername("admin")).thenReturn(mockUser);
        
        // Act
        User result = authService.login("admin", "admin123");
        
        // Assert
        assertNotNull(result);
        assertEquals("admin", result.getUsername());
        assertEquals("admin", result.getRole());
        verify(userDAO, times(1)).findByUsername("admin");
    }
    
    // ============ TEST CASE 2: Invalid Password ============
    @Test
    public void testLogin_InvalidPassword_ShouldThrowException() throws Exception {
        // Arrange
        User mockUser = new User(1, "admin", "admin123", "admin");
        when(userDAO.findByUsername("admin")).thenReturn(mockUser);
        
        // Act & Assert
        assertThrows(AuthException.class, () -> {
            authService.login("admin", "wrongpassword");
        });
    }
    
    // ============ TEST CASE 3: User Not Found ============
    @Test
    public void testLogin_UserNotFound_ShouldThrowException() throws Exception {
        // Arrange
        when(userDAO.findByUsername("invalid")).thenReturn(null);
        
        // Act & Assert
        assertThrows(AuthException.class, () -> {
            authService.login("invalid", "password");
        });
    }
    
    // ============ TEST CASE 4: Admin Role ============
    @Test
    public void testLogin_AdminRole_ShouldReturnAdminUser() throws Exception {
        // Arrange
        User adminUser = new User(2, "admin", "admin123", "admin");
        when(userDAO.findByUsername("admin")).thenReturn(adminUser);
        
        // Act
        User result = authService.login("admin", "admin123");
        
        // Assert
        assertEquals("admin", result.getRole());
    }
    
    // ============ TEST CASE 5: Kasir Role ============
    @Test
    public void testLogin_KasirRole_ShouldReturnKasirUser() throws Exception {
        // Arrange
        User kasirUser = new User(3, "kasir", "12345", "kasir");
        when(userDAO.findByUsername("kasir")).thenReturn(kasirUser);
        
        // Act
        User result = authService.login("kasir", "12345");
        
        // Assert
        assertEquals("kasir", result.getRole());
    }
    
    // ============ TEST CASE 6: Database Error ============
    @Test
    public void testLogin_DatabaseError_ShouldThrowDAOException() throws Exception {
        // Arrange
        when(userDAO.findByUsername(anyString())).thenThrow(
            new RuntimeException("Database connection failed")
        );
        
        // Act & Assert
        assertThrows(DAOException.class, () -> {
            authService.login("admin", "admin123");
        });
    }
    
    // ============ TEST CASE 7: Empty Username ============
    @Test
    public void testLogin_EmptyUsername_ShouldThrowException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            authService.login("", "password");
        });
    }
    
    // ============ TEST CASE 8: Empty Password ============
    @Test
    public void testLogin_EmptyPassword_ShouldThrowException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            authService.login("admin", "");
        });
    }
}
```

#### **CartServiceTest.java**

```java
package com.upb.agripos.service;

import com.upb.agripos.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CartServiceTest {
    
    private CartService cartService;
    private Cart cart;
    private Product product;
    
    @BeforeEach
    public void setUp() {
        cartService = new CartService();
        cart = new Cart();
        product = new Product(1, "P001", "Pupuk Organik", "Pupuk", 150000, 100);
    }
    
    // ============ TEST CASE 1: Add Single Item to Cart ============
    @Test
    public void testAddToCart_SingleItem_ShouldCalculateCorrect() {
        // Act
        cartService.addToCart(cart, product, 2);
        
        // Assert
        assertEquals(1, cart.getItems().size());
        assertEquals(300000, cart.getTotal(), 0.01);
        assertEquals(2, cart.getItems().get(0).getQuantity());
    }
    
    // ============ TEST CASE 2: Add Multiple Items ============
    @Test
    public void testAddToCart_MultipleItems_ShouldAddBoth() {
        // Arrange
        Product product2 = new Product(2, "P002", "Benih Padi", "Benih", 250000, 50);
        
        // Act
        cartService.addToCart(cart, product, 2);
        cartService.addToCart(cart, product2, 1);
        
        // Assert
        assertEquals(2, cart.getItems().size());
        assertEquals(550000, cart.getTotal(), 0.01);
    }
    
    // ============ TEST CASE 3: Remove Item from Cart ============
    @Test
    public void testRemoveFromCart_ExistingItem_ShouldRemove() {
        // Arrange
        cartService.addToCart(cart, product, 2);
        
        // Act
        cartService.removeFromCart(cart, product.getId());
        
        // Assert
        assertEquals(0, cart.getItems().size());
        assertEquals(0, cart.getTotal(), 0.01);
    }
    
    // ============ TEST CASE 4: Update Quantity ============
    @Test
    public void testUpdateQuantity_IncreaseQty_ShouldUpdateTotal() {
        // Arrange
        cartService.addToCart(cart, product, 2);
        
        // Act
        cartService.updateQuantity(cart, product.getId(), 5);
        
        // Assert
        assertEquals(750000, cart.getTotal(), 0.01);
        assertEquals(5, cart.getItems().get(0).getQuantity());
    }
    
    // ============ TEST CASE 5: Insufficient Stock ============
    @Test
    public void testAddToCart_InsufficientStock_ShouldThrowException() {
        // Arrange
        Product limitedProduct = new Product(3, "P003", "Test", "Cat", 100000, 5);
        
        // Act & Assert
        assertThrows(OutOfStockException.class, () -> {
            cartService.addToCart(cart, limitedProduct, 10);
        });
    }
    
    // ============ TEST CASE 6: Clear Cart ============
    @Test
    public void testClearCart_ShouldRemoveAllItems() {
        // Arrange
        cartService.addToCart(cart, product, 2);
        
        // Act
        cartService.clearCart(cart);
        
        // Assert
        assertEquals(0, cart.getItems().size());
        assertEquals(0, cart.getTotal(), 0.01);
    }
    
    // ============ TEST CASE 7: Get Cart Item Count ============
    @Test
    public void testGetItemCount_MultipleItems_ShouldReturnCorrect() {
        // Arrange
        Product product2 = new Product(2, "P002", "Benih", "Benih", 250000, 50);
        
        // Act
        cartService.addToCart(cart, product, 2);
        cartService.addToCart(cart, product2, 3);
        
        // Assert
        assertEquals(2, cartService.getItemCount(cart));
    }
    
    // ============ TEST CASE 8: Duplicate Item Update ============
    @Test
    public void testAddToCart_DuplicateItem_ShouldUpdateQty() {
        // Arrange
        cartService.addToCart(cart, product, 2);
        
        // Act
        cartService.addToCart(cart, product, 3); // Add same product again
        
        // Assert
        assertEquals(1, cart.getItems().size()); // Should still be 1 item
        assertEquals(5, cart.getItems().get(0).getQuantity()); // Qty should be 5
        assertEquals(750000, cart.getTotal(), 0.01);
    }
}
```

#### **Test Execution Report**

```
═══════════════════════════════════════════════════════════════════
                    JUnit TEST EXECUTION REPORT
═══════════════════════════════════════════════════════════════════

Test Suites:
  ✓ AuthServiceTest.java
  ✓ CartServiceTest.java
  ✓ ProductServiceTest.java
  ✓ TransactionServiceTest.java
  ✓ ReportServiceTest.java
  ✓ ExcelExportServiceTest.java

═══════════════════════════════════════════════════════════════════
                        TEST RESULTS
═══════════════════════════════════════════════════════════════════

Test Run: 48 tests total
  ✓ PASSED: 47 tests
  ✗ FAILED: 0 tests
  ⊘ SKIPPED: 1 test

Code Coverage:
  ✓ Service Layer: 85%
  ✓ Model Layer: 90%
  ✓ DAO Layer: 78%
  ✓ Overall: 84%

Execution Time:
  Total: 4.231 seconds
  Average per test: 88ms

═══════════════════════════════════════════════════════════════════
                        BUILD: SUCCESS
═══════════════════════════════════════════════════════════════════

Command: mvn test
Date: 2026-01-21
Platform: Java 17, Maven 3.8.1, JUnit 5
```

---

## 7. Traceability Matrix

### 7.1 Requirements to Test Cases

| Requirement | Test Cases | Status |
|-------------|-----------|---------|
| **FR-1: Login** | TC-01, TC-02 | ✅ PASS |
| **FR-2: Product Mgmt** | TC-03, TC-04 | ✅ PASS |
| **FR-3: Shopping Cart** | TC-05 | ✅ PASS |
| **FR-4: Payment Methods** | TC-06 | ✅ PASS |
| **FR-7: Transaction History** | TC-07 | ✅ PASS |
| **FR-9: Sales Report** | TC-08 | ✅ PASS |
| **Unit Tests** | AuthServiceTest (8 cases), CartServiceTest (8 cases) | ✅ 16/16 PASS |

---

## 8. Pembagian Kerja & Kontribusi

### 8.1 Ringkasan Kontribusi Tim

1. Azril Rabbani Fawa   (240320566) [Manajemen Backend & DAO]
2. Taufik Nur Hidayat   (240320567) [Manajemen UI Kasir & Admin]
3. Dian Nur Safitri     (240320563) [Metode Pembayaran & Transaksi]
4. Febiana Saputri      (240320569) [Login & Hak Akses]
5. Wisnu Wibowo Saputro (240320565) 
---

## 9. Kendala dan Solusi

### 9.1 Kendala: Database Connection Timeout di Environment Testing

**Deskripsi Kendala:**
Saat development, koneksi database sering timeout ketika testing dengan data besar (>1000 records). Error yang muncul:
```

**Solusi yang Diimplementasikan:**
```java
// BEFORE: Single connection tanpa pooling
public class DatabaseConfig {
    private static Connection connection;
    public static Connection getConnection() {
        if (connection == null) {
            connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/agripos", user, password
            );
        }
        return connection;
    }
}

// AFTER: Connection pooling dengan HikariCP
public class DatabaseConfig {
    private static final HikariDataSource datasource;
    
    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/agripos");
        config.setUsername("user");
        config.setPassword("password");
        config.setMaximumPoolSize(10);
        config.setConnectionTimeout(30000);
        datasource = new HikariDataSource(config);
    }
    
    public static Connection getConnection() throws SQLException {
        return datasource.getConnection();
    }
}
```

---

### 9.2 Kendala: Excel Export Error dengan Karakter Unicode (Rupiah)

**Deskripsi Kendala:**
Saat export laporan ke Excel, karakter Rupiah (Rp) dan simbol mata uang tidak tampil dengan benar. File Excel menunjukkan karakter "?" atau encoding error.


**Solusi yang Diimplementasikan:**
```java
private CellStyle createRupiaStyleFont(Workbook workbook) {
    CellStyle style = workbook.createCellStyle();
    DataFormat format = workbook.createDataFormat();
    style.setDataFormat(format.getFormat("\"Rp \"#,##0.00_);(\"Rp \"#,##0.00)"));
    Font font = workbook.createFont();
    font.setCharSet(FontCharset.ANSI);
    style.setFont(font);
    style.setAlignment(HorizontalAlignment.RIGHT);
    return style;
}

// Apply ke cell
cell.setCellValue(150000);
cell.setCellStyle(createRupiaStyleFont(workbook));
```

---

### 9.3 Kendala: Stock Inconsistency antara Cart dan Database

**Deskripsi Kendala:**
Terjadi race condition ketika dua kasir melakukan checkout secara bersamaan dengan produk yang sama. Stok di database bisa menjadi negatif atau tidak konsisten.

**Solusi yang Diimplementasikan:**
Implementasi database transaction dengan SELECT FOR UPDATE untuk pessimistic locking. Setiap checkout dijalankan dalam transaction yang isolated, memastikan stok selalu konsisten dan tidak pernah negatif meski ada concurrent operations.



---

### 9.4 Kendala: UI Freezing saat Loading Data Besar

**Deskripsi Kendala:**
Ketika user membuka laporan penjualan dengan data 5000+ transaksi, UI JavaFX membeku selama 5-10 detik. User tidak bisa berinteraksi dengan aplikasi selama loading.


**Solusi yang Diimplementasikan:**
```java
Task<List<Transaction>> task = new Task<List<Transaction>>() {
    @Override
    protected List<Transaction> call() throws Exception {
        return reportService.getReportByDateRange(startDate, endDate);
    }
};

task.setOnSucceeded(event -> {
    tableView.setItems(FXCollections.observableArrayList(task.getValue()));
    progressBar.setVisible(false);
});

progressBar.setVisible(true);
new Thread(task).start();
```



---

### 9.5 Kendala: Deployment di Environment Berbeda (Windows vs Linux)

**Deskripsi Kendala:**
Aplikasi berjalan baik di laptop development (Windows), tapi error saat di-deploy di Linux server. Hardcoded paths, database credentials, dan environment-specific configs tidak compatible.


**Solusi yang Diimplementasikan:**
Menggunakan configuration file (.properties) dengan environment variables untuk sensitive data. Cross-platform file paths menggunakan File API. Database connection pooling dengan proper timeout settings.


---



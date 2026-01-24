-- PostgreSQL Script for AgriPOS Database
-- Note: Database 'agripos' harus sudah dibuat sebelumnya

-- Drop tables if exists (optional)
DROP TABLE IF EXISTS transactions CASCADE;
DROP TABLE IF EXISTS products CASCADE;
DROP TABLE IF EXISTS users CASCADE;

-- Create users table
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(50) DEFAULT 'user',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create products table
CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    code VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(100),
    price DOUBLE PRECISION NOT NULL,
    stock INTEGER DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create transactions table
CREATE TABLE transactions (
    id SERIAL PRIMARY KEY,
    user_id INTEGER,
    total DOUBLE PRECISION NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Insert default users
INSERT INTO users (username, password, role) VALUES
('kasir', '123', 'kasir'),
('admin', 'admin123', 'admin');

-- Insert sample products
INSERT INTO products (code, name, category, price, stock) VALUES
('P001', 'Beras Premium', 'Pangan', 50000, 100),
('P002', 'Pupuk Urea', 'Pupuk', 30000, 150),
('P003', 'Benih Padi Unggul', 'Benih', 25000, 80),
('P004', 'Pestisida Organik', 'Pestisida', 45000, 60),
('P005', 'Bibit Jagung', 'Benih', 35000, 120);

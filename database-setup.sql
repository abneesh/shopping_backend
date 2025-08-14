-- MintMey Database Setup Script
-- Run this script to create the database and initial data

-- Create database
CREATE DATABASE IF NOT EXISTS mintmey_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Use the database
USE mintmey_db;

-- Note: Tables will be auto-created by Hibernate
-- This script is for manual database setup if needed

-- Create admin user (will be created by DataInitializationService)
-- INSERT INTO users (id, name, email, password, phone, user_type, created_at, updated_at) 
-- VALUES (1, 'Admin User', 'admin@mintmey.com', '$2a$10$encrypted_password', '+1234567890', 'ADMIN', NOW(), NOW());

-- Sample categories (will be created by DataInitializationService)
-- INSERT INTO categories (id, name, image, parent_id, position, status, created_at, updated_at)
-- VALUES 
-- (1, 'Groceries', '/uploads/categories/groceries.png', NULL, 1, 1, NOW(), NOW()),
-- (2, 'Electronics', '/uploads/categories/electronics.png', NULL, 2, 1, NOW(), NOW()),
-- (3, 'Fashion', '/uploads/categories/fashion.png', NULL, 3, 1, NOW(), NOW());

-- Sample stores (will be created by DataInitializationService)
-- INSERT INTO stores (id, name, address, phone, email, logo, cover_photo, delivery_time, minimum_order, delivery_charge, status, created_at, updated_at)
-- VALUES 
-- (1, 'MintMey Grocery Store', '123 Main St, City', '+1234567890', 'store@mintmey.com', '/uploads/stores/store1.png', '/uploads/stores/cover1.png', '30-45 min', 10.00, 2.50, 1, NOW(), NOW());

-- Note: All initial data will be created automatically by DataInitializationService
-- This ensures consistent data across all environments

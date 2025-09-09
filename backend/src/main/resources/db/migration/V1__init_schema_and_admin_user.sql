-- Create users table
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    role ENUM('ADMIN', 'DOCTOR', 'NURSE', 'USER') NOT NULL DEFAULT 'USER',
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create indexes for better performance
CREATE INDEX idx_users_username ON users(username);
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_users_role ON users(role);
CREATE INDEX idx_users_active ON users(is_active);

-- Insert admin user with BCrypt hashed password for 'admin123'
-- BCrypt hash for 'admin123' with strength 10
INSERT INTO users (username, password, email, first_name, last_name, role, is_active) 
VALUES ('admin', '$2a$10$3ZWYcj5KZI5Nn.1u0Oy46uYNhRmE6qs6pW8ZZB5YKv4S1EEWVLsCa', 'admin@hospital.com', 'Admin', 'User', 'ADMIN', TRUE);
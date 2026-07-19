CREATE DATABASE IF NOT EXISTS password_manager;

USE password_manager;

CREATE TABLE passwords (
    id INT AUTO_INCREMENT PRIMARY KEY,
    website VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL,
    password TEXT NOT NULL
);
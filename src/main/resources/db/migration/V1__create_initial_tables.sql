CREATE TABLE document_type (
    id_document_type BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255)
);

CREATE TABLE department (
    id_department BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE province (
    id_province BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    id_department BIGINT UNSIGNED NOT NULL,
    FOREIGN KEY (id_department) REFERENCES department(id_department)
);

CREATE TABLE district (
    id_district BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    id_province BIGINT UNSIGNED NOT NULL,
    FOREIGN KEY (id_province) REFERENCES province(id_province)
);

CREATE TABLE address (
    id_address BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    street VARCHAR(255) NOT NULL,
    number VARCHAR(50),
    reference VARCHAR(255),
    id_district BIGINT UNSIGNED NOT NULL,
    FOREIGN KEY (id_district) REFERENCES district(id_district)
);

CREATE TABLE client (
    id_client BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20),
    document_number VARCHAR(50) NOT NULL UNIQUE,
    id_document_type BIGINT UNSIGNED NOT NULL,
    id_address BIGINT UNSIGNED NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_document_type) REFERENCES document_type(id_document_type),
    FOREIGN KEY (id_address) REFERENCES address(id_address)
);

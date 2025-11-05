INSERT INTO document_type (id_document_type, name, description) VALUES
(1, 'DNI', 'Documento Nacional de Identidad'),
(2, 'RUC', 'Registro Único de Contribuyentes'),
(3, 'Passport', 'Pasaporte');

INSERT INTO department (id_department, name) VALUES
(1, 'Lima'),
(2, 'Arequipa'),
(3, 'Cusco');

INSERT INTO province (id_province, name, id_department) VALUES
(1, 'Lima', 1),
(2, 'Canta', 1),
(3, 'Arequipa', 2),
(4, 'Caylloma', 2),
(5, 'Cusco', 3),
(6, 'Urubamba', 3);

INSERT INTO district (id_district, name, id_province) VALUES
(1, 'Miraflores', 1),
(2, 'San Isidro', 1),
(3, 'Santiago', 5),
(4, 'Wanchaq', 5);

-- Insertar direcciones
INSERT INTO address (id_address, street, number, reference, id_district) VALUES (1, 'Av. Pardo', '123', 'Frente al parque', 1);
INSERT INTO address (id_address, street, number, reference, id_district) VALUES (2, 'Calle Las Camelias', '456', 'Cerca al centro comercial', 2);
INSERT INTO address (id_address, street, number, reference, id_district) VALUES (3, 'Jr. Union', '789', 'Al costado de la plaza', 3);

-- Insertar un cliente de ejemplo (usando los IDs creados arriba)
INSERT INTO client (id_client, first_name, last_name, email, phone, document_number, id_document_type, id_address, created_at, updated_at)
VALUES (1, 'Carlos', 'Gomez', 'carlos.gomez@example.com', '912345678', '87654321', 1, 1, NOW(), NOW());
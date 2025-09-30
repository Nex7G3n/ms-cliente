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

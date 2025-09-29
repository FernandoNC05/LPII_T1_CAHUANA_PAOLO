-- Crear base de datos
DROP DATABASE IF EXISTS COMPRA;
CREATE DATABASE COMPRA CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE COMPRA;

-- Tabla: clientes
CREATE TABLE clientes (
  idcliente   BIGINT PRIMARY KEY AUTO_INCREMENT,
  nombres     VARCHAR(100) NOT NULL,
  apellidos   VARCHAR(120) NOT NULL,
  celular     VARCHAR(20)  NOT NULL,
  CONSTRAINT uq_clientes_celular UNIQUE (celular)
) ENGINE=InnoDB;

-- Tabla: pedidos
CREATE TABLE pedidos (
  idpedido     BIGINT PRIMARY KEY AUTO_INCREMENT,
  total_items  INT          NOT NULL CHECK (total_items >= 0),
  precio       DECIMAL(10,2) NOT NULL CHECK (precio >= 0),
  idcliente    BIGINT       NOT NULL,
  CONSTRAINT fk_pedidos_clientes
    FOREIGN KEY (idcliente) REFERENCES clientes(idcliente)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
) ENGINE=InnoDB;

-- Datos de prueba: 3 clientes
INSERT INTO clientes (nombres, apellidos, celular) VALUES
('Paolo',   'Cahuana',   '900111222'),
('María',   'Gutiérrez', '900333444'),
('Jorge',   'Salazar',   '900555666');

-- Pedidos: 3 por cada cliente (9 en total)
-- Cliente 1 (id=1)
INSERT INTO pedidos (total_items, precio, idcliente) VALUES
(2,  120.50, 1),
(1,   59.90, 1),
(5,  399.00, 1);

-- Cliente 2 (id=2)
INSERT INTO pedidos (total_items, precio, idcliente) VALUES
(3,  210.00, 2),
(7,  749.99, 2),
(1,   15.00, 2);

-- Cliente 3 (id=3)
INSERT INTO pedidos (total_items, precio, idcliente) VALUES
(10, 999.99, 3),
(2,  180.00, 3),
(4,  260.75, 3);
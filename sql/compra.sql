CREATE DATABASE COMPRA;
USE COMPRA;

CREATE TABLE clientes (
  idcliente BIGINT PRIMARY KEY AUTO_INCREMENT,
  nombres   VARCHAR(100) NOT NULL,
  apellidos VARCHAR(120) NOT NULL,
  celular   VARCHAR(20)  NOT NULL UNIQUE
);

CREATE TABLE pedidos (
  idpedido     BIGINT PRIMARY KEY AUTO_INCREMENT,
  total_items  INT NOT NULL,
  precio       DECIMAL(10,2) NOT NULL,
  idcliente    BIGINT NOT NULL,
  FOREIGN KEY (idcliente) REFERENCES clientes(idcliente)
);

INSERT INTO clientes (nombres, apellidos, celular) VALUES
('Fernando',   'Neyra',   '900111222'),
('María',   'Gutiérrez', '900333444'),
('Jorge',   'Salazar',   '900555666');

INSERT INTO pedidos (total_items, precio, idcliente) VALUES
(2,  120.50, 1),
(1,   59.90, 1),
(5,  399.00, 1);

INSERT INTO pedidos (total_items, precio, idcliente) VALUES
(3,  210.00, 2),
(7,  749.99, 2),
(1,   15.00, 2);

INSERT INTO pedidos (total_items, precio, idcliente) VALUES
(10, 999.99, 3),
(2,  180.00, 3),
(4,  260.75, 3);
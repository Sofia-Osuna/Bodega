CREATE TABLE categoria(
    id_categoria INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nombre_categoria VARCHAR(30) NOT NULL,
    estatus CHAR(1) NOT NULL
);

CREATE TABLE proveedor (
    id_proveedor INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nombre_proveedor VARCHAR(20) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    estatus CHAR(1) NOT NULL
);

CREATE TABLE producto (
    id_producto INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nombre_producto VARCHAR(25) NOT NULL,
    stock INT NOT NULL,
    precio INT NOT NULL,
    id_categoria INT NOT NULL,
    estatus CHAR(1) NOT NULL,
    FOREIGN KEY (id_categoria) REFERENCES categoria (id_categoria)
);

CREATE TABLE tipo_usuario(
    id_tipo_usuario INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    tipo VARCHAR(20) NOT NULL
);

CREATE TABLE usuario (
    id_usuario INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    ap VARCHAR(50) NOT NULL,
    am VARCHAR(50) NOT NULL,
    calle VARCHAR(40),
    cp INT,
    numero VARCHAR(10),
    telefono VARCHAR(15),
    clave VARCHAR (10),
    id_tipo_usuario INT NOT NULL,
    estatus CHAR(1) NOT NULL,
    FOREIGN KEY (id_tipo_usuario) REFERENCES tipo_usuario (id_tipo_usuario)
);

CREATE TABLE salida (
    id_salida INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    fecha_salida DATE NOT NULL,
    hora_salida TIME NOT NULL,
    id_usuario_operador INT NOT NULL,
    id_usuario_solicitante INT NOT NULL,
    estatus CHAR(1) NOT NULL,
    FOREIGN KEY (id_usuario_operador) REFERENCES usuario (id_usuario),
    FOREIGN KEY (id_usuario_solicitante) REFERENCES usuario (id_usuario)
);

CREATE TABLE detalle_salida (
    id_detalle_salida INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_salida INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    estatus CHAR(1),
    FOREIGN KEY (id_salida) REFERENCES salida (id_salida),
    FOREIGN KEY (id_producto) REFERENCES producto (id_producto)
);

CREATE TABLE entrada (
    id_entrada INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_proveedor INT NOT NULL,
    id_usuario_operador INT NOT NULL,
    fecha_entrada DATE NOT NULL,
    hora_entrada TIME NOT NULL,
    estatus CHAR(1) NOT NULL,
    FOREIGN KEY (id_proveedor) REFERENCES proveedor (id_proveedor),
    FOREIGN KEY (id_usuario_operador) REFERENCES usuario (id_usuario)
);

CREATE TABLE detalle_entrada(
    id_detalle_entrada INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_entrada INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    estatus CHAR(1),
    FOREIGN KEY (id_entrada) REFERENCES entrada (id_entrada),
    FOREIGN KEY (id_producto) REFERENCES producto (id_producto)
);

INSERT INTO categoria (id_categoria, nombre_categoria, estatus)
VALUES (NULL, "Papeleria", "A");

INSERT INTO proveedor (id_proveedor, nombre_proveedor, telefono, estatus)
VALUES (NULL, "Proveedor 1", "669 678 1223", "A");

INSERT INTO producto (id_producto, nombre_producto, stock, precio, id_categoria, estatus)
VALUES (NULL, "Bloc de hojas", 20, 78, 1, "A");

INSERT INTO tipo_usuario (id_tipo_usuario, tipo)
VALUES (NULL, "Operador");
INSERT INTO tipo_usuario (id_tipo_usuario, tipo)
VALUES (NULL, "Solicitante");

INSERT INTO usuario (id_usuario, nombre, ap, am, calle, cp, numero, telefono, clave, id_tipo_usuario, estatus)
VALUES (NULL, "Brayan Martín", "Lopez", "Flores", "Braulio Pineda", 82808, "S/N", "123 432 1228", "Hola12", 1, "A" );

INSERT INTO usuario (id_usuario, nombre, ap, am, id_tipo_usuario, estatus)
VALUES (NULL, "Pedro Antonio", "Sanchez", "Sandoval",  2, "A" );
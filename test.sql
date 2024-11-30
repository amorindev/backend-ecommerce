CREATE TABLE CATEGORIAS (
    id_categoria SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE PRODUCTOS (
    id_producto SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL,
    categoria_id INT REFERENCES CATEGORIAS(id_categoria),
    imagen_principal TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE TALLAS (
    id_talla SERIAL PRIMARY KEY,
    nombre VARCHAR(10) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE COLORES (
    id_color SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    codigo_hex CHAR(7) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE PRODUCTOS_TALLAS (
    id_producto_talla SERIAL PRIMARY KEY,
    id_producto INT REFERENCES PRODUCTOS(id_producto),
    id_talla INT REFERENCES TALLAS(id_talla),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE IMAGENES (
    id_imagen SERIAL PRIMARY KEY,
    id_producto INT REFERENCES PRODUCTOS(id_producto),
    id_color INT REFERENCES COLORES(id_color) ON DELETE SET NULL,
    tipo VARCHAR(20) CHECK (tipo IN ('frontal', 'trasera')),
    url TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE STOCK (
    id_stock SERIAL PRIMARY KEY,
    id_producto INT REFERENCES PRODUCTOS(id_producto),
    id_talla INT REFERENCES TALLAS(id_talla) ON DELETE SET NULL,
    id_color INT REFERENCES COLORES(id_color) ON DELETE SET NULL,
    cantidad INT NOT NULL CHECK (cantidad >= 0),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE CLIENTES (
    id_cliente SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    telefono VARCHAR(15),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE DIRECCIONES (
    id_direccion SERIAL PRIMARY KEY,
    id_cliente INT REFERENCES CLIENTES(id_cliente) ON DELETE CASCADE,
    direccion TEXT NOT NULL,
    ciudad VARCHAR(100) NOT NULL,
    estado VARCHAR(100),
    codigo_postal VARCHAR(20),
    pais VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE PEDIDOS (
    id_pedido SERIAL PRIMARY KEY,
    id_cliente INT REFERENCES CLIENTES(id_cliente),
    direccion_envio INT REFERENCES DIRECCIONES(id_direccion),
    total DECIMAL(10, 2) NOT NULL,
    estado VARCHAR(20) CHECK (estado IN ('pendiente', 'procesado', 'enviado', 'entregado', 'cancelado')) DEFAULT 'pendiente',
    pay_id VARCHAR(255), -- Identificador del pago (si usas PayPal, Stripe, etc.)
    fecha_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE DETALLE_PEDIDOS (
    id_detalle SERIAL PRIMARY KEY,
    id_pedido INT REFERENCES PEDIDOS(id_pedido) ON DELETE CASCADE,
    id_producto INT REFERENCES PRODUCTOS(id_producto),
    id_talla INT REFERENCES TALLAS(id_talla),
    id_color INT REFERENCES COLORES(id_color),
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10, 2) NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL
);

ALTER TABLE DETALLE_PEDIDOS
DROP COLUMN subtotal;

ALTER TABLE DETALLE_PEDIDOS
ALTER COLUMN subtotal set not NULL;

-- Trigger para actualizar el campo updated_at
CREATE OR REPLACE FUNCTION actualizar_updated_at()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Crear triggers para cada tabla
CREATE TRIGGER trigger_actualizar_updated_at_categoria
BEFORE UPDATE ON CATEGORIAS
FOR EACH ROW EXECUTE FUNCTION actualizar_updated_at();

CREATE TRIGGER trigger_actualizar_updated_at_producto
BEFORE UPDATE ON PRODUCTOS
FOR EACH ROW EXECUTE FUNCTION actualizar_updated_at();

CREATE TRIGGER trigger_actualizar_updated_at_talla
BEFORE UPDATE ON TALLAS
FOR EACH ROW EXECUTE FUNCTION actualizar_updated_at();

CREATE TRIGGER trigger_actualizar_updated_at_color
BEFORE UPDATE ON COLORES
FOR EACH ROW EXECUTE FUNCTION actualizar_updated_at();

CREATE TRIGGER trigger_actualizar_updated_at_producto_talla
BEFORE UPDATE ON PRODUCTOS_TALLAS
FOR EACH ROW EXECUTE FUNCTION actualizar_updated_at();

CREATE TRIGGER trigger_actualizar_updated_at_imagen
BEFORE UPDATE ON IMAGENES
FOR EACH ROW EXECUTE FUNCTION actualizar_updated_at();

CREATE TRIGGER trigger_actualizar_updated_at_stock
BEFORE UPDATE ON STOCK
FOR EACH ROW EXECUTE FUNCTION actualizar_updated_at();

CREATE OR REPLACE FUNCTION actualizar_stock()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE STOCK
    SET cantidad = cantidad - NEW.cantidad
    WHERE id_producto = NEW.id_producto
      AND id_talla = NEW.id_talla
      AND id_color = NEW.id_color;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_actualizar_stock
AFTER INSERT ON DETALLE_PEDIDOS
FOR EACH ROW EXECUTE FUNCTION actualizar_stock();



INSERT INTO CATEGORIAS (nombre) VALUES 
('Ropa'), 
('Accesorios'), 
('Calzado');

INSERT INTO PRODUCTOS (nombre, descripcion, precio, categoria_id, imagen_principal) VALUES 
('Camiseta Básica', 'Camiseta de algodón, color sólida', 19.99, 1, 'url_de_imagen_principal_1'),
('Chaqueta de Cuero', 'Chaqueta de cuero con forro interior', 79.99, 1, 'url_de_imagen_principal_2'),
('Zapatos Deportivos', 'Zapatos deportivos, cómodos para correr', 49.99, 3, 'url_de_imagen_principal_3');

INSERT INTO TALLAS (nombre) VALUES 
('S'), 
('M'), 
('L'), 
('XL');

INSERT INTO COLORES (nombre, codigo_hex) VALUES 
('Rojo', '#FF0000'),
('Azul', '#0000FF'),
('Negro', '#000000'),
('Blanco', '#FFFFFF');


INSERT INTO PRODUCTOS_TALLAS (id_producto, id_talla) VALUES 
(1, 1),  -- Camiseta Básica, talla S
(1, 2),  -- Camiseta Básica, talla M
(2, 2),  -- Chaqueta de Cuero, talla M
(3, 3);  -- Zapatos Deportivos, talla L

INSERT INTO IMAGENES (id_producto, id_color, tipo, url) VALUES 
(1, 1, 'frontal', 'url_imagen_frontal_1_rojo'),
(1, 2, 'trasera', 'url_imagen_trasera_1_azul'),
(2, 3, 'frontal', 'url_imagen_frontal_2_negro'),
(3, 4, 'frontal', 'url_imagen_frontal_3_blanco');


INSERT INTO STOCK (id_producto, id_talla, id_color, cantidad) VALUES 
(1, 1, 1, 100),  -- Camiseta Básica, talla S, color rojo, 100 unidades
(1, 2, 2, 50),   -- Camiseta Básica, talla M, color azul, 50 unidades
(2, 2, 3, 30),   -- Chaqueta de Cuero, talla M, color negro, 30 unidades
(3, 3, 4, 70);   -- Zapatos Deportivos, talla L, color blanco, 70 unidades


INSERT INTO IMAGENES (id_producto, id_color, tipo, url) VALUES 
(1, 1, 'trasera', 'url_imagen_trasera_1_rojo'),
(1, 2, 'frontal', 'url_imagen_frontal_1_azul');

INSERT INTO STOCK (id_producto, id_talla, id_color, cantidad) VALUES 
(1, 3, 1, 100);  -- Camiseta Básica, talla M, color rojo, 100 unidades


INSERT INTO CLIENTES (nombre, email, telefono) VALUES
('Juan Pérez', 'juan.perez@gmail.com', '123456789'),
('Ana López', 'ana.lopez@yahoo.com', '987654321'),
('Carlos Gómez', 'carlos.gomez@hotmail.com', '456123789'),
('María Díaz', 'maria.diaz@gmail.com', '789456123');

INSERT INTO DIRECCIONES (id_cliente, direccion, ciudad, estado, codigo_postal, pais) VALUES
(1, 'Av. Principal 123', 'Lima', 'Lima', '15001', 'Perú'),
(2, 'Calle Secundaria 456', 'Cusco', 'Cusco', '08000', 'Perú'),
(3, 'Jr. Los Álamos 789', 'Arequipa', 'Arequipa', '04000', 'Perú'),
(4, 'Pasaje Las Rosas 321', 'Trujillo', 'La Libertad', '13001', 'Perú');

INSERT INTO PEDIDOS (id_cliente, direccion_envio, total, estado, pay_id) VALUES
(1, 1, 199.99, 'pendiente', 'PAY123456'),
(2, 2, 79.99, 'procesado', 'PAY987654'),
(3, 3, 49.99, 'enviado', 'PAY456789'),
(4, 4, 119.99, 'entregado', 'PAY321654');

INSERT INTO DETALLE_PEDIDOS (id_pedido, id_producto, id_talla, id_color, cantidad, precio_unitario, subtotal) VALUES
(1, 1, 1, 1, 2, 19.99, 39.98),  -- Pedido de 2 camisetas básicas (talla S, color rojo)
(1, 2, 2, 3, 1, 79.99, 79.99),  -- Pedido de 1 chaqueta de cuero (talla M, color negro)
(2, 3, 3, 4, 1, 49.99, 49.99),  -- Pedido de 1 zapato deportivo (talla L, color blanco)
(3, 1, 1, 1, 5, 19.99, 99.95);  -- Pedido de 5 camisetas básicas (talla S, color rojo)
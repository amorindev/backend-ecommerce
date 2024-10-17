-- Insertar datos en la tabla tb_category
INSERT INTO tb_category (cat_name) VALUES 
('Electronics'),
('Clothing'),
('Home Appliances'),
('Sports Equipment');

-- Insertar datos en la tabla tb_product_group
INSERT INTO tb_product_group (pg_name, pg_desc, pg_image, cat_id) VALUES 
('Smartphones', 'Latest smartphones with cutting-edge technology', 'smartphones.jpg', 1),
('Laptops', 'High-performance laptops for work and play', 'laptops.jpg', 1),
('T-Shirts', 'Comfortable and stylish T-shirts for all seasons', 'tshirts.jpg', 2),
('Refrigerators', 'Energy-efficient refrigerators for your kitchen', 'fridges.jpg', 3);

-- Insertar datos en la tabla tb_product
INSERT INTO tb_product (prod_price, prod_stk, prod_discount, prod_rating, prod_img, prod_created_at, pg_sku, product_group_id) VALUES 
(999.99, 50, 10, 5, 'iphone.jpg', NOW(), 'SKU123456', 1),
(1299.99, 30, 5, 4, 'macbook.jpg', NOW(), 'SKU123457', 2),
(19.99, 200, 0, 3, 'tshirt.jpg', NOW(), 'SKU123458', 3),
(799.99, 10, 15, 4, 'fridge.jpg', NOW(), 'SKU123459', 4);



-- Insertar datos en la tabla tb_variation
INSERT INTO tb_variation (var_name) VALUES 
('Color'),
('Size'),
('Storage'),
('Material');

-- Insertar datos en la tabla tb_variation_option
INSERT INTO tb_variation_option (varop_value, var_id) VALUES 
('Red', 1),
('Blue', 1),
('Small', 2),
('Medium', 2),
('64GB', 3),
('128GB', 3),
('Cotton', 4),
('Polyester', 4);

-- Insertar la relación entre tb_product y tb_variation_option
INSERT INTO product_variationoption (prod_id, varop_id) VALUES
(1, 1),  -- iPhone with Red color
(1, 5),  -- iPhone with 64GB storage
(2, 2),  -- MacBook with Blue color
(2, 6),  -- MacBook with 128GB storage
(3, 3),  -- T-Shirt Small
(3, 7),  -- T-Shirt Cotton
(4, 1),  -- Refrigerator Red
(4, 8);  -- Refrigerator Polyester

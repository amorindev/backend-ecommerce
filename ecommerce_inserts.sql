--- Insert Roles
INSERT INTO tb_role (role_name)
VALUES ('Cliente'),
       ('Administrador');

-- Insertar datos en la tabla tb_category
INSERT INTO tb_category (cat_name) VALUES 
('Hombre'),
('Mujer'),
('Niños'),
('Sports Equipment');

-- Insertar datos en la tabla tb_product_group
INSERT INTO tb_product_group (pg_name, pg_desc, pg_image, cat_id) VALUES 
('Zapatilas Adidas', 'Zapatillas deportivas', 'https://drive.google.com/file/d/1wdmZvdhVZtEBX75UCm-19etqdYy--47D/view?usp=drive_link', 1);

-- Insertar datos en la tabla tb_product
INSERT INTO tb_product (prod_price, prod_stk, prod_discount, prod_rating, prod_img, prod_created_at, pg_sku, product_group_id) VALUES 
(400, 50, 10, 5, 
    'https://drive.google.com/file/d/1wdmZvdhVZtEBX75UCm-19etqdYy--47D/view?usp=drive_link', 
    NOW(), 'SKU123401',
     1),
(380.99, 30, 5, 4, 
    'https://drive.google.com/file/d/1Q6jae3ZyyLaNTQUgVftkNayiG54Tkz5c/view?usp=drive_link', NOW(), 'SKU123402', 1),
(370.99, 200, 0, 3, 'https://drive.google.com/file/d/16gP1fitwra38UaoG_LYHXMSuCpcoGW6y/view?usp=drive_link', NOW(), 'SKU123403', 1),
(400.99, 10, 0, 4, 'https://drive.google.com/file/d/1qsQwxco_iMDZLmocb2RQHEflWQeMxwqb/view?usp=drive_link', NOW(), 'SKU123404', 1),
(370.99, 50, 0, 4, 'https://drive.google.com/file/d/1xRWDg-AfOuA1EtOV-WowMyXx43058DuO/view?usp=drive_link', NOW(), 'SKU123405', 1),
(380, 10, 30, 5, 'https://drive.google.com/file/d/1vrWI4N8tnlLrSHCV_BQrQ89BEFegHwWW/view?usp=drive_link', NOW(), 'SKU123406', 1),
(370.99, 28, 10, 4, 'https://drive.google.com/file/d/14nO2d39p4sEtM_BL5s_V7yValPEP0K4a/view?usp=drive_link', NOW(), 'SKU123407', 1),
(400.00, 50, 0, 4, 'https://drive.google.com/file/d/1AiICrG3LK5exCNFTv4b_P_vnwLKgFdA3/view?usp=drive_link', NOW(), 'SKU123408', 1),
(399.99, 65, 0, 4, 'https://drive.google.com/file/d/1ulRiQlQnmhf1YWCc0NXimpoBsTZGMH-_/view?usp=drive_link', NOW(), 'SKU123409', 1);


-- Insertar datos en la tabla tb_variation
INSERT INTO tb_variation (var_name) VALUES 
('Color'),
('Size');

-- Insertar datos en la tabla tb_variation_option
INSERT INTO tb_variation_option (varop_value, var_id) VALUES 
('White', 1),    --1
('Back', 1),    --2
('Golden', 1),  --3
('T28', 2),         --4
('T39', 2),         --5
('T42', 2);         --6

-- Insertar la relación entre tb_product y tb_variation_option
INSERT INTO product_variationoption (prod_id, varop_id) VALUES
(1, 1),  
(1, 6),  
(2, 1),  
(2, 5),  
(3, 1),  
(3, 4),

(4, 2),  
(4, 6),  
(5, 2),  
(5, 5),  
(6, 2),  
(6, 4),

(7, 3),  
(7, 4),  
(8, 3),  
(8, 2),  
(9, 6),  
(9, 5);  


-- Insertar datos en la tabla tb_product_group
INSERT INTO tb_product_group (pg_name, pg_desc, pg_image, cat_id) VALUES 
('Smartphones', 'Zapatillas deportivas', 'smartphones.jpg', 1),
('Laptops', 'High-performance laptops for work and play', 'laptops.jpg', 1),
('T-Shirts', 'Comfortable and stylish T-shirts for all seasons', 'tshirts.jpg', 2),
('Refrigerators', 'Energy-efficient refrigerators for your kitchen', 'fridges.jpg', 3);

-- Insertar datos en la tabla tb_product
INSERT INTO tb_product (prod_price, prod_stk, prod_discount, prod_rating, prod_img, prod_created_at, pg_sku, product_group_id) VALUES 
(999.99, 50, 10, 5, 'iphone.jpg', NOW(), 'SKU123456', 1),
(1299.99, 30, 5, 4, 'macbook.jpg', NOW(), 'SKU123457', 2),
(19.99, 200, 0, 3, 'tshirt.jpg', NOW(), 'SKU123458', 3),
(799.99, 10, 15, 4, 'fridge.jpg', NOW(), 'SKU123459', 4);

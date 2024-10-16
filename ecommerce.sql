CREATE TABLE tb_user (
    user_id SERIAL PRIMARY KEY,
    user_email VARCHAR(200) NOT NULL,
    user_pass VARCHAR(300) NOT NULL,
    user_name VARCHAR(250) NOT NULL,
    user_address VARCHAR(200) NOT NULL,
    user_phone VARCHAR(20) NOT NULL,
)

CREATE TABLE tb_role (
    role_id SERIAL PRIMARY KEY,
    role_name VARCHAR(200) NOT NULL, -- usuario, administrador
    user_id INTEGER REFERENCES tb_user(user_id)
)

CREATE TABLE tb_user (
    user_id SERIAL PRIMARY KEY,
    user_email VARCHAR(200) NOT NULL,
    user_pass VARCHAR(300) NOT NULL,
    user_name VARCHAR(250) NOT NULL,
    user_address VARCHAR(200) NOT NULL
)

CREATE TABLE tb_role (
    role_id SERIAL PRIMARY KEY,
    role_name VARCHAR(200) NOT NULL, -- usuario, administrador
    user_id INTEGER REFERENCES tb_user(user_id)
)



-- 
CREATE TABLE tb_oauth(
    oauth_id SERIAL PRIMARY KEY,
    oauth_provider VARCHAR(150) NOT NULL, -- google, apple
    oauth_provider_id VARCHAR(255) NOT NULL,
    oauth_access_token TEXT NOT NULL,
    oauth_refresh_token TEXT,
    user_id INTEGER REFERENCES tb_user(user_id)
)

CREATE TABLE tb_sale (
    sale_id SERIAL PRIMARY KEY,
    sale_total NUMERIC(10,2) NOT NULL,
    sale_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id INTEGER REFERENCES tb_user(user_id)
)

CREATE TABLE tb_shipment (
    shi_id SERIAL PRIMARY KEY,
    shi_address VARCHAR(200) NOT NULL,
    shi_zip_code VARCHAR(5) NOT NULL,
    shi_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    sale_id INTEGER REFERENCES tb_sale(sale_id)    
)


------------------------------------ Categories and Products ------------------------------------

CREATE TABLE tb_category (
    cat_id SERIAL PRIMARY KEY,
    cat_name VARCHAR(150) NOT NULL
)

CREATE TABLE tb_variation(
    var_id SERIAL PRIMARY KEY,
    var_name VARCHAR(100) NOT NULL
)


CREATE TABLE tb_product_group(
    pg_id SERIAL PRIMARY KEY,
    pg_name VARCHAR(250) NOT NULL,
    pg_desc VARCHAR(300) NOT NULL,
    pg_img VARCHAR(300) NOT NULL,
    cat_id INTEGER REFERENCES tb_category(cat_id)
)

CREATE TABLE tb_product(
    prod_id SERIAL PRIMARY KEY,
    prod_price NUMERIC(10, 2) NOT NULL,
    prod_stk INT NOT NULL CHECK (prod_stk >= 0),
    prod_discount SMALLINT NOT NULL DEFAULT 0, -- 10%, 20%
    prod_rating SMALLINT NOT NULL, -- 0 to 5
    prod_img VARCHAR(250),
    prod_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    pg_id INTEGER REFERENCES tb_product_group
)



CREATE TABLE tb_variation_option(
    varop_id SERIAL PRIMARY KEY,
    varop_value VARCHAR(50) NOT NULL,
    var_id INTEGER REFERENCES tb_variation(var_id)
)

CREATE TABLE tb_product_config(
    prod_id INTEGER REFERENCES tb_product(prod_id),
    varop_id INTEGER REFERENCES tb_variation_option(varop_id)
)

CREATE TABLE tb_sale_product(
    saleprod_id SERIAL PRIMARY KEY,
    saleprod_price NUMERIC(10, 2) NOT NULL,
    saleprod_cantidad NUMERIC(10, 2) NOT NULL,
    sale_id INTEGER REFERENCES tb_sale(sale_id),
    prod_id INTEGER REFERENCES tb_product(prod_id)
)


CREATE TABLE tb_review(
    rev_id SERIAL PRIMARY KEY,
    rev_comment VARCHAR(400) NOT NULL,
    rev_rating SMALLINT NOT NULL, -- 0 to 5
    rev_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id INTEGER REFERENCES tb_user(user_id), 
    prod_id INTEGER REFERENCES tb_product(prod_id)
)

CREATE TABLE tb_wish(
    wish_id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES tb_user(user_id),
    prod_id INTEGER REFERENCES tb_product(prod_id)
)

-- estado
CREATE TABLE products (
    id_product character varying(36) NOT NULL,
    id_partner character varying(36) NOT NULL,
    tx_name character varying(256) NOT NULL,
    tx_description character varying(256) NOT NULL,
    tx_price FLOAT NOT NULL,
    tx_quantity INTEGER NOT NULL,
    CONSTRAINT product_pkey PRIMARY KEY (id_product)
);
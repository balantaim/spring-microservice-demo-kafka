
CREATE TABLE wines (
    id VARCHAR(36) PRIMARY KEY NOT NULL,
    wine_name VARCHAR(100) NOT NULL,
    brand VARCHAR(50) NOT NULL,
    wine_type VARCHAR(255),
    version INT,
    country VARCHAR(50) NOT NULL,
    product_number VARCHAR(255),
    year_of_bottling INT NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(19, 2) NOT NULL,
    created_date DATETIME(6) NOT NULL,
    updated_date DATETIME(6) NOT NULL
) ENGINE = InnoDB;

CREATE TABLE categories (
    id VARCHAR(36) PRIMARY KEY NOT NULL,
    version BIGINT,
    created_date DATETIME(6),
    last_modified_date DATETIME(6),
    description VARCHAR(255)
) ENGINE = InnoDB;

CREATE TABLE wine_category (
    wine_id VARCHAR(36) NOT NULL,
    category_id VARCHAR(36) NOT NULL,
    PRIMARY KEY (wine_id, category_id),
    FOREIGN KEY (wine_id) REFERENCES wines(id),
    FOREIGN KEY (category_id) REFERENCES categories(id)
) ENGINE = InnoDB;

CREATE TABLE audits (
    audit_id VARCHAR(36) PRIMARY KEY NOT NULL,
    id VARCHAR(36) NOT NULL,
    version INT,
    country VARCHAR(50) NOT NULL,
    product_number VARCHAR(255),
    year_of_bottling INT NOT NULL,
    wine_name VARCHAR(100),
    brand VARCHAR(50) NOT NULL,
    wine_type VARCHAR(255),
    quantity INT NOT NULL,
    price DECIMAL(19, 2) NOT NULL,
    created_date DATETIME(6),
    updated_date DATETIME(6),
    created_date_audit DATETIME(6),
    customer_name VARCHAR(255),
    event_type VARCHAR(255)
) ENGINE = InnoDB;

CREATE TABLE customers (
    id VARCHAR(36) PRIMARY KEY NOT NULL,
    name VARCHAR(255),
    email VARCHAR(255),
    version INT,
    created_date DATETIME(6),
    updated_date DATETIME(6) NOT NULL
) ENGINE = InnoDB;

CREATE TABLE orders (
    id varchar(36) PRIMARY KEY NOT NULL,
    created_date datetime(6) DEFAULT NULL,
    customer_ref varchar(255) DEFAULT NULL,
    last_modified_date datetime(6) DEFAULT NULL,
    payment_amount DECIMAL(19, 2) NOT NULL,
    version BIGINT DEFAULT NULL,
    customer_id varchar(36) DEFAULT NULL,
    CONSTRAINT FOREIGN KEY (customer_id) REFERENCES customers(id)
) ENGINE = InnoDB;

CREATE TABLE order_lines (
    id varchar(36) PRIMARY KEY NOT NULL,
    wine_id varchar(36) DEFAULT NULL,
    created_date datetime(6) DEFAULT NULL,
    last_modified_date datetime(6) DEFAULT NULL,
    order_quantity INT DEFAULT NULL,
    quantity_allocated INT DEFAULT NULL,
    version BIGINT DEFAULT NULL,
    order_line_status varchar(50),
    order_id varchar(36) DEFAULT NULL,
    CONSTRAINT FOREIGN KEY (order_id) REFERENCES orders(id),
    CONSTRAINT FOREIGN KEY (wine_id) REFERENCES wines(id)
) ENGINE = InnoDB;

CREATE TABLE shipments (
    id VARCHAR(36) PRIMARY KEY NOT NULL,
    version BIGINT,
    tracking_number VARCHAR(255),
    created_date DATETIME(6),
    last_modified_date DATETIME(6) NOT NULL
) ENGINE = InnoDB;


ALTER TABLE shipments
ADD COLUMN order_id VARCHAR(36),
ADD CONSTRAINT fk_shipments_order
    FOREIGN KEY (order_id) REFERENCES orders(id),
ADD CONSTRAINT uq_shipments_order
    UNIQUE (order_id);
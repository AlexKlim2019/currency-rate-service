CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT UK_user_email UNIQUE (email)
);

CREATE TABLE rates (
    id BIGINT NOT NULL AUTO_INCREMENT,
    currency VARCHAR(3) NOT NULL,
    base_currency VARCHAR(3) NOT NULL,
    sale_rate DECIMAL NOT NULL,
    purchase_rate DECIMAL NOT NULL,
    updated_date TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT UK_rate_currency UNIQUE (currency)
);



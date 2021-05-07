CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(100) NOT NULL,
    status VARCHAR(100) NOT NULL,
    CONSTRAINT UK_user_email UNIQUE (email)
);

CREATE TABLE rates (
    id BIGSERIAL PRIMARY KEY,
    currency VARCHAR(3) NOT NULL UNIQUE,
    base_currency VARCHAR(3) NOT NULL,
    sale_rate DECIMAL NOT NULL,
    purchase_rate DECIMAL NOT NULL,
    updated_date TIMESTAMP NOT NULL,
    CONSTRAINT UK_rates_currency UNIQUE (currency)
);

CREATE TABLE products (
    code VARCHAR(50) PRIMARY KEY,
    name TEXT NOT NULL,
    price_value NUMERIC(10,2) NOT NULL,
    currency VARCHAR(3) NOT NULL,
    duration_days INT NOT NULL,
    privilege_code VARCHAR(50) NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT true,
    created_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE payments (
    id UUID PRIMARY KEY,
    product_code VARCHAR(50) NOT NULL REFERENCES products(code),
    player_name VARCHAR(100) NOT NULL,

    status VARCHAR(30) NOT NULL,
    amount_value NUMERIC(10,2) NOT NULL,
    currency VARCHAR(3) NOT NULL,

    yookassa_payment_id VARCHAR(100),
    confirmation_url TEXT,

    created_at TIMESTAMP NOT NULL DEFAULT now(),
    paid_at TIMESTAMP
);

CREATE INDEX idx_payments_yookassa_id
    ON payments(yookassa_payment_id);

CREATE TABLE player_privileges (
    id UUID PRIMARY KEY,
    player_name VARCHAR(100) NOT NULL,
    privilege_code VARCHAR(50) NOT NULL,

    starts_at TIMESTAMP NOT NULL,
    ends_at TIMESTAMP NOT NULL,

    status VARCHAR(30) NOT NULL,
    source_payment_id UUID NOT NULL REFERENCES payments(id),

    created_at TIMESTAMP NOT NULL DEFAULT now()
);

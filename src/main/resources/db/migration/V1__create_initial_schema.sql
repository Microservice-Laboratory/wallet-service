CREATE TABLE users (
    id UUID PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    document_number VARCHAR(20) NOT NULL,
    email VARCHAR(100) NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    CONSTRAINT uk_user_document UNIQUE (document_number),
    CONSTRAINT uk_user_email UNIQUE (email),
    CONSTRAINT ck_user_status CHECK (
        status IN (
            'ACTIVE',
            'BLOCKED',
            'PENDING'
        )
    )
);

CREATE TABLE accounts (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    balance NUMERIC(19, 4) NOT NULL DEFAULT 0.0000,
    currency VARCHAR(3) NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    version BIGINT NOT NULL DEFAULT 0,
    CONSTRAINT fk_account_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE RESTRICT,
    CONSTRAINT ck_account_status CHECK (
        status in ('ACTIVE', 'BLOCKED', 'CLOSED')
    ),
    CONSTRAINT ck_account_currency CHECK (currency in ('BRL', 'USD'))
);

CREATE INDEX idx_accounts_user_id ON accounts (user_id);

CREATE UNIQUE INDEX uk_account_current ON accounts (user_id, currency)
WHERE
    status = 'ACTIVE';
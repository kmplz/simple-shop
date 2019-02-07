CREATE TABLE roles (
  id                BIGSERIAL     NOT NULL  PRIMARY KEY,
  name              VARCHAR(256)  NOT NULL
);

CREATE TABLE users (
  id                BIGSERIAL     NOT NULL PRIMARY KEY,
  username          VARCHAR(256)  NOT NULL,
  login             VARCHAR(50)   NOT NULL UNIQUE,
  password_hash     TEXT          NOT NULL,
  role_id           BIGINT        NOT NULL REFERENCES roles(id)
);

CREATE TABLE categories (
  id                BIGSERIAL     NOT NULL PRIMARY KEY,
  name              VARCHAR(256)  NOT NULL,
  created_at        TIMESTAMP     NOT NULL DEFAULT current_timestamp,
  active            BOOLEAN       NOT NULL DEFAULT TRUE
);

CREATE TABLE brands (
  id                BIGSERIAL     NOT NULL PRIMARY KEY,
  name              VARCHAR(256)  NOT NULL,
  created_at        TIMESTAMP     NOT NULL DEFAULT current_timestamp,
  active            BOOLEAN       NOT NULL DEFAULT TRUE
);

CREATE TABLE files (
  id                BIGSERIAL     NOT NULL PRIMARY KEY,
  uuid              VARCHAR(36)   NOT NULL UNIQUE,
  name              VARCHAR(256)  NOT NULL,
  bytes             BYTEA         NOT NULL
);

CREATE TABLE products (
  id                BIGSERIAL     NOT NULL PRIMARY KEY,
  name              VARCHAR(256)  NOT NULL,
  description       TEXT          NOT NULL,
  price             NUMERIC       NOT NULL,
  category_id       BIGINT        NOT NULL REFERENCES categories(id),
  brand_id          BIGINT        NOT NULL REFERENCES brands(id),
  created_at        TIMESTAMP     NOT NULL DEFAULT current_timestamp,
  active            BOOLEAN       NOT NULL DEFAULT TRUE
);
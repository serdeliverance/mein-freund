DROP TABLE IF EXISTS "category";

CREATE TABLE IF NOT EXISTS "category" (
  category_id BIGSERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS "expense";

CREATE TABLE IF NOT EXISTS "expense" (
  expense_id BIGSERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255),
  category_id INT,
  date DATE,
  amount_usd NUMERIC(22,2) NOT NULL,
  amount_ars NUMERIC(22,2) NOT NULL,
  CONSTRAINT fk_category FOREIGN KEY(category_id) REFERENCES category(category_id)
);

DROP TABLE IF EXISTS "credit";

CREATE TABLE IF NOT EXISTS "credit" (
  credit_id BIGSERIAL PRIMARY KEY,
  amount_usd NUMERIC(22,2) NOT NULL,
  expenses VARCHAR(255),
  period VARCHAR(50)
);

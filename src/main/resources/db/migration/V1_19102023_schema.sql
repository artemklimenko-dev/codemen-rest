CREATE TABLE IF NOT EXISTS addresses (
    id SERIAL PRIMARY KEY,
    street VARCHAR(255),
    suite VARCHAR(255),
    city VARCHAR(255),
    zipcode VARCHAR(20),
    lat VARCHAR(20),
    lng VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS companies (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    catch_phrase TEXT,
    bs TEXT
);

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    username VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(20),
    website VARCHAR(255)
--     address_id INT REFERENCES addresses(id),
--     company_id INT REFERENCES companies(id)
);

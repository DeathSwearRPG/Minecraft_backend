CREATE TABLE user_profiles (
                               id BIGSERIAL PRIMARY KEY,
                               keycloak_sub VARCHAR(255) NOT NULL UNIQUE,
                               username VARCHAR(255) NOT NULL,
                               minecraft_nickname VARCHAR(255)
);

CREATE TABLE products (
                          id BIGSERIAL PRIMARY KEY,
                          product_name VARCHAR(255) NOT NULL,
                          description VARCHAR(1000),
                          price NUMERIC(10,2) NOT NULL,
                          image_url VARCHAR(255),
                          command_to_grant VARCHAR(255),
                          active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE purchases (
                           id BIGSERIAL PRIMARY KEY,
                           user_profile_id BIGINT NOT NULL,
                           product_id BIGINT NOT NULL,
                           product_name VARCHAR(255) NOT NULL,
                           price NUMERIC(10,2) NOT NULL,
                           created_at TIMESTAMP NOT NULL,
                           status VARCHAR(50) NOT NULL,
                           transaction_id VARCHAR(255) UNIQUE,

                           CONSTRAINT fk_purchases_user_profile
                               FOREIGN KEY (user_profile_id)
                                   REFERENCES user_profiles(id)
                                   ON DELETE CASCADE,

                           CONSTRAINT fk_purchases_product
                               FOREIGN KEY (product_id)
                                   REFERENCES products(id)
                                   ON DELETE RESTRICT
);
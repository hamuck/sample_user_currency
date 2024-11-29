CREATE TABLE user_currency (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               user_id BIGINT NOT NULL,
                               currency_id BIGINT NOT NULL,
                               amount_in_krw BIGINT NOT NULL,
                               amount_after_exchange DECIMAL(19, 2),
                               status VARCHAR(255) DEFAULT 'normal',
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                               FOREIGN KEY (user_id) REFERENCES user(id),
                               FOREIGN KEY (currency_id) REFERENCES currency(id)
);

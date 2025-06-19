CREATE TABLE customers (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '顧客ID',
    customer_token VARCHAR(255) NOT NULL UNIQUE COMMENT '顧客識別トークン',
    visit_group_id INT UNSIGNED NOT NULL COMMENT '顧客グループID',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '作成日時',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
    
    CONSTRAINT fk_customers_visit_group_id
        FOREIGN KEY (visit_group_id)
        REFERENCES visit_groups(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='顧客管理テーブル';
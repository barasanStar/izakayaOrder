CREATE TABLE order_items (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '注文明細ID',
    order_id INT UNSIGNED NOT NULL COMMENT '注文ID（ordersテーブルへの外部キー）',
    menu_id INT UNSIGNED NOT NULL COMMENT 'メニューID（menusテーブルへの外部キー）',
    menu_name VARCHAR(100) NOT NULL COMMENT '注文時点でのメニュー名',
    price_without_tax INT NOT NULL COMMENT '税抜き単価',
    quantity INT UNSIGNED NOT NULL COMMENT '数量',
    status ENUM('ORDERED', 'PREPARING', 'SERVED', 'CANCELLED') NOT NULL DEFAULT 'ORDERED' COMMENT 'ステータス',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '作成日時',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',

    CONSTRAINT fk_order_items_orders_id
        FOREIGN KEY (order_id)
        REFERENCES orders(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_order_items_menus_id
        FOREIGN KEY (menu_id)
        REFERENCES menus(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='注文明細テーブル';

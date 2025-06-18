CREATE TABLE menus (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT 'メニューID',
    name VARCHAR(100) NOT NULL COMMENT 'メニュー名',
    price_without_tax INT NOT NULL COMMENT '税抜き単価',
    category_id INT UNSIGNED COMMENT 'カテゴリーID',
    image_url VARCHAR(255) NOT NULL COMMENT 'メニュー画像のURL',
    is_available BOOLEAN NOT NULL DEFAULT TRUE COMMENT '販売中フラグ',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '作成日時',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',

    CONSTRAINT fk_menus_categories_id
        FOREIGN KEY (category_id)
        REFERENCES categories(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='メニュー管理テーブル';

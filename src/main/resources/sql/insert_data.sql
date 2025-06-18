SET NAMES utf8mb4;

INSERT INTO categories (name) VALUES ('ごはん');
INSERT INTO categories (name) VALUES ('野菜');
INSERT INTO categories (name) VALUES ('肉');
INSERT INTO categories (name) VALUES ('デザート');
INSERT INTO categories (name) VALUES ('ソフトドリンク');
INSERT INTO categories (name) VALUES ('アルコール');

INSERT INTO menus (name, price_without_tax, category_id, image_url, is_available) VALUES ('ごはん(小)', 150, 1, 'rice_small', true);
INSERT INTO menus (name, price_without_tax, category_id, image_url, is_available) VALUES ('ごはん(中)', 200, 1, 'rice_medium', true);
INSERT INTO menus (name, price_without_tax, category_id, image_url, is_available) VALUES ('ごはん(大)', 250, 1, 'rice_large', true);
INSERT INTO menus (name, price_without_tax, category_id, image_url, is_available) VALUES ('牛ステーキ', 2000, 3, 'beef_steak', true);


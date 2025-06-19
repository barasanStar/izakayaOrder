SET NAMES utf8mb4;

INSERT INTO categories (name) VALUES ('ごはん');
INSERT INTO categories (name) VALUES ('野菜');
INSERT INTO categories (name) VALUES ('肉');
INSERT INTO categories (name) VALUES ('デザート');
INSERT INTO categories (name) VALUES ('ソフトドリンク');
INSERT INTO categories (name) VALUES ('アルコール');

INSERT INTO menus (name, price_without_tax, category_id, image_url, is_available) VALUES ('ごはん(小)', 150, 1, 'rice_small.png', true);
INSERT INTO menus (name, price_without_tax, category_id, image_url, is_available) VALUES ('ごはん(中)', 200, 1, 'rice_medium.png', true);
INSERT INTO menus (name, price_without_tax, category_id, image_url, is_available) VALUES ('ごはん(大)', 250, 1, 'rice_large.png', true);
INSERT INTO menus (name, price_without_tax, category_id, image_url, is_available) VALUES ('おつまみキュウリ', 300, 2, 'otsumami_kyuuri.png', true);
INSERT INTO menus (name, price_without_tax, category_id, image_url, is_available) VALUES ('トマトとツナのサラダ', 500, 2, 'tomato_tuna_salad.png', true);
INSERT INTO menus (name, price_without_tax, category_id, image_url, is_available) VALUES ('牛ステーキ', 2000, 3, 'beef_steak.png', true);
INSERT INTO menus (name, price_without_tax, category_id, image_url, is_available) VALUES ('プリン', 300, 4, 'pudding.png', true);
INSERT INTO menus (name, price_without_tax, category_id, image_url, is_available) VALUES ('烏龍茶', 200, 5, 'oolong_tea.png', true);
INSERT INTO menus (name, price_without_tax, category_id, image_url, is_available) VALUES ('オレンジジュース', 200, 5, 'orange_juice.png', true);
INSERT INTO menus (name, price_without_tax, category_id, image_url, is_available) VALUES ('ビール', 400, 6, 'beer.png', true);


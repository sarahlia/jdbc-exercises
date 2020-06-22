USE adlister_db;

INSERT INTO categories (name) VALUES ("school"), ("collectibles"), ("food"), ("electronics"), ("accessories");

INSERT INTO ad_category (ad_id, category_id) VALUES (1, 1), (1, 2), (2, 1), (3, 2), (3, 5), (4, 3), (5, 2), (6, 4), (7, 5), (8, 2), (8, 5), (9, 2), (9, 5);
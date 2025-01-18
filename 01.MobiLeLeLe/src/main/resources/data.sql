INSERT INTO roles
VALUES (1, 'USER'),
       (2, 'ADMIN');

INSERT INTO users (id, created, email, first_name, image_url, is_active, last_name, modified, password, uuid)
VALUES (1, null, 'test@abv.bg', 'Test', null, false, 'Test', null,
        '30db02733fdc8b6d303dae72fc857c515a02baeb569521a0b2f1ca9042f5fad5e50997daf536eb49acc419bcf8c2b71f',
        '0adfd6a5-8d80-4c52-87e4-54b96fa8b6f8');


INSERT INTO users_roles (`user_id`, `role_id`)
VALUES (1, 1),
       (1, 2);
INSERT INTO users (id, email, fullname, password, phone, role, card_id)
VALUES (1, 'admin@admin.admin', 'Admin Admin', 'admin', null, 'ADMIN', null);

ALTER SEQUENCE user_seq RESTART WITH 2;
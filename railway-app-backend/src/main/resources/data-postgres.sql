
insert into role(role_name) values
    ('ROLE_ADMIN'),
    ('ROLE_REGULAR');


insert into address(city, street, street_number, zipcode) values
    ('Novi Sad', 'Laze Kostica', '10', '21000'),
    ('Novi Sad', 'Maksima Gorkog', '2', '21000'),
    ('Novi Sad', 'Laze Kostica', '10', '21000');

insert into balance_account(account_num, tokens_num, total_token_spending) values
    ('1234567890345', 4, 0),
    ('1230027890345', 0, 0);

insert into balance_transaction(time_stamp, tokens_num, money_spent, bought, currency, balance_account_id) values
     ('2023-07-01', 2, 2, true, 'USD', 1),
     ('2023-07-03', 2, 2, true, 'USD', 1);

insert into regular_user(id, email, password, full_name, gender, balance_account_id, address_id, verified, role_id, social_account) values
    (nextval('users_id_gen'), 'srdjan@gmail.com', '$2y$10$Pc9a5MzNrtCPlBMaJoyITuBc16s/F9oe4vdc78x0kO8cDlZpMB6RC', 'Srdjan Djuric', 1, 1, 1, true, 2, false),
    (nextval('users_id_gen'), 'srdjandjuric57@gmail.com', null, 'Srdjan Djuric', 1, 2, 3, true, 2, true);

insert into admin(id, email, password, full_name, gender, address_id, verified, role_id, social_account) values
    (nextval('users_id_gen'), 'admin@gmail.com', '$2y$10$Pc9a5MzNrtCPlBMaJoyITuBc16s/F9oe4vdc78x0kO8cDlZpMB6RC', 'Admin Adminovic', 1, 2, true, 1, false);


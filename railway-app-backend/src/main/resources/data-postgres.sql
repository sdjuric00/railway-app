
insert into address(city, street, number, zip_code) values
    ('Novi Sad', 'Laze Kostica', '10', '21000'),
    ('Novi Sad', 'Maksima Gorkog', '2', '21000');

insert into balance_account(account_num, tokens_num, total_token_spending) values
    ('1234567890345', 4, 0);

insert into balance_transaction(time_stamp, tokens_num, money_spent, bought, currency, balance_account_id) values
     ('2023-07-01', 2, 2, true, 'USD', 1),
     ('2023-07-03', 2, 2, true, 'USD', 1);

insert into regular_user(id, email, password, name, surname, gender, balance_account_id, address_id) values
    (nextval('users_id_gen'), 'srdjan@gmail.com', 'sifra123@', 'Srdjan', 'Djuric', 1, 1, 1);

insert into admin(id, email, password, name, surname, gender, address_id) values
    (nextval('users_id_gen'), 'admin@gmail.com', 'sifra123@', 'Admin', 'Adminovic', 1, 2);


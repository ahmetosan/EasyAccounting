insert into roles(insert_date_time, insert_user_id, is_deleted, last_update_date_time,
                  last_update_user_id, description)
values ('2021-09-09 00:00:00', 1, false, '2021-09-09 00:00:00', 1, 'Root User');

insert into companies(insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id,
                      address1, address2, email, enabled, establishment_date, representative, state, title,
                      zip, phone)
values ('2021-01-05 00:00:00', 1, false, '2021-01-05 00:00:00', 1, '15th Avenue',
        'Ronaldinho Street, Buenos Aires', 'welfare@yahoo.com', false, '2021-01-05', 'Tomy Hunt', 'WI', 'Hunt Techno',
        '33600', '3216549877');

insert into users(insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id, email,
                  firstname, lastname, password, enabled, phone, username, company_id, role_id,product_status)
values ('2021-09-09 00:00:00', 1, false, '2021-09-09 00:00:00', 1, 'michael@ct.com', 'Michael', 'Jordan',
        'Abc1', true,
        '5557962233', 'michael@ct.com', 1, 1,'ACTIVE'),
       ('2021-09-09 00:00:00', 1, false, '2021-09-09 00:00:00', 1, 'mike@ct.com', 'Mike', 'Smith',
        'Abc1', true,
        '5557962233', 'mike@ct.com', 1, 1,'ACTIVE'),
       ('2021-09-09 00:00:00', 1, false, '2021-09-09 00:00:00', 1, 'adam@ct.com', 'Adam', 'Smith',
        'Abc1', true,
        '5557962233', 'adam@ct.com', 1, 1,'ACTIVE');

insert into categories(insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id,
                       description, enabled, company_id)
values ('2021-01-05 00:00:00', 1, false, '2021-01-05 00:00:00', 1, 'Smart Phone', false, 1),
('2021-01-05 00:00:00', 1, false, '2021-01-05 00:00:00', 1, 'Electronics', true, 1),
('2021-01-05 00:00:00', 1, false, '2021-01-05 00:00:00', 1, 'TV', true, 1);

insert into client_vendor (company_name, phone, email, company_id, type, zip_code, address, enabled,
                           insert_date_time,
                           last_update_date_time, insert_user_id, last_update_user_id, is_deleted)
values ('Twitterlist', '4513577934', 'adot0@blinklist.com', 1, 'VENDOR', '174 59', '868 Johnson Trail',
        true, '2020-12-06', '2020-12-22', 1, 1, false);

insert into invoices(insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id,
                     invoice_date, enabled, invoice_number, invoice_status, invoice_type, company_id, client_vendor_id)
values ('2021-04-04 09:00:00', 1, false, '2021-09-02 09:00:00', 1, '2021-07-10 09:00:00', false,
        'P-INV-001', 'APPROVED', 'PURCHASE', 1, 1),
        ('2021-05-09 04:00:00', 2, false, '2021-10-02 05:00:00', 2, '2021-10-10 09:00:00', false,
        'S-INV-002', 'PENDING', 'SALE', 1, 1),
       ('2021-05-10 04:00:00', 2, false, '2021-10-03 05:00:00', 2, '2021-10-11 09:00:00', false,
        'S-INV-004', 'APPROVED', 'SALE', 1, 1),
       ('2021-04-04 09:00:00', 1, false, '2021-09-02 09:00:00', 1, '2021-07-10 09:00:00', false,
        'P-INV-003', 'PENDING', 'PURCHASE', 1, 1);

insert into product(insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id,
                    description, enabled, low_limit_alert, name, qty, unit, product_status,
                    category_id, company_id)
values ('2021-09-09 00:00:00', 1, false, '2021-09-09 00:00:00', 1, 'kitchen utils', true, 5,
        'Tefal Coffee Maker A26', 5, 'PCS', 'ACTIVE', 1, 1),
       ('2020-01-08 00:00:00', 2, false , '2021-09-09 00:00:00', 1, 'Smart phone', true, 5,
        'Apple', 5, 'PCS', 'ACTIVE', 1, 1),
    ('2020-01-08 00:00:00', 2, false , '2021-09-09 00:00:00', 1, 'TV', true, 5,
        'LG', 5, 'PCS', 'ACTIVE', 1, 1);


insert into invoice_products(insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id,
                             qty, tax, price, invoice_id, product_id, profit)
values  ('2021-09-09 00:00:00', 1, false, '2021-09-09 00:00:00', 1, 1, 10, 100, 1, 1, 0);

INSERT INTO "payment" ("id", "insert_date_time", "insert_user_id", "is_deleted", "last_update_date_time", "last_update_user_id", "amount", "institution_id", "is_paid", "month", "year", "company_id") VALUES (1, '2022-06-06 01:39:06.726483', 1, 'f', '2022-06-06 01:39:06.726483', 1, '1200', '1', 'f', 'JANUARY', '2022', 1);
INSERT INTO "payment" ("id", "insert_date_time", "insert_user_id", "is_deleted", "last_update_date_time", "last_update_user_id", "amount", "institution_id", "is_paid", "month", "year", "company_id") VALUES (2, '2022-06-06 01:39:06.726483', 1, 'f', '2022-06-06 01:39:06.726483', 1, '1200', '1', 'f', 'FEBRUARY', '2022', 1);
INSERT INTO "payment" ("id", "insert_date_time", "insert_user_id", "is_deleted", "last_update_date_time", "last_update_user_id", "amount", "institution_id", "is_paid", "month", "year", "company_id") VALUES (3, '2022-06-06 01:39:06.726483', 1, 'f', '2022-06-06 01:39:06.726483', 1, '1200', '1', 'f', 'MARCH', '2022', 1);
INSERT INTO "payment" ("id", "insert_date_time", "insert_user_id", "is_deleted", "last_update_date_time", "last_update_user_id", "amount", "institution_id", "is_paid", "month", "year", "company_id") VALUES (4, '2022-06-06 01:39:06.726483', 1, 'f', '2022-06-06 01:39:06.726483', 1, '1200', '1', 'f', 'APRIL', '2022', 1);
INSERT INTO "payment" ("id", "insert_date_time", "insert_user_id", "is_deleted", "last_update_date_time", "last_update_user_id", "amount", "institution_id", "is_paid", "month", "year", "company_id") VALUES (5, '2022-06-06 01:39:06.726483', 1, 'f', '2022-06-06 01:39:06.726483', 1, '1200', '1', 'f', 'MAY', '2022', 1);
INSERT INTO "payment" ("id", "insert_date_time", "insert_user_id", "is_deleted", "last_update_date_time", "last_update_user_id", "amount", "institution_id", "is_paid", "month", "year", "company_id") VALUES (6, '2022-06-06 01:39:06.726483', 1, 'f', '2022-06-06 01:39:06.726483', 1, '1200', '1', 'f', 'JUNE', '2022', 1);
INSERT INTO "payment" ("id", "insert_date_time", "insert_user_id", "is_deleted", "last_update_date_time", "last_update_user_id", "amount", "institution_id", "is_paid", "month", "year", "company_id") VALUES (7, '2022-06-06 01:39:06.726483', 1, 'f', '2022-06-06 01:39:06.726483', 1, '1200', '1', 'f', 'JULY', '2022', 1);
INSERT INTO "payment" ("id", "insert_date_time", "insert_user_id", "is_deleted", "last_update_date_time", "last_update_user_id", "amount", "institution_id", "is_paid", "month", "year", "company_id") VALUES (8, '2022-06-06 01:39:06.726483', 1, 'f', '2022-06-06 01:39:06.726483', 1, '1200', '1', 'f', 'AUGUST', '2022', 1);
INSERT INTO "payment" ("id", "insert_date_time", "insert_user_id", "is_deleted", "last_update_date_time", "last_update_user_id", "amount", "institution_id", "is_paid", "month", "year", "company_id") VALUES (9, '2022-06-06 01:39:06.726483', 1, 'f', '2022-06-06 01:39:06.726483', 1, '1200', '1', 'f', 'SEPTEMBER', '2022', 1);
INSERT INTO "payment" ("id", "insert_date_time", "insert_user_id", "is_deleted", "last_update_date_time", "last_update_user_id", "amount", "institution_id", "is_paid", "month", "year", "company_id") VALUES (10, '2022-06-06 01:39:06.726483', 1, 'f', '2022-06-06 01:39:06.726483', 1, '1200', '1', 'f', 'OCTOBER', '2022', 1);

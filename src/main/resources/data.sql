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
                  firstname, lastname, password, enabled, phone, username, company_id, role_id)
values ('2021-09-09 00:00:00', 1, false, '2021-09-09 00:00:00', 1, 'mike@ct.com', 'Michael', 'Jordan',
        'Abc1', true,
        '5557962233', 'mike@ct.com', null, 1);

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
        'P-INV001', 'APPROVED', 'PURCHASE', 1, 1);

insert into product(insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id,
                    description, enabled, low_limit_alert, name, qty, unit, product_status,
                    category_id, company_id)
values ('2021-09-09 00:00:00', 1, false, '2021-09-09 00:00:00', 1, 'kitchen utils', true, 5,
        'Tefal Coffee Maker A26', 0, 'PCS', 'ACTIVE', 1, 1);


insert into invoice_products(insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id,
                             qty, tax, price, invoice_id, product_id, profit)
values  ('2021-09-09 00:00:00', 1, false, '2021-09-09 00:00:00', 1, 1, 10, 100, 1, 1, 0);

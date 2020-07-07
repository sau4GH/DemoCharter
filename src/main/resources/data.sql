/* Insert seed data for CUSTOMER table */
insert into customer (id, name) values (1, 'Saurabh');
insert into customer (id, name) values (2, 'Shivani');
insert into customer (id, name) values (3, 'Samarth');

/* Insert seed data for customer_purchase table */
insert into customer_purchase (id, cust_id, item_amount, item, rewards, txn_date) values (1, 1, 101.10, 'Item_A', 52, '2020-06-01');
insert into customer_purchase (id, cust_id, item_amount, item, rewards, txn_date) values (2, 2, 96, 'Item_B', 46, '2020-06-02');
insert into customer_purchase (id, cust_id, item_amount, item, rewards, txn_date) values (3, 3, 201.55, 'Item_C', 254, '2020-06-11');
insert into customer_purchase (id, cust_id, item_amount, item, rewards, txn_date) values (4, 1, 10, 'Item_D', 0, '2020-05-21');
insert into customer_purchase (id, cust_id, item_amount, item, rewards, txn_date) values (5, 2, 60.40, 'Item_E', 10, '2020-05-15');
insert into customer_purchase (id, cust_id, item_amount, item, rewards, txn_date) values (6, 3, 150, 'Item_F', 150, '2020-05-01');
insert into customer_purchase (id, cust_id, item_amount, item, rewards, txn_date) values (7, 1, 80.50, 'Item_G', 31, '2020-04-11');
insert into customer_purchase (id, cust_id, item_amount, item, rewards, txn_date) values (8, 2, 75.50, 'Item_H', 26, '2020-04-17');
insert into customer_purchase (id, cust_id, item_amount, item, rewards, txn_date) values (9, 3, 65, 'Item_I', 15, '2020-04-05');
insert into customer_purchase (id, cust_id, item_amount, item, rewards, txn_date) values (10, 1, 125, 'Item_J', 100, '2020-03-11');

insert into tb_user (username, loginname, password, phone, address) values ('杰克', 'jack', '123456', '13920001616', '广州');
insert into tb_article (name, price, remark) values ('书1', 1.9, '描述1');
insert into tb_article (name, price, remark) values ('书2', 2.9, '描述2');
insert into tb_article (name, price, remark) values ('书2', 3.9, '描述2');
insert into tb_article (name, price, remark) values ('书2', 4.9, '描述2');
insert into tb_order (code, total, user_id) values ('xxxx1', 100.9, 1);
insert into tb_order (code, total, user_id) values ('xxxx2', 101.9, 1);
insert into tb_item (order_id, article_id, amount) values (1, 1, 1);
insert into tb_item (order_id, article_id, amount) values (1, 2, 1);
insert into tb_item (order_id, article_id, amount) values (1, 3, 2);
insert into tb_item (order_id, article_id, amount) values (2, 4, 2);
insert into tb_item (order_id, article_id, amount) values (2, 1, 1);

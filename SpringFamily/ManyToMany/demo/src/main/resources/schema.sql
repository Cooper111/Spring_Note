drop table if exists tb_item;
drop table if exists tb_order;
drop table if exists tb_user;
drop table if exists tb_article;
-- 用户表
create table tb_user (
  id int primary key auto_increment,
  username varchar (18),
  loginname varchar (18),
  password  varchar (18),
  phone varchar (18),
  address varchar (18)
);
-- 商品表
create table tb_article (
  id int primary key auto_increment,
  name varchar (18),
  price double,
  remark varchar (18)
);
-- 订单表
create table tb_order (
  id int primary key auto_increment,
  code varchar (32),
  total double,
  user_id int,
  foreign key (user_id) references tb_user(id)
);
-- 中间表，维护商品和订单多对多关系
create table tb_item (
  order_id int,
  article_id int,
  amount int,
  primary key (order_id, article_id),
  foreign key (order_id) references tb_order(id),
  foreign key (article_id) references tb_article(id)
);
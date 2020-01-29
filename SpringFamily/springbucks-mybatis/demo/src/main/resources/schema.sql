-- drop table t_coffee if exists;
-- drop table t_order if exists;
-- drop table t_order_coffee if exists;
drop table if exists t_coffee;
drop table if exists t_order;
drop table if exists t_order_coffee;

create table t_coffee (
  id bigint auto_increment,
  create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  name varchar(255),
  price bigint,
  primary key (id)
);
-- alter table t_coffee alter column CREATE_TIME set default '1970-01-01 10:00:00';
-- alter table t_coffee alter column UPDATE_TIME set default '1970-01-01 10:00:00';

create table t_order (
  id bigint auto_increment,
  create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  customer varchar(255),
  state integer not null,
  primary key (id)
);

-- alter table t_order alter column CREATE_TIME set default '1970-01-01 10:00:00';
-- alter table t_order alter column UPDATE_TIME set default '1970-01-01 10:00:00';

create table t_order_coffee (
  coffee_order_id bigint not null,
  items_id bigint not null,
  primary key (coffee_order_id, items_id),
  foreign key (coffee_order_id) references t_order(id),
  foreign key (items_id) references t_coffee(id)
);
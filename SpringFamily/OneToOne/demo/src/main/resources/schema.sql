drop table if exists tb_person;
drop table if exists tb_card;

create table tb_card (
  id int primary key auto_increment,
  code varchar(18)
);

create table tb_person (
  id int primary key auto_increment,
  name varchar(18),
  sex varchar(18),
  age int,
  card_id int unique,
  foreign key (card_id) references tb_card (id)
);

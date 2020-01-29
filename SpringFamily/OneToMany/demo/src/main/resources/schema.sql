drop table if exists tb_student;
drop table if exists tb_clazz;

create table tb_clazz (
  id int primary key auto_increment,
  code varchar (18),
  name varchar (18)
);

create table tb_student (
  id int primary key auto_increment,
  name varchar (18),
  sex varchar (18),
  age int,
  clazz_id int,
  foreign key (clazz_id) references tb_clazz (id)
);
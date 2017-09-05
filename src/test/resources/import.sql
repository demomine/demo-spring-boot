drop table if exists T_USER;

create table T_USER (id varchar(32) primary key,name varchar(32) ,age int(10) );

insert into T_USER (id,name,age) values ('1','lance',27);
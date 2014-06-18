drop table if exists person;

create table person (
  first_name varchar(256) not null,
  last_name varchar(256) not null,
  age int null
);
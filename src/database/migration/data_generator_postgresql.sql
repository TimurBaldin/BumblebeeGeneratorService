create database data_generator;

create sequence bufer.row_id;

create sequence users.client_user_id_seq
  as integer
  maxvalue 2147483647;

create table repository.stringtablebufer
(
  row_id           integer      not null
    constraint table_name_pkey
    primary key,
  testvalue        varchar(1000000),
  reportcolumnname varchar(100) not null,
  user_id          integer,
  alive            boolean
);

create table users.client
(
  user_id    serial       not null
    constraint client_pkey
    primary key,
  surname    varchar(100),
  name       varchar(100) not null,
  patronymic varchar(100),
  email      varchar(100) not null,
  password   varchar(120) not null,
  login      varchar(100) not null
);

create unique index client_login_uindex
  on users.client (login);
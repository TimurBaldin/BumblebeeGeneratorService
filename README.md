# Generator Bumblebee
## Version : 0.0.1 (prototype)
## Описание :
>`Сервис позволяет сгенерировать тестовые данные по заданным шаблонам и выгрузить в формате xlsx и csv`
## Настройка :
>`Для локального развертывания приложения необходима БД PostgreSQL`
``` postgresplsql
create database data_generator;

create sequence bufer.row_id;

create sequence users.client_user_id_seq
  as integer
  maxvalue 2147483647;

create table bufer.stringtablebufer
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
 ```
 >`Добавить конфигурационный файл для hibernate :`
 ``` xml
 <hibernate-configuration>
     <session-factory>
         <property name="hibernate.dialect">org.hibernate.dialect.PostgreSQL9Dialect</property>
         <property name="hibernate.connection.driver_class">org.postgresql.Driver</property>
         <property name="connection_pool_size">1</property>
         <property name="show_sql">false</property>
         <property name="hbm2ddl.auto">update</property>
         <property name="format_sql">true</property>
         <property name="use_sql_comments">false</property>
         <mapping class="com.rufus.bumblebee.Main.Tables.StringTableBufer"></mapping>
         <mapping class="com.rufus.bumblebee.Main.Tables.Client"></mapping>
     </session-factory>
 </hibernate-configuration>
 ```
 ## Структура приложения  :
 >1. `Описание`
 >2. `Описание`


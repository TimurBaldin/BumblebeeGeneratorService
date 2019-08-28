create database data_generator
	with owner postgres;

create schema REPOSITORY;

create sequence row_id;

alter sequence row_id owner to postgres;

create sequence container_id;

alter sequence container_id owner to postgres;

create table "REPOSITORY.CLIENTS"
(
    "ID"          numeric(20)  not null
        constraint clients_pk
            primary key,
    "LOGIN"       varchar(100) not null,
    "FIRST_NAME"  varchar(100) not null,
    "LAST_NAME"   varchar(100) not null,
    "SECOND_NAME" varchar(100) not null,
    "EMAIL"       varchar(100)
);

comment on table "REPOSITORY.CLIENTS" is 'Таблица с данными пользователя';

alter table "REPOSITORY.CLIENTS"
    owner to postgres;

create table "REPOSITORY.CONTAINERS"
(
    "ID"            numeric(20)  not null
        constraint containers_pk
            primary key,
    "NAME"          varchar(100) not null,
    "CREATION_DATE" timestamp(6),
    "CLIENT_REF"    numeric(200)
        constraint containers_clients_id_fk
            references "CLIENTS"
            on update cascade on delete cascade,
    update_date     timestamp(6)
);

comment on table "REPOSITORY.CONTAINERS" is 'Хранит контейнеры тестовых данных';

alter table "REPOSITORY.CONTAINERS"
    owner to postgres;

create table "REPOSITORY.TEST_DATA"
(
    "ID"            numeric(20) not null
        constraint stringtablebufer_pkey
            primary key,
    "VALUE"         varchar(1000000),
    "CONTAINER_REF" numeric(20)
        constraint test_data_containers_id_fk
            references "CONTAINERS"
            on update cascade on delete cascade
);

comment on table "REPOSITORY.TEST_DATA" is '//Таблица для тестовых данных';

alter table "REPOSITORY.TEST_DATA"
    owner to postgres;

create table "REPOSITORY.REPORTS"
(
    "ID"            numeric(20) not null
        constraint reports_pk
            primary key,
    "REPORT_TYPE"   varchar(100),
    "CREATION_DATE" timestamp(6),
    "STATUS"        varchar(100),
    "CONTAINER_REF" numeric(20)
        constraint reports_containers_id_fk
            references "CONTAINERS"
            on delete cascade
);

comment on table "REPOSITORY.REPORTS" is 'Таблица хранит информацию по отчетам';

alter table "REPOSITORY.REPORTS"
    owner to postgres;


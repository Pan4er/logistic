create sequence hibernate_sequence start 1 increment 1;

create table goods (
    id int4 not null generated always as identity,
    name varchar(255),
    price int4 not null,
    size float8 not null,
    store int4 not null,
    primary key (id)
    );

create table store (
    id int4 not null generated always as identity,
    currentf float8 not null,
    fullness float8 not null,
    store_name varchar(255),
    primary key (id)
    );

alter table if exists goods
add constraint store_goods_fk
foreign key (store) references store;



insert into store(currentf, fullness, store_name) VALUES (0,20,'Ангар');
insert into store(currentf, fullness, store_name) VALUES (0,12,'Подвал');


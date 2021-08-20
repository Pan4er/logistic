create table usrs (
      id int4 not null generated always as identity,
      login varchar(255) not null,
      password varchar(255) not null,
      primary key (id)
);

insert into usrs(login, password) VALUES ('tester1@mail.ru','pass13351');
insert into usrs(login, password) VALUES ('tester2@mail.ru','passls');
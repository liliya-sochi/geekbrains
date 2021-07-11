create table products (id bigserial primary key, title varchar(255), cost int);

insert into products (title, cost) values
('bread', 30),
('salt', 10),
('milk', 80),
('oil', 50),
('whiskey', 999);
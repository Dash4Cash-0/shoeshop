drop database if exists shoeshopdatabase;
create database shoeshopdatabase;
use shoeshopdatabase;

create table customer
(id int not null auto_increment primary key,
 name varchar(90) not null,
 address varchar(90) not null,
 city varchar(90) not null,
 userName varchar (90) not null,
 password varchar (90) not null
);

create table category
(id int not null auto_increment primary key,
 name varchar(50) not null
);

create table shoe
(id int not null auto_increment primary key,
 brand varchar(30) not null,
 colour varchar(30) not null,
 shoeSize int not null,
 price int not null,
 quantity int not null
);

create table customerOrder
(id int not null auto_increment primary key,
 customerId int not null,
 orderDate timestamp not null default current_timestamp,
 status varchar(30),
 foreign key (customerId) references customer(id)
);

create table cart
(customerOrderId int,
 shoeId int,
 quantity int,
 foreign key (shoeId) references shoe(id) on delete cascade,
 foreign key (customerOrderId) references customerOrder(id) on delete cascade
);

create table shoeCategory
(id int not null auto_increment primary key,
 categoryId int not null,
 shoeId int not null,
 foreign key (categoryId) references category(id) on delete cascade,
 foreign key (shoeId) references shoe(id) on delete cascade
);

create table outOfStock
(id int not null auto_increment primary key,
 shoeId int not null,
 outOfStockDate timestamp not null default current_timestamp,
 foreign key(shoeId) references shoe(id)
);

insert into customer(name,address,city,userName,password) value
    ('Nisse Svensson','Hemma 12','Stockholm','Nisse','pass'),('Sofia Johansson','Borta 13','Boden','Sofia','pass'),('John Doe','Bara hemma','Stockholm','John','pass'),
('Amanda Andersson','Bara borta','Karlstad','Amanda','pass'),('Bosse Boson','The moon 15','Uppsala','Bosse','pass'),('Olga olgsson','Hemmagatan 25','Luleå','Olga','pass'),
('Yuri Nakamura','Bortagatan 1337','Malmö','Yuri','pass');

insert into category(name) value
    ('Mens Footwear'),('Womens Footwear'),('Running shoes'),
('Sandals'),('Sport shoes');

insert into shoe(brand,colour,shoeSize,price,quantity) value
    ('DC','Black',44,1300,3),('DC','Red',45,1700,2),('Nike','Blue',35,2199,1),('Nike','Green',37,699,2),
('Adidas','Black',44,799,10),('ECCO','Black',38,299,10),('ECCO','White',41,299,1),('Reebok','Grey',43,899,2);

insert into shoeCategory(categoryId,shoeId) value
    (1,1),(5,1),(1,2),(5,2),(2,3),(3,3),(5,3),(2,4),(1,5),(5,5),(4,6),(1,6),
(2,6),(1,7),(2,7),(4,7),(1,8),(3,8),(5,8);
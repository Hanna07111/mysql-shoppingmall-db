-- customers
insert into customers (CustomerName, Address, City, PostalCode, Country) values 
('Alfreds', 'Obere Str. 57','Berlin',12209, 'Germany');
insert into customers (CustomerName, Address, City, PostalCode, Country) values ('Ana','Avda.Constitucion', 'Mexico D.F.', 15021, 'Mexico');
insert into customers (CustomerName, Address, City, PostalCode, Country) values ('Antonio', 'Mataderos 2312', 'Mexico D.F.', '15023', 'Mexico');
insert into customers (CustomerName, Address, City, PostalCode, Country) values ('Blauer','Forsterstr. 57', 'Mannheim', 68306, 'Germany');
insert into customers (CustomerName, Address, City, PostalCode, Country) values 
('Blondel', '24, place Kleber','Strasbourg', 67000, 'France');
insert into customers (CustomerName, Address, City, PostalCode, Country) values 
('Bolido', 'C/ Araquil, 67', 'Madrid', 28023, 'Spain');
insert into customers (CustomerName, Address, City, PostalCode, Country) values 
('Bon app', '12, rue des Bouchers', 'Marseille', 13008, 'France');


-- product
insert into product (ProductName, Unit, Price) values
('Chais', '10 boxes', 18.00);
insert into product (ProductName, Unit, Price) values
('Chang', '12 oz bottles', 19.00);
insert into product (ProductName, Unit, Price) values
('Syrup', '550 ml bottles', 10.00);
insert into product (ProductName, Unit, Price) values
('Seasoning', '6 oz jars', 22.00);
insert into product (ProductName, Unit, Price) values
('Gumbo Mix', '36 boxes', 21.35);

-- select * from customers;
-- select * from product;


-- orders
insert into orders values(10248, 5, '2023-05-21', 3);
insert into orders values(10249, 3, '2023-05-22', 5);
insert into orders values(10250, 4, '2023-05-22', 7);
insert into orders values(10251, 7, '2023-05-23', 6);
insert into orders values(10252, 1, '2023-05-25', 9);
insert into orders values(10253, 3, '2023-05-26', 5);
insert into orders values(10254, 2, '2023-05-28', 4);
insert into orders values(10255, 7, '2023-05-29', 2);
insert into orders values(10256, 2, '2023-05-30', 5);
insert into orders values(10257, 6, '2023-05-31', 6);
insert into orders values(10258, 7, '2023-05-31', 3);
insert into orders values(10259, 4, '2023-06-01', 5);
insert into orders values(10260, 1, '2023-06-01', 7);
insert into orders values(10261, 5, '2023-06-02', 6);
insert into orders values(10262, 6, '2023-06-03', 5);



-- orderdetails
insert into orderdetails values(1, 10248,1,4);
insert into orderdetails values(2,10249, 2,8);
insert into orderdetails values(3,10250, 3,50);
insert into orderdetails values(4,10251, 4,5);
insert into orderdetails values(5,10252, 5,10);
insert into orderdetails values(6,10253, 1,6);
insert into orderdetails values(7,10254, 2,7);
insert into orderdetails values(8,10255, 3,25);
insert into orderdetails values(9,10256, 4,3);
insert into orderdetails values(10,10257, 5,25);
insert into orderdetails values(11,10258, 1,10);
insert into orderdetails values(12,10259,2,40);
insert into orderdetails values(13,10260, 3,9);
insert into orderdetails values(14,10261, 4,5);
insert into orderdetails values(15,10262,5,12);


-- select * from orders;
-- select * from orderdetails;



-- customers schema
create table customers(
CustomerID int not null auto_increment primary key,
CustomerName varchar(30) not null,
Address varchar(20),
City varchar(20),
PostalCode int,
Country varchar(20));
create index CustomerID_index on customers(CustomerID);

-- product schema
create table product(
ProductID int not null auto_increment primary key,
ProductName varchar(20) not null,
Unit varchar(30),
Price float(2));
create index ProductID_index on product(ProductID);




-- orders schema
CREATE TABLE orders (
  OrderID int NOT NULL,
  CustomerID int,
  OrderDate DATE,
  DeliveryPeriod int,
  PRIMARY KEY (OrderID),
  FOREIGN KEY (CustomerID) REFERENCES customers(CustomerID) ON DELETE CASCADE
);

-- orderdetails schema
CREATE TABLE orderdetails (
  OrderDetailID int NOT NULL,
  OrderID int,
  ProductID int,
  Quantity int,
  PRIMARY KEY (OrderDetailID),
  FOREIGN KEY (OrderID) REFERENCES orders(OrderID) ON DELETE CASCADE,
  FOREIGN KEY (ProductID) REFERENCES product(ProductID) ON DELETE CASCADE
);

-- Create indexes
CREATE INDEX OrderID_index ON orders(OrderID);
CREATE INDEX OrderDetailID_index ON orderdetails(OrderDetailID);

-- Create the view
CREATE VIEW v AS
SELECT orders.OrderID, orders.CustomerID, orders.OrderDate, orders.DeliveryPeriod,
       orderdetails.OrderDetailID, orderdetails.ProductID, orderdetails.Quantity
FROM orders
JOIN orderdetails ON orders.OrderID = orderdetails.OrderID;


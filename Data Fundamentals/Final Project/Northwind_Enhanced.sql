#Drop the northwind_Enhanced Table if it exists
DROP DATABASE IF EXISTS northwind_Enhanced;
#Create the northwind_Enhanced database
create database northwind_Enhanced;
#Use the northwind_Enhanced database
use northwind_Enhanced;

#Drop the Countries table if it exists
drop table if exists Countries;
#Create the Countries table
create table Countries
(
    CountryID   int(10)     not null auto_increment,
    CountryName varchar(15) not null default '' unique,
    primary key (CountryID)
);

#Temporary table to Implement all countries from Employees, Suppliers, Orders, Customers
create table tempCountries
(
    CountryName varchar(15)
);

#Distinct countries from suppliers to have no repetitions from the same table
insert into tempCountries
select distinct Country
from northwind_orig.suppliers;

#Distinct countries from Customers to have no repetitions from the same table
insert into tempCountries
select distinct Country
from northwind_orig.customers;

#Distinct countries from Employees to have no repetitions from the same table
insert into tempCountries
select distinct Country
from northwind_orig.employees;

#Distinct countries from orders to have no repetitions from the same table
insert into tempCountries
select distinct ShipCountry
from northwind_orig.orders;

#insert distinct countries to have no repetitions in the Countries table
insert into Countries (CountryName)
select distinct CountryName
from tempCountries;
#drop the temporary table
drop table if exists tempCountries
;

#Drop the Category table if it exists
DROP TABLE IF EXISTS Categories;
#Create the Category table
CREATE TABLE Categories
(
    `CategoryID`   int(5)      NOT NULL AUTO_INCREMENT,
    `CategoryName` varchar(15) NOT NULL DEFAULT '' unique,
    `Description`  mediumtext  NOT NULL,
    `Picture`      varchar(50) NOT NULL DEFAULT '',
    primary key (CategoryID)
);

#Inserting all Category data from northwind_orig
insert into Categories
select *
from northwind_orig.categories
;

#Drop the Suppliers table if it exists
DROP TABLE IF EXISTS `Suppliers`;
#Create the Suppliers table
CREATE TABLE `Suppliers`
(
    `SupplierID`   int(10)      not null AUTO_INCREMENT,
    `CompanyName`  varchar(40)  NOT NULL DEFAULT '',
    `ContactName`  varchar(30)  NOT NULL DEFAULT '',
    `ContactTitle` varchar(30)  NOT NULL DEFAULT '',
    `Address`      varchar(60)  NOT NULL DEFAULT '',
    `City`         varchar(15)  NOT NULL DEFAULT '',
    `Region`       varchar(15)  NOT NULL DEFAULT '',
    `PostalCode`   varchar(10)  NOT NULL DEFAULT '',
    `CountryID`    int(10)      NOT NULL,
    `Phone`        varchar(24)  NOT NULL DEFAULT '' unique,
    `Fax`          varchar(24)  NOT NULL DEFAULT '',
    `HomePage`     varchar(255) NOT NULL DEFAULT '',
    primary key (SupplierID)
);

#Inserting all Supplier data from northwind_orig except CountrID
insert into northwind_Enhanced.Suppliers (CompanyName, ContactName, ContactTitle, Address, City, Region, PostalCode,
                                          Phone, Fax, HomePage)
select northwind_orig.suppliers.CompanyName,
       northwind_orig.suppliers.ContactName,
       northwind_orig.suppliers.ContactTitle,
       northwind_orig.suppliers.Address,
       northwind_orig.suppliers.City,
       northwind_orig.suppliers.Region,
       northwind_orig.suppliers.PostalCode,
       northwind_orig.suppliers.Phone,
       northwind_orig.suppliers.Fax,
       northwind_orig.suppliers.HomePage
from northwind_orig.suppliers
;

#Applying the countryID where northwind_Enhanced.Countries.CountryName = northwind_orig.suppliers.Country and northwind_Enhanced.Suppliers.SupplierID = northwind_orig.suppliers.SupplierID
update northwind_Enhanced.Suppliers , northwind_orig.suppliers , northwind_Enhanced.Countries
set northwind_Enhanced.Suppliers.CountryID = northwind_Enhanced.Countries.CountryID
where northwind_Enhanced.Countries.CountryName = northwind_orig.suppliers.Country
  and northwind_Enhanced.Suppliers.SupplierID = northwind_orig.suppliers.SupplierID
;

#Drop the Products table if it exists
DROP TABLE IF EXISTS Products;
#Create the Products table
CREATE TABLE Products
(
    `ProductID`       int(10)        not null AUTO_INCREMENT,
    `ProductName`     varchar(40)    NOT NULL DEFAULT '',
    `SupplierID`      int(10)        NOT NULL,
    `CategoryID`      int(5)         NOT NULL,
    `QuantityPerUnit` varchar(20)    NOT NULL DEFAULT '',
    `UnitPrice`       double         NOT NULL DEFAULT '0',
    `Discontinued`    enum ('y','n') NOT NULL DEFAULT 'n',
    primary key (ProductID)
);

#Inserting all Products data from northwind_orig that is needed for the Enhanced Database
insert into Products (ProductID, ProductName, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, Discontinued)
    (select northwind_orig.products.ProductID,
            northwind_orig.products.ProductName,
            northwind_orig.products.SupplierID,
            northwind_orig.products.CategoryID,
            northwind_orig.products.QuantityPerUnit,
            northwind_orig.products.UnitPrice,
            northwind_orig.products.Discontinued
     from northwind_orig.products
    );

#Drop the Shippers table if it exists
DROP TABLE IF EXISTS shippers;
#Create the shippers table
CREATE TABLE shippers
(
    `ShipperID`   int(10)     not null AUTO_INCREMENT,
    `CompanyName` varchar(40) NOT NULL DEFAULT '',
    `Phone`       varchar(24) NOT NULL DEFAULT '' unique,
    primary key (ShipperID)
);

#Inserting all shippers data from northwind_orig
insert into shippers
select *
from northwind_orig.shippers
;

#Drop the Customers table if it exists
DROP TABLE IF EXISTS Customers;
#Create the Customers table
CREATE TABLE Customers
(
    `CustomerID`   varchar(5)  not null default '',
    `CompanyName`  varchar(40) NOT NULL DEFAULT '',
    `ContactName`  varchar(30) NOT NULL DEFAULT 'Unknown',
    `ContactTitle` varchar(30) NOT NULL DEFAULT '',
    `Address`      varchar(60) NOT NULL DEFAULT '',
    `City`         varchar(15) NOT NULL DEFAULT '',
    `Region`       varchar(15) NOT NULL DEFAULT '',
    `PostalCode`   varchar(10) NOT NULL DEFAULT '',
    `CountryID`    int(10)     NOT NULL,
    `Phone`        varchar(24) NOT NULL DEFAULT '' unique,
    `Fax`          varchar(24) NOT NULL DEFAULT '',
    primary key (CustomerID)
);

#Inserting all Customers data from northwind_orig except CountrID
insert into Customers (CustomerID, CompanyName, ContactName, ContactTitle, Address, City, Region, PostalCode, Phone,
                       Fax)
select northwind_orig.customers.CustomerID,
       northwind_orig.customers.CompanyName,
       northwind_orig.customers.ContactName,
       northwind_orig.customers.ContactTitle,
       northwind_orig.customers.Address,
       northwind_orig.customers.City,
       northwind_orig.customers.Region,
       northwind_orig.customers.PostalCode,
       northwind_orig.customers.Phone,
       northwind_orig.customers.Fax
from northwind_orig.customers
;

#Applying the countryID where northwind_Enhanced.Countries.CountryName = northwind_orig.customers.Country and northwind_Enhanced.Customers.CustomerID = northwind_orig.customers.CustomerID
update northwind_Enhanced.Customers , northwind_orig.customers , northwind_Enhanced.Countries
set northwind_Enhanced.Customers.CountryID = northwind_Enhanced.Countries.CountryID
where northwind_Enhanced.Countries.CountryName = northwind_orig.customers.Country
  and northwind_Enhanced.Customers.CustomerID = northwind_orig.customers.CustomerID
;

#Drop the Employees table if it exists
DROP TABLE IF EXISTS Employees;
#Create the Employees table
CREATE TABLE Employees
(
    `EmployeeID`      int(10)     not null AUTO_INCREMENT,
    `FullName`        varchar(60) not null default '',
    `LastName`        varchar(20) NOT NULL DEFAULT '',
    `FirstName`       varchar(10) NOT NULL DEFAULT '',
    `Title`           varchar(30) NOT NULL DEFAULT '',
    `TitleOfCourtesy` varchar(25) NOT NULL DEFAULT '',
    `BirthDate`       datetime    NOT NULL,
    `HireDate`        datetime    NOT NULL,
    `Address`         varchar(60) NOT NULL DEFAULT '',
    `City`            varchar(15) NOT NULL DEFAULT '',
    `Region`          varchar(15) NOT NULL DEFAULT '',
    `PostalCode`      varchar(10) NOT NULL DEFAULT '',
    `CountryID`       int(10)     NOT NULL,
    `HomePhone`       varchar(24) NOT NULL DEFAULT '',
    `Extension`       varchar(4)  NOT NULL DEFAULT '',
    `Photo`           varchar(50) NOT NULL DEFAULT '',
    `Notes`           mediumtext,
    `ReportsTo`       int(10)              DEFAULT NULL,
    primary key (EmployeeID)
);

#Inserting all Employees data from northwind_orig except CountryID
insert into Employees (EmployeeID, LastName, FirstName, Title, TitleOfCourtesy, BirthDate, HireDate, Address, City,
                       Region, PostalCode, HomePhone, Extension, Photo, Notes, ReportsTo)
select northwind_orig.employees.EmployeeID,
       northwind_orig.employees.LastName,
       northwind_orig.employees.FirstName,
       northwind_orig.employees.Title,
       northwind_orig.employees.TitleOfCourtesy,
       northwind_orig.employees.BirthDate,
       northwind_orig.employees.HireDate,
       northwind_orig.employees.Address,
       northwind_orig.employees.City,
       northwind_orig.employees.Region,
       northwind_orig.employees.PostalCode,
       northwind_orig.employees.HomePhone,
       northwind_orig.employees.Extension,
       northwind_orig.employees.Photo,
       northwind_orig.employees.Notes,
       northwind_orig.employees.ReportsTo
from northwind_orig.employees
;

#Applying the countryID where northwind_Enhanced.Countries.CountryName = northwind_orig.employees.Country and northwind_Enhanced.Employees.EmployeeID = northwind_orig.employees.EmployeeID
update northwind_Enhanced.Employees , northwind_orig.employees , northwind_Enhanced.Countries
set northwind_Enhanced.Employees.CountryID = northwind_Enhanced.Countries.CountryID
where northwind_Enhanced.Countries.CountryName = northwind_orig.employees.Country
  and northwind_Enhanced.Employees.EmployeeID = northwind_orig.employees.EmployeeID
;

#concating the (TitleOfCourtesy, FirsName, LastName) to create the Full Name with TitleOfCourtesy
update northwind_Enhanced.Employees
set northwind_Enhanced.Employees.FullName = concat(northwind_Enhanced.Employees.TitleOfCourtesy,
                                                   northwind_Enhanced.Employees.FirstName, ' ',
                                                   northwind_Enhanced.Employees.LastName)
where northwind_Enhanced.Employees.EmployeeID = northwind_Enhanced.Employees.EmployeeID
;

#Drop the Orders table if it exists
DROP TABLE IF EXISTS `Orders`;
#Create the Orders table
CREATE TABLE `Orders`
(
    `OrderID`        int(10)     not null AUTO_INCREMENT,
    `CustomerID`     varchar(5)  NOT NULL DEFAULT '',
    `EmployeeID`     int(10)     NOT NULL,
    `OrderDate`      datetime    NOT NULL,
    `RequiredDate`   datetime             DEFAULT NULL,
    `ShippedDate`    datetime             DEFAULT NULL,
    `ShipVia`        int(10)     NOT NULL,
    `Freight`        double      NOT NULL DEFAULT '0',
    `ShipName`       varchar(40) NOT NULL DEFAULT '',
    `ShipAddress`    varchar(60) NOT NULL DEFAULT '',
    `ShipCity`       varchar(15) NOT NULL DEFAULT '',
    `ShipRegion`     varchar(15) NOT NULL DEFAULT '',
    `ShipPostalCode` varchar(10) NOT NULL DEFAULT '',
    `ShipCountryID`  int(10)     NOT NULL,
    primary key (OrderID)
);

#Inserting all Orders data from northwind_orig except ShipCountryID
insert into Orders (OrderID, CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate, ShipVia, Freight, ShipName,
                    ShipAddress, ShipCity, ShipRegion, ShipPostalCode)
select northwind_orig.orders.OrderID,
       northwind_orig.orders.CustomerID,
       northwind_orig.orders.EmployeeID,
       northwind_orig.orders.OrderDate,
       northwind_orig.orders.RequiredDate,
       northwind_orig.orders.ShippedDate,
       northwind_orig.orders.ShipVia,
       northwind_orig.orders.Freight,
       northwind_orig.orders.ShipName,
       northwind_orig.orders.ShipAddress,
       northwind_orig.orders.ShipCity,
       northwind_orig.orders.ShipRegion,
       northwind_orig.orders.ShipPostalCode
from northwind_orig.orders
;

#Applying the countryID where northwind_Enhanced.Countries.CountryName = northwind_orig.orders.ShipCountry and northwind_Enhanced.Orders.OrderID = northwind_orig.orders.OrderID
update northwind_Enhanced.Orders , northwind_orig.orders , northwind_Enhanced.Countries
set northwind_Enhanced.Orders.ShipCountryID= northwind_Enhanced.Countries.CountryID
where northwind_Enhanced.Countries.CountryName = northwind_orig.orders.ShipCountry
  and northwind_Enhanced.Orders.OrderID = northwind_orig.orders.OrderID
;

#Drop the order_details table if it exists
DROP TABLE IF EXISTS order_details;
#Create the orders_details table
CREATE TABLE order_details
(
    `ID`        int(10) not null AUTO_INCREMENT,
    `OrderID`   int(10) NOT NULL,
    `ProductID` int(10) NOT NULL,
    `UnitPrice` double  NOT NULL DEFAULT '0',
    `Quantity`  int(5)  NOT NULL DEFAULT '1',
    `Discount`  float   NOT NULL DEFAULT '0',
    primary key (ID),
    #creating 2 unique keys (OrderID, ProductID)
    UNIQUE KEY `Uidx_OrderID_ProductID` (`OrderID`, `ProductID`)
);

#Inserting all Order_details data from northwind_orig
insert into order_details (ID, OrderID, ProductID, UnitPrice, Quantity, Discount)
select northwind_orig.order_details.ID,
       northwind_orig.order_details.OrderID,
       northwind_orig.order_details.ProductID,
       northwind_orig.order_details.UnitPrice,
       northwind_orig.order_details.Quantity,
       northwind_orig.order_details.Discount
from northwind_orig.order_details
;

#Drop the Warehouses table if it exists
DROP TABLE IF EXISTS Warehouses;
#Create the Warehouses table
create table Warehouses
(
    WarehouseID      int(10)     not null auto_increment,
    WarehouseName    varchar(40) not null default '',
    WarehouseAddress varchar(60) not null default '',
    primary key (WarehouseID)
);

#Inserting Warehouse locations and names (Values)
insert into Warehouses (WarehouseName, WarehouseAddress)
values ('Dockside Warehouse', '190 Tomahawk run'),
       ('Airport Warehouse', '23 Liegnitzer street'),
       ('Central Warehouse', '200 Mumford street');

#Drop the Warehouses_Products table if it exists
drop table if exists Warehouses_Products;
#Create the Warehouses_Products table
create table Warehouses_Products
(
    ID           int(10) not null AUTO_INCREMENT,
    WarehouseID  int(10) NOT NULL check ( WarehouseID > 0 ),
    ProductID    int(10) NOT NULL check ( ProductID > 0 ),
    UnitsInStock int(5)  NOT NULL DEFAULT '0',
    UnitsOnOrder int(5)  NOT NULL DEFAULT '0',
    ReorderLevel int(5)  NOT NULL DEFAULT '0',
    primary key (ID)
);

#Dividing a Portion of products to Warehouse 1 where ProductID <= 25
insert into Warehouses_Products (WarehouseID, ProductID, UnitsInStock, UnitsOnOrder, ReorderLevel)
select 1,
       northwind_orig.products.ProductID,
       northwind_orig.products.UnitsInStock,
       northwind_orig.products.UnitsOnOrder,
       northwind_orig.products.ReorderLevel
from northwind_orig.Products
where ProductID <= 25
;

#Dividing a Portion of products to Warehouse 2 where ProductID <= 50 and ProductID > 25
insert into Warehouses_Products (WarehouseID, ProductID, UnitsInStock, UnitsOnOrder, ReorderLevel)
select 2,
       northwind_orig.products.ProductID,
       northwind_orig.products.UnitsInStock,
       northwind_orig.products.UnitsOnOrder,
       northwind_orig.products.ReorderLevel
from northwind_orig.Products
where ProductID <= 50
  and ProductID > 25
;

#Dividing a Portion of products to Warehouse 3 where ProductID > 50
insert into Warehouses_Products (WarehouseID, ProductID, UnitsInStock, UnitsOnOrder, ReorderLevel)
select 3,
       northwind_orig.products.ProductID,
       northwind_orig.products.UnitsInStock,
       northwind_orig.products.UnitsOnOrder,
       northwind_orig.products.ReorderLevel
from northwind_orig.Products
where ProductID > 50
;

#Supplier foreign constraint
alter table Suppliers
    add constraint FK_Supplier_CountryID
        foreign key (CountryID)
            references Countries (CountryID)
;

#Products foreign constraint
alter table Products
    add constraint FK_products_categoryID
        foreign key (CategoryID)
            references Categories (CategoryID),
    add constraint FK_products_supplierID
        foreign key (SupplierID)
            references Suppliers (SupplierID)
;

#Customers foreign constraint
alter table Customers
    add constraint FK_Customers_CountryID
        foreign key (CountryID)
            references Countries (CountryID)
;

alter table Employees
    add constraint FK_Employees_CountryID
        foreign key (CountryID)
            references Countries (CountryID),
    add constraint FK_employees_reports_to
        foreign key (ReportsTo)
            references Employees (EmployeeID)
;

#Orders foreign constraint
alter table orders
    add constraint FK_Orders_shipvia
        foreign key (ShipVia)
            references shippers (ShipperID),
    add constraint FK_Orders_ShipCountryID
        foreign key (ShipCountryID)
            references Countries (CountryID),
    add constraint FK_Orders_CustomerID
        foreign key (CustomerID)
            references Customers (CustomerID),
    add constraint FK_Orders_EmplyeeID
        foreign key (EmployeeID)
            references Employees (EmployeeID)
;

#order_details foreign constraint
alter table order_details
    add constraint FK_order_details_orderID
        foreign key (OrderID)
            references Orders (OrderID),
    add constraint FK_order_details_productID
        foreign key (ProductID)
            references Products (ProductID)
;

#Warehouses_Products foreign constraint
alter table Warehouses_Products
    add constraint FK_Warehouses_Products_WarehouseID
        foreign key (WarehouseID)
            references Warehouses (WarehouseID),
    add constraint FK_Warehouses_Products_ProductID
        foreign key (ProductID)
            references Products (ProductID)
;
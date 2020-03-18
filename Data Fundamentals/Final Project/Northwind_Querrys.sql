use northwind_enhanced;

#Checking the number of Country ID's in the Enhanced database if they equal the number of Country names of Original database
    #Employees Country Check
select count(northwind_enhanced.employees.CountryID) as CountryID ,
       (select count(northwind_orig.employees.Country)
            FROM northwind_orig.employees) as CountryName
from northwind_enhanced.employees;

    #Customers Country Check
select count(northwind_enhanced.customers.CountryID) as CountryID ,
       (select count(northwind_orig.customers.Country)
            FROM northwind_orig.customers) as CountryName
from northwind_enhanced.customers;

    #Supplier Country Check
select count(northwind_enhanced.suppliers.CountryID) as CountryID ,
       (select count(northwind_orig.suppliers.Country)
            FROM northwind_orig.suppliers) as CountryName
from northwind_enhanced.suppliers;

    #Orders Country Check
select count(northwind_enhanced.orders.ShipCountryID) as CountryID ,
       (select count(northwind_orig.orders.ShipCountry)
            FROM northwind_orig.orders) as CountryName
from northwind_enhanced.orders;

#Querry to show what has to be reordered and hasn't been ordered yet
select *
from northwind_enhanced.warehouses_products
where UnitsInStock < ReorderLevel and UnitsOnOrder = 0;

#Products that have a discount and are on stock
select ProductName , Discount
from northwind_orig.order_details
    inner join products p on order_details.ProductID = p.ProductID
where Discount > 0 and Quantity > 0
group by p.ProductID;

#Orders grouped by customer and the employee who helped them and shipment Information, ordered with Required Date ascending
select OrderID , c.ContactName , e.FullName as 'Employees', o.OrderDate , o.RequiredDate , o.ShippedDate , o.Freight , o.ShipName , c2.CountryName as 'Ship Country'
from northwind_enhanced.orders o
    inner join customers c on o.CustomerID = c.CustomerID
    inner join employees e on o.EmployeeID = e.EmployeeID
    inner join countries c2 on o.ShipCountryID = c2.CountryID
    inner join shippers s on o.ShipVia = s.ShipperID
group by c.CustomerID
order by RequiredDate ;

#Customer Information ordered by Contact name with their according country location
select CustomerID , CompanyName , ContactName , ContactTitle , Address , City , PostalCode , CountryName
from customers
    inner join countries c on customers.CountryID = c.CountryID
order by ContactName;

#Product Information & its Location in which warehouse + WareHouse address
select products.ProductID , ProductName , c.CategoryName , QuantityPerUnit , UnitPrice , w.WarehouseName as 'WareHouse' , w.WarehouseAddress as 'Warehouse Location'
from products
    inner join warehouses_products wp on products.ProductID = wp.ProductID
    inner join warehouses w on wp.WarehouseID = w.WarehouseID
    inner join suppliers s on products.SupplierID = s.SupplierID
    inner join categories c on products.CategoryID = c.CategoryID;
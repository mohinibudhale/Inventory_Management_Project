# itmd4515-f23-fp-mohinivbudhale
 README
                                                                            Mohini Budhale
                                                                ITMD: 515 Advanced Software Programming
                                                                          Lab 4 - ORM and JPA

Business Domain: Inventory Management System for Warehouse

Brief about system:

An Inventory Management System is a software solution designed to efficiently track and control a company's inventory of goods. It works by maintaining a real-time database of product information, including stock levels, item descriptions, and supplier details. When inventory is received, the system updates its records, and when items are sold or used, it deducts them from the inventory. This ensures that businesses have a clear view of their stock levels at all times.

Why:

I've chosen this domain because it offers a rich ecosystem of interconnected entities, making it a great platform for deepening my understanding of relational databases. Working on this project provides a comprehensive learning experience in software development, user interface design, and data analysis. An Inventory Management System for Warehouses provides an opportunity to work on real-world software development challenges, such as designing a database schema for efficient data storage, implementing algorithms for demand forecasting, and creating a user-friendly interface for inventory monitoring. This domain offers valuable hands-on experience in software development, database management, and data analysis, which are highly transferable skills sought after by employers in various industries. 


Other Entities in My Business Domain:
- Accounts- I used this entity for LAb 4 It has Customer and Vendor as Account Type
- Customer - Customer is someone who purchases the Inventory Important Entity in project
- Vendor - Someone from whom we purchases the Inventory Important Entity in project
- Inventory - keeps track of Products and its stocks
- Product - Details of Individual Product, category,brand, cost etc. product is linked with category and brand table
- Brand - all the brands of the products
- category - category of product
- orders - stores all the orders
- order_Items - saves individual item order and is linked with order table
- Payment - details about the order payment

  
  
  Brief Relation between tables I have explained in below diagram
  ![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/a5e7952a-86d6-4f41-a086-036a64693847)



- Accounts: This entity represents both Customers and Vendors, who are essential in the project. Customers purchase inventory, while Vendors supply inventory.
- Inventory: The Inventory table is central to the system, as it keeps track of products and their stock levels. It likely has relationships with other tables, such as Products and Orders.
- Product: The Product table contains details about individual products, including their category and brand. It is linked to the Category and Brand tables to associate products with their respective categories and brands.
- Brand: This table holds information about all the brands of the products in the inventory. It's linked to the Product table to specify the brand of each product.
- Category: The Category table classifies products into different categories. It's linked to the Product table to categorize each product.
- Orders: The Orders table stores information about all the orders placed, likely including customer/vendor details, order dates, and payment information.
- Order_Items: This table records individual items within orders and is linked to the Orders table to associate items with specific orders.
- Payment: The Payment table contains details about order payments, such as payment methods, amounts, and transaction statuses.

Screenshots of Test Cases:

Database CRUD Operations JPA Test:

Creare Operation is being tested in below diagram:
![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/30d5ee95-5840-41b7-82e9-4bf44a8ff5f3)


Read Operation is being tested in below diagram:
![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/d71e2311-4608-477e-a92b-eb67579ea8a6)


Update Operation is being tested in below diagram:
![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/95bcf689-3e75-4061-abfd-2164747d9bae)


Delete Operation is being tested in below diagram:
![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/a9dfd78e-9949-4652-8283-8fdb6cf9bc86)


Accounts Bean Validation constraints tested below:
If account is Valid:

![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/6720b1b0-d4c7-4c2d-8053-7d3780549dc1)

If Account is Invalid:

Passing all bad values and expecting 5 violations to pass the test
![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/11a2ca3b-9c30-44f4-9097-aecaa4455dd8)


I have used abstract class to keep the generic functinality in one class so that I can reuse it.

database screenshot:
![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/07ad307c-6af6-4bb4-9cbd-d3bda348c27e)



Thank you,
Mohini Budhale.









  

  
  


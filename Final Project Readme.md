
Project Summary
o Use this section to describe the project in your own words.
This project is an example of an application that any small business that offers products can utilize to manage their products and sales data.
There are two main user-types, Admin, and Customer. Each user-type has their own UI portal.

o How did you fulfill each requirement of the specification?
Used JSF template page for navigation bar
Used JSF composite components for performing CRUD operations on accounts and products.
Used different application layers to perform different functionalities like fetching data from service-layer, providing it to controller, and displaying it to view xhtml file.
Admin functionality: implemented admin functionality to create a user based on different account types (customer/employee/admin). 
Sign-in functionality: ability to log in with the user created by admin.
Customer functionality: provided functionality for browsing product entity, selecting that entity, and populating data from service-layer to the view.
On admin and customer portals, data is displayed using tabular formats (list of accounts, list of products, list of orders).
Displaying associated customer orders based on their associated orders only.
Performed adding deleting and modifying entities (account, product) in admin portal.
Customer is not allowed to modify any other entity. It can only place orders.
The navigation is done using the navigation bar. Home, products, accounts, orders.
Used h: message bean validation to display appropriate validation messages.
Used css with bootstrap to make the UI look better.
Enough sample data is provided to test all the functionalities. 
Made use of exception handling to handle the errors.

• Design
o Use this section to describe the design of your final project’s functionality.
The project is designed in three different layers. Domain, web, and services. The domain is the presentation layer. Web is the controller layer. Service is the service layer. All the web pages are stored and separated in their appropriate folders (admin pages in admin folder, customer pages in customer folder, etc). 

Project includes different source packages for configuration. I.e. Config package is for configuration, domain is for domain, resources for resources, security for security, service for service, web for web.

o What functionality did you implement?
For Admin users, the following functionality exists:
-Create Account, Read Account, Update Account, Delete Account
-Create Product, Read Product, Update Product, Delete Product
-View all orders from all customer-type accounts.

For Customer users, the following functionality exists:
-Log-in to Account
-Create Order, Read Order, Delete Order

Order-form functionality:
-Searchable input field that queries database and returns selectable-matching result
-Based on matching result, fetch and display product unit, brand, category, and price
-Based on quantity input field, dynamically display total cost to customer on key-up event

o How does navigation flow from one functional area to another?
All users land on a home page. There is a navigation bar that dynamically adjusts based on which user-type is logged in. For example, if an Admin user is logged in, they can view the Home, Product, Accounts, and Order sections. However, if a Customer user is logged in, they may only view the Home, and Customer sections. Additional functionality exists within these sections. For example, an Admin user may navigate to the Product section, and proceed to Add Product, which brings them to a form where product creation occurs. Upon creation, the Admin is then redirected to the Product list section, which shows all created products. Similarly, a Customer user may navigate to the Customer section, where they proceed to Place Order, which brings them to a form for order creation. After an order is successfully created, the Customer is redirected to the Customer section, which shows all their orders.  

o Also, use this section to list any extra credit you have implemented, and how the
additional features were incorporated into your design, including your insights.
Incorporated PrimeFaces component library to auto-complete / search the product list when customer tries to place an order. 

• Requirements (Installation, Compile, Runtime, Database, etc)
o Use this section to explain how I should install, build and run your project.
Extract project zip file, open the project in NetBeans, clean and build the project, and run.

o Write it step-by-step as if I do not have any knowledge of how to do so.
Extract the project zip file
Open the project in NetBeans from the location where the extracted zip file is.
Right click on the project, and clean and build the project.
Go to services section in NetBeans and connect to itmd4515 database with login and password (itmd4515 / itmd4515).
Go to Servers and start the Payara server.
Run the project

o What are the versions of the tools, libraries and API’s used in your project?
Jakarta EE api (dependencies/libraries in pom.xml file of project)

• Screen Captures
o Include enough screen captures to illustrate your working project.
Screen captures:

Sign In page:

Login with cred username admin password admin
![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/0beabf3f-eb10-4acc-a34c-4c4d059c5fac)

If we dont enter any of the field it gives error 

![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/57ce36b1-0ad9-4c4f-a027-43207fc5d970)

Welcome page/ Home Page 

![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/80120b50-f69c-495d-be91-d8897621b573)


To navigate click on the options in navigation bar 
when click on accounts

![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/ed79b88e-2cfa-4d79-ae4e-53fc0446912d)


when click on products

![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/a3ee7b02-f98c-44bb-8467-f1bd9394dc18)

when click on orders

![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/b73fbe8e-63da-4cd1-b805-2082f182f0b8)


back to accounts when click on new account its scrollable page you can scroll if you dont see any fields

![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/da5f26e1-e2a0-4ef8-8800-7816210f5285)

creating a new account/user please choose account type as customer
username- customer i entered and password customer
![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/1c7a90d5-a995-440c-892f-47bbffd90e84)


when i click on add account button it takes me to accounts page where i can see the latest added account/user

![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/315f8385-fe96-4e70-9e7a-375d538cd4b2)

now logging out and trying to login through user customer

![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/721f8350-8d32-470a-bb87-332e86990e6a)

this is customer welcome/home page 

![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/39b7b471-715b-4acb-aeb7-6534224f0c79)

now navigate to orders in navigation bar
 you can see for this newly  created user there are no orders
 ![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/6c0cf413-fb9e-4d9b-949a-f21947e28448)


 now click on place order
![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/f562a40f-2efc-4fe5-b355-38355ec36f18)


now search for product pretzels type p and you will see product in dropdown primefaces dropdown then click on product and it will populate all its information then click on quantity and you can see total price is automatically getting calculated 

![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/46405503-7823-4784-8c2f-4536b22482d0)

and click on place order and you will see order in orders section 
now you can see one order 
![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/547905fe-3f86-440c-9655-67f3d98fa186)

do not delete a user/account who already placed a order
now logging out from cutomer and logging in as admin if you go to orders section you will see a new order added

![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/8ae7d57a-a409-45df-8c80-992ebb4805cc)



Admin can create a account, update it and delete it 

here are read account,update,delete pages

read account

![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/b951f4d3-5d9a-4ecb-8129-fd787370c010)

update account

![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/cdf2c7db-8a09-4d75-bb71-103762838c3a)

you can see star plaza1 is updated 
![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/680d2bad-1a9a-425d-bf2f-609e0882c330)

lets create a new demo account and delete it without placing any order

![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/6c30b238-3f54-42b0-8ba0-1c4bcf218dfa)


now lets delete demo1234

![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/5bef2d5d-357d-400c-bed9-a5fb3b28004a)


when you click on delete account it deletes the account you can see that in accounts section

![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/2ac4ea9d-7487-40f3-a887-6e9b3a661b8c)


Same we can do for products we can add a product

i have added this chips product
![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/403074eb-baf0-418d-a851-fa2503e4d4c0)
 and we can update it and delete it

 


• Expected Results/Known Issues
o Use this section to describe any known issues with your project. Nothing is ever
perfect, and it is better to document issues than ignore them.

When user attempts to login with incorrect credentials, project does not validate credentials in front-end layer. It navigates to the error page, and validates the user credentials in the backend. Delete order/product functionality may not work as I’m unable to fetch pk id from UI to delete the particular data.

o Also, provide me with a known working test script to follow when I run your
project. For example:
 
1.Login as admin with password admin
2.You can create user from admin portal and log in with that user. Otherwise, for customer portal, log in with these credentials: user “customer1” and password “customer1”
3.When placing order, search with the product name “Pretzels”, or “Whole Milk”, or “Lays Chips” 
4. Please do not delete user who placed orders you can create new account and delete it.

• Development Insights
o Use this section to tell me anything you want about the project, and your
design/development experience during the project.
The project mainly focuses on creating a user interface with different user groups and roles (admin and customer). The development experience felt like a rollercoaster. I encountered so many issues and spent substantial amounts of time troubleshooting and debugging. This left me with less time to focus on additional functionalities and frontend design. 

o What did you learn?
The issues mentioned above helped me learn how service-layer, controller-layer and web pages interact with each other. I learned about JSF composite components and how it is useful to make the code reusable. I learned about PrimeFaces, xhtml, Java annotations (request scoped, application scoped, named). Named queries, which makes it easy to fetch the data from database.  

o Was there something you would like to explore further?
Yes, initially I intended to create an inventory table where the product-stock levels would increment and decrement based on purchases and sales data. However, this would have required many more entities and quickly widened the scope of the project beyond what was practical given deadlines. Also, I would like to explore and learn more about SpringBoot.



o What did you like, or not like?
I enjoyed completing this project. Unfortunately, I didn’t like how much time it took to resolve the issues encountered along the way. And I didn’t like that I wasn’t able to implement a lot of functionality that I originally planned to include. 







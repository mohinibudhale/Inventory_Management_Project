
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









• Expected Results/Known Issues
o Use this section to describe any known issues with your project. Nothing is ever
perfect, and it is better to document issues than ignore them.
When user attempts to login with incorrect credentials, project does not validate credentials in front-end layer. It navigates to the error page, and validates the user credentials in the backend. Delete order functionality may not work as I’m unable to fetch pk id from UI to delete the particular data.

o Also, provide me with a known working test script to follow when I run your
project. For example:
 Login as admin with password admin
You can create user from admin portal and log in with that user. Otherwise, for customer portal, log in with these credentials: user “customer1” and password “customer1”
When placing order, search with the product name “Pretzels”, or “Whole Milk”, or “Lays Chips” 

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







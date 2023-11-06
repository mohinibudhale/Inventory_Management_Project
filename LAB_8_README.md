                                            Lab 8 - Web Application Security
                                            Project Inventory Management System

User - For my Project the main users are Customer, Admin/Manager , Employees
Groups - There are three groups Customer Group, Employee Group and a Admin Group

In my Business Domain I have a account entity which stores all the Information about Customers, Vendors and Employees,
I Associated User to account entity based on Condition, only account with type Customer/Employee are  allowed to become User.

Insted of having three different entities I preffered to save all Infomation in one account entity and separate them with enum account_type.

Here are my Users and their related group and Credentials 
1. Username - admin , Password - admin
   admin User is part of ADMIN_GROUP and EMPLOYEE_GROUP
2. Username - customer1 , Password - customer1
   customer1 user is part of CUSTOMER_GROUP
   same with customer2
3. Username - employee1 , Password - employee1
   employee1 user is part of EMPLOYEE_GROUP and ADMIN_GROUP
   but employee2 user is part of just EMPLOYEE_GROUP


Screenshots of Authentication Work:

![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/a33aefa2-3eca-4bf2-a3f9-78cd037162d6)

![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/d4c2680f-7b7b-4237-81b7-ce6817caba1d)

Error Page After wrong Password

![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/305616b8-ca5e-4b3b-9f66-be0e8aa19c82)

Payara Log

![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/b4758779-802d-492c-a92c-4f36adf806fe)


Success using right cred 

![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/b44c8b8a-139d-4d6d-a665-c0e469132c78)

In Payara log I know who the authenticated user is (admin)
![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/c389a0c3-d47b-43e6-be3e-f7c0d70df01c)



when click on admin it navigates to admin welcome page 
![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/55f4b59c-9ed3-446b-ad16-d760d0e37ebc)

By Navigating through all the links in welcome page I gets to know about all the user access, If try to access page with wrong role it redirects to error page 

![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/ee46b67d-100c-4149-aa36-6fec5c2d8cca)

Logout Button takes back to login.xhtml page 

![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/db40c9b8-4ba6-4a79-939c-44ce7d0575c6)

Successful Login and access page for each

Admin Access Page

![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/f0cbbdad-2616-4c59-95f6-56a4565a75f3)


Customer Access Page

![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/f9e0cb1d-8414-41e6-a287-75a0f159b685)

Employee Access Page

![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/3e10aee1-ae03-4090-9dcd-f6b0a8739471)


My Experience with Lab 8 

It took me time to figure out how I can relate my business entity as user, as I did not wanted to create multiple Customer/Vendor/Employee entities, I did a conditional User creation for this Lab,
The security authentication was easy to understand authorization was quite tricky, JSR-375 security is something new and interesting I learned, Surprisingly I did not have any code error during the lab (happens very rare), Overall It is a good learning lab experience and I am more confident about building and finishing my final project. 

Ciatation:
I did use some Instructor Design Patterns


Thank you Professor!
















                                        

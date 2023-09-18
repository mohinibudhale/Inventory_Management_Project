                                                README
                                            Mohini Budhale
                                ITMD: 515 Advanced Software Programming
                                Lab 3: Web Applications, Servlet and JSP

In this lab the main URL JSP Page can be opened using any of these url patter "/address", "/a", "/addr".
and you can scroll through the webpage

Initial Landing Page:
![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/ecfc7f34-9e68-4696-8f17-8f13ea65040b)

All user field Validation error:
![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/ae5ff63b-8a83-4bdd-8e0a-cfe5594a6754)

![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/1cd09db4-8797-42ef-9f63-a59fb43e6a23)

![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/6eecbc3e-b3aa-49dc-b5c2-b01f8e734a51)

If we Put incorrect phone number It wont accept until phone number is 10 digit

![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/59f6e800-2ac3-4261-a555-564c86180b8a)


and if we put any symbol or brackets validation will trim it and only forward the numbers into database table


![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/51825595-4f99-4796-8a46-3c2f3e9f2f5f)

![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/ee902a60-8862-4972-bdce-f199583da70f)


Database Screenshot:


![image](https://github.com/itmd4515/itmd4515-f23-fp-mohinivbudhale/assets/143038221/c3fb9ce1-6c5e-40ed-9528-0d6190c40899)


	Per my understanding forward operation performed at server side, client sends request to the server, server performs operation and sends back the response whereas redirect operation is performed at client side, client web browser makes new HTTP request to redirects to new URL.

In forward the URL remains the same where in Redirect URL changes to new diff URL, Forward is used mostly in projects with MVC lifecycle where there are no big data operations, Redirect is used when there is change in data or information needs to be used in another function.

	I’ll validating user submissions using the custom validation where I can create a validation class to add all my validations, and servlet will handle user submissions, hence I can use the validation methods from validation class to check the submitted data. If validation fails, I can handle errors.
I can set error messages in the servlet to indicate the validation errors. These error messages can be displayed to the user on the form page to inform them of the issues with their input.

	When working on a large and complex application that involves hundreds of different types of data (entities) and requires thorough and intricate validation rules, it's essential to use a well-structured and standardized approach. In this case, employing tools like the Bean Validation API, Expression Language (EL), and validation constraints is strongly advised, and here's why:
Modular and Reusable Rules: By using the Bean Validation API and validation constraints, you can create validation rules that are organized into distinct, reusable units. These rules can be applied to various parts of your application, promoting code reuse, and reducing duplication.
Centralized Validation Logic: You can centralize all your validation logic, making it easier to manage. Instead of spreading validation rules throughout your code, you define them in one place, making maintenance simpler.

	We have used custom validation logic to validate and for the database connection we used Jakarta EE annotations.@ ApplicationScoped annotations. And @DataSourceDefinition This annotation is used to define a data source for the application. It configures the connection properties needed to connect to a database.



Thank you!





















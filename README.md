# DemoCharter

Demo App provides REST api endpoints to store/manipulate Customer and Purchase transaction data in H2 In-Memory database. App pre-populates seed data in following in-memory tables at application startup -

- CUSTOMER
- CUSTOMER_PURCHASE

# Steps to build and deploy

- Go to source code root folder (/DemoCharter)
- Run following maven command to BUILD the App -> mvn clean install
- Run following maven command to START the App -> mvn spring-boot:run
	
# Test if Application is up and running

Test URL: http://localhost:8083/api/v1/customers
	
# Accessing H2 console

Once application is started, use following url to access H2 DB console - http://localhost:8083/h2-console 

Enter following to login -

- JDBC URL: jdbc:h2:mem:charterdb
- User Name: sa
- Password: password 

# Test App using Postman

Import postman collection provide with the source code. Following REST api are exposed and can be tested -

- Get Customers List
- Add Customer
- Remove Customer
- Get All Purchase Transactions
- Add Purchase Transaction
- Get Purchase Transaction by Customer Id
- Get Purchase Transactions by Customer Id and Month
- Get Customer Total Rewards
- Get Customer Monthly Rewards
- Delete Purchase Transaction by Id

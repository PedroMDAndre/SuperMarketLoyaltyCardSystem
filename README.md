# Supermarket Loyalty Card System
## Description
For this task you are required to design and implement a RESTful API, backing service and data models to create a simple
Supermarket Loyalty Card System where interaction with this API is made using HTTP requests. For the system you can use any
technology within the Java ecosystem you feel comfortable with, but using Java with Spring Boot is recommended. Swagger documents
are required to help users discover and use the API. Scripts may be used to seed your database with initial data (e.g. sample accounts
and cashier details).

User Accounts are created by supplying the name, surname, a mobile number and an id card number. Both the mobile number and the
id card number can be used to fetch the user accounts uniquely. Once an account is created, one can add 10 purchase points for every
50 euros spent or can redeem the points as either a discount on the last sale (1 euro for every 100 points) or as a packet of water (1
packet for every 100 points). Purchase points are calculated by the API which accepts the total amount spent at the cash. The points to
redeem are accepted in multiples of 100.

All operations (both purchase and redeem) require that the cashier's ID (that can be checked against a list of cashier ids) is entered
every time for audit trail purposes. A specific call will return all unclaimed (positive) balances for existing users. APIs will use JSON
payloads when applicable and error codes need to be returned when operations do not succeed. In order to make the application more
portable, an in-memory database as a backing store is recommended.

As mentioned, the application needs to be built using Java. Use Maven for dependency management and include unit testing for your
service layer. The source code can be committed on any version control repository of your choice as long as it's private and we're given
access to it.

## Database H2 console:
http://localhost:8081/h2-console/ <br>
username: sa<br>
password: password

## API documentation:
http://localhost:8081/v3/api-docs/

## Swagger UI
http://localhost:8081/swagger-ui.html

# Installation

Please start of by installing maven if you don't already have it :
https://maven.apache.org/install.html

This is a spring-boot application with boot start plugin, to run the application please make sure that port 8080 is available then run the command : 
```
mvn spring-boot:run
```
* Feel free to change the port by altering the server.port info on application.yml.
* Application comes with some default data present as requested. Feel free to change the `stock.csv` file under resources for different boot&showcase data

# Extras

* Added liquibase and turned off the jpa auto ddl properties for early control and easier migration to real databases.
* Separated the test and application context early on, because that would definitely be needed going forward.
* Added swagger support which is available under: http://localhost:8080/swagger-ui.html


# Future changes

* I would prefer to separate the service layer in future, when there is more than crud and actual business logic.
* I could have gone for spring rest-data for this example but that would have made it harder to separate going forward. I would prefer to use that tech for showcasing only.


Overall tried to leave it simple, but easy to build on top off later on with integral tech integration.
# Auctions

### Compiling application and running tests
Application requires Java 11.

You can run application with maven:

```java
mvn clean compile
```

You can run test with:
```java
mvn test
```


### Setting up local environment

* Make sure your MySQL database is running.
* Create database by running script [create_database.sql](src/main/resources/db/create_database.sql) as a root user.

* Then you can start up application using maven:
  ```
  mvn spring-boot:run
  ```



When started application with apply all migrations from [src/main/resources/db/migrations](src/main/resources/db/migrations) folder using Flyway.


### Test environment
Test environment is available at []().


### Collaborating
Create a new branch from main using format: `feature/description-of-your-branch`.



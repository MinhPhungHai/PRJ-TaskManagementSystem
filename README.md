1. create application-local.properties file in dir: src/main/resources/application-local.properties
2. add the following:
```
   #Database credentials
   spring.datasource.url=jdbc:postgresql://localhost:5432/{database name}
   spring.datasource.username={dbms username}
   spring.datasource.password={dbms password}
```
3. run Spring Boot application
```
mvn spring-boot:run
```
4. insert roles into database
```
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MANAGER');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
```
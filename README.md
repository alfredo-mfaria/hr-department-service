# HR DEPARTMENT SERVICE


hr-department-service is REST based microservice that enables an HR department to  manage developers and the teams they belong to.

## Description

The colleagues in the HR department should be able to create a new developer entity, add the developer to existing teams and also remove the developer from teams.

A team can exist without developers but a developer must be assigned to a team.


## Installation

Packaging the application:
```bash
mvn clean package
```
Run application:
```bash
mvn spring-boot:run
```
From the target folder, run the application jar:
```bash
java -jar hr-department-service-0.0.1-SNAPSHOT.jar
```

Build a docker image:
```bash
docker image build -t hr-department-service:1.0 .
```
Run docker image as a container
```bash
docker run -p 8080:8080 hr-department-service:1.0
 ```

## API
Check the /swagger-ui.html path while running the service for a more detailed explanation of the api.

## Tech Stack
* Maven 3.*
* JAVA11
* Spring-boot 2.*
* Lombok
* H2 in memory database
* Open-api 3
* Postman collection
* Docker

## Authors
Alfredo Faria

## License
[MIT](https://choosealicense.com/licenses/mit/)

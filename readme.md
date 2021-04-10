# Spring multipart/form-data in a Rest API

## About

This is an example of a Spring multipart/form-data and JSON Rest API

## Technical Stack:

- Java 8
- Maven 3.6+
- Spring boot 2.4.5.RELEASE
- Spring Boot Actuator for exposing management endopoints
- Lombok abstraction
- Open API documentation (available at /swagger-ui.html)
- REST API model validation 

## Installation
This application is configured with two SPRING profiles:
- "local": For local dev environments.
- "cloud": For dockerized environments, where application properties are set by ENV variables.

Start the spring boot application and browser the swagger/openapi UI http://localhost:8080/actuate/swagger-ui.html


# Customer Portfolio WebFlux

This application, `portfolio-service`, is a Java 17-based project built using Spring Boot. It provides endpoints for managing a customer portfolio.

## Description

The `portfolio-service` focuses on customer portfolio management and exposes various endpoints for handling customer-related operations.

## Prerequisites

To run this application, ensure you have:

- Java 17 installed
- Maven as the build tool

## Dependencies

### Spring Boot Dependencies
- `spring-boot-starter-webflux`: Enables reactive, non-blocking endpoints.
- `spring-boot-starter-data-r2dbc`: Provides support for reactive relational database interactions.
- `spring-boot-starter-validation`: Offers validation support for request payloads.
- `spring-boot-devtools`: Facilitates development by providing additional tools.

### Database Dependencies
- `h2`: An in-memory database used for local development and testing.
- `r2dbc-h2`: R2DBC driver for H2.

### OpenAPI Documentation Dependencies
- `springdoc-openapi-starter-common`: OpenAPI common module.
- `springdoc-openapi-starter-webflux-ui`: OpenAPI UI module for WebFlux applications.

### Testing Dependencies
- `spring-boot-starter-test`: Includes testing support for Spring Boot applications.
- `reactor-test`: Provides testing utilities for reactive code.

## Building the Project

To build the project, run the following command:

```
mvn clean install
```

Running the Application
After building the project, you can run the application using:

```
mvn spring-boot:run
```

The application will start on the default port 8080.

## Additional Information
This project is configured with the latest Spring Boot version 3.0.5 and Java 17. It leverages the power of WebFlux for reactive, non-blocking endpoints and R2DBC for reactive database interactions.

For more details about specific endpoints and their functionalities, access the OpenAPI UI at http://localhost:8080/swagger-ui.html when the application is running.
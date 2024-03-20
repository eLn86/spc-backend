# Scrabble Points Calculator Kotlin REST API backend

This is a REST API backend built with Kotlin and Spring Boot, designed to allow users to save their scores and also
return the top 10 scores via API response to the spc-web frontend.
It uses PostgreSQL for data persistence, incorporates Springdoc OpenAPI for API documentation, and supports Docker for
easy setup
and deployment.

## Features

- Core REST API functionalities to support spc-web.
- API documentation with Springdoc OpenAPI.
- Server side data validation and error handling.
- Easy setup and run with Docker.

## Getting Started

Follow these instructions to get the project up and running on your local machine for development and testing purposes.

### Prerequisites

- JDK 19.0.1
- Docker

### Setting Up the Development Environment

1. **Clone the repository**

```bash
git clone https://github.com/eLn86/spc-backend.git
cd spc-backend
```

2. **Start Docker Desktop**
   If you don't have docker desktop, you can download it at https://www.docker.com/products/docker-desktop/
   Or if you have it or docker CLI, you can proceed to the next step.

3. **Build and Run application with docker**
```bash
docker-compose up --build
```

This will start both the Postgresql database and the application, and you are good to go!

## API Documentation

The project uses Swagger for API documentation. Once the application is running, you can access the Swagger UI
at http://localhost:8080/swagger-ui.html to view and interact with the API's endpoints.

## Running Tests

I am using JUnit (with Mockito) and Mockito-Kotlin for unit tests. You can run the tests via the following command:

```bash
./gradlew test
```

Or you can run it through your IDE/Text Editor's Gradle GUI (eg. in IntelliJ, on the right most panel under Gradle >
spc-backend > Tasks > verification > test)

I added Mockito-Kotlin as JUnit's Mockito has problems with Mock, Any, `When` methods when working with Kotlin

## Built With

- Kotlin with Spring Boot (Dockerised)
- Mockito (Testing)
- Mockito-Kotlin (Testing)
- Liquibase (Database Change Management)
- PostgreSQL (Database) (Dockerised)
- Springdoc OpenAPI (API Documentation with OpenAPI specification)
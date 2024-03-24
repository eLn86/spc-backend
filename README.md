# Scrabble Points Calculator Kotlin REST API backend

This is a REST API backend built with Kotlin, Spring Boot and Gradle, designed to allow users to save their scores and
also
return the top 10 scores via API response to the spc-web frontend.
It uses PostgreSQL for data persistence, incorporates Swagger for API documentation, and supports Docker for
easy setup and deployment.

## Features

- Core REST API functionalities to support spc-web.
- API documentation with Swagger.
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
docker-compose -f docker-compose.yml -f docker-compose-test.yml up --build
```

This will start both the Postgresql database and the application, and you are good to go!

4. **Making changes to the application code**
   When you make changes to the application code and want to see them reflected, please rebuild the application:

Bring the services down first.

```bash
docker-compose -f docker-compose.yml -f docker-compose-test.yml down
```

Then bring them up again.

```bash
docker-compose -f docker-compose.yml -f docker-compose-test.yml up --build
```

Alternatively, if you want the app to live reload on manual save, check out this
thread: https://stackoverflow.com/questions/43129647/intellij-idea-spring-boot-hot-reload-on-manual-save

## API Documentation

The project uses Swagger for API documentation. Once the application is running, you can access the Swagger UI
at http://localhost:8080/swagger-ui/index.html to view and interact with the API's endpoints.

## Running Tests

I am using JUnit MockK and Mockito-Kotlin for unit tests. Please note that there is a test DB spun up in docker
on port 8001 (you can find the configuration in docker-compose-test.yml). You can run the tests via the following
command:

```bash
./gradlew test
```

Jacoco is used for test coverage. To generate the coverage report, run:

```bash
./gradlew test jacocoTestReport
```

Or you can run it through your IDE/Text Editor's Gradle GUI (eg. in IntelliJ, on the right most panel under Gradle >
spc-backend > Tasks > verification > test)

I added Mockito-Kotlin as JUnit's Mockito has problems with Mock, Any, `When` methods when working with Kotlin

## Built With

- Kotlin with Spring Boot (Dockerised)
- MockK (Kotlin Testing)
- Mockito-Kotlin (Kotlin Testing)
- Liquibase (Database Change Management) (Dockerised)
- PostgreSQL (Database) (Dockerised)
- Swagger (API Documentation)
- Spring Security
- Kotlin Gradle DSL
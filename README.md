# Scrabble Points Calculator Kotlin REST API backend

This is a REST API backend built with Kotlin and Spring Boot, designed to allow users to save their scores and also
return the top 10 scores via API response to the spc-web frontend.
It uses PostgreSQL for data persistence, incorporates Swagger for API documentation, and supports Docker for easy setup
and deployment.

## Features

- Core REST API functionalities to support spc-web.
- API documentation with Swagger.
- Data validation and error handling.
- Easy setup with Docker.

## Getting Started

Follow these instructions to get the project up and running on your local machine for development and testing purposes.

### Prerequisites

- JDK 11 or newer
- Docker

### Setting Up the Development Environment

1. **Clone the repository**

```bash
git clone https://github.com/eLn86/spc-backend.git
cd spc-backend
```

### Start PostgreSQL and the Application with Docker

Ensure Docker is installed and running on your machine. Use Docker Compose to start the PostgreSQL database and the
application:

```bash
docker-compose up
```

## API Documentation

The project uses Swagger for API documentation. Once the application is running, you can access the Swagger UI
at http://localhost:8080/swagger-ui.html to view and interact with the API's endpoints.

## Running Tests

I am using JUnit for unit tests. You can run the tests via the following command:

```bash
./gradlew test
```

## Built With

- Kotlin
- Spring Boot
- PostgreSQL
- Swagger
- Docker
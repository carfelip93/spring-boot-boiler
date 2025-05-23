# Spring Boot Application with Hexagonal Architecture

This project is built using Spring Boot 3.5.0 and follows the Hexagonal Architecture (also known as Ports and Adapters) pattern, which separates the application into layers:

- Domain (core business logic)
- Application (use cases)
- Infrastructure (external interfaces)

## Prerequisites

- Java 17 or higher
- Maven
- H2 Database (included as dependency)

## Installation

1. Install Maven (if not already installed):

```bash
sudo pacman -S maven
```

2. Install dependencies:

```bash
mvn clean install
```

## Running the Application

To run the application, use:

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## Project Structure

The project follows Hexagonal Architecture principles:

- `domain/`: Contains the core business logic and domain entities
- `application/`: Contains use cases and ports
- `infrastructure/`: Contains adapters and external interfaces
- `api/`: Contains REST controllers and DTOs

## Technologies Used

- Spring Boot 3.5.0
- Spring Data JPA
- H2 Database
- Lombok
- Maven

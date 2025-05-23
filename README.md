# Spring Boot Application with Hexagonal Architecture and DDD

This project is built using Spring Boot 3.5.0 and follows both Hexagonal Architecture (also known as Ports and Adapters) and Domain-Driven Design (DDD) principles. The architecture separates the application into layers:

- Domain (core business logic and DDD concepts like entities, value objects, aggregates, and domain services)
- Application (use cases and ports)
- Infrastructure (external interfaces)

## Project Structure

```
src/main/java/com/rocketicg/app/
├── domain/
│   └── product/
│       ├── model/
│       │   ├── Product.java (Entity)
│       │   ├── Price.java (Value Object)
│       │   └── ProductStatistics.java (Value Object)
│       ├── event/
│       │   └── ProductCreatedEvent.java
│       ├── specification/
│       │   ├── ProductSpecification.java
│       │   ├── ProductInStockSpecification.java
│       │   └── ProductPriceRangeSpecification.java
│       ├── service/
│       │   └── ProductDomainService.java
│       └── repository/
│           └── ProductRepository.java
├── application/
│   └── product/
│       ├── port/
│       │   └── ProductUseCase.java
│       ├── dto/
│       │   ├── ProductRequest.java
│       │   ├── ProductResponse.java
│       │   └── StockRequest.java
│       └── service/
│           └── ProductService.java
├── infrastructure/
│   └── product/
│       ├── persistence/
│       │   └── JpaProductRepository.java
│       └── config/
│           └── ProductPersistenceConfig.java
└── api/
    └── product/
        ├── controller/
        │   └── ProductController.java
        └── exception/
            └── ProductExceptionHandler.java
```

## Architecture Overview

### Domain Layer

- **Models**: Core business entities and value objects
- **Events**: Domain events for event-driven architecture
- **Specifications**: Business rules and query specifications
- **Services**: Domain services for complex business logic
- **Repository**: Domain repository interfaces

### Application Layer

- **Ports**: Use case interfaces (input ports)
- **DTOs**: Data transfer objects for API communication
- **Services**: Implementation of use cases

### Infrastructure Layer

- **Persistence**: JPA repository implementations
- **Config**: Infrastructure configuration

### API Layer

- **Controllers**: REST API endpoints
- **Exception**: Global exception handling

## Technologies

- Spring Boot 3.5.0
- Spring Data JPA
- H2 Database
- Lombok
- Swagger/OpenAPI
- Java 17

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

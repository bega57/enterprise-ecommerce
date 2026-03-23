# E-Commerce Microservices Application

## Description

This project implements a simple e-commerce backend using Java and Spring Boot based on a **microservice architecture**.

The system is divided into multiple independent services:

* User Service
* Product Service
* Order Service (including Cart functionality)
* Gateway Service

Users interact only with the Gateway, which routes requests to the appropriate services.

In addition to synchronous REST communication, the system uses **asynchronous messaging with Spring Cloud Stream** to enable event-driven workflows.

---

## Features

* Create and manage users
* CRUD operations for products with stock management
* Add and remove products from a shopping cart
* Place orders based on cart items
* Stock availability check when placing orders
* View order history and order details
* Event-driven communication between services
* Saga pattern for distributed transaction handling

---

## Architecture

Each microservice:

* runs on its own port
* has its own database (H2)
* is independently deployable
* communicates via REST and asynchronous messaging

### Services & Ports

* Gateway Service → 8080
* User Service → 8081
* Product Service → 8082
* Order Service → 8083

---

## Asynchronous Communication

This application uses **Spring Cloud Stream** to enable asynchronous communication between microservices.

Instead of relying solely on synchronous REST calls, services communicate via **events** through a message broker (e.g., RabbitMQ or Kafka).

### Benefits:

* Loose coupling between services
* Improved scalability
* Better fault tolerance
* Event-driven workflows

---

## Saga Pattern (Choreography)

A **Saga pattern** is implemented using **event-based choreography** to ensure consistency across services.

There is no central coordinator. Each service reacts to events and performs its own logic.

### Example Flow:

1. Order is created → `OrderCreatedEvent`
2. Product Service checks stock and reserves items
3. If stock is sufficient:
    * → `OrderConfirmedEvent`
    * → Stock is reduced
4. If stock is insufficient:
    * → `OrderCanceledEvent`
    * → No stock is deducted / reservation is reverted

This ensures **eventual consistency** across distributed services.

---

## Example Scenario

1. Product stock = 10
2. Order 5 items → confirmed → stock = 5
3. Order 10 items → canceled → stock remains 5

This demonstrates correct saga behavior and proper handling of distributed transactions.

---

## Technologies

* Java
* Spring Boot
* Spring Data JPA
* H2 Database
* Spring Cloud Stream
* REST (RestTemplate)
* Swagger / OpenAPI
* RabbitMQ

---

## Running the Application

Start each service individually (e.g. via IDE or):

mvn spring-boot:run


Then open:


http://localhost:8080/swagger-ui/index.html


---

## API Documentation

Swagger UI is available via the Gateway:


http://localhost:8080/swagger-ui/index.html


---

## Database

Each microservice uses its own **in-memory H2 database**, ensuring full service isolation.

---

## Repository

https://github.com/bega57/enterprise-ecommerce

---

## Notes

This application was developed as part of a university exercise to demonstrate:

* Microservice architecture
* Synchronous and asynchronous communication
* Event-driven design
* Saga pattern (choreography)
* Basic domain-driven design principles
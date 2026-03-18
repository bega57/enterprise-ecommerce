# E-Commerce Microservices Application

## Description

This project implements a simple e-commerce backend using Java and Spring Boot based on a **microservice architecture**.

The system is divided into multiple independent services:

* User Service
* Product Service
* Order Service (including Cart functionality)
* Gateway Service

Users interact only with the Gateway, which communicates with the other services via REST APIs.

## Features

* Create and manage users
* CRUD operations for products with stock management
* Add and remove products from a shopping cart
* Place orders based on cart items
* Stock availability check when placing orders
* View order history and order details

## Architecture

Each microservice:

* runs on its own port
* has its own database (H2)
* communicates via REST APIs

### Services & Ports

* Gateway Service → 8080
* User Service → 8081
* Product Service → 8082
* Order Service → 8083

## Technologies

* Java
* Spring Boot
* Spring Data JPA
* H2 Database
* REST (RestTemplate)
* Swagger / OpenAPI

## Running the Application

Start each service individually (e.g. via IDE or):

mvn spring-boot:run

Then open:

http://localhost:8080/swagger-ui/index.html

## API Documentation

Swagger UI is available via the Gateway:

http://localhost:8080/swagger-ui/index.html

## Database

Each microservice uses its own in-memory H2 database.

## Repository
https://github.com/bega57/enterprise-ecommerce

## Notes

This application was developed as part of a university exercise to demonstrate microservice architecture, inter-service communication, and basic domain-driven design principles.

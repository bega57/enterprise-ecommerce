# E-Commerce Application

## Description

This project implements a simple e-commerce backend using Java and Spring Boot.
Users can browse products, add them to a shopping cart, and place orders.

The system manages users, products, carts, and orders and stores data in an in-memory H2 database.

## Features

* Create and manage users
* CRUD operations for products with stock management
* Add and remove products from a shopping cart
* Place orders based on cart items
* Stock availability check when placing orders
* View order history and order details

## Technologies

* Java
* Spring Boot
* Spring Data JPA
* H2 Database
* Swagger / OpenAPI

## Running the Application

Start the application with:

mvn spring-boot:run

or run the main Spring Boot application class in your IDE.

## API Documentation

Swagger UI is available at:

http://localhost:8080/swagger-ui/index.html

## Database

The application uses an in-memory H2 database for testing.

H2 console (optional):

http://localhost:8080/h2-console

## Notes

This application is a simplified e-commerce system created as part of a university exercise.

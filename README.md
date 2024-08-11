# Spring Boot AOP Project

This repository contains a Spring Boot application that demonstrates the use of Aspect-Oriented Programming (AOP) to handle various cross-cutting concerns such as asynchronous method execution, exception handling, logging, access control, successful operation logging, and data validation.

## Table of Contents

- [Overview](#overview)
- [Technologies Used](#technologies-used)
- [Features](#features)
  - [Asynchronous Method Execution](#asynchronous-method-execution)
  - [Exception Handling](#exception-handling)
  - [Logging](#logging)
  - [Access Control](#access-control)
  - [Successful Operation Logging](#successful-operation-logging)
  - [Data Validation](#data-validation)
- [Project Structure](#project-structure)

## Overview

This project leverages Spring Boot and AOP to modularize and centralize cross-cutting concerns in a clean and maintainable way. Using AOP, we have implemented various aspects that handle common functionalities across the application, such as logging, validation, and access control.

## Technologies Used

- **Java 22**
- **Spring Boot 3.3**
- **Spring AOP**
- **Lombok**
- **Maven**

## Features

### Asynchronous Method Execution

Using `@Asynchronously` annotation, methods can be executed asynchronously, allowing for non-blocking operations. This is particularly useful for tasks that do not need to be completed immediately or can be performed in parallel.

### Exception Handling

Custom aspects are implemented to catch and handle exceptions thrown by the application methods. These aspects ensure that exceptions are logged properly and can also trigger fallback mechanisms or custom error responses.

### Logging

Logging is implemented using `AspectJ` to automatically log method entry, exit, and the time taken to execute methods. This helps in tracking the application flow and diagnosing performance issues.

### Access Control

Access control aspects are used to check user roles and permissions before allowing method execution. This ensures that only authorized users can access specific features of the application.

### Successful Operation Logging

In addition to logging method entry and exit, aspects also log the successful completion of critical operations, which can be useful for audit purposes.

### Data Validation

Validation aspects ensure that the data being processed by the application meets the required criteria before the method proceeds with its execution. If the data is invalid, the aspect throws an `ApplicationException` with a detailed error message.

## Project Structure

```plaintext
src/
│
├── main/
│   ├── java/com/example/SpringBootAOP/
│   │   ├── annotation/          # Custom annotations used by AOP aspects
│   │   ├── aspect/              # AOP aspects for logging, validation, etc.
│   │   ├── exception/           # Custom exceptions for the application
│   │   ├── model/               # Domain models (e.g., Plant, RoleType)
│   │   ├── service/             # Service classes with business logic
│   │   ├── utils/               # Utility classes like UserContext
│   │   └── SpringBootAopApplication.java  # Main Spring Boot application
│   └── resources/
│       ├── application.properties  # Application configuration
└── test/
    └── java/com/example/SpringBootAOP/  # Unit and integration tests

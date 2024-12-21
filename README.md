# Car Rental System - Backend Service

## Overview
The **Car Rental System (CRS)** backend service is a Spring Boot application designed to handle car rental operations. It provides RESTful APIs for managing cars, customers, reservations, and rentals. Built with Spring Data JPA for database interaction and secured using Spring Security.

---

## Features
- Manage cars, customers, and reservations.
- Handle rental transactions including pickup and return.
- Generate rental invoices and reports.
- Role-based authentication and authorization.
- RESTful APIs for seamless integration with front-end systems.

---

## Technologies
- **Spring Boot** - Application framework.
- **Spring Data JPA** - Database interaction.
- **Spring Security** - Authentication and authorization.
- **H2 Database** - In-memory database for development.
- **Swagger** - API documentation.

---

## Prerequisites
- **Java 11** or higher.
- **Maven** - Build and dependency management tool.
- IDE like IntelliJ IDEA or Eclipse.

---

## Getting Started
1. Clone the repository:
   ```bash
   git clone https://github.com/anhvu2889/miu-cs425-crs-service
   ```
2. Navigate to the project directory:
   ```bash
   cd miu-cs425-crs-service
   ```
3. Build and run the application:
   ```bash
   mvn spring-boot:run
   ```
4. Access the APIs via Swagger UI:
   ```
   http://localhost:8080/swagger-ui.html
   ```

---

## API Endpoints
- **/api/cars** - Manage car records.
- **/api/customers** - Manage customer data.
- **/api/reservations** - Create and manage reservations.
- **/api/rentals** - Handle rental transactions.

---

## Contributing
Contributions are welcome! Follow these steps:
1. Fork the repository.
2. Create a new branch.
3. Commit your changes.
4. Submit a pull request.

---

## Contact
For questions or suggestions, create an issue in the repository or reach out to the course instructor.


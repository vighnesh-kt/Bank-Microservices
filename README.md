# 🏦 Bank Microservices

## 📚 Overview
This project is a **banking system** built using **Spring Boot microservices architecture**. It follows **best practices** for API design, error handling, and scalability.

## 🏷️ Architecture
The project follows **microservices architecture**, where each service is **independent** and communicates via **REST APIs**.

## 🛠️ Tech Stack
- **Backend**: Java, Spring Boot, Spring Cloud, Docker, RabbitMQ
- **Build Tool**: Maven
- **Database**: PostgreSQL / H2
- **Dependencies**: MapStruct, Springboot actuators, Spring cloud bus
- **Containerization**: Docker, Docker Compose
- **Exception Handling**: Global Exception Handler
- **API Response**: Custom Response Structure
- **API Documentation**: Open API Swagger UI

## ⚙️ Microservices Implemented
1. **Account and Customer Service** – Manages bank accounts and handles customer onboarding  
2. **Loans Service** – Manages Loans  
3. **Cards Service** – Manages Cards  

## ✅ Key Features
- 📌 **Robust Exception Handling** – Uses a **Global Exception Handler** for standardized error responses.  
- 📌 **Readable API Responses** – Implements a **custom Response Structure** for consistency.  
- 📌 **Implemented Open API** – Improves **API Documentation** using Open API. ans Swagger UI.
- 📌 **Used Docker** – For **Containerization** of each microservice and Docker Compose for multi-container applications.  
- 📌 *Implemented SpringBoot Profiles using Spring Cloud ConfigServer*  – For external config management using github implementing encrypted configuration and changing it during runtime.


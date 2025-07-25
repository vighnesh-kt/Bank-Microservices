# 🏦 Bank Microservices

## 📚 Overview
A modular **banking system** built using **Spring Boot microservices**, designed for scalability, maintainability, and clean API design.

## 🛠️ Tech Stack

### 📦 Backend & Microservices
- Java 17
- Spring Boot
- Spring Cloud (Config, Gateway, Eureka, OpenFeign)
- Spring Data JPA
- Springdoc OpenAPI (Swagger)

### 🧱 Architecture & Design
- Microservices Architecture
- RESTful APIs
- DTO, Builder Pattern
- Resilience4j (Circuit Breaker, Retry, RateLimiter)
- Config Server with Git Integration

### 🗃️ Databases
- MySQL (via Docker)
- H2 (for initial development/testing)

### 🐳 Containerization & DevOps
- Docker & Docker Compose
- Buildpacks (alternative to Dockerfiles)
- DockerHub (image registry)

### 🔐 Configuration & Profiles
- Spring Profiles (`dev`, `qa`, `prod`)
- Externalized Configuration with Spring Cloud Config Server
- Encrypted Properties (JCE + Spring Cloud)

### 🔍 Observability & Monitoring
- **Logging**: Grafana + Loki + Promtail
- **Metrics**: Micrometer + Prometheus
- **Tracing**: OpenTelemetry + Grafana Tempo
- **Alerting**: Grafana Alerting

### 📄 Documentation
- Swagger UI (Springdoc OpenAPI)

---

## ⚙️ Microservices

- **🧾 Account & Customer Service** – Handles bank accounts and customer data  
- **🏠 Loans Service** – Manages loans  
- **💳 Cards Service** – Manages card issuance and operations  


---
### 🛠️ Git Branches Summary

### 🛠️ Service Infrastructure Branch Overview

The course also emphasizes critical service infrastructure components essential for running microservices in production:

- **🔍 Observability**: Enables monitoring and debugging of microservices using:
  - Centralized logging (Grafana + Loki + Promtail)
  - Metrics and dashboards (Micrometer + Prometheus + Grafana)
  - Distributed tracing (OpenTelemetry + Tempo)
  - Alerting mechanisms

- **🔐 Security**: Although detailed security sections come later, foundational infrastructure includes:
  - API Gateway security and filters
  - Property encryption using Spring Cloud Config
  - Service-level communication protection and token handling

These infrastructure features help ensure **scalability, reliability, and maintainability** of microservices in real-world environments.

---


### 🎯 Core Features

### ✅  CRUD Operations in Accounts Service
- Created JPA Entities and Repositories
- DTO pattern introduction and implementation
- Implemented:
  - Create Account API
  - Read Account API
  - Update Account API
  - Delete Account API
- Global exception handling
- Auto-update of audit fields (created_at, updated_at)

### ✅ API Documentation using Springdoc OpenAPI
- Integrated Swagger UI with Springdoc
- Used annotations:
  - `@OpenAPIDefinition`
  - `@Tag`, `@Operation`, `@ApiResponse`, `@Schema`
- Enhanced auto-generated documentation


### ✅ Dockerizing Microservices
- Dockerfile created for each service
- Generated Docker images locally
- Ran containers using Docker CLI
- Introduced Buildpacks and compared with Dockerfile approach
- Docker image push to DockerHub

### ✅ Spring Boot Profiles and Configuration Management
- Added `dev`, `qa`, and `prod` Spring profiles
- Externalized configs using:
  - `@Value`
  - `Environment`
  - `@ConfigurationProperties`
- Activated profiles using command-line/JVM/environment variables

### ✅ Spring Cloud Config Server
- Built `config-server` with Spring Cloud Config
- Externalized properties to file system and Git
- Implemented:
  - Encryption/decryption of config properties
  - Auto-refresh via `/actuator/refresh`
  - Spring Cloud Bus for distributed refresh
- Updated docker-compose for config server integration

### ✅ Integrating MySQL Database
- Replaced H2 DB with MySQL for all services
- Dockerized MySQL containers
- Created schema and updated entity configurations
- Demonstrated Docker networking with services + DB containers
- Added uptil this in seperate git branch use s7 image from docker hub

### ✅ Service Discovery with Eureka
- Implemented `eureka-server`
- Registered `accounts`, `loans`, `cards` microservices to Eureka
- Replaced static URLs with service names
- Feign client integration for service-to-service calls
- Eureka client deregistration on shutdown
- Docker-based setup:
- Docker `s8` version images

### ✅ API Gateway using Spring Cloud Gateway
- **Created Gateway microservice using Spring Cloud Gateway**
- Configured default and custom routing for internal services
- Added response headers using filters
- Implemented cross-cutting concerns: logging and tracing at the gateway
- Discussed design patterns used in API Gateway
- Docker-based setup:
- Docker `s9` version images

### ✅ Making Microservices Resilient
- Introduced need for resiliency and typical failure scenarios
- Implemented:
  - **Circuit Breaker pattern (Gateway + Feign)**
  - **Retry pattern (Accounts)**
  - **Rate Limiting using Redis RateLimiter (Gateway + Accounts)**
- Configured timeouts and aspect order for resilience
- Docker-based setup:
- Docker `s10` version images

### ✅ Observability and Monitoring
- Concepts of **observability, centralized logging, and monitoring**
- Setup and integrated:
  - Logging: Grafana + Loki + Promtail
  - Metrics: Micrometer + Prometheus + Grafana Dashboards
  - Alerts: Grafana alerting with 2 approaches
  - Tracing: OpenTelemetry + Grafana Tempo
- Implemented log tracing, metrics collection, and distributed tracing end-to-end
- Docker-based setup:
- Docker `s11` version images

###  ✅Microservices Security with OAuth2 & Keycloak
- Implementing OAuth2 and OpenID Connect:
- Implemented **Client Credentials Grant Flow**:
  - Keycloak setup and client registration
  - Gateway server secured as a **Resource Server**
  - Access tokens fetched and validated
  - Role-based authorization enforced inside the gateway
- Implemented **Authorization Code Grant Flow**:
  - User and client setup in Keycloak
  - Full login flow with token issuance and validation
  - Role-based access control using Keycloak roles and scopes
- **Secured all microservices through API Gateway**
- Docker-based setup:
- Docker `s12` version images

### ✅ Event-Driven Microservices with Spring Cloud Stream & RabbitMQ
- Implemented **Event-Driven Architecture** using RabbitMQ as a message broker
- Developed **Message Microservice** using Spring Cloud Function:
  - Functional-style `@Bean` consumers for event handling
  - Used `StreamBridge` to publish events
- Updated **Message & Accounts Microservices** to support **2-way communication**:
  - `message` microservice publishes events consumed by `accounts`
  - `accounts` microservice processes those events and responds with its own events
  - Enabled full **bi-directional asynchronous communication**
- Used **Spring Cloud Stream** for messaging abstraction:
  - Configured bindings to connect microservices with RabbitMQ
- Demonstrated **Asynchronous Communication** between services:
  - Verified end-to-end message flow via RabbitMQ exchanges and queues
- Docker-based setup:
- **Docker**: `s13` version images used

### ✅ Event-Driven Microservices with Spring Cloud Stream & Apache Kafka (KRaft Mode)

- Compared **Apache Kafka vs RabbitMQ** for event-driven architecture:
  - Kafka: log-based, distributed, high-throughput message streaming (KRaft mode – no Zookeeper)
- Implemented **Asynchronous Event Streaming** using Kafka (KRaft mode)
- Developed **Producer & Consumer Microservices** using Spring Cloud Stream:
  - Functional-style `@Bean` consumers for clean message processing
  - Used `StreamBridge` to publish events to Kafka topics
- Enabled **end-to-end async communication**:
  - `producer-service` sends events to Kafka topic
  - `consumer-service` consumes and processes those events
- Configured **Spring Cloud Stream Kafka Binder**:
  - Defined topic bindings and Kafka broker settings in `application.yml`
- Docker-based setup:
- **Docker**: `s14` version images used

---

## 📦 How to Run

```bash
# Step 1: Select containers based on features 
dockerhub URL: https://hub.docker.com/repositories/vighneshkt
docker-compose up --build


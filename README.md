# Blog Application

A RESTful Blog Application built with Spring Boot, using Redis for caching and as a database. The project is containerized with Docker and orchestrated using Docker Compose.

---

## Features

- CRUD operations for blog posts
- RESTful API endpoints
- Redis integration for caching and data storage
- Dockerized deployment for easy setup

---

## Technologies Used

- Spring Boot
- Spring Data Redis
- MapStruct (DTO mapping)
- Docker & Docker Compose
- Lombok

---

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- Docker & Docker Compose

### Build the Application

```bash
cd blog-service
mvn clean package
```

### Build Docker Image

```bash
docker build -t shadangi54/blog-service:1.0 -f docker/Dockerfile .
```

### Run with Docker Compose

```bash
docker compose -f docker/docker-compose.yml up -d
```

---

## Docker Compose Overview

- **blog-service**: Runs the Spring Boot application, exposes port 8080, connects to Redis.
- **redis**: Runs the Redis server, exposes port 6379.
- Both services are connected via the `blog-network` bridge network.

---

## API Endpoints

| Method | Endpoint                 | Description       |
| ------ | ------------------------ | ----------------- |
| GET    | /blog-service/blogs      | List all blogs    |
| GET    | /blog-service/blogs/{id} | Get blog by ID    |
| POST   | /blog-service/blogs      | Create new blog   |
| PUT    | /blog-service/blogs/{id} | Update blog by ID |
| DELETE | /blog-service/blogs/{id} | Delete blog by ID |

### Sample POST Request

```json
{
  "title": "Spring Boot with Redis",
  "content": "This post explains how to use Redis with Spring Boot.",
  "author": "Shadangi"
}
```

---

## Author

Shivam Shadangi

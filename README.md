# Person Tasks

## Overview
This is a Spring Boot application for managing people, companies, and tasks.

## Prerequisites
- Java 21 (JDK)
- Maven (or use the included Maven Wrapper: `./mvnw`)
- Docker installed and running

## How to run the project
### 1) Install and start Docker
Make sure Docker is installed on your machine and the Docker daemon is running.

### 2) Start the database (Docker)
In the IDE, run the **Start database** run configuration (located under `.run/`).

This will start PostgreSQL with the following defaults (see `docker-compose.yml`):

- **Host**: `localhost`
- **Port**: `5432`
- **Database**: `postgres`
- **Username**: `test`
- **Password**: `test`

### 3) Start the application
In the IDE, run the **PersonTasksApplication** run configuration.

After startup, the application will be available on the default Spring Boot port:

- `http://localhost:8080`

Database connection settings are configured in `src/main/resources/application.yaml` and can be overridden with environment variables:

- `DB_URL` (default: `jdbc:postgresql://localhost:5432/postgres`)
- `DB_USERNAME` (default: `test`)
- `DB_PASSWORD` (default: `test`)

## Fill the database with test data
To populate the database with test data, run the SQL script:

- `src/test/resources/init_test_data.sql`



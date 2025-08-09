# Job Tracker API (Spring Boot + Java 21)

A REST API that is used to track job applicatons. Built with **Java 21, Spring Boot 3, Spring Data JPA,** and **PostgreSQL**. Includes validation, PATCH semantics, and Swagger UI docs. 

## Features
- CRUD for Jobs with partial updates (HTTP PATCH)
- Reference data: Cities and States are preloaded via CSV.
- Validation and consistent error responses (400/404/204)
- OpenAPI + Swagger UI for docs

## Tech Stack
- Java 21, Spring Boot 3x
- Spring Data JPA, Hibernate
- PostgreSQL (H2 in Postgres mode for testing)
- Lombok (optional)
- springdoc-openapi (Swagger UI)
- JUnit and Spring Test

## Getting Started
#### Prerequisites
- Java 21+
- Maven
- PostgreSQL
#### Environment
Copy this -> .env file. and set values, if using a Docker Compose, these values will be auto-consumed
```
POSTGRES_USER=USER
POSTGRES_PASSWORD=changemeinprod!
```

#### Run the Database (Docker Compose)
```
services:

  db:
    image: postgres
    ports:
      - "5432:5432"
    restart: always
    environment:
      POSTGRES_USER: ${POSTGRES_USER}
      POSTGRES_PASSWORD: ${POSTGRES_PASSWORD}
```
Start DB:
`docker compose up -d`


#### Load Data
CSV files are under 
```
src/main/resources/cities_only.csv
src/main/resources/states_only.csv
```

**Source/Credit**: Generated from **SimpleMaps.com** and scraped to only include unique state-city combinations. These files are used for demo purposes 

#### Import Instructions (psql)
```
\COPY states(id, name)
  FROM 'src/main/resources/data/states_only.csv' DELIMITER ',' CSV HEADER;

\COPY cities(id, cityName, state_id)
  FROM 'src/main/resources/data/cities_only.csv' DELIMITER ',' CSV HEADER;
```
#### Build and Run!
`mvn clean spring-boot:run`

## API
Base URL: `http://localhost:8080`

#### Jobs

`GET  /jobs` — list jobs

`GET  /jobs/{id}` — fetch one

`POST /jobs` — create

`PUT  /jobs/{id}` — full update

`PATCH /jobs/{id}` — partial update (only provided fields)

`DELETE /jobs/{id}` — delete (204 on success)

## Swagger / OpenAPI 
Run the app, then open
`http://localhost:8080/swagger-ui/index.html`
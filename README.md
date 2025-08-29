# Task Management Backend

---

## Tech Stack

* **Backend:** Java Spring Boot
* **Database:** PostgreSQL
* **Cache/Session:** Redis
* **Build Tool:** Maven
* **Containerization:** Docker & Docker Compose

---

## 1. Prerequisites

* **Java 17+**
* **Maven**
* **Docker & Docker Compose**
* **PostgreSQL**

---

## 2. Setup Instructions

### Clone the repository

```bash
 git clone <your-repo-url>
 cd taskmanagementsystem
```

### Configure Database

Create `application-local.properties` file in `src/main/resources/application-local.properties` and add the following:

```properties
# Database credentials
spring.datasource.url=jdbc:postgresql://localhost:5432/{database_name}
spring.datasource.username={db_username}
spring.datasource.password={db_password}
```

### Run PostgreSQL (Optional if you already have PostgreSQL running)

Inside `db/postgresql` directory you can find initialization scripts:

* `db.sql` : Database schema
* `data.sql` : Sample data
* `query.sql` : Sample queries

### Run Redis with Docker

Inside `db/redis` directory:

```bash
docker compose -f redis.yml up
```

---

## 3. Running the Application

Run the Spring Boot application using Maven:

```bash
mvn spring-boot:run
```

Or build and run the JAR:

```bash
mvn clean install
java -jar target/taskmanagementsystem-0.0.1-SNAPSHOT.jar
```

---

## 4. Project Structure

```
project-root
│── db
│   ├── postgresql
│   │   ├── db.sql
│   │   ├── data.sql
│   │   └── query.sql
│   └── redis
│       └── redis.yml
│
│── documents
│   ├── ERD.png
│   ├── Report.docx
│   └── UseCase Diagram.asta
│
│── src
│   ├── main
│   └── test
│
│── pom.xml
│── README.md
```

---

## 5. Features

* User authentication and authorization (JWT based)
* Task creation, update, delete
* PostgreSQL as persistent storage
* Redis for caching and session management

---

## 6. Useful Commands

* Run Spring Boot:

```bash
mvn spring-boot:run
```

* Clean and rebuild:

```bash
mvn clean install
```

---

## 7. Notes

* Ensure PostgreSQL and Redis are running before starting the Spring Boot application.
* Update credentials in `application-local.properties` accordingly.

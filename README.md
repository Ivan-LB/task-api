# “Taskify” – Task Management System project:

# Table of Contents
1. [Overview](#overview)
2. [Features](#features)
3. [Technologies Used](#technologies-used)
4. [Milestones](#milestone-breakdown)
5. [Set Up Instructions](#setup-instructions)
## Overview
Taskify is a robust task management application built with Spring Boot, JPA/Hibernate, and Flyway for database migrations. It offers seamless persistence using either an in-memory H2 database for local development or a PostgreSQL database managed via Docker for production-like environments. Additionally, pgAdmin4 is integrated for efficient database management.

## Features
* **CRUD Operations:** Create, Read, Update, and Delete tasks.
* **Database Flexibility:** Switch between an in-memory H2 database for development and PostgreSQL for production-like environments.
* **Database Migrations:** Manage schema changes seamlessly with Flyway.
* **Docker Integration:** Containerize the PostgreSQL database and pgAdmin4 for easy deployment and management.
* **Database Management Tools:** Utilize pgAdmin4 and H2 Console for efficient database interactions.

## Technologies Used
* Java 18
* Spring Boot
* Spring Data JPA
* Hibernate
* Flyway
* PostgreSQL
* H2 Database
* Docker & Docker Compose
* pgAdmin4
* Maven
* JUnit
* Mockito

## Milestone Breakdown

### Milestone 1: Project Setup and Git Basics

**Scope:**
* Set up a Spring Boot project with Maven.
* Initialize a Git repository and commit the basic folder structure.
* Create a meaningful ***.gitignore.***
* Write a simple ***/health*** endpoint to test the setup.

**Output:**
* A functioning Spring Boot project.
* A README file describing the initial setup.

### Milestone 2: Core Task API

**Scope:**

* Implement REST APIs for creating, updating, deleting, and retrieving tasks.
* Tasks should have attributes: id, title, description, status (e.g., Pending/Completed).
* Add basic request validation using ***@Valid***.

**Output:**

* APIs tested with tools like Postman or cURL.
* Code committed in a new Git branch and squash merged to main branch

### Milestone 3: Unit Testing Basics

**Scope:**

* Write unit tests for the Task API service layer using JUnit and Mockito.

**Output:**

* Explanation of test cases added as comments or documentation.
* Code committed in a new Git branch and squash merged to main branch

### Milestone 4: Database Integration

**Scope:**

Set up a PostgreSQL database or an in-memory H2 database.

Implement persistence using JPA and Hibernate.

Create schema for
Task
 table and use it for CRUD operations.

Output:

Tasks are saved in the database.

SQL script or
schema.sql
 added to the project.

Code committed in a new Git branch and squash merged to main branch

### Milestone 5: Clean Code Practices

**Scope:**

* Refactor code for readability, maintainability, and adherence to clean code principles.
* Use meaningful variable/method names and reduce duplication.
* Run tools like SonarQube (https://plugins.jetbrains.com/plugin/7973-sonarqube-for-ide/versions#tabs) to identify issues and fix them.

**Output:**

* Refactored, clean, and readable code with a before/after comparison.
* Code committed in a new Git branch and squash merged to main branch

### Milestone 6: Diagrams

**Scope:**

* Create a UML class diagram to represent entities like Task.
* Draw a sequence diagram for a task creation flow.

**Output:**

* Two diagrams saved as PNG files in the project. (you can use tools like
https://mermaid.js.org/intro/ (https://mermaid.js.org/intro/), https://excalidraw.com/ (https://excalidraw.com/), https://www.websequencediagrams.com/ (https://www.websequencediagrams.com/))

* The provided tools also allow you to store your diagrams in “text”, so, commit your diagram in a new Git branch and squash merge to main branch.

## Setup Instructions
### 1. Clone the Repository
```
git clone https://github.com/Ivan-LB/task-api.git
cd taskify
```
### 2. Create a `.env` file in the root directory of your project to manage environment variables required by Docker Compose.

#### a. Environment Variables
```env
POSTGRES_PASSWORD=setYourPassword
SPRING_DATASOURCE_PASSWORD=setYourPassword
PGADMIN_PASSWORD=setYourPassword
```
**Note:**  Do not include `SPRING_PROFILES_ACTIVE` in the .env file. This variable should be set externally based on the environment you are running.

#### b. Application Properties
Ensure you have the following configuration files in src/main/resources/:

`application.properties` (Default Configuration)
```
# Common Configuration
spring.application.name=taskify

# JPA/Hibernate Configuration
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Flyway Configuration
spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration
```
`application-local.properties` (Local/H2 Configuration)
```
# H2 In-Memory Database Configuration
spring.datasource.url=jdbc:h2:mem:taskifydb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driver-class-name=org.h2.Driver

# Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update

# H2 Console Configuration
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```
`application-docker.properties` (Docker/PostgreSQL Configuration)
```
spring.datasource.url=jdbc:postgresql://db:5432/taskifydb
spring.datasource.username=postgres
spring.datasource.password=${POSTGRES_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=validate
```
### 3. Running the Application

#### a. Running Locally with H2
To run Taskify locally using the in-memory H2 database:

##### 1. Build the Application
```
mvn clean package
```
##### 2. Run the Application with the `local` Profile
```
java -jar target/taskify-0.0.1-SNAPSHOT.jar --spring.profiles.active=local
```
##### 3. Access the Application
* **Application URL:** http://localhost:8080
* **H2 Console:** http://localhost:8080/h2-console

##### 4. Accessing the H2 Console
1. **Navigate to the H2 Console:**

    Open your browser and go to: http://localhost:8080/h2-console

2. **Login to H2:**
    * JDBC URL: jdbc:h2:mem:taskifydb
    * Username: sa
    * Password: (leave blank)
2. **Connect and Query:**

    After connecting, you can run SQL queries to interact with your in-memory database.

#### b. Running with Docker and PostgreSQL
To run Taskify using Docker with a PostgreSQL database:

##### 1. Ensure Docker and Docker Compose are Installed
- **Install Docker:** [Docker](https://www.docker.com/get-started) 
- **Install Docker Compose:** [Docker Compose](https://docs.docker.com/compose/install/)

##### 2. Build and Start Services with Docker Compose
Navigate to your project root and run:
```
docker-compose up --build
```
This command will:
* Build the app service from your Dockerfile.
* Start the db (PostgreSQL) and pgadmin services.
* Activate the docker profile for your application.

**Note:** Optionally, you can remove existing containers and associated volumes to ensure a fresh setup by running the following commands:
```
docker-compose down -v                                                    
docker-compose up --build
```

##### 3. Accessing the Application and pgAdmin4

Application URL: http://localhost:8080
pgAdmin4 URL: http://localhost:5050
##### 4. Accessing pgAdmin4
1. **Open pgAdmin4:**
    
    Navigate to http://localhost:5050 in your browser.
2. **Login Credentials:**
    * Email: admin@admin.com
    * Password: sameAsYourEnvironmentVariable
3. Add a New Server Connection:
    * Name: Taskify DB (or any preferred name)
    * Host: db (as defined in docker-compose.yml)
    * Port: 5432
    * Database: taskifydb
    * Username: postgres
    * Password: sameAsYourEnvironmentVariable
4. Connect and Explore:
    
    You can now navigate through Databases > taskifydb > Schemas > public > Tables to view the tasks table and its data.
5. Stop Services
    
    To stop the Docker services, press Ctrl + C in the terminal where Docker Compose is running, then execute:
```
bash
Copy code
docker-compose down
```




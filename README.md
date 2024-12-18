# “Taskify” – Task Management System project, with milestones scoped to be achievable over individual weekends:

## Overview
This is a basic Spring Boot project set up with Maven. Currently, the project includes:

- A `/health` endpoint to verify the application is running.
- A simple unit test (`HealthControllerTest`) to validate the `/health` endpoint.

## How to Run
1. Clone the repository.
2. Run `mvn spring-boot:run` in the project’s root directory.
3. Go to `http://localhost:8080/health` to confirm the application is up.

## How to Run Tests
- From IntelliJ: Right-click on `HealthControllerTest` and select `Run`.
- From the terminal: `mvn test`

## Weekend Milestone Breakdown

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

## Dockerización

### Requisitos Previos

- [Docker](https://www.docker.com/get-started) instalado en tu máquina.
- [Docker Compose](https://docs.docker.com/compose/install/) instalado.

### Configuración de Variables de Entorno

Crea un archivo `.env` en la raíz del proyecto con las siguientes variables:

```env
POSTGRES_PASSWORD=123qwe123
SPRING_DATASOURCE_PASSWORD=123qwe123

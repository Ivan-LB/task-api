# “Taskify” – Task Management System project, with milestones scoped to be achievable over individual weekends:

## Instructions

1. Please review the entire project breakdown to get a clear understanding of what needs to be done.

2. While it’s optional, we recommend working with separate branches and using squash merges to integrate them back into the main branch. Feel free to commit directly to the main branch if that’s easier for you.

3. We suggest keeping a record of your observations, learnings, and the time spent on each milestone.

4. Once completed, kindly share your GitHub repository by sending the link to mailto:arturo.solano@itj.com.

5. Don’t worry if you can’t finish the entire project! It’s more important to focus on understanding the concepts in each milestone.

6. You’ll have until Monday afternoon to work on this project.

7. We invite you to a live Presentation and Q&A session on Tuesday, December 17, 2024, from 9:30 AM to 12:00 PM (PST).

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
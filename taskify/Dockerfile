# Stage 1: Build the application
FROM maven:3.8.6-openjdk-18 AS build
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:18-jdk-slim
VOLUME /tmp
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/taskify-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080
EXPOSE 8080

# Define the entry point
ENTRYPOINT ["java","-jar","app.jar"]

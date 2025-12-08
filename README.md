# Movies Website

A Spring Boot application for browsing movies, managing favorites, and user authentication.

## Tech Stack
- **Backend**: Spring Boot 3.2, Java 17+
- **Database**: MongoDB (Atlas)
- **Security**: BCrypt Password Hashing
- **Testing**: JUnit 5, Mockito, Playwright (E2E), JMeter (Performance)

## Prerequisites
- Java 17 or higher
- Maven 3.6+

## Configuration
The application connects to a MongoDB Atlas cluster. Ensure `src/main/resources/application.properties` contains valid credentials:
```properties
spring.data.mongodb.uri=mongodb+srv://Movies:1010102020@cluster0.8rrhno2.mongodb.net/movies
```

## How to Run

### Option 1: Using Maven (Recommended for Dev)
Open your terminal, navigate to the `Movies` folder, and run:
```bash
cd Movies
mvn spring-boot:run
```

### Option 2: Running the JAR (Production-like)
Build the project first (from `Movies` folder):
```bash
cd Movies
mvn clean package -DskipTests
```
Run the JAR:
```bash
java -jar target/movies-0.0.1-SNAPSHOT.jar
```

The application will start on **http://localhost:8080**.

## Testing
Run unit and integration tests:
```bash
mvn test
```

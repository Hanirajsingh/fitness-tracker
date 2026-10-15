# fitwith-us

A Spring Boot REST API that handles the backend logic for the FitWith-Us fitness tracking application. It manages user accounts, tracks daily activity metrics, generates fitness recommendations, and secures data access using JWT authentication.

## Core Features

*   **Auth & Security:** User registration and login flow using Spring Security, returning a JWT token for session management.
*   **Data Validation:** Integrated field validation (e.g., email formatting, password length checks) to maintain data integrity.
*   **Activity Tracking:** CRUD endpoints to track user activity metrics like steps, active duration, and logs.
*   **Recommendations Engine:** Serves personalized health or fitness recommendations based on user profiles.
*   **Boilerplate-free Code:** Uses Lombok annotations to keep the data models clean and readable.

## Tech Stack

*   **Java 25** with **Spring Boot 3.x**
*   **Spring Security & JWT**
*   **Spring Data JPA & Hibernate**
*   **Lombok**
*   **MySQL Database**
*   **Docker** (Eclipse Temurin JRE 25)
*   **Maven**

## Getting Started

### Prerequisites
*   JDK 25 (or JDK 17+) installed locally
*   MySQL Server running
*   Maven

### Local Setup

1. **Create the Database**
   Log into your MySQL instance and run:
   ```sql
   CREATE DATABASE fitness_tracker;
   ```

2. **Configure Environment Variables**
   To keep your database credentials safe, this project uses environment variables. Set the following variables in your local environment or IDE:
   *   `DB_URL` = `jdbc:mysql://localhost:3306/fitness_tracker`
   *   `DB_USER` = `your_mysql_username`
   *   `DB_PWD` = `your_mysql_password`
   *   `JWT_SECRET` = `your_super_long_secure_secret_key_here`

3. **Run the Application**
   ```bash
   # Linux/macOS
   ./mvnw spring-boot:run

   # Windows
   mvnw.cmd spring-boot:run
   ```
   The API will be live at `http://localhost:8080`.

---

### Running with Docker

This application is containerized using a lightweight **Eclipse Temurin Java 25 Runtime** image.

1. **Package the Jar**
   ```bash
   ./mvnw clean package -DskipTests
   ```

2. **Build the Image**
   ```bash
   docker build -t fitness-tracker-backend .
   ```

3. **Run the Container**
   ```bash
   docker run -p 8080:8080 \
     -e DB_URL=jdbc:mysql://host.docker.internal:3306/fitness_tracker \
     -e DB_USER=your_username \
     -e DB_PWD=your_password \
     -e JWT_SECRET=your_jwt_secret \
     --name fitness-backend fitness-tracker-backend
   ```

---

## API Overview & Examples

### 1. Authentication (Public)
*   `POST /api/auth/signup` - Register a user
*   `POST /api/auth/login` - Login to get the JWT token

**Example Login Request Body (`POST /api/auth/login`):**
```json
{
  "email": "user@example.com",
  "password": "SecurePassword123"
}
```

**Example Login Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer",
  "email": "user@example.com"
}
```

### 2. Protected Endpoints
*Must include the header: `Authorization: Bearer <your_token>`*

*   `GET /api/activities` - Fetch logged user activities
*   `POST /api/activities` - Log a new activity entry
*   `GET /api/recommendations` - Get tailored fitness recommendations

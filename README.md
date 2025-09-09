# Hospital Management System

A production-ready hospital management system built with Spring Boot 3 and modern web technologies.

## Features

- 🔐 JWT-based authentication and authorization
- 🏥 User management with role-based access control (Admin, Doctor, Nurse, User)
- 📊 Health monitoring with Spring Boot Actuator
- 🗄️ Database migrations with Flyway
- 📚 API documentation with OpenAPI/Swagger
- 🔄 Global exception handling
- 🌐 CORS configuration for cross-origin requests

## Technology Stack

- **Backend**: Spring Boot 3.2.0, Java 17
- **Security**: Spring Security with JWT
- **Database**: MySQL with JPA/Hibernate
- **Migration**: Flyway
- **Documentation**: SpringDoc OpenAPI
- **Build Tool**: Maven

## Quick Start

### Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+

### Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/yaoyu49/hospital.git
   cd hospital
   ```

2. **Setup MySQL Database**
   ```bash
   # Create database
   mysql -u root -p
   CREATE DATABASE hospital_db;
   ```

3. **Configure application**
   
   Update `backend/src/main/resources/application.yml` with your database credentials:
   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/hospital_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true
       username: your_username
       password: your_password
   ```

4. **Run the application**
   ```bash
   cd backend
   mvn spring-boot:run
   ```

The application will start on `http://localhost:8080`

### Docker Compose (Alternative)

```bash
docker-compose up -d
```

## API Documentation

Once the application is running, visit:
- Swagger UI: http://localhost:8080/swagger-ui.html
- API Docs: http://localhost:8080/v3/api-docs

## Default Credentials

The system comes with a pre-configured admin user:
- **Username**: `admin`
- **Password**: `admin123`

## API Usage

### Authentication

**Login Request:**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "admin123"
  }'
```

**Response:**
```json
{
  "success": true,
  "message": "Login successful",
  "data": {
    "accessToken": "eyJhbGciOiJIUzI1NiJ9...",
    "tokenType": "Bearer",
    "user": {
      "id": 1,
      "username": "admin",
      "email": "admin@hospital.com",
      "firstName": "Admin",
      "lastName": "User",
      "role": "ADMIN"
    }
  },
  "timestamp": "2024-01-01T10:00:00"
}
```

### Using JWT Token

Include the token in the Authorization header for protected endpoints:
```bash
curl -X GET http://localhost:8080/api/auth/me \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

## Health Check

Monitor application health:
```bash
curl http://localhost:8080/api/actuator/health
```

## Database Schema

The application uses Flyway for database migrations. The initial migration (`V1__init_schema_and_admin_user.sql`) creates:

- `users` table with role-based access control
- Indexes for performance optimization
- Default admin user with BCrypt-hashed password

## Development

### Build
```bash
cd backend
mvn clean compile
```

### Package
```bash
cd backend
mvn clean package
```

### Run Tests
```bash
cd backend
mvn test
```

## Production Deployment

### Environment Variables

Set the following environment variables for production:

```bash
export SPRING_DATASOURCE_URL=jdbc:mysql://your-db-host:3306/hospital_db
export SPRING_DATASOURCE_USERNAME=your_username
export SPRING_DATASOURCE_PASSWORD=your_password
export APP_JWT_SECRET=your-256-bit-secret-key
```

### Docker Build

```bash
cd backend
docker build -t hospital-backend .
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.
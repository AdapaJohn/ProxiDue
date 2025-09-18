# ProxiDue - Full-Stack Todo Management Application

Welcome to **ProxiDue**, a comprehensive full-stack Todo management application built with React.js frontend and Spring Boot backend. This application provides secure user authentication, todo management, and email notification features with a modern, responsive user interface.

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0-blue)
![React](https://img.shields.io/badge/React-18-61dafb)
![JWT](https://img.shields.io/badge/Security-JWT-green)
![MySQL](https://img.shields.io/badge/Database-MySQL-4479a1)

---

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Architecture](#architecture)
- [Project Structure](#project-structure)
- [Installation & Setup](#installation--setup)
- [API Endpoints](#api-endpoints)
- [Usage](#usage)
- [Security Features](#security-features)
- [Email Integration](#email-integration)
- [Contributing](#contributing)
- [License](#license)

---

## Overview

**ProxiDue** is a full-stack web application that allows users to manage their tasks efficiently. It features secure JWT-based authentication, RESTful APIs, real-time email notifications, and a responsive React frontend. The application follows modern development practices with proper separation of concerns between frontend and backend.

---

## Features

### 🔐 Authentication & Security
- JWT-based authentication with token refresh mechanism
- Role-based access control
- Secure password handling
- Automatic token management

### 📝 Todo Management
- Create, read, update, and delete todos
- Set due dates and mark tasks as complete
- Organize todos by user
- Attractive user interface with form validation

### 📧 Email Integration
- Send order confirmation emails
- HTML email templates with styling
- Test email functionality
- SMTP integration with Gmail

### 🎨 User Experience
- Responsive React frontend with Bootstrap styling
- Form validation using Formik
- Error handling and user feedback
- Intuitive navigation and routing

### 🔧 Backend Features
- RESTful APIs with proper HTTP status codes
- MySQL database integration
- Spring Data JPA for data persistence
- CORS configuration for frontend-backend communication

---

## Tech Stack

### Frontend
- **React 18** - UI framework
- **React Router** - Navigation and routing
- **Axios** - HTTP client for API calls
- **Formik** - Form handling and validation
- **Bootstrap** - UI styling and components
- **JWT Decode** - Token management

### Backend
- **Spring Boot 3.0** - Application framework
- **Spring Security** - Authentication and authorization
- **Spring Data JPA** - Database operations
- **MySQL** - Relational database
- **Java Mail** - Email functionality
- **JWT** - Token-based authentication

### Development Tools
- **Maven** - Dependency management
- **Node.js & npm** - Frontend package management
- **Eclipse/IntelliJ** - IDE support

---

## Architecture

```
Client (React) ←HTTP→ Spring Boot REST API ←→ MySQL Database
    │                             │
    │                             │
    └──────JWT Authentication─────┘
                │
                │
          Email Service (SMTP)
```

The application follows a client-server architecture with clear separation between:
- Frontend React application running on port 5173
- Backend Spring Boot application running on port 8080
- MySQL database for data persistence
- SMTP server for email functionality

---

## Project Structure

### Frontend (React)
```
Todo/
├── Components/
│   ├── EmailComponent.jsx       # Email sending interface
│   ├── ErrorComponent.jsx       # 404 error page
│   ├── FooterComponent.jsx      # Application footer
│   ├── HeaderComponent.jsx      # Navigation header
│   ├── LoginComponent.jsx       # User authentication
│   ├── LogOutComponent.jsx      # Logout confirmation
│   ├── TodoComponent.jsx        # Single todo form
│   ├── TodoListComponent.jsx    # Todo list display
│   └── WelcomeComponent.jsx     # Welcome dashboard
├── api/
│   ├── ApiClient.jsx            # Axios configuration
│   ├── AuthenticationApiService.jsx  # Auth API calls
│   ├── EmailService.jsx         # Email API calls
│   ├── HelloWorldRestAPIService.jsx  # Test endpoints
│   └── TodoRestAPIService.jsx   # Todo CRUD operations
├── security/
│   └── AuthContext.jsx          # Authentication context
└── TodoApp.jsx                  # Main application component
```

### Backend (Spring Boot)
```
src/main/java/com/in28minutes/rest/webservices/restfulwebservices/
├── EmailDemo/
│   ├── EmailService.java        # Email service implementation
│   ├── NSController.java        # Order notification controller
│   └── Notificationservice.java # Notification service
├── helloworld/
│   ├── HelloWorldBean.java      # Test bean
│   └── HelloWorldController.java # Test endpoints
├── jwt/
│   ├── JwtAuthenticationController.java # JWT auth endpoints
│   ├── JwtSecurityConfig.java   # Security configuration
│   ├── JwtTokenRequest.java     # Auth request DTO
│   ├── JwtTokenResponse.java    # Auth response DTO
│   └── JwtTokenService.java     # JWT token handling
├── security/
│   └── BasicSecurityAuthentication.java # Basic auth config
├── todo/
│   ├── Todo.java                # Todo entity
│   ├── TodoJpaResource.java     # Todo REST controller
│   ├── TodoRepository.java      # Todo repository
│   ├── TodoResource.java        # Legacy todo controller
│   └── TodoService.java         # Todo business logic
└── RestfulWebServicesApplication.java # Main application class
```

---

## Installation & Setup

### Prerequisites
- Java 17 or higher
- Node.js 16+ and npm
- MySQL 8.0+
- Maven 3.6+

### Database Setup
1. Create MySQL database:
   ```sql
   CREATE DATABASE todos;
   CREATE USER 'todos-user'@'localhost' IDENTIFIED BY 'dummytodos';
   GRANT ALL PRIVILEGES ON todos.* TO 'todos-user'@'localhost';
   FLUSH PRIVILEGES;
   ```

2. The application will automatically create tables using JPA auto-ddl

### Backend Setup
1. Navigate to the Spring Boot project directory:
   ```bash
   cd 01-rest-api-starting-code
   ```

2. Configure database and email in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/todos
   spring.datasource.username=todos-user
   spring.datasource.password=dummytodos
   
   spring.mail.host=smtp.gmail.com
   spring.mail.port=587
   spring.mail.username=your-email@gmail.com
   spring.mail.password=your-app-password
   ```

3. Build and run the application:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

### Frontend Setup
1. Navigate to the React project directory:
   ```bash
   cd Todo
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Start the development server:
   ```bash
   npm start
   ```

4. The application will be available at `http://localhost:5173`

---

## API Endpoints

### Authentication
- `POST /authenticate` - Login and receive JWT token
- `GET /basicAuth` - Basic authentication test

### Todos
- `GET /users/{username}/todos` - Get all todos for user
- `GET /users/{username}/todos/{id}` - Get specific todo
- `POST /users/{username}/todos` - Create new todo
- `PUT /users/{username}/todos/{id}` - Update todo
- `DELETE /users/{username}/todos/{id}` - Delete todo

### Email
- `POST /api/orders/send-notification` - Send order email
- `GET /test/email` - Test email functionality

### Test Endpoints
- `GET /hello-world` - Simple test endpoint
- `GET /hello-world-bean` - JSON test endpoint
- `GET /hello-world/path-variable/{name}` - Path variable test

---

## Usage

### Authentication
1. Open the application in your browser
2. Use the default credentials:
   - Username: `nanna`
   - Password: `amma`
3. Upon successful login, you'll receive a JWT token for subsequent requests

### Managing Todos
1. Navigate to the "Todos" section from the header
2. View your existing todos in a table format
3. Use "Add New Todo" to create a new task
4. Click "Update" to modify an existing todo
5. Click "Delete" to remove a todo

### Email Features
1. Navigate to the email section (if available in your UI)
2. Test email functionality using the "Send Test Email" button
3. Send order confirmation emails with recipient and details

---

## Security Features

- **JWT Authentication**: Secure token-based authentication with expiration
- **Password Encryption**: BCrypt password encoding
- **CORS Configuration**: Proper cross-origin resource sharing setup
- **Role-Based Access**: Protected routes based on user roles
- **Token Refresh**: Automatic token renewal mechanism
- **SQL Injection Prevention**: Parameterized queries through JPA

---

## Email Integration

The application integrates with Gmail SMTP for sending emails:

1. **HTML Email Templates**: Attractive formatted emails for todo notifications
2. **Order Confirmations**: Send order details to customers
3. **Test Endpoint**: Verify email functionality without UI
4. **Error Handling**: Proper exception handling for email failures

To configure email:
1. Enable 2-factor authentication on your Gmail account
2. Generate an app-specific password
3. Update the `application.properties` with your credentials

---

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

**Developed and maintained by [AdapaJohn](https://github.com/AdapaJohn)**

For questions or support, please open an issue on GitHub or contact the development team.
```

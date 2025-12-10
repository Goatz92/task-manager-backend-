# Task Manager Pro

A Spring Boot 4.0 application for managing tasks with robust role-based security, JWT authentication, and a PostgreSQL backend. Built with a layered MVC architecture for clarity and maintainability.

## Features

## Installation

1. **Clone the repository:**
  ```bash
  git clone https://github.com/Goatz92/task-manager-backend-.git
  cd task-manager-pro/task-manager-pro
  ```
2. **Install Java 17+ and Maven 3.8+** (if not already installed).
3. **Set up PostgreSQL:**
  - Create a database (e.g., `taskdb`).
  - Create a user and grant privileges.
4. **Configure environment variables:**
  - Copy `.env.example` to `.env` and set:
    - `DB_URL`=your_db_connection_string
    - `DB_USER`=your_db_username
    - `DB_PASSWORD`=your_db_password
    - `JWT_SECRET`=your_jwt_secret
5. **Build and run the application:**
  ```bash
  mvn clean package
  mvn spring-boot:run
  ```

---

## Architecture
- **Controllers**: Handle HTTP requests, form validation, and model binding
- **Services**: Business logic, transactional operations, data transformation
- **Repositories**: JPA interfaces for data access
- **Models**: JPA entities extending `AbstractEntity` for auditing
- **DTOs**: Data transfer objects with validation


## Getting Started

- See the **Installation** section above for setup steps.
- To run tests:
  ```bash
  mvn test
  ```

## Key Files & Directories
- `src/main/java/com/goatz/task_manager_pro/`
  - `controller/` — Web controllers
  - `service/` — Business logic
  - `repository/` — Data access
  - `model/` — JPA entities
  - `dto/` — Data transfer objects
  - `auth/` — Security config and user details
  - `util/` — JWT and environment helpers
  - `validator/` — Custom form validators
- `src/main/resources/`
  - `application.properties` — Spring config
  - `templates/` — Thymeleaf views
  - `static/` — Static assets

## API Endpoints

### Authentication & User
- `GET /login` — Login page
- `POST /task/users/register` — Register a new user

### Task Management
- `GET /task/edit/{uuid}` — Edit a task (requires `EDIT_TASK` capability)
- `GET /task/delete/{uuid}` — Delete a task (requires `EDIT_TASK` capability)
- `POST /task/edit` — Submit task edits (requires `EDIT_TASK` capability)

### Admin & User Management
- `GET /users/tasks/**` — User task management (admin only)
- `GET /users/admin/**` — Admin-only endpoints

### Public & Static
- `GET /` — Home page
- `GET /index.html` — Index page
- `GET /css/**`, `/js/**`, `/img/**` — Static assets

> **Note:** Most endpoints require authentication and specific roles/capabilities. See `SecurityConfig.java` for detailed access rules.

## Security
- Custom `SecurityConfig` for endpoint protection
- `CustomUserDetailsService` for authentication
- JWT utilities in `util/JwtUtil.java`
- Passwords encoded with BCrypt

## Validation
- DTOs use annotation-based validation
- Custom validators (e.g., `UserInsertValidator`) for business rules

## Notes
- The `Task` entity is currently empty (feature in progress)
- Role entities must exist in the database before assigning to users
- All entities inherit auditing fields from `AbstractEntity`

## License
MIT

---
For more details, see `.github/copilot-instructions.md` for AI agent and contributor guidelines.

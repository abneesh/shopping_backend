# 6amMart Backend API

A comprehensive Spring Boot REST API for the 6amMart multi-vendor marketplace application.

## Features

- **User Authentication & Authorization** with JWT
- **Multi-vendor Store Management**
- **Product/Item Management** with categories
- **Shopping Cart** functionality
- **Order Management** with real-time tracking
- **Zone-based Delivery** system
- **Module-based Architecture** (Grocery, Food, Pharmacy, etc.)
- **Banner Management** for promotions
- **RESTful API** with comprehensive endpoints
- **Swagger Documentation** for API testing
- **H2 Database** for development (easily configurable for production)

## Technology Stack

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Security** with JWT
- **Spring Data JPA**
- **H2 Database** (development)
- **Maven** for dependency management
- **Swagger/OpenAPI 3** for documentation

## Quick Start

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

### Installation & Running

1. **Clone the repository**
   ```bash
   cd java_backend
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the application**
   - API Base URL: `http://localhost:8080/api/v1`
   - Swagger UI: `http://localhost:8080/swagger-ui/index.html`
   - H2 Console: `http://localhost:8080/h2-console`

### H2 Database Configuration

- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: `password`

## API Endpoints

### Authentication
- `POST /api/v1/auth/register` - Register new user
- `POST /api/v1/auth/login` - User login
- `POST /api/v1/auth/social-login` - Social media login

### Configuration
- `GET /api/v1/config` - Get app configuration
- `GET /api/v1/config/get-zone-id` - Get zone by coordinates

### Stores
- `GET /api/v1/stores/get-stores/all` - Get all stores
- `GET /api/v1/stores/get-stores/featured` - Get featured stores
- `GET /api/v1/stores/{id}` - Get store by ID
- `GET /api/v1/stores/search` - Search stores

### Items/Products
- `GET /api/v1/items` - Get all items
- `GET /api/v1/items/popular` - Get popular items
- `GET /api/v1/items/recommended` - Get recommended items
- `GET /api/v1/items/{id}` - Get item by ID

### Categories
- `GET /api/v1/categories` - Get all categories
- `GET /api/v1/categories/featured` - Get featured categories
- `GET /api/v1/categories/{id}` - Get category by ID

### Cart (Requires Authentication)
- `GET /api/v1/customer/cart/list` - Get cart items
- `POST /api/v1/customer/cart/add` - Add item to cart
- `PUT /api/v1/customer/cart/update/{id}` - Update cart item
- `DELETE /api/v1/customer/cart/remove/{id}` - Remove from cart

### Orders (Requires Authentication)
- `GET /api/v1/customer/order/list` - Get user orders
- `POST /api/v1/customer/order/place` - Place new order
- `GET /api/v1/customer/order/{id}` - Get order details
- `PUT /api/v1/customer/order/{id}/cancel` - Cancel order

### Banners
- `GET /api/v1/banners` - Get all banners
- `GET /api/v1/banners/featured` - Get featured banners

### Modules
- `GET /api/v1/modules` - Get all modules
- `GET /api/v1/modules/{id}` - Get module by ID

## Authentication

The API uses JWT (JSON Web Tokens) for authentication. Include the token in the Authorization header:

```
Authorization: Bearer <your-jwt-token>
```

### Sample User Credentials

The application initializes with sample users:

1. **Regular User**
   - Email: `john@example.com`
   - Password: `password123`

2. **Admin User**
   - Email: `admin@6ammart.com`
   - Password: `admin123`

## Sample API Calls

### 1. Register a new user
```bash
curl -X POST http://localhost:8080/api/v1/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "fName": "John",
    "lName": "Doe",
    "email": "john@example.com",
    "phone": "+1234567890",
    "password": "password123"
  }'
```

### 2. Login
```bash
curl -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "phoneOrEmail": "john@example.com",
    "password": "password123"
  }'
```

### 3. Get all stores
```bash
curl -X GET http://localhost:8080/api/v1/stores/get-stores/all
```

### 4. Add item to cart (requires authentication)
```bash
curl -X POST http://localhost:8080/api/v1/customer/cart/add \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <your-jwt-token>" \
  -d '{
    "itemId": 1,
    "quantity": 2
  }'
```

## Database Schema

The application uses the following main entities:

- **Users** - Customer information and authentication
- **Stores** - Vendor store details
- **Items** - Products/items in stores
- **Categories** - Product categories
- **Orders** - Customer orders
- **OrderDetails** - Order line items
- **CartItems** - Shopping cart items
- **Banners** - Promotional banners
- **Modules** - Business modules (Grocery, Food, etc.)
- **Zones** - Delivery zones
- **Addresses** - Customer addresses

## Configuration

### Application Properties

Key configuration options in `application.yml`:

```yaml
server:
  port: 8080
  servlet:
    context-path: /api/v1

spring:
  datasource:
    url: jdbc:h2:mem:testdb
    username: sa
    password: password

jwt:
  secret: 6amMartSecretKey2024ForJWTTokenGeneration
  expiration: 86400000 # 24 hours
```

### Production Configuration

For production deployment, update the database configuration:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/sixammart
    username: your_username
    password: your_password
    driver-class-name: com.mysql.cj.jdbc.Driver
  
  jpa:
    hibernate:
      ddl-auto: validate
    database-platform: org.hibernate.dialect.MySQL8Dialect
```

## Development

### Project Structure

```
src/main/java/com/sixammart/
├── SixamMartApplication.java          # Main application class
├── config/                            # Configuration classes
├── controller/                        # REST controllers
├── dto/                              # Data Transfer Objects
├── entity/                           # JPA entities
├── repository/                       # Data repositories
├── security/                         # Security configuration
├── service/                          # Business logic services
└── exception/                        # Exception handlers
```

### Adding New Features

1. Create entity classes in `entity/` package
2. Create repository interfaces in `repository/` package
3. Implement business logic in `service/` package
4. Create REST endpoints in `controller/` package
5. Add DTOs in `dto/` package if needed

## Testing

Run tests with:

```bash
mvn test
```

## API Documentation

Once the application is running, visit:
- **Swagger UI**: http://localhost:8080/swagger-ui/index.html

This provides interactive API documentation where you can test all endpoints.

## Support

For support and questions, please contact the development team or create an issue in the repository.

## License

This project is licensed under the MIT License.

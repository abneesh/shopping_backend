# MintMey Backend - Render Deployment Guide

## 🚀 Deployment Steps

### 1. Create MySQL Database on Render

1. **Go to Render Dashboard**
2. **Click "New" → "PostgreSQL"** (Note: Render provides PostgreSQL, not MySQL in free tier)
3. **Alternative: Use MySQL from external provider like PlanetScale, Railway, or Aiven**

### 2. Environment Variables for Render

Set these environment variables in Render dashboard:

```bash
# Database Configuration
DATABASE_URL=jdbc:mysql://your-mysql-host:3306/mintmey_db?useSSL=true&serverTimezone=UTC
DATABASE_USERNAME=your_username
DATABASE_PASSWORD=your_password

# Application Configuration
SPRING_PROFILES_ACTIVE=prod

# JWT Configuration (Optional - has defaults)
JWT_SECRET=MintMeySecretKey2024ForJWTTokenGeneration
JWT_EXPIRATION=86400000
```

### 3. Build and Start Commands

**Build Command:**
```bash
chmod +x mvnw && ./mvnw clean package -DskipTests
```

**Start Command:**
```bash
java -jar target/sixammart-backend-1.0.0.jar --server.port=$PORT --server.address=0.0.0.0 --spring.profiles.active=prod
```

### 4. Health Check

**Health Check Path:** `/api/v1/health`

## 🗄️ Database Options

### Option A: External MySQL Provider (Recommended)

**PlanetScale (Free Tier):**
- 5GB storage
- 1 billion row reads/month
- 10 million row writes/month

**Railway (Free Tier):**
- 1GB storage
- Shared CPU

**Aiven (Free Tier):**
- 1 month free trial

### Option B: Use PostgreSQL on Render (Alternative)

If you prefer PostgreSQL instead of MySQL:

1. **Create PostgreSQL database on Render**
2. **Update dependencies in pom.xml:**
```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>
```

3. **Update application-prod.properties:**
```properties
spring.datasource.url=${DATABASE_URL}
spring.datasource.driverClassName=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

## 🔧 Current Configuration

- **Database:** MySQL 8.0+
- **Connection Pool:** HikariCP
- **JPA:** Hibernate with MySQL8Dialect
- **DDL:** Auto-update (creates tables automatically)
- **Initial Data:** Loaded via DataInitializationService

## 📋 API Endpoints

Once deployed, your API will be available at:
- **Base URL:** `https://your-app-name.onrender.com/api/v1`
- **Health Check:** `https://your-app-name.onrender.com/api/v1/health`
- **Config:** `https://your-app-name.onrender.com/api/v1/config`
- **Swagger UI:** `https://your-app-name.onrender.com/swagger-ui/index.html`

## 🚨 Important Notes

1. **Database Creation:** Tables are auto-created by Hibernate
2. **Initial Data:** Sample data is loaded automatically
3. **File Uploads:** Stored in `/uploads` directory (consider cloud storage for production)
4. **CORS:** Configured to allow all origins (update for production security)
5. **SSL:** Render provides HTTPS automatically

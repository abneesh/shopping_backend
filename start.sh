#!/bin/bash

# Render start script for Java Spring Boot application

echo "Starting MintMey Backend Server..."

# Set environment variables
export SPRING_PROFILES_ACTIVE=prod
export SERVER_PORT=${PORT:-8080}

# Start the application
java -jar target/sixammart-backend-1.0.0.jar \
    --server.port=$SERVER_PORT \
    --server.address=0.0.0.0 \
    --spring.profiles.active=prod

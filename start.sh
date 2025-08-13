#!/bin/bash

echo "Starting MintMey Backend Server..."

# Start the application
java -jar target/sixammart-backend-1.0.0.jar \
    --server.port=${PORT:-8080} \
    --server.address=0.0.0.0 \
    --spring.profiles.active=prod

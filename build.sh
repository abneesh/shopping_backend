#!/bin/bash

# Render build script for Java Spring Boot application

echo "Starting build process..."

# Install Maven if not present
if ! command -v mvn &> /dev/null; then
    echo "Installing Maven..."
    curl -fsSL https://archive.apache.org/dist/maven/maven-3/3.8.6/binaries/apache-maven-3.8.6-bin.tar.gz | tar xz -C /opt
    export PATH="/opt/apache-maven-3.8.6/bin:$PATH"
fi

# Set JAVA_HOME if not set
if [ -z "$JAVA_HOME" ]; then
    export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
fi

echo "Java version:"
java -version

echo "Maven version:"
mvn -version

# Clean and build the project
echo "Building the application..."
mvn clean package -DskipTests

echo "Build completed successfully!"

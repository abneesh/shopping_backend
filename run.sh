#!/bin/bash

# 6amMart Backend Startup Script

echo "🚀 Starting 6amMart Backend API..."
echo "=================================="

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "❌ Java is not installed. Please install Java 17 or higher."
    exit 1
fi

# Check Java version
JAVA_VERSION=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}' | cut -d'.' -f1)
if [ "$JAVA_VERSION" -lt 17 ]; then
    echo "❌ Java 17 or higher is required. Current version: $JAVA_VERSION"
    exit 1
fi

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven is not installed. Please install Maven 3.6 or higher."
    exit 1
fi

echo "✅ Java version: $(java -version 2>&1 | head -n 1)"
echo "✅ Maven version: $(mvn -version | head -n 1)"
echo ""

# Clean and build the project
echo "🔨 Building the project..."
mvn clean install -q

if [ $? -ne 0 ]; then
    echo "❌ Build failed. Please check the error messages above."
    exit 1
fi

echo "✅ Build successful!"
echo ""

# Start the application
echo "🌟 Starting the application..."
echo "📍 API will be available at: http://localhost:8080/api/v1"
echo "📚 Swagger UI: http://localhost:8080/swagger-ui/index.html"
echo "🗄️  H2 Console: http://localhost:8080/h2-console"
echo ""
echo "Press Ctrl+C to stop the application"
echo "=================================="

mvn spring-boot:run

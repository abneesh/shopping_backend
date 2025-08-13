#!/bin/bash

echo "Starting MintMey Backend build..."

# Simple build command for Render
mvn clean package -DskipTests

echo "Build completed!"

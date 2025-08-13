#!/bin/bash

# 6amMart Backend API Test Script

BASE_URL="http://localhost:8082/api/v1"

echo "🧪 Testing 6amMart Backend API"
echo "==============================="

# Test if server is running
echo "1. Testing server connectivity..."
if curl -s "$BASE_URL/config" > /dev/null; then
    echo "✅ Server is running"
else
    echo "❌ Server is not running. Please start the server first."
    exit 1
fi

echo ""

# Test configuration endpoint
echo "2. Testing configuration endpoint..."
CONFIG_RESPONSE=$(curl -s "$BASE_URL/config")
if echo "$CONFIG_RESPONSE" | grep -q "business_name"; then
    echo "✅ Configuration endpoint working"
else
    echo "❌ Configuration endpoint failed"
fi

echo ""

# Test stores endpoint
echo "3. Testing stores endpoint..."
STORES_RESPONSE=$(curl -s "$BASE_URL/stores/get-stores/all")
if echo "$STORES_RESPONSE" | grep -q "success"; then
    echo "✅ Stores endpoint working"
else
    echo "❌ Stores endpoint failed"
fi

echo ""

# Test modules endpoint
echo "4. Testing modules endpoint..."
MODULES_RESPONSE=$(curl -s "$BASE_URL/modules")
if echo "$MODULES_RESPONSE" | grep -q "success"; then
    echo "✅ Modules endpoint working"
else
    echo "❌ Modules endpoint failed"
fi

echo ""

# Test categories endpoint
echo "5. Testing categories endpoint..."
CATEGORIES_RESPONSE=$(curl -s "$BASE_URL/categories")
if echo "$CATEGORIES_RESPONSE" | grep -q "success"; then
    echo "✅ Categories endpoint working"
else
    echo "❌ Categories endpoint failed"
fi

echo ""

# Test items endpoint
echo "6. Testing items endpoint..."
ITEMS_RESPONSE=$(curl -s "$BASE_URL/items")
if echo "$ITEMS_RESPONSE" | grep -q "success"; then
    echo "✅ Items endpoint working"
else
    echo "❌ Items endpoint failed"
fi

echo ""

# Test banners endpoint
echo "7. Testing banners endpoint..."
BANNERS_RESPONSE=$(curl -s "$BASE_URL/banners")
if echo "$BANNERS_RESPONSE" | grep -q "success"; then
    echo "✅ Banners endpoint working"
else
    echo "❌ Banners endpoint failed"
fi

echo ""

# Test user registration
echo "8. Testing user registration..."
REGISTER_RESPONSE=$(curl -s -X POST "$BASE_URL/auth/register" \
  -H "Content-Type: application/json" \
  -d '{
    "fName": "Test",
    "lName": "User",
    "email": "test@example.com",
    "phone": "+1234567899",
    "password": "password123"
  }')

if echo "$REGISTER_RESPONSE" | grep -q "token"; then
    echo "✅ User registration working"
    TOKEN=$(echo "$REGISTER_RESPONSE" | grep -o '"token":"[^"]*"' | cut -d'"' -f4)
else
    echo "⚠️  User registration failed (user might already exist)"
    # Try login instead
    LOGIN_RESPONSE=$(curl -s -X POST "$BASE_URL/auth/login" \
      -H "Content-Type: application/json" \
      -d '{
        "phoneOrEmail": "john@example.com",
        "password": "password123"
      }')
    
    if echo "$LOGIN_RESPONSE" | grep -q "token"; then
        echo "✅ User login working"
        TOKEN=$(echo "$LOGIN_RESPONSE" | grep -o '"token":"[^"]*"' | cut -d'"' -f4)
    else
        echo "❌ Both registration and login failed"
        TOKEN=""
    fi
fi

echo ""

# Test authenticated endpoints if we have a token
if [ -n "$TOKEN" ]; then
    echo "9. Testing authenticated cart endpoint..."
    CART_RESPONSE=$(curl -s "$BASE_URL/customer/cart/list" \
      -H "Authorization: Bearer $TOKEN")
    
    if echo "$CART_RESPONSE" | grep -q "success"; then
        echo "✅ Cart endpoint working"
    else
        echo "❌ Cart endpoint failed"
    fi
else
    echo "9. ⚠️  Skipping authenticated tests (no token available)"
fi

echo ""
echo "🎉 API Testing Complete!"
echo "========================"
echo ""
echo "📍 API Base URL: $BASE_URL"
echo "📚 Swagger UI: http://localhost:8082/swagger-ui/index.html"
echo "🗄️  H2 Console: http://localhost:8082/h2-console"
echo ""
echo "Sample credentials:"
echo "  Email: john@example.com"
echo "  Password: password123"

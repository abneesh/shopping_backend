#!/bin/bash

# MySQL Connection Test Script for MintMey Backend

echo "🔍 Testing MySQL Database Connection"
echo "===================================="

# Test with production URL
PROD_URL="https://shopping-backend-21en.onrender.com/api/v1"

echo "1. Testing Production Health Check..."
HEALTH_RESPONSE=$(curl -s -w "%{http_code}" "$PROD_URL/health")
HTTP_CODE="${HEALTH_RESPONSE: -3}"
RESPONSE_BODY="${HEALTH_RESPONSE%???}"

if [ "$HTTP_CODE" = "200" ]; then
    echo "✅ Production Health Check: SUCCESS (HTTP $HTTP_CODE)"
    echo "   Response: $RESPONSE_BODY"
else
    echo "❌ Production Health Check: FAILED (HTTP $HTTP_CODE)"
    echo "   Response: $RESPONSE_BODY"
fi

echo ""

echo "2. Testing Production Config API..."
CONFIG_RESPONSE=$(curl -s -w "%{http_code}" "$PROD_URL/config")
HTTP_CODE="${CONFIG_RESPONSE: -3}"
RESPONSE_BODY="${CONFIG_RESPONSE%???}"

if [ "$HTTP_CODE" = "200" ]; then
    echo "✅ Production Config API: SUCCESS (HTTP $HTTP_CODE)"
    echo "   Database connection working!"
else
    echo "❌ Production Config API: FAILED (HTTP $HTTP_CODE)"
    echo "   Response: $RESPONSE_BODY"
fi

echo ""

echo "3. Testing Production Categories API..."
CATEGORIES_RESPONSE=$(curl -s -w "%{http_code}" "$PROD_URL/categories")
HTTP_CODE="${CATEGORIES_RESPONSE: -3}"
RESPONSE_BODY="${CATEGORIES_RESPONSE%???}"

if [ "$HTTP_CODE" = "200" ]; then
    echo "✅ Production Categories API: SUCCESS (HTTP $HTTP_CODE)"
    echo "   MySQL data retrieval working!"
else
    echo "❌ Production Categories API: FAILED (HTTP $HTTP_CODE)"
    echo "   Response: $RESPONSE_BODY"
fi

echo ""
echo "🎉 MySQL Connection Test Complete!"
echo "=================================="
echo ""
echo "📍 Production API: $PROD_URL"
echo "🗄️  Database: MySQL (Production)"
echo "🔗 Health Check: $PROD_URL/health"

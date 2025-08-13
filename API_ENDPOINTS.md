# 6amMart Backend API Endpoints

Complete list of all available API endpoints in the 6amMart backend.

## Base URL
```
http://localhost:8080/api/v1
```

## Authentication Endpoints

### POST /auth/register
Register a new user account.

**Request Body:**
```json
{
  "fName": "John",
  "lName": "Doe",
  "email": "john@example.com",
  "phone": "+1234567890",
  "password": "password123",
  "refCode": "optional_referral_code"
}
```

### POST /auth/login
Login with email/phone and password.

**Request Body:**
```json
{
  "phoneOrEmail": "john@example.com",
  "password": "password123"
}
```

### POST /auth/social-login
Login with social media credentials.

### POST /auth/verify-phone
Verify phone number with OTP.

### POST /auth/verify-email
Verify email address with token.

### POST /auth/forgot-password
Request password reset.

### POST /auth/reset-password
Reset password with OTP.

## Configuration Endpoints

### GET /config
Get complete app configuration.

**Response includes:**
- Business settings
- Feature flags
- Payment methods
- Language settings
- Social login configuration

### GET /config/get-zone-id
Get zone ID by coordinates.

**Parameters:**
- `lat` (required): Latitude
- `lng` (required): Longitude

### GET /config/place-api-autocomplete
Google Places API autocomplete.

### GET /config/distance-api
Calculate distance between coordinates.

### GET /config/geocode-api
Reverse geocoding for coordinates.

## Store Endpoints

### GET /stores/get-stores/all
Get all active stores with pagination.

**Parameters:**
- `offset` (default: 0): Pagination offset
- `limit` (default: 10): Items per page

### GET /stores/get-stores/latest
Get latest stores.

### GET /stores/get-stores/popular
Get popular stores.

### GET /stores/get-stores/featured
Get featured stores.

### GET /stores/{id}
Get store details by ID.

### GET /stores/details/{slug}
Get store details by slug.

### GET /stores/search
Search stores by name.

**Parameters:**
- `name` (required): Search term
- `offset`, `limit`: Pagination

### GET /stores/zone/{zoneId}
Get stores in specific zone.

### GET /stores/module/{moduleId}
Get stores by module.

### GET /stores/nearby
Get nearby stores.

**Parameters:**
- `latitude`, `longitude` (required): Location
- `radius` (default: 10.0): Search radius in km

## Item/Product Endpoints

### GET /items
Get all items with filtering options.

**Parameters:**
- `offset`, `limit`: Pagination
- `store_id`: Filter by store
- `category_id`: Filter by category

### GET /items/latest
Get latest items.

### GET /items/popular
Get popular items.

**Parameters:**
- `limit` (default: 10): Number of items
- `store_id`: Filter by store

### GET /items/recommended
Get recommended items.

### GET /items/organic
Get organic items.

### GET /items/discounted
Get items with discounts.

### GET /items/top-rated
Get top-rated items.

### GET /items/veg
Get vegetarian items.

### GET /items/non-veg
Get non-vegetarian items.

### GET /items/{id}
Get item details by ID.

### GET /items/slug/{slug}
Get item details by slug.

### GET /items/search
Search items by name.

### GET /items/store/{storeId}
Get items by store.

### GET /items/category/{categoryId}
Get items by category.

## Category Endpoints

### GET /categories
Get categories with filtering.

**Parameters:**
- `module_id`: Filter by module
- `parent_id`: Get subcategories

### GET /categories/all
Get all categories including subcategories.

### GET /categories/featured
Get featured categories.

### GET /categories/priority
Get priority categories.

### GET /categories/{id}
Get category by ID.

### GET /categories/slug/{slug}
Get category by slug.

### GET /categories/search
Search categories by name.

### GET /categories/module/{moduleId}
Get categories by module.

### GET /categories/subcategories/{parentId}
Get subcategories.

## Cart Endpoints (Authenticated)

### GET /customer/cart/list
Get user's cart items.

### POST /customer/cart/add
Add item to cart.

**Request Body:**
```json
{
  "itemId": 1,
  "quantity": 2,
  "variations": "optional_variations",
  "addOns": "optional_addons"
}
```

### PUT /customer/cart/update/{cartItemId}
Update cart item quantity.

### DELETE /customer/cart/remove/{cartItemId}
Remove item from cart.

### DELETE /customer/cart/clear
Clear entire cart.

### DELETE /customer/cart/clear-store/{storeId}
Clear cart items from specific store.

### GET /customer/cart/count
Get cart items count and total.

### GET /customer/cart/store/{storeId}
Get cart items from specific store.

## Order Endpoints (Authenticated)

### GET /customer/order/list
Get user's orders with pagination.

### GET /customer/order/{id}
Get order details by ID.

### POST /customer/order/place
Place a new order.

**Request Body:**
```json
{
  "storeId": 1,
  "orderAmount": 25.99,
  "paymentMethod": "cash_on_delivery",
  "deliveryAddressId": 1,
  "orderType": "delivery",
  "orderItems": [
    {
      "itemId": 1,
      "quantity": 2,
      "price": 12.99
    }
  ]
}
```

### PUT /customer/order/{id}/cancel
Cancel an order.

### GET /customer/order/track/{id}
Track order status.

### GET /customer/order/running
Get active/running orders.

### GET /customer/order/history
Get completed order history.

### POST /customer/order/{id}/review
Submit order review.

## Banner Endpoints

### GET /banners
Get banners with filtering.

**Parameters:**
- `module_id`: Filter by module
- `zone_id`: Filter by zone

### GET /banners/featured
Get featured banners.

### GET /banners/type/{type}
Get banners by type.

### GET /banners/{id}
Get banner by ID.

## Module Endpoints

### GET /modules
Get all active modules.

### GET /modules/{id}
Get module by ID.

### GET /modules/slug/{slug}
Get module by slug.

### GET /modules/type/{moduleType}
Get modules by type.

### GET /modules/search
Search modules by name.

### GET /modules/all-zone-service
Get modules available in all zones.

## Response Format

All endpoints return responses in this format:

```json
{
  "success": true,
  "message": "Operation successful",
  "data": { ... },
  "total": 100,
  "limit": 10,
  "offset": 0
}
```

## Authentication

For authenticated endpoints, include the JWT token in the Authorization header:

```
Authorization: Bearer <your-jwt-token>
```

## Error Responses

Error responses follow this format:

```json
{
  "success": false,
  "message": "Error description",
  "data": null
}
```

## Status Codes

- `200` - Success
- `400` - Bad Request
- `401` - Unauthorized
- `404` - Not Found
- `500` - Internal Server Error

## Sample Data

The application initializes with sample data including:
- 4 modules (Grocery, Food, Pharmacy, Electronics)
- 3 zones (Central, North, South)
- 6 categories (Fruits & Vegetables, Dairy & Eggs, etc.)
- 3 stores (Fresh Market, Green Grocery, Super Store)
- 5 items (Fresh Apples, Organic Bananas, etc.)
- 3 banners
- 3 sample users

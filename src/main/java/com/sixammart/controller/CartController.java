package com.sixammart.controller;

import com.sixammart.dto.ApiResponse;
import com.sixammart.dto.CartRequest;
import com.sixammart.entity.CartItem;
import com.sixammart.security.UserPrincipal;
import com.sixammart.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer/cart")
@Tag(name = "Cart", description = "Shopping cart management APIs")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/list")
    @Operation(summary = "Get cart items")
    public ResponseEntity<ApiResponse<List<CartItem>>> getCartItems(
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        try {
            List<CartItem> cartItems = cartService.getCartItems(userPrincipal.getId());
            return ResponseEntity.ok(ApiResponse.success("Cart items retrieved successfully", cartItems));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping("/add")
    @Operation(summary = "Add item to cart")
    public ResponseEntity<ApiResponse<CartItem>> addToCart(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @Valid @RequestBody CartRequest request) {
        try {
            CartItem cartItem = cartService.addToCart(userPrincipal.getId(), request);
            return ResponseEntity.ok(ApiResponse.success("Item added to cart successfully", cartItem));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @PutMapping("/update/{cartItemId}")
    @Operation(summary = "Update cart item")
    public ResponseEntity<ApiResponse<CartItem>> updateCartItem(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable Long cartItemId,
            @Valid @RequestBody CartRequest request) {
        try {
            CartItem cartItem = cartService.updateCartItem(userPrincipal.getId(), cartItemId, request);
            return ResponseEntity.ok(ApiResponse.success("Cart item updated successfully", cartItem));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @DeleteMapping("/remove/{cartItemId}")
    @Operation(summary = "Remove item from cart")
    public ResponseEntity<ApiResponse<String>> removeFromCart(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable Long cartItemId) {
        try {
            cartService.removeFromCart(userPrincipal.getId(), cartItemId);
            return ResponseEntity.ok(ApiResponse.success("Item removed from cart successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @DeleteMapping("/clear")
    @Operation(summary = "Clear cart")
    public ResponseEntity<ApiResponse<String>> clearCart(
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        try {
            cartService.clearCart(userPrincipal.getId());
            return ResponseEntity.ok(ApiResponse.success("Cart cleared successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @DeleteMapping("/clear-store/{storeId}")
    @Operation(summary = "Clear cart by store")
    public ResponseEntity<ApiResponse<String>> clearCartByStore(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable Long storeId) {
        try {
            cartService.clearCartByStore(userPrincipal.getId(), storeId);
            return ResponseEntity.ok(ApiResponse.success("Store items removed from cart successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/count")
    @Operation(summary = "Get cart items count")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getCartCount(
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        try {
            Long count = cartService.getCartItemsCount(userPrincipal.getId());
            Double total = cartService.getCartTotal(userPrincipal.getId());
            
            Map<String, Object> response = Map.of(
                "count", count,
                "total", total
            );
            
            return ResponseEntity.ok(ApiResponse.success("Cart count retrieved successfully", response));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/store/{storeId}")
    @Operation(summary = "Get cart items by store")
    public ResponseEntity<ApiResponse<List<CartItem>>> getCartItemsByStore(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable Long storeId) {
        try {
            List<CartItem> cartItems = cartService.getCartItemsByStore(userPrincipal.getId(), storeId);
            return ResponseEntity.ok(ApiResponse.success("Store cart items retrieved successfully", cartItems));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
}

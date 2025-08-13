package com.sixammart.controller;

import com.sixammart.dto.ApiResponse;
import com.sixammart.entity.Item;
import com.sixammart.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@Tag(name = "Items", description = "Item management APIs")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    @Operation(summary = "Get all active items")
    public ResponseEntity<ApiResponse<List<Item>>> getAllItems(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(required = false) Long store_id,
            @RequestParam(required = false) Long category_id) {
        try {
            List<Item> items;
            
            if (store_id != null && category_id != null) {
                items = itemService.getItemsByStoreAndCategory(store_id, category_id);
            } else if (store_id != null) {
                items = itemService.getItemsByStore(store_id);
            } else if (category_id != null) {
                items = itemService.getItemsByCategory(category_id);
            } else {
                int page = offset / limit;
                Page<Item> itemPage = itemService.getAllActiveItems(page, limit);
                return ResponseEntity.ok(ApiResponse.success(
                    "Items retrieved successfully", 
                    itemPage.getContent(),
                    (int) itemPage.getTotalElements(),
                    limit,
                    offset
                ));
            }
            
            // Apply pagination for non-pageable results
            int total = items.size();
            int fromIndex = Math.min(offset, total);
            int toIndex = Math.min(offset + limit, total);
            List<Item> paginatedItems = items.subList(fromIndex, toIndex);
            
            return ResponseEntity.ok(ApiResponse.success(
                "Items retrieved successfully", 
                paginatedItems,
                total,
                limit,
                offset
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/latest")
    @Operation(summary = "Get latest items")
    public ResponseEntity<ApiResponse<List<Item>>> getLatestItems(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        try {
            int page = offset / limit;
            Page<Item> itemPage = itemService.getAllActiveItems(page, limit);
            
            return ResponseEntity.ok(ApiResponse.success(
                "Latest items retrieved successfully", 
                itemPage.getContent(),
                (int) itemPage.getTotalElements(),
                limit,
                offset
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/popular")
    @Operation(summary = "Get popular items")
    public ResponseEntity<ApiResponse<List<Item>>> getPopularItems(
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(required = false) Long store_id) {
        try {
            List<Item> items;
            if (store_id != null) {
                items = itemService.getPopularItemsByStore(store_id, limit);
            } else {
                items = itemService.getPopularItems(limit);
            }
            return ResponseEntity.ok(ApiResponse.success("Popular items retrieved successfully", items));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/recommended")
    @Operation(summary = "Get recommended items")
    public ResponseEntity<ApiResponse<List<Item>>> getRecommendedItems() {
        try {
            List<Item> items = itemService.getRecommendedItems();
            return ResponseEntity.ok(ApiResponse.success("Recommended items retrieved successfully", items));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/organic")
    @Operation(summary = "Get organic items")
    public ResponseEntity<ApiResponse<List<Item>>> getOrganicItems() {
        try {
            List<Item> items = itemService.getOrganicItems();
            return ResponseEntity.ok(ApiResponse.success("Organic items retrieved successfully", items));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/discounted")
    @Operation(summary = "Get discounted items")
    public ResponseEntity<ApiResponse<List<Item>>> getDiscountedItems() {
        try {
            List<Item> items = itemService.getDiscountedItems();
            return ResponseEntity.ok(ApiResponse.success("Discounted items retrieved successfully", items));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/top-rated")
    @Operation(summary = "Get top rated items")
    public ResponseEntity<ApiResponse<List<Item>>> getTopRatedItems(
            @RequestParam(defaultValue = "10") int limit) {
        try {
            List<Item> items = itemService.getTopRatedItems(limit);
            return ResponseEntity.ok(ApiResponse.success("Top rated items retrieved successfully", items));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/veg")
    @Operation(summary = "Get vegetarian items")
    public ResponseEntity<ApiResponse<List<Item>>> getVegItems() {
        try {
            List<Item> items = itemService.getVegItems();
            return ResponseEntity.ok(ApiResponse.success("Vegetarian items retrieved successfully", items));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/non-veg")
    @Operation(summary = "Get non-vegetarian items")
    public ResponseEntity<ApiResponse<List<Item>>> getNonVegItems() {
        try {
            List<Item> items = itemService.getNonVegItems();
            return ResponseEntity.ok(ApiResponse.success("Non-vegetarian items retrieved successfully", items));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get item by ID")
    public ResponseEntity<ApiResponse<Item>> getItemById(@PathVariable Long id) {
        try {
            Item item = itemService.getItemById(id);
            return ResponseEntity.ok(ApiResponse.success("Item retrieved successfully", item));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/slug/{slug}")
    @Operation(summary = "Get item by slug")
    public ResponseEntity<ApiResponse<Item>> getItemBySlug(@PathVariable String slug) {
        try {
            Item item = itemService.getItemBySlug(slug);
            return ResponseEntity.ok(ApiResponse.success("Item retrieved successfully", item));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/search")
    @Operation(summary = "Search items by name")
    public ResponseEntity<ApiResponse<List<Item>>> searchItems(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        try {
            List<Item> items = itemService.searchItemsByName(name);
            int total = items.size();
            int fromIndex = Math.min(offset, total);
            int toIndex = Math.min(offset + limit, total);
            List<Item> paginatedItems = items.subList(fromIndex, toIndex);
            
            return ResponseEntity.ok(ApiResponse.success(
                "Items found successfully", 
                paginatedItems,
                total,
                limit,
                offset
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/store/{storeId}")
    @Operation(summary = "Get items by store")
    public ResponseEntity<ApiResponse<List<Item>>> getItemsByStore(@PathVariable Long storeId) {
        try {
            List<Item> items = itemService.getItemsByStore(storeId);
            return ResponseEntity.ok(ApiResponse.success("Store items retrieved successfully", items));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/category/{categoryId}")
    @Operation(summary = "Get items by category")
    public ResponseEntity<ApiResponse<List<Item>>> getItemsByCategory(@PathVariable Long categoryId) {
        try {
            List<Item> items = itemService.getItemsByCategory(categoryId);
            return ResponseEntity.ok(ApiResponse.success("Category items retrieved successfully", items));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
}

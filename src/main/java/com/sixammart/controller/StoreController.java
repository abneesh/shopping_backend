package com.sixammart.controller;

import com.sixammart.dto.ApiResponse;
import com.sixammart.entity.Store;
import com.sixammart.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
@Tag(name = "Stores", description = "Store management APIs")
@CrossOrigin(origins = "*", maxAge = 3600)
public class StoreController {

    @Autowired
    private StoreService storeService;

    @GetMapping("/get-stores/all")
    @Operation(summary = "Get all active stores")
    public ResponseEntity<ApiResponse<List<Store>>> getAllStores(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        try {
            int page = offset / limit;
            Page<Store> storePage = storeService.getAllActiveStores(page, limit);
            
            return ResponseEntity.ok(ApiResponse.success(
                "Stores retrieved successfully", 
                storePage.getContent(),
                (int) storePage.getTotalElements(),
                limit,
                offset
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/get-stores/latest")
    @Operation(summary = "Get latest stores")
    public ResponseEntity<ApiResponse<List<Store>>> getLatestStores(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        try {
            int page = offset / limit;
            Page<Store> storePage = storeService.getAllActiveStores(page, limit);
            
            return ResponseEntity.ok(ApiResponse.success(
                "Latest stores retrieved successfully", 
                storePage.getContent(),
                (int) storePage.getTotalElements(),
                limit,
                offset
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/get-stores/popular")
    @Operation(summary = "Get popular stores")
    public ResponseEntity<ApiResponse<List<Store>>> getPopularStores(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        try {
            int page = offset / limit;
            Page<Store> storePage = storeService.getAllActiveStores(page, limit);
            
            return ResponseEntity.ok(ApiResponse.success(
                "Popular stores retrieved successfully", 
                storePage.getContent(),
                (int) storePage.getTotalElements(),
                limit,
                offset
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/get-stores/featured")
    @Operation(summary = "Get featured stores")
    public ResponseEntity<ApiResponse<List<Store>>> getFeaturedStores() {
        try {
            List<Store> stores = storeService.getFeaturedStores();
            return ResponseEntity.ok(ApiResponse.success("Featured stores retrieved successfully", stores));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get store by ID")
    public ResponseEntity<ApiResponse<Store>> getStoreById(@PathVariable Long id) {
        try {
            Store store = storeService.getStoreById(id);
            return ResponseEntity.ok(ApiResponse.success("Store retrieved successfully", store));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/details/{slug}")
    @Operation(summary = "Get store by slug")
    public ResponseEntity<ApiResponse<Store>> getStoreBySlug(@PathVariable String slug) {
        try {
            Store store = storeService.getStoreBySlug(slug);
            return ResponseEntity.ok(ApiResponse.success("Store retrieved successfully", store));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/search")
    @Operation(summary = "Search stores by name")
    public ResponseEntity<ApiResponse<List<Store>>> searchStores(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        try {
            List<Store> stores = storeService.searchStoresByName(name);
            int total = stores.size();
            int fromIndex = Math.min(offset, total);
            int toIndex = Math.min(offset + limit, total);
            List<Store> paginatedStores = stores.subList(fromIndex, toIndex);
            
            return ResponseEntity.ok(ApiResponse.success(
                "Stores found successfully", 
                paginatedStores,
                total,
                limit,
                offset
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/zone/{zoneId}")
    @Operation(summary = "Get stores by zone")
    public ResponseEntity<ApiResponse<List<Store>>> getStoresByZone(@PathVariable Long zoneId) {
        try {
            List<Store> stores = storeService.getStoresByZone(zoneId);
            return ResponseEntity.ok(ApiResponse.success("Stores retrieved successfully", stores));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/module/{moduleId}")
    @Operation(summary = "Get stores by module")
    public ResponseEntity<ApiResponse<List<Store>>> getStoresByModule(@PathVariable Long moduleId) {
        try {
            List<Store> stores = storeService.getStoresByModule(moduleId);
            return ResponseEntity.ok(ApiResponse.success("Stores retrieved successfully", stores));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/nearby")
    @Operation(summary = "Get nearby stores")
    public ResponseEntity<ApiResponse<List<Store>>> getNearbyStores(
            @RequestParam Double latitude,
            @RequestParam Double longitude,
            @RequestParam(defaultValue = "10.0") Double radius) {
        try {
            List<Store> stores = storeService.getStoresWithinRadius(latitude, longitude, radius);
            return ResponseEntity.ok(ApiResponse.success("Nearby stores retrieved successfully", stores));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/vendor/{vendorId}")
    @Operation(summary = "Get stores by vendor")
    public ResponseEntity<ApiResponse<List<Store>>> getStoresByVendor(@PathVariable Long vendorId) {
        try {
            List<Store> stores = storeService.getStoresByVendor(vendorId);
            return ResponseEntity.ok(ApiResponse.success("Vendor stores retrieved successfully", stores));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
}

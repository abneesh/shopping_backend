package com.sixammart.controller;

import com.sixammart.dto.ApiResponse;
import com.sixammart.entity.Banner;
import com.sixammart.service.BannerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banners")
@Tag(name = "Banners", description = "Banner management APIs")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @GetMapping
    @Operation(summary = "Get all active banners")
    public ResponseEntity<ApiResponse<List<Banner>>> getAllBanners(
            @RequestParam(required = false) Long module_id,
            @RequestParam(required = false) Long zone_id) {
        try {
            List<Banner> banners;
            
            if (module_id != null && zone_id != null) {
                banners = bannerService.getBannersByModuleAndZone(module_id, zone_id);
            } else if (module_id != null) {
                banners = bannerService.getBannersByModule(module_id);
            } else if (zone_id != null) {
                banners = bannerService.getBannersByZone(zone_id);
            } else {
                banners = bannerService.getAllActiveBanners();
            }
            
            return ResponseEntity.ok(ApiResponse.success("Banners retrieved successfully", banners));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/featured")
    @Operation(summary = "Get featured banners")
    public ResponseEntity<ApiResponse<List<Banner>>> getFeaturedBanners() {
        try {
            List<Banner> banners = bannerService.getFeaturedBanners();
            return ResponseEntity.ok(ApiResponse.success("Featured banners retrieved successfully", banners));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/type/{type}")
    @Operation(summary = "Get banners by type")
    public ResponseEntity<ApiResponse<List<Banner>>> getBannersByType(@PathVariable String type) {
        try {
            List<Banner> banners = bannerService.getBannersByType(type);
            return ResponseEntity.ok(ApiResponse.success("Banners retrieved successfully", banners));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get banner by ID")
    public ResponseEntity<ApiResponse<Banner>> getBannerById(@PathVariable Long id) {
        try {
            Banner banner = bannerService.getBannerById(id);
            return ResponseEntity.ok(ApiResponse.success("Banner retrieved successfully", banner));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
}

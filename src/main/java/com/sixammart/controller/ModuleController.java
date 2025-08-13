package com.sixammart.controller;

import com.sixammart.dto.ApiResponse;
import com.sixammart.entity.Module;
import com.sixammart.service.ModuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modules")
@Tag(name = "Modules", description = "Module management APIs")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @GetMapping
    @Operation(summary = "Get all active modules")
    public ResponseEntity<ApiResponse<List<Module>>> getAllModules() {
        try {
            List<Module> modules = moduleService.getAllActiveModules();
            return ResponseEntity.ok(ApiResponse.success("Modules retrieved successfully", modules));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get module by ID")
    public ResponseEntity<ApiResponse<Module>> getModuleById(@PathVariable Long id) {
        try {
            Module module = moduleService.getModuleById(id);
            return ResponseEntity.ok(ApiResponse.success("Module retrieved successfully", module));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/slug/{slug}")
    @Operation(summary = "Get module by slug")
    public ResponseEntity<ApiResponse<Module>> getModuleBySlug(@PathVariable String slug) {
        try {
            Module module = moduleService.getModuleBySlug(slug);
            return ResponseEntity.ok(ApiResponse.success("Module retrieved successfully", module));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/type/{moduleType}")
    @Operation(summary = "Get modules by type")
    public ResponseEntity<ApiResponse<List<Module>>> getModulesByType(@PathVariable String moduleType) {
        try {
            List<Module> modules = moduleService.getModulesByType(moduleType);
            return ResponseEntity.ok(ApiResponse.success("Modules retrieved successfully", modules));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/search")
    @Operation(summary = "Search modules by name")
    public ResponseEntity<ApiResponse<List<Module>>> searchModules(@RequestParam String name) {
        try {
            List<Module> modules = moduleService.searchModulesByName(name);
            return ResponseEntity.ok(ApiResponse.success("Modules found successfully", modules));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/all-zone-service")
    @Operation(summary = "Get all zone service modules")
    public ResponseEntity<ApiResponse<List<Module>>> getAllZoneServiceModules() {
        try {
            List<Module> modules = moduleService.getAllZoneServiceModules();
            return ResponseEntity.ok(ApiResponse.success("All zone service modules retrieved successfully", modules));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
}

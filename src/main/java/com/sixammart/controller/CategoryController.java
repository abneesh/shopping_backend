package com.sixammart.controller;

import com.sixammart.dto.ApiResponse;
import com.sixammart.entity.Category;
import com.sixammart.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@Tag(name = "Categories", description = "Category management APIs")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    @Operation(summary = "Get all active categories")
    public ResponseEntity<ApiResponse<List<Category>>> getAllCategories(
            @RequestParam(required = false) Long module_id,
            @RequestParam(required = false) Long parent_id) {
        try {
            List<Category> categories;
            
            if (parent_id != null) {
                categories = categoryService.getSubCategories(parent_id);
            } else if (module_id != null) {
                categories = categoryService.getMainCategoriesByModule(module_id);
            } else {
                categories = categoryService.getMainCategories();
            }
            
            return ResponseEntity.ok(ApiResponse.success("Categories retrieved successfully", categories));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/all")
    @Operation(summary = "Get all categories including subcategories")
    public ResponseEntity<ApiResponse<List<Category>>> getAllCategoriesIncludingSubcategories() {
        try {
            List<Category> categories = categoryService.getAllActiveCategories();
            return ResponseEntity.ok(ApiResponse.success("All categories retrieved successfully", categories));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/featured")
    @Operation(summary = "Get featured categories")
    public ResponseEntity<ApiResponse<List<Category>>> getFeaturedCategories() {
        try {
            List<Category> categories = categoryService.getFeaturedCategories();
            return ResponseEntity.ok(ApiResponse.success("Featured categories retrieved successfully", categories));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/priority")
    @Operation(summary = "Get priority categories")
    public ResponseEntity<ApiResponse<List<Category>>> getPriorityCategories() {
        try {
            List<Category> categories = categoryService.getPriorityCategories();
            return ResponseEntity.ok(ApiResponse.success("Priority categories retrieved successfully", categories));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get category by ID")
    public ResponseEntity<ApiResponse<Category>> getCategoryById(@PathVariable Long id) {
        try {
            Category category = categoryService.getCategoryById(id);
            return ResponseEntity.ok(ApiResponse.success("Category retrieved successfully", category));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/slug/{slug}")
    @Operation(summary = "Get category by slug")
    public ResponseEntity<ApiResponse<Category>> getCategoryBySlug(@PathVariable String slug) {
        try {
            Category category = categoryService.getCategoryBySlug(slug);
            return ResponseEntity.ok(ApiResponse.success("Category retrieved successfully", category));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/search")
    @Operation(summary = "Search categories by name")
    public ResponseEntity<ApiResponse<List<Category>>> searchCategories(@RequestParam String name) {
        try {
            List<Category> categories = categoryService.searchCategoriesByName(name);
            return ResponseEntity.ok(ApiResponse.success("Categories found successfully", categories));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/module/{moduleId}")
    @Operation(summary = "Get categories by module")
    public ResponseEntity<ApiResponse<List<Category>>> getCategoriesByModule(@PathVariable Long moduleId) {
        try {
            List<Category> categories = categoryService.getCategoriesByModule(moduleId);
            return ResponseEntity.ok(ApiResponse.success("Module categories retrieved successfully", categories));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/subcategories/{parentId}")
    @Operation(summary = "Get subcategories by parent ID")
    public ResponseEntity<ApiResponse<List<Category>>> getSubCategories(@PathVariable Long parentId) {
        try {
            List<Category> categories = categoryService.getSubCategories(parentId);
            return ResponseEntity.ok(ApiResponse.success("Subcategories retrieved successfully", categories));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
}

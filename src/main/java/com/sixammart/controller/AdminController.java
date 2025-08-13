package com.sixammart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sixammart.service.StoreService;
import com.sixammart.service.CategoryService;
import com.sixammart.service.ItemService;
import com.sixammart.service.ConfigService;


import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private StoreService storeService;
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private ItemService itemService;
    
    @Autowired
    private ConfigService configService;

    // Admin Login Page
    @GetMapping("/login")
    public String loginPage() {
        return "admin/login";
    }

    // Admin Login POST
    @PostMapping("/login")
    public String login(@RequestParam String email,
                       @RequestParam String password,
                       @RequestParam(required = false) String remember,
                       RedirectAttributes redirectAttributes) {

        // Demo credentials
        if ("admin@mintmey.com".equals(email) && "admin123".equals(password)) {
            redirectAttributes.addFlashAttribute("success_msg", "Welcome back, Super Admin!");
            return "redirect:/admin/dashboard";
        } else {
            redirectAttributes.addFlashAttribute("error_msg", "Invalid email or password");
            return "redirect:/admin/login";
        }
    }

    // Admin Dashboard
    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        try {
            // Get statistics
            Map<String, Object> stats = new HashMap<>();
            stats.put("totalStores", storeService.getAllStores().size());
            stats.put("totalCategories", categoryService.getAllCategories().size());
            stats.put("totalItems", itemService.getAllItems().size());
            stats.put("totalOrders", 156); // Mock data
            stats.put("totalRevenue", 45678.90); // Mock data
            stats.put("activeUsers", 1234); // Mock data
            stats.put("pendingOrders", 23); // Mock data
            stats.put("completedOrders", 133); // Mock data

            model.addAttribute("stats", stats);
            model.addAttribute("stores", storeService.getAllStores());
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("items", itemService.getAllItems());
            
        } catch (Exception e) {
            // If API calls fail, use mock data
            Map<String, Object> stats = new HashMap<>();
            stats.put("totalStores", 0);
            stats.put("totalCategories", 0);
            stats.put("totalItems", 0);
            stats.put("totalOrders", 0);
            stats.put("totalRevenue", 0.0);
            stats.put("activeUsers", 0);
            stats.put("pendingOrders", 0);
            stats.put("completedOrders", 0);
            
            model.addAttribute("stats", stats);
        }

        return "admin/dashboard";
    }

    // Stores Management
    @GetMapping("/stores")
    public String stores(Model model) {

        try {
            model.addAttribute("stores", storeService.getAllStores());
        } catch (Exception e) {
            model.addAttribute("stores", java.util.Collections.emptyList());
            model.addAttribute("error_msg", "Error loading stores data");
        }

        return "admin/stores";
    }

    // Categories Management
    @GetMapping("/categories")
    public String categories(Model model) {

        try {
            model.addAttribute("categories", categoryService.getAllCategories());
        } catch (Exception e) {
            model.addAttribute("categories", java.util.Collections.emptyList());
            model.addAttribute("error_msg", "Error loading categories data");
        }

        return "admin/categories";
    }

    // Items Management
    @GetMapping("/items")
    public String items(Model model) {

        try {
            model.addAttribute("items", itemService.getAllItems());
            model.addAttribute("stores", storeService.getAllStores());
            model.addAttribute("categories", categoryService.getAllCategories());
        } catch (Exception e) {
            model.addAttribute("items", java.util.Collections.emptyList());
            model.addAttribute("stores", java.util.Collections.emptyList());
            model.addAttribute("categories", java.util.Collections.emptyList());
            model.addAttribute("error_msg", "Error loading items data");
        }

        return "admin/items";
    }

    // Orders Management
    @GetMapping("/orders")
    public String orders(Model model) {
        // Mock orders data
        model.addAttribute("orders", java.util.Collections.emptyList());
        return "admin/orders";
    }

    // Users Management
    @GetMapping("/users")
    public String users(Model model) {
        // Mock users data
        model.addAttribute("users", java.util.Collections.emptyList());
        return "admin/users";
    }

    // Settings
    @GetMapping("/settings")
    public String settings(Model model) {

        try {
            model.addAttribute("config", configService.getConfig());
        } catch (Exception e) {
            model.addAttribute("config", new HashMap<>());
            model.addAttribute("error_msg", "Error loading configuration");
        }

        return "admin/settings";
    }

    // Logout
    @GetMapping("/logout")
    public String logout(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("success_msg", "You have been logged out successfully");
        return "redirect:/admin/login";
    }

    // API endpoint for AJAX requests
    @GetMapping("/api/stats")
    @ResponseBody
    public Map<String, Object> getStats() {

        Map<String, Object> stats = new HashMap<>();
        try {
            stats.put("totalStores", storeService.getAllStores().size());
            stats.put("totalCategories", categoryService.getAllCategories().size());
            stats.put("totalItems", itemService.getAllItems().size());
        } catch (Exception e) {
            stats.put("totalStores", 0);
            stats.put("totalCategories", 0);
            stats.put("totalItems", 0);
        }
        
        stats.put("totalOrders", 156);
        stats.put("totalRevenue", 45678.90);
        stats.put("activeUsers", 1234);
        
        return stats;
    }
}

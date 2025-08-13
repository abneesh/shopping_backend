package com.sixammart.controller;

import com.sixammart.dto.ApiResponse;
import com.sixammart.service.ConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/config")
@Tag(name = "Configuration", description = "App configuration APIs")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ConfigController {

    @Autowired
    private ConfigService configService;

    @GetMapping
    @Operation(summary = "Get app configuration")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getConfig() {
        try {
            Map<String, Object> config = configService.getAppConfig();
            return ResponseEntity.ok(ApiResponse.success("Configuration retrieved successfully", config));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/get-zone-id")
    @Operation(summary = "Get zone ID by coordinates")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getZoneId(
            @RequestParam Double lat,
            @RequestParam Double lng) {
        try {
            Long zoneId = configService.getZoneId(lat, lng);
            Map<String, Object> response = Map.of(
                "zone_id", zoneId,
                "zone_data", Map.of(
                    "id", zoneId,
                    "name", "Default Zone",
                    "status", 1,
                    "cash_on_delivery", true,
                    "digital_payment", true
                )
            );
            return ResponseEntity.ok(ApiResponse.success("Zone ID retrieved successfully", response));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/place-api-autocomplete")
    @Operation(summary = "Place API autocomplete")
    public ResponseEntity<ApiResponse<Object>> placeApiAutocomplete(
            @RequestParam String search_text,
            @RequestParam(required = false) String session_token) {
        try {
            // Mock implementation - in real app, integrate with Google Places API
            return ResponseEntity.ok(ApiResponse.success("Autocomplete results", Map.of(
                "predictions", new Object[]{},
                "status", "OK"
            )));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/distance-api")
    @Operation(summary = "Distance API")
    public ResponseEntity<ApiResponse<Object>> distanceApi(
            @RequestParam String origin_lat,
            @RequestParam String origin_lng,
            @RequestParam String destination_lat,
            @RequestParam String destination_lng) {
        try {
            // Mock implementation - in real app, integrate with Google Distance Matrix API
            double distance = calculateDistance(
                Double.parseDouble(origin_lat), Double.parseDouble(origin_lng),
                Double.parseDouble(destination_lat), Double.parseDouble(destination_lng)
            );
            
            return ResponseEntity.ok(ApiResponse.success("Distance calculated", Map.of(
                "rows", new Object[]{
                    Map.of("elements", new Object[]{
                        Map.of(
                            "distance", Map.of("text", String.format("%.2f km", distance), "value", (int)(distance * 1000)),
                            "duration", Map.of("text", "15 mins", "value", 900),
                            "status", "OK"
                        )
                    })
                },
                "status", "OK"
            )));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/geocode-api")
    @Operation(summary = "Geocode API")
    public ResponseEntity<ApiResponse<Object>> geocodeApi(
            @RequestParam Double lat,
            @RequestParam Double lng) {
        try {
            // Mock implementation - in real app, integrate with Google Geocoding API
            return ResponseEntity.ok(ApiResponse.success("Geocode results", Map.of(
                "results", new Object[]{
                    Map.of(
                        "formatted_address", "Sample Address, City, Country",
                        "geometry", Map.of(
                            "location", Map.of("lat", lat, "lng", lng)
                        ),
                        "place_id", "sample_place_id"
                    )
                },
                "status", "OK"
            )));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    // Helper method to calculate distance between two coordinates
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radius of the earth in km
        
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c; // Distance in km
        
        return distance;
    }

    @GetMapping("/flutter-landing-page")
    @Operation(summary = "Get Flutter landing page data")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getFlutterLandingPage() {
        try {
            Map<String, Object> response = Map.of(
                "fixed_header_title", "Welcome to 6amMart",
                "fixed_header_sub_title", "Your one-stop marketplace",
                "fixed_module_title", "Our Services",
                "fixed_module_sub_title", "Choose from our wide range of services",
                "fixed_location_title", "Service Areas",
                "fixed_location_sub_title", "We deliver to your location",
                "fixed_download_app_title", "Download Our App",
                "fixed_download_app_sub_title", "Get the best experience on mobile"
            );
            return ResponseEntity.ok(ApiResponse.success("Landing page data retrieved successfully", response));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
}

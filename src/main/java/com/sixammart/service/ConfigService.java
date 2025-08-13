package com.sixammart.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ConfigService {

    public Map<String, Object> getConfig() {
        return getAppConfig();
    }

    public Map<String, Object> getAppConfig() {
        Map<String, Object> config = new HashMap<>();
        
        // Business settings
        config.put("business_name", "6amMart");
        config.put("logo", "/uploads/logo.png");
        config.put("logo_full_url", "https://shopping-backend-21en.onrender.com/api/v1/uploads/logo.png");
        config.put("country_code", "BD");
        config.put("business_address", "Dhaka, Bangladesh");
        config.put("business_phone", "+8801234567890");
        config.put("business_email", "admin@6ammart.com");
        config.put("base_url", "https://shopping-backend-21en.onrender.com/api/v1");
        config.put("currency_symbol", "$");
        config.put("currency_symbol_direction", "left");
        config.put("app_minimum_version_android", "1.0.0");
        config.put("app_minimum_version_ios", "1.0.0");
        config.put("app_url_android", "https://play.google.com/store/apps/details?id=com.sixamtech.sixammart");
        config.put("app_url_ios", "https://apps.apple.com/app/6ammart/id1569847334");
        
        // Features
        config.put("schedule_order", true);
        config.put("order_confirmation_model", "deliveryman");
        config.put("show_dm_earning", true);
        config.put("canceled_by_deliveryman", true);
        config.put("canceled_by_store", true);
        config.put("timeformat", "24");
        config.put("language", createLanguageConfig());
        config.put("social_login", createSocialLoginConfig());
        config.put("apple_login", createAppleLoginConfig());
        config.put("toggle_veg_non_veg", true);
        config.put("toggle_dm_registration", true);
        config.put("toggle_store_registration", true);
        config.put("maintenance_mode", false);
        config.put("order_delivery_verification", true);
        config.put("cash_on_delivery", true);
        config.put("digital_payment", true);
        config.put("partial_payment", false);
        config.put("partial_payment_method", "cod");
        config.put("add_fund_status", true);
        config.put("offline_payment_status", false);
        config.put("guest_checkout_status", true);
        config.put("disbursement_type", "automated");
        config.put("restaurant_disbursement_waiting_time", 7);
        config.put("dm_disbursement_waiting_time", 7);
        config.put("default_location", createDefaultLocation());
        config.put("free_delivery_over", 100.0);
        config.put("demo", false);
        config.put("module_config", createModuleConfig());
        config.put("parcel_per_km_shipping_charge", 5.0);
        config.put("parcel_minimum_shipping_charge", 10.0);
        config.put("landing_page_links", createLandingPageLinks());
        config.put("loyalty_point_status", true);
        config.put("loyalty_point_exchange_rate", 100);
        config.put("loyalty_point_item_purchase_point", 1.0);
        config.put("loyalty_point_minimum_point", 10);
        config.put("wallet_status", true);
        config.put("dm_tips_status", true);
        config.put("ref_earning_status", true);
        config.put("ref_earning_exchange_rate", 100);
        config.put("refund_active_status", true);
        config.put("refund_policy_status", true);
        config.put("refund_policy_data", "Refund policy content here...");
        config.put("cancellation_policy_status", true);
        config.put("cancellation_policy_data", "Cancellation policy content here...");
        config.put("shipping_policy_status", true);
        config.put("shipping_policy_data", "Shipping policy content here...");
        config.put("privacy_policy", "Privacy policy content here...");
        config.put("terms_and_conditions", "Terms and conditions content here...");
        config.put("about_us", "About us content here...");
        config.put("faq", "FAQ content here...");
        
        return config;
    }

    public Long getZoneId(Double latitude, Double longitude) {
        // Simple zone detection logic - in real implementation, 
        // this would check if the coordinates fall within zone boundaries
        return 1L; // Default zone
    }

    private Map<String, Object> createLanguageConfig() {
        Map<String, Object> language = new HashMap<>();
        language.put("key", "en");
        language.put("value", "English");
        language.put("direction", "ltr");
        language.put("default", true);
        return language;
    }

    private Map<String, Object> createSocialLoginConfig() {
        Map<String, Object> socialLogin = new HashMap<>();
        socialLogin.put("login_medium", "all");
        socialLogin.put("google_social_login", true);
        socialLogin.put("facebook_social_login", true);
        return socialLogin;
    }

    private Map<String, Object> createAppleLoginConfig() {
        Map<String, Object> appleLogin = new HashMap<>();
        appleLogin.put("login_medium", "all");
        appleLogin.put("status", true);
        return appleLogin;
    }

    private Map<String, Object> createDefaultLocation() {
        Map<String, Object> location = new HashMap<>();
        location.put("lat", "23.8103");
        location.put("lng", "90.4125");
        return location;
    }

    private Map<String, Object> createModuleConfig() {
        Map<String, Object> moduleConfig = new HashMap<>();
        moduleConfig.put("module_type", "grocery");
        moduleConfig.put("description", "Grocery delivery service");
        moduleConfig.put("order_place_to_schedule_interval", 30);
        return moduleConfig;
    }

    private Map<String, Object> createLandingPageLinks() {
        Map<String, Object> links = new HashMap<>();
        links.put("app_url_android_status", true);
        links.put("app_url_ios_status", true);
        links.put("web_app_url_status", true);
        return links;
    }
}

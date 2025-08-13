package com.sixammart.service;

import com.sixammart.entity.*;
import com.sixammart.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Service
public class DataInitializationService implements CommandLineRunner {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BannerRepository bannerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        initializeData();
    }

    private void initializeData() {
        // Initialize Modules
        if (moduleRepository.count() == 0) {
            initializeModules();
        }

        // Initialize Zones
        if (zoneRepository.count() == 0) {
            initializeZones();
        }

        // Initialize Categories
        if (categoryRepository.count() == 0) {
            initializeCategories();
        }

        // Initialize Stores
        if (storeRepository.count() == 0) {
            initializeStores();
        }

        // Initialize Items
        if (itemRepository.count() == 0) {
            initializeItems();
        }

        // Initialize Banners
        if (bannerRepository.count() == 0) {
            initializeBanners();
        }

        // Initialize Users
        if (userRepository.count() == 0) {
            initializeUsers();
        }
    }

    private void initializeModules() {
        List<com.sixammart.entity.Module> modules = Arrays.asList(
            createModule("Grocery", "grocery", "grocery.png", "Fresh groceries delivered to your door"),
            createModule("Food", "food", "food.png", "Delicious food from your favorite restaurants"),
            createModule("Pharmacy", "pharmacy", "pharmacy.png", "Medicines and health products"),
            createModule("Electronics", "electronics", "electronics.png", "Latest electronics and gadgets")
        );
        moduleRepository.saveAll(modules);
    }

    private void initializeZones() {
        List<Zone> zones = Arrays.asList(
            createZone("Central Zone", "[[23.8103,90.4125],[23.8203,90.4225],[23.8303,90.4325]]"),
            createZone("North Zone", "[[23.8403,90.4425],[23.8503,90.4525],[23.8603,90.4625]]"),
            createZone("South Zone", "[[23.7903,90.3925],[23.8003,90.4025],[23.8103,90.4125]]")
        );
        zoneRepository.saveAll(zones);
    }

    private void initializeCategories() {
        com.sixammart.entity.Module groceryModule = moduleRepository.findByModuleTypeAndActive("grocery").get(0);
        
        List<Category> categories = Arrays.asList(
            createCategory("Fruits & Vegetables", "fruits.png", groceryModule.getId()),
            createCategory("Dairy & Eggs", "dairy.png", groceryModule.getId()),
            createCategory("Meat & Seafood", "meat.png", groceryModule.getId()),
            createCategory("Bakery", "bakery.png", groceryModule.getId()),
            createCategory("Beverages", "beverages.png", groceryModule.getId()),
            createCategory("Snacks", "snacks.png", groceryModule.getId())
        );
        categoryRepository.saveAll(categories);
    }

    private void initializeStores() {
        com.sixammart.entity.Module groceryModule = moduleRepository.findByModuleTypeAndActive("grocery").get(0);
        Zone centralZone = zoneRepository.findAllActiveZones().get(0);
        
        List<Store> stores = Arrays.asList(
            createStore("Fresh Market", "fresh-market.png", 23.8103, 90.4125, groceryModule.getId(), centralZone.getId()),
            createStore("Green Grocery", "green-grocery.png", 23.8203, 90.4225, groceryModule.getId(), centralZone.getId()),
            createStore("Super Store", "super-store.png", 23.8303, 90.4325, groceryModule.getId(), centralZone.getId())
        );
        storeRepository.saveAll(stores);
    }

    private void initializeItems() {
        Store store = storeRepository.findAllActiveStores().get(0);
        Category category = categoryRepository.findAllActiveCategories().get(0);
        
        List<Item> items = Arrays.asList(
            createItem("Fresh Apples", "apples.png", 2.99, category.getId(), store),
            createItem("Organic Bananas", "bananas.png", 1.99, category.getId(), store),
            createItem("Fresh Oranges", "oranges.png", 3.49, category.getId(), store),
            createItem("Green Lettuce", "lettuce.png", 1.49, category.getId(), store),
            createItem("Red Tomatoes", "tomatoes.png", 2.29, category.getId(), store)
        );
        itemRepository.saveAll(items);
    }

    private void initializeBanners() {
        com.sixammart.entity.Module groceryModule = moduleRepository.findByModuleTypeAndActive("grocery").get(0);
        Zone centralZone = zoneRepository.findAllActiveZones().get(0);
        
        List<Banner> banners = Arrays.asList(
            createBanner("Fresh Deals", "promotional", "banner1.png", groceryModule.getId(), centralZone.getId()),
            createBanner("Weekend Special", "promotional", "banner2.png", groceryModule.getId(), centralZone.getId()),
            createBanner("New Arrivals", "promotional", "banner3.png", groceryModule.getId(), centralZone.getId())
        );
        bannerRepository.saveAll(banners);
    }

    private void initializeUsers() {
        List<User> users = Arrays.asList(
            createUser("John", "Doe", "john@example.com", "+1234567890", "password123"),
            createUser("Jane", "Smith", "jane@example.com", "+1234567891", "password123"),
            createUser("Admin", "User", "admin@6ammart.com", "+1234567892", "admin123")
        );
        userRepository.saveAll(users);
    }

    // Helper methods
    private com.sixammart.entity.Module createModule(String name, String type, String thumbnail, String description) {
        com.sixammart.entity.Module module = new com.sixammart.entity.Module();
        module.setModuleName(name);
        module.setModuleType(type);
        module.setThumbnail(thumbnail);
        module.setThumbnailFullUrl("/uploads/modules/" + thumbnail);
        module.setDescription(description);
        module.setStatus(true);
        module.setAllZoneService(true);
        return module;
    }

    private Zone createZone(String name, String coordinates) {
        Zone zone = new Zone();
        zone.setName(name);
        zone.setCoordinates(coordinates);
        zone.setStatus(true);
        zone.setCashOnDelivery(true);
        zone.setDigitalPayment(true);
        zone.setMinimumShippingCharge(5.0);
        zone.setPerKmShippingCharge(1.0);
        return zone;
    }

    private Category createCategory(String name, String image, Long moduleId) {
        Category category = new Category();
        category.setName(name);
        category.setImage(image);
        category.setImageFullUrl("/uploads/categories/" + image);
        category.setModuleId(moduleId);
        category.setStatus(true);
        category.setPosition(0);
        return category;
    }

    private Store createStore(String name, String logo, Double lat, Double lng, Long moduleId, Long zoneId) {
        Store store = new Store();
        store.setName(name);
        store.setLogo(logo);
        store.setLogoFullUrl("/uploads/stores/" + logo);
        store.setLatitude(lat);
        store.setLongitude(lng);
        store.setAddress("Sample Address, City");
        store.setPhone("+1234567890");
        store.setEmail("store@example.com");
        store.setModuleId(moduleId);
        store.setZoneId(zoneId);
        store.setVendorId(1L);
        store.setMinimumOrder(10.0);
        store.setDeliveryTime("30-45 mins");
        store.setStatus(true);
        store.setActive(true);
        return store;
    }

    private Item createItem(String name, String image, Double price, Long categoryId, Store store) {
        Item item = new Item();
        item.setName(name);
        item.setImage(image);
        item.setImageFullUrl("/uploads/items/" + image);
        item.setPrice(price);
        item.setCategoryId(categoryId);
        item.setStore(store);
        item.setDescription("Fresh and high quality " + name.toLowerCase());
        item.setStatus(true);
        item.setVeg(1);
        item.setAvailableTimeStarts(LocalTime.of(6, 0));
        item.setAvailableTimeEnds(LocalTime.of(23, 59));
        return item;
    }

    private Banner createBanner(String title, String type, String image, Long moduleId, Long zoneId) {
        Banner banner = new Banner();
        banner.setTitle(title);
        banner.setType(type);
        banner.setImage(image);
        banner.setImageFullUrl("/uploads/banners/" + image);
        banner.setModuleId(moduleId);
        banner.setZoneId(zoneId);
        banner.setStatus(true);
        return banner;
    }

    private User createUser(String firstName, String lastName, String email, String phone, String password) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(passwordEncoder.encode(password));
        user.setStatus(true);
        user.setRefCode("REF" + System.currentTimeMillis());
        return user;
    }
}

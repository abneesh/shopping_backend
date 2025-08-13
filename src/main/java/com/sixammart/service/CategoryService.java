package com.sixammart.service;

import com.sixammart.entity.Category;
import com.sixammart.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public List<Category> getAllActiveCategories() {
        return categoryRepository.findAllActiveCategories();
    }

    public List<Category> getMainCategories() {
        return categoryRepository.findMainCategories();
    }

    public List<Category> getSubCategories(Long parentId) {
        return categoryRepository.findSubCategories(parentId);
    }

    public List<Category> getCategoriesByModule(Long moduleId) {
        return categoryRepository.findByModuleIdAndActive(moduleId);
    }

    public List<Category> getMainCategoriesByModule(Long moduleId) {
        return categoryRepository.findMainCategoriesByModule(moduleId);
    }

    public List<Category> getFeaturedCategories() {
        return categoryRepository.findFeaturedCategories();
    }

    public List<Category> getPriorityCategories() {
        return categoryRepository.findPriorityCategories();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    public Category getCategoryBySlug(String slug) {
        return categoryRepository.findBySlug(slug)
            .orElseThrow(() -> new RuntimeException("Category not found with slug: " + slug));
    }

    public List<Category> searchCategoriesByName(String name) {
        return categoryRepository.findByNameContainingIgnoreCase(name);
    }

    public Long getActiveCategoriesCount() {
        return categoryRepository.countActiveCategories();
    }

    public Category createCategory(Category category) {
        if (category.getImageFullUrl() == null && category.getImage() != null) {
            category.setImageFullUrl("/uploads/categories/" + category.getImage());
        }
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category categoryDetails) {
        Category category = getCategoryById(id);
        
        category.setName(categoryDetails.getName());
        category.setImage(categoryDetails.getImage());
        category.setParentId(categoryDetails.getParentId());
        category.setPosition(categoryDetails.getPosition());
        category.setStatus(categoryDetails.getStatus());
        category.setPriority(categoryDetails.getPriority());
        category.setFeatured(categoryDetails.getFeatured());
        
        if (category.getImageFullUrl() == null && category.getImage() != null) {
            category.setImageFullUrl("/uploads/categories/" + category.getImage());
        }
        
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        Category category = getCategoryById(id);
        category.setStatus(false);
        categoryRepository.save(category);
    }
}

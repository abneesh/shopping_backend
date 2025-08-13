package com.sixammart.service;

import com.sixammart.entity.Module;
import com.sixammart.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    public List<Module> getAllActiveModules() {
        return moduleRepository.findAllActiveModules();
    }

    public Module getModuleById(Long id) {
        return moduleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Module not found with id: " + id));
    }

    public Module getModuleBySlug(String slug) {
        return moduleRepository.findBySlug(slug)
            .orElseThrow(() -> new RuntimeException("Module not found with slug: " + slug));
    }

    public List<Module> getModulesByType(String moduleType) {
        return moduleRepository.findByModuleTypeAndActive(moduleType);
    }

    public List<Module> searchModulesByName(String name) {
        return moduleRepository.findByModuleNameContainingIgnoreCase(name);
    }

    public List<Module> getAllZoneServiceModules() {
        return moduleRepository.findAllZoneServiceModules();
    }

    public Long getActiveModulesCount() {
        return moduleRepository.countActiveModules();
    }

    public Module createModule(Module module) {
        // Set default values
        if (module.getThumbnailFullUrl() == null && module.getThumbnail() != null) {
            module.setThumbnailFullUrl("/uploads/modules/" + module.getThumbnail());
        }
        if (module.getIconFullUrl() == null && module.getIcon() != null) {
            module.setIconFullUrl("/uploads/modules/" + module.getIcon());
        }
        
        return moduleRepository.save(module);
    }

    public Module updateModule(Long id, Module moduleDetails) {
        Module module = getModuleById(id);
        
        module.setModuleName(moduleDetails.getModuleName());
        module.setModuleType(moduleDetails.getModuleType());
        module.setThumbnail(moduleDetails.getThumbnail());
        module.setDescription(moduleDetails.getDescription());
        module.setStatus(moduleDetails.getStatus());
        module.setAllZoneService(moduleDetails.getAllZoneService());
        
        if (module.getThumbnailFullUrl() == null && module.getThumbnail() != null) {
            module.setThumbnailFullUrl("/uploads/modules/" + module.getThumbnail());
        }
        if (module.getIconFullUrl() == null && module.getIcon() != null) {
            module.setIconFullUrl("/uploads/modules/" + module.getIcon());
        }
        
        return moduleRepository.save(module);
    }

    public void deleteModule(Long id) {
        Module module = getModuleById(id);
        module.setStatus(false);
        moduleRepository.save(module);
    }

    public Module updateModuleStatus(Long id, Boolean status) {
        Module module = getModuleById(id);
        module.setStatus(status);
        return moduleRepository.save(module);
    }
}

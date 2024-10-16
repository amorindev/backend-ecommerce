package com.fernando.backend_ecommerce.category;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    ICategoryRepository categoryRepository;

    public ArrayList<CategoryModel> getCategoriesDto() {
        return (ArrayList<CategoryModel>) categoryRepository.findAll();
    }

    public Optional<CategoryModel> getCategoryDto(Long id) {
        return categoryRepository.findById(id);
    }

    public CategoryModel createCategoryDto(CategoryModel category) {
        return categoryRepository.save(category);
    }

    public boolean deleteCategoryDto(Long id) {
        try {
            categoryRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

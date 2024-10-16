package com.fernando.backend_ecommerce.category;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ArrayList<CategoryModel> getCategories() {
        return this.categoryService.getCategoriesDto();
    }
    
    @GetMapping(path = "/{id}")
    public Optional<CategoryModel> getCategory(@PathVariable("id") Long categoryId) {
        return this.categoryService.getCategoryDto(categoryId);
    }
    
    
    @PostMapping()
    public CategoryModel postCategory(@RequestBody CategoryModel category) {        
        return this.categoryService.createCategoryDto(category);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteCategory(@PathVariable("id") Long categoryId) {
        if (this.categoryService.deleteCategoryDto(categoryId)) {
            return "category deleted";
        }
        return "the category could not be deleted";
    }
    
}

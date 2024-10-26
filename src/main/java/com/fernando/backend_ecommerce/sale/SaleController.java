package com.fernando.backend_ecommerce.sale;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fernando.backend_ecommerce.utils.ResponseMessage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/sales")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @GetMapping(path = "/{userId}")
    public ResponseEntity<List<SaleResponse>> getSalesById(@PathVariable("userId") Long userId) {
        List<SaleResponse> sales = saleService.getSalesByIdDto(userId);
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<?> createSale(@RequestBody SaleRequest saleRequest) {
        try {
            SaleResponse createdSale = saleService.createSaleDto(saleRequest);
            return new ResponseEntity<>(createdSale, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            ResponseMessage responseMessage = new ResponseMessage(e.getMessage());
            return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
        }
    }
    
}

/*

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
 */
package com.fernando.backend_ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fernando.backend_ecommerce.model.CategoryModel;

@Repository
public interface ICategoryRepository extends JpaRepository<CategoryModel, Long>{
    
}

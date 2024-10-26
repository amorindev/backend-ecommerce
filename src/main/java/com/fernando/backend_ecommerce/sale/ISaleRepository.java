package com.fernando.backend_ecommerce.sale;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISaleRepository extends JpaRepository<SaleModel, Long>{
    List<SaleModel> findByUserUserId(Long userId); 
    
}

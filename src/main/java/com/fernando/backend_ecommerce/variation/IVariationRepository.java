package com.fernando.backend_ecommerce.variation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVariationRepository extends JpaRepository<VariationModel, Long>{
    
}

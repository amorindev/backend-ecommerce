package com.fernando.backend_ecommerce.productvariationoption;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductVariationOptionRepository extends JpaRepository<ProductVariationOptionModel,Long>{

}

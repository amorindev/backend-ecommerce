package com.fernando.backend_ecommerce.variationoption;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVariationOptionRepository extends JpaRepository<VariationOptionModel, Long>{

}

package com.fernando.backend_ecommerce.productgroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductGroupRepository extends JpaRepository<ProductGroupModel, Long>{

}

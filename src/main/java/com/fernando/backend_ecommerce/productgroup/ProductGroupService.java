package com.fernando.backend_ecommerce.productgroup;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductGroupService {
    @Autowired
    IProductGroupRepository productGroupRepository;
    
    public ArrayList<ProductGroupModel> getProductGroupsDto() {
        return (ArrayList<ProductGroupModel>) productGroupRepository.findAll();
    }

    public ProductGroupModel createProductGroupDto(ProductGroupModel productGroupModel) {
        return productGroupRepository.save(productGroupModel);
    }
}

package com.fernando.backend_ecommerce.productgroup;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/productgroups")
public class ProductGroupController {
    @Autowired
    private ProductGroupService productGroupService;
    @GetMapping()
    public List<ProductGroupResponse> getProductGroups() {
        return this.productGroupService.getProductGroupsDto();
    }
    
    @PostMapping()
    public ProductGroupModel postProductGroup(@RequestBody ProductGroupModel productGroupModel) {
        return this.productGroupService.createProductGroupDto(productGroupModel);        
    }
    
}

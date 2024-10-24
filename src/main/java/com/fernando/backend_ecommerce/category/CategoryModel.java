package com.fernando.backend_ecommerce.category;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fernando.backend_ecommerce.productgroup.ProductGroupModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_category")
public class CategoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id")
    private Long id;
    
    @Column(name = "cat_name", nullable = false, length = 150)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<ProductGroupModel> productGroups;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductGroupModel> getProductGroups() {
        return productGroups;
    }

    public void setProductGroups(List<ProductGroupModel> productGroups) {
        this.productGroups = productGroups;
    }
    
    
}

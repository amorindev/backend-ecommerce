package com.fernando.backend_ecommerce.productgroup;

import java.util.List;

import com.fernando.backend_ecommerce.category.CategoryModel;
import com.fernando.backend_ecommerce.product.ProductModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_product_group")
public class ProductGroupModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pg_id")
    private Long id;
    
    @Column(name = "pg_name", nullable = false, length = 250)
    private String name;

    @Column(name = "pg_desc", nullable = false, length = 300)
    private String description;

    @Column(name = "pg_image", length = 300)
    private String image;

    @ManyToOne
    @JoinColumn(name = "cat_id")
    private CategoryModel category;

    @OneToMany(mappedBy = "productGroup")
    private List<ProductModel> products;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public List<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductModel> products) {
        this.products = products;
    }

    
}

package com.fernando.backend_ecommerce.productvariationoption;

import com.fernando.backend_ecommerce.product.ProductModel;
import com.fernando.backend_ecommerce.variationoption.VariationOptionModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_product_variationoption")
public class ProductVariationOptionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "prod_id", nullable = false)
    private ProductModel product;

    @ManyToOne
    @JoinColumn(name = "pro_varop_id", nullable = false)
    private VariationOptionModel variationOption;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public VariationOptionModel getVariationOption() {
        return variationOption;
    }

    public void setVariationOption(VariationOptionModel variationOption) {
        this.variationOption = variationOption;
    }

    
}

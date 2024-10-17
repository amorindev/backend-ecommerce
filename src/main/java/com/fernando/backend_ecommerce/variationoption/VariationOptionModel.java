package com.fernando.backend_ecommerce.variationoption;

import java.util.List;

import com.fernando.backend_ecommerce.product.ProductModel;
import com.fernando.backend_ecommerce.variation.VariationModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_variation_option")
public class VariationOptionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "varop_id")
    private Long id;

    @Column(name = "varop_value", nullable = false, length = 15)
    private String value;

    @ManyToOne
    @JoinColumn(name = "var_id")
    private VariationModel variation;

    @ManyToMany(mappedBy = "variationsOptions")
    private List<ProductModel> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public VariationModel getVariation() {
        return variation;
    }

    public void setVariation(VariationModel variation) {
        this.variation = variation;
    }

    public List<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductModel> products) {
        this.products = products;
    }

    
}

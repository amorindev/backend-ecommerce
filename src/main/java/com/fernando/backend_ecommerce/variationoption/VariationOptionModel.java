package com.fernando.backend_ecommerce.variationoption;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fernando.backend_ecommerce.productvariationoption.ProductVariationOptionModel;
import com.fernando.backend_ecommerce.variation.VariationModel;

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

    @JsonIgnore
    @OneToMany(mappedBy = "variationOption")
    private List<ProductVariationOptionModel> productVariationOptionModels;


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

    

    public VariationOptionModel(String value, VariationModel variation) {
        this.value = value;
        this.variation = variation;
    }

    public List<ProductVariationOptionModel> getProductVariationOptionModels() {
        return productVariationOptionModels;
    }

    public void setProductVariationOptionModels(List<ProductVariationOptionModel> productVariationOptionModels) {
        this.productVariationOptionModels = productVariationOptionModels;
    }

    public VariationOptionModel() {
    }
}

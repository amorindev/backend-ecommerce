package com.fernando.backend_ecommerce.variation;

import java.util.List;

import com.fernando.backend_ecommerce.variationoption.VariationOptionModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_variation")
public class VariationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "var_id")
    private Long id;

    @Column(name = "var_name", nullable = false,length = 90)
    private String name;

    @OneToMany(mappedBy = "variation")
    private List<VariationOptionModel> variationsOptions;

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

    public List<VariationOptionModel> getVariationsOptions() {
        return variationsOptions;
    }

    public void setVariationsOptions(List<VariationOptionModel> variationsOptions) {
        this.variationsOptions = variationsOptions;
    }

    public VariationModel(String name) {
        this.name = name;
    }

    public VariationModel() {
    }

    
}

package com.fernando.backend_ecommerce.product;

import java.sql.Timestamp;
import java.util.List;

import com.fernando.backend_ecommerce.productgroup.ProductGroupModel;
import com.fernando.backend_ecommerce.variationoption.VariationOptionModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_product")
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_id")
    private Long id;
    
    @Column(name = "prod_price", nullable = false)
    private double price;

    @Column(name = "prod_stk", nullable = false)
    private int stock;

    @Column(name = "prod_discount", nullable = false)
    private short discount = 0;

    @Column(name = "prod_rating", nullable = false)
    private short rating = 0;

    @Column(name = "prod_img", length = 250)
    private String image;

    @Column(name = "prod_created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "pg_sku", nullable = false, length = 100, unique = true)
    private String sku;

    @ManyToOne
    @JoinColumn(name = "products")
    private ProductGroupModel productGroup;

    @ManyToMany
    @JoinTable(
        name = "product_variationoption",
        joinColumns = @JoinColumn(name = "prod_id"),
        inverseJoinColumns = @JoinColumn(name = "varop_id")
    )
    private List<VariationOptionModel> variationsOptions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public short getDiscount() {
        return discount;
    }

    public void setDiscount(short discount) {
        this.discount = discount;
    }

    public short getRating() {
        return rating;
    }

    public void setRating(short rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public ProductGroupModel getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroupModel productGroup) {
        this.productGroup = productGroup;
    }

    public List<VariationOptionModel> getVariationsOptions() {
        return variationsOptions;
    }

    public void setVariationsOptions(List<VariationOptionModel> variationsOptions) {
        this.variationsOptions = variationsOptions;
    }

    
}

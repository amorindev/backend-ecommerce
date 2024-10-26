package com.fernando.backend_ecommerce.saleproduct;

import com.fernando.backend_ecommerce.product.ProductModel;
import com.fernando.backend_ecommerce.sale.SaleModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_sale_product")
public class SaleProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "saleprod_id")
    private Long id;

    @Column(name = "saleprod_price", nullable = false)
    private Double price;

    @Column(name = "saleprod_quantity", nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false)
    private SaleModel sale;

    @ManyToOne
    @JoinColumn(name = "prod_id", nullable = false)
    private ProductModel product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public SaleModel getSale() {
        return sale;
    }

    public void setSale(SaleModel sale) {
        this.sale = sale;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }    

    
}



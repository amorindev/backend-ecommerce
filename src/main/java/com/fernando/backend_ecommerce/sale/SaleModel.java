package com.fernando.backend_ecommerce.sale;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fernando.backend_ecommerce.saleproduct.SaleProductModel;
import com.fernando.backend_ecommerce.user.UserModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_sale")
public class SaleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id")
    private Long id;

    

    @Column(name = "sale_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @Column(name = "sale_address", nullable = false)
    private String address;

    @Column(name = "sale_payment_id", nullable = false, length = 300)
    private String paymentId;
    
    @Column(name = "sale_created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @JsonIgnore
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<SaleProductModel> saleProducts;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
   

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public List<SaleProductModel> getSaleProducts() {
        return saleProducts;
    }

    public void setSaleProducts(List<SaleProductModel> saleProducts) {
        this.saleProducts = saleProducts;
    }

    
}


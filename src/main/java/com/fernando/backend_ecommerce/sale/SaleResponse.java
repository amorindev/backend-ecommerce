package com.fernando.backend_ecommerce.sale;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class SaleResponse {
    private Long id;
    private BigDecimal total;
    private String address;
    private String paymentId;
    private Timestamp createdAt;
    private List<SaleProductResponse> saleProducts;


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
    public List<SaleProductResponse> getSaleProducts() {
        return saleProducts;
    }
    public void setSaleProducts(List<SaleProductResponse> saleProducts) {
        this.saleProducts = saleProducts;
    }
    public static class SaleProductResponse {
        private Long id;
        private Double price;
        private Integer quantity;
        private ProductSummary product;

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
        public ProductSummary getProduct() {
            return product;
        }
        public void setProduct(ProductSummary product) {
            this.product = product;
        }

        
    }
    public static class ProductSummary {
        private Long id;
        private String sku;
        private String image;
        private double price;
        private Integer quantity;
        public Integer getQuantity() {
            return quantity;
        }
        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }
        private short discount;
        private short rating;
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public String getSku() {
            return sku;
        }
        public void setSku(String sku) {
            this.sku = sku;
        }
        public String getImage() {
            return image;
        }
        public void setImage(String image) {
            this.image = image;
        }
        public double getPrice() {
            return price;
        }
        public void setPrice(double price) {
            this.price = price;
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

        
    }
}

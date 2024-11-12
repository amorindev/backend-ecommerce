package com.fernando.backend_ecommerce.sale;

import java.math.BigDecimal;
import java.util.List;

public class SaleRequest {
    private BigDecimal total;
    private String address;
    private String paymentId;
    private Long userId;
    private List<SaleProductRequest> saleProducts;


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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<SaleProductRequest> getSaleProducts() {
        return saleProducts;
    }

    public void setSaleProducts(List<SaleProductRequest> saleProducts) {
        this.saleProducts = saleProducts;
    }

    public static class SaleProductRequest {
        private Long productId;
        private Integer quantity;
        private Double price;

        public Long getProductId() {
            return productId;
        }
        public void setProductId(Long productId) {
            this.productId = productId;
        }
        public Integer getQuantity() {
            return quantity;
        }
        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }
        public Double getPrice() {
            return price;
        }
        public void setPrice(Double price) {
            this.price = price;
        }
        
    }
}

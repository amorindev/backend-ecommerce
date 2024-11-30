package com.fernando.backend_ecommerce.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernando.backend_ecommerce.product.IProductRepository;
import com.fernando.backend_ecommerce.product.ProductModel;
import com.fernando.backend_ecommerce.saleproduct.SaleProductModel;
import com.fernando.backend_ecommerce.user.UserModel;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleService {
    @Autowired
    ISaleRepository saleRepository;

    @Autowired
    private IProductRepository productRepository; 

    public List<SaleResponse> getSalesByIdDto(Long userId) {
        return saleRepository.findByUserUserId(userId).stream()
        .map(this::mapToSaleResponse)
        .collect(Collectors.toList());
    }

    @Transactional
    public SaleResponse createSaleDto(SaleRequest saleRequest) {
        SaleModel sale = new SaleModel();
        sale.setTotal(saleRequest.getTotal());
        sale.setAddress(saleRequest.getAddress());
        sale.setPaymentId(saleRequest.getPaymentId());
        sale.setUser(new UserModel(saleRequest.getUserId()));

        List<SaleProductModel> saleProducts = saleRequest.getSaleProducts().stream().map(saleProductRequest -> {
            ProductModel product = productRepository.findById(saleProductRequest.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + saleProductRequest.getProductId()));
            
            if (product.getStock() < saleProductRequest.getQuantity()) {
                throw new IllegalArgumentException("Stock insuficiente para el producto: " + product.getId());
            }

            product.setStock(product.getStock() - saleProductRequest.getQuantity());
            productRepository.save(product);

            SaleProductModel saleProduct = new SaleProductModel();
            saleProduct.setProduct(product);
            saleProduct.setQuantity(saleProductRequest.getQuantity());
            saleProduct.setPrice(saleProductRequest.getPrice());
            saleProduct.setSale(sale);

            return saleProduct;
        }).collect(Collectors.toList());

       

        sale.setSaleProducts(saleProducts);
        saleRepository.save(sale);

        return mapToSaleResponse(sale);
    }

    private SaleResponse mapToSaleResponse(SaleModel sale) {
        SaleResponse response = new SaleResponse();
        response.setId(sale.getId());
        response.setTotal(sale.getTotal());
        response.setAddress(sale.getAddress());
        response.setPaymentId(sale.getPaymentId());
        response.setCreatedAt(sale.getCreatedAt());

        response.setSaleProducts(sale.getSaleProducts().stream().map(saleProduct -> {
            SaleResponse.SaleProductResponse saleProductResponse = new SaleResponse.SaleProductResponse();
            saleProductResponse.setId(saleProduct.getId());
            saleProductResponse.setPrice(saleProduct.getPrice());
            saleProductResponse.setQuantity(saleProduct.getQuantity());

            SaleResponse.ProductSummary productSummary = new SaleResponse.ProductSummary();
            productSummary.setId(saleProduct.getProduct().getId());
            productSummary.setSku(saleProduct.getProduct().getSku());
            productSummary.setImage(saleProduct.getProduct().getImage());
            productSummary.setPrice(saleProduct.getProduct().getPrice());
            productSummary.setDiscount(saleProduct.getProduct().getDiscount());
            productSummary.setRating(saleProduct.getProduct().getRating());

            saleProductResponse.setProduct(productSummary);
            return saleProductResponse;
        }).collect(Collectors.toList()));

        return response;
    }
}

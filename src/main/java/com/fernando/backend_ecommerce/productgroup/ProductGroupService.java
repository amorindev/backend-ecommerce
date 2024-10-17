package com.fernando.backend_ecommerce.productgroup;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;


@Service
public class ProductGroupService {
    @Autowired
    IProductGroupRepository productGroupRepository;

    
    public List<ProductGroupResponse> getProductGroupsDto() {

        return productGroupRepository.findAll().stream().map(
            model ->{
                ProductGroupResponse response = new ProductGroupResponse();
                response.setId(model.getId());
                response.setName(model.getName());
                response.setDescription(model.getDescription());
                response.setImage(model.getImage());
                response.setCategory(model.getCategory().getName());

                // crear la ista de productos
                List<ProductGroupResponse.ProductResponse> productResponses = model.getProducts().stream()
                .map(product -> {
                    ProductGroupResponse.ProductResponse productResponse = new ProductGroupResponse.ProductResponse();
                                productResponse.setId(product.getId());
                                productResponse.setPrice(product.getPrice());
                                productResponse.setStock(product.getStock());
                                productResponse.setDiscount(product.getDiscount());
                                productResponse.setRating(product.getRating());
                                productResponse.setImage(product.getImage());
                                productResponse.setCreatedAt(product.getCreatedAt());
                                productResponse.setSku(product.getSku());

                        //Crear la lista de variaciones
                        List<ProductGroupResponse.ProductResponse.VariationOptionResponse> variationsOptions =product.getVariationsOptions().stream().map(
                            option -> {
                                ProductGroupResponse.ProductResponse.VariationOptionResponse variationOptionResponse = new ProductGroupResponse.ProductResponse.VariationOptionResponse();

                                // Extraer el name de la variación

                                variationOptionResponse.setOptionName(option.getVariation().getName());
                                variationOptionResponse.setOptionValue(option.getValue());

                                // Aqui llamas con   option.getVariation().getName()                

                                return variationOptionResponse;
                            }
                        ).collect(Collectors.toList());
                    
                    productResponse.setVariationOptions(variationsOptions);
                    return productResponse;       
                }).collect(Collectors.toList());

                response.setProducts(productResponses);

                List<ProductGroupResponse.OptionResponse> optionResponses = new ArrayList<>();
                
                model.getProducts().forEach(
                    product -> {
                        product.getVariationsOptions().forEach(option -> {
                            ProductGroupResponse.OptionResponse existingOption = optionResponses.stream()
                            .filter(opt -> opt.getId().equals(option.getVariation().getId()))
                            .findFirst()
                            .orElse(null);
                            
                            if (existingOption == null) {
                                // Si no existe, crear una nueva opción
                                ProductGroupResponse.OptionResponse newOption = new ProductGroupResponse.OptionResponse();
                                newOption.setId(option.getVariation().getId());
                                newOption.setName(option.getVariation().getName());
                                newOption.setValues(new ArrayList<>());
                                newOption.getValues().add(option.getValue());
                                optionResponses.add(newOption);
                            } else {
                                // Si existe, agregar el valor si no está ya en la lista
                                if (!existingOption.getValues().contains(option.getValue())) {
                                    existingOption.getValues().add(option.getValue());
                                }
                            }
                        });   
                    });

                response.setOptions(optionResponses);
                

                return response;
            }
        ).collect(Collectors.toList());
    }


    public ProductGroupModel createProductGroupDto(ProductGroupModel productGroupModel) {
        return productGroupRepository.save(productGroupModel);
    }
}

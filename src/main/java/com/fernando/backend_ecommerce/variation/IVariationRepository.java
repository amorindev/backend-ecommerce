package com.fernando.backend_ecommerce.variation;
import java.util.List;

public interface IVariationRepository {
    List<VariationModel> findByProductId(Long productId);
}

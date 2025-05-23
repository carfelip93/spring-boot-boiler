package com.rocketicg.app.application.product.port;

import com.rocketicg.app.application.product.dto.ProductRequest;
import com.rocketicg.app.application.product.dto.ProductResponse;
import java.util.List;
import java.util.Optional;

public interface ProductUseCase {
    ProductResponse createProduct(ProductRequest request);

    Optional<ProductResponse> getProductById(Long id);

    List<ProductResponse> getAllProducts();

    ProductResponse updateProduct(Long id, ProductRequest request);

    ProductResponse updateStock(Long id, Integer stock);

    ProductResponse addStock(Long id, Integer quantity);

    ProductResponse removeStock(Long id, Integer quantity);

    void deleteProduct(Long id);
}
package com.rocketicg.app.domain.product.port;

import com.rocketicg.app.domain.product.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product createProduct(String name, String description, Double price, Integer stock);

    List<Product> getAllProducts();

    Optional<Product> getProductById(Long id);

    Product updateProduct(Long id, String name, String description, Double price);

    Product updateStock(Long id, Integer stock);

    Product addStock(Long id, Integer quantity);

    Product removeStock(Long id, Integer quantity);

    void deleteProduct(Long id);
}
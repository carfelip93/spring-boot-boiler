package com.rocketicg.app.domain.product.port;

import com.rocketicg.app.domain.product.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);

    Optional<Product> findById(Long id);

    List<Product> findAll();

    void delete(Product product);
}
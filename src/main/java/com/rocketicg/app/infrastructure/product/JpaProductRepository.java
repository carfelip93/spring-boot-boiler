package com.rocketicg.app.infrastructure.product;

import com.rocketicg.app.domain.product.repository.ProductRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProductRepository extends ProductRepository {
}
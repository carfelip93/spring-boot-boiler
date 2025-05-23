package com.rocketicg.app.infrastructure.product.persistence;

import com.rocketicg.app.domain.product.Product;
import com.rocketicg.app.domain.product.port.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductRepositoryAdapter implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;

    public ProductRepositoryAdapter(ProductJpaRepository productJpaRepository) {
        this.productJpaRepository = productJpaRepository;
    }

    @Override
    public Product save(Product product) {
        ProductJpaEntity jpaEntity = toJpaEntity(product);
        ProductJpaEntity savedEntity = productJpaRepository.save(jpaEntity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productJpaRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public List<Product> findAll() {
        return productJpaRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Product product) {
        ProductJpaEntity jpaEntity = toJpaEntity(product);
        productJpaRepository.delete(jpaEntity);
    }

    private ProductJpaEntity toJpaEntity(Product product) {
        ProductJpaEntity entity = new ProductJpaEntity();
        entity.setId(product.getId());
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
        entity.setStock(product.getStock());
        return entity;
    }

    private Product toDomain(ProductJpaEntity entity) {
        return new Product(
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getStock());
    }
}
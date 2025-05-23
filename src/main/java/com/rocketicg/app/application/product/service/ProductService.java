package com.rocketicg.app.application.product.service;

import com.rocketicg.app.application.product.dto.ProductRequest;
import com.rocketicg.app.application.product.dto.ProductResponse;
import com.rocketicg.app.application.product.port.ProductUseCase;
import com.rocketicg.app.domain.product.model.Price;
import com.rocketicg.app.domain.product.model.Product;
import com.rocketicg.app.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService implements ProductUseCase {
    private final ProductRepository productRepository;

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        Product product = new Product(
                request.getName(),
                request.getDescription(),
                new Price(request.getPrice()),
                request.getStock(),
                request.getCategory());
        return toResponse(productRepository.save(product));
    }

    @Override
    public Optional<ProductResponse> getProductById(Long id) {
        return productRepository.findById(id)
                .map(this::toResponse);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        product.updateDetails(
                request.getName(),
                request.getDescription(),
                new Price(request.getPrice()));
        return toResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse updateStock(Long id, Integer stock) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        product.updateStock(stock);
        return toResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse addStock(Long id, Integer quantity) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        product.addStock(quantity);
        return toResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse removeStock(Long id, Integer quantity) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        product.removeStock(quantity);
        return toResponse(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product not found");
        }
        productRepository.deleteById(id);
    }

    private ProductResponse toResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice().getAmount());
        response.setStock(product.getStock());
        return response;
    }
}
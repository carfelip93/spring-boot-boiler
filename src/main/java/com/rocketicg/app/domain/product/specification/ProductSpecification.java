package com.rocketicg.app.domain.product.specification;

import com.rocketicg.app.domain.product.model.Product;
import java.util.List;

public interface ProductSpecification {
    boolean isSatisfiedBy(Product product);

    List<Product> filter(List<Product> products);
}

class ProductInStockSpecification implements ProductSpecification {
    @Override
    public boolean isSatisfiedBy(Product product) {
        return product.getStock() > 0;
    }

    @Override
    public List<Product> filter(List<Product> products) {
        return products.stream()
                .filter(this::isSatisfiedBy)
                .toList();
    }
}

class ProductPriceRangeSpecification implements ProductSpecification {
    private final double minPrice;
    private final double maxPrice;

    public ProductPriceRangeSpecification(double minPrice, double maxPrice) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    @Override
    public boolean isSatisfiedBy(Product product) {
        double price = product.getPrice().getAmount().doubleValue();
        return price >= minPrice && price <= maxPrice;
    }

    @Override
    public List<Product> filter(List<Product> products) {
        return products.stream()
                .filter(this::isSatisfiedBy)
                .toList();
    }
}
package com.rocketicg.app.domain.product.event;

import com.rocketicg.app.domain.product.model.Product;
import lombok.Getter;
import java.time.Instant;

@Getter
public class ProductCreatedEvent {
    private final Product product;
    private final Instant timestamp;

    public ProductCreatedEvent(Product product) {
        this.product = product;
        this.timestamp = Instant.now();
    }
}
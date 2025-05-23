package com.rocketicg.app.domain.product.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProductStatistics {
    int totalProducts;
    Price totalValue;
    Price averagePrice;
}
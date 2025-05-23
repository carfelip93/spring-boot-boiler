package com.rocketicg.app.application.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Schema(description = "Response object for product data")
public class ProductResponse {
    @Schema(description = "Product ID", example = "1")
    private Long id;

    @Schema(description = "Product name", example = "Laptop")
    private String name;

    @Schema(description = "Product description", example = "High-performance laptop")
    private String description;

    @Schema(description = "Product price", example = "999.99")
    private BigDecimal price;

    @Schema(description = "Product stock quantity", example = "10")
    private Integer stock;
}
package com.rocketicg.app.application.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Schema(description = "Request object for creating or updating a product")
public class ProductRequest {
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    @Schema(description = "Product name", example = "Laptop")
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    @Schema(description = "Product description", example = "High-performance laptop")
    private String description;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    @Schema(description = "Product price", example = "999.99")
    private BigDecimal price;

    @NotNull(message = "Stock is required")
    @Positive(message = "Stock must be positive")
    @Schema(description = "Product stock quantity", example = "10")
    private Integer stock;

    @NotBlank(message = "Category is required")
    @Schema(description = "Product category", example = "Electronics")
    private String category;
}
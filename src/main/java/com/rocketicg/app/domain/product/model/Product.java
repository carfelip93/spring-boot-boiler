package com.rocketicg.app.domain.product.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 500)
    private String description;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "price_amount", nullable = false))
    })
    private Price price;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private boolean premium;

    @Column(nullable = false)
    private String category;

    public Product(String name, String description, Price price, Integer stock, String category) {
        validateName(name);
        validateStock(stock);
        this.name = name;
        this.description = description;
        this.price = Objects.requireNonNull(price, "Price cannot be null");
        this.stock = stock;
        this.premium = false;
        this.category = Objects.requireNonNull(category, "Category cannot be null");
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (name.length() > 100) {
            throw new IllegalArgumentException("Product name cannot exceed 100 characters");
        }
    }

    private void validateStock(Integer stock) {
        if (stock == null || stock < 0) {
            throw new IllegalArgumentException("Stock cannot be null or negative");
        }
    }

    public void updateDetails(String name, String description, Price price) {
        validateName(name);
        this.name = name;
        this.description = description;
        this.price = Objects.requireNonNull(price, "Price cannot be null");
    }

    public void updatePrice(Price price) {
        this.price = Objects.requireNonNull(price, "Price cannot be null");
    }

    public void updateStock(Integer stock) {
        validateStock(stock);
        this.stock = stock;
    }

    public void addStock(Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        this.stock += quantity;
    }

    public void removeStock(Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if (this.stock < quantity) {
            throw new IllegalStateException("Insufficient stock");
        }
        this.stock -= quantity;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
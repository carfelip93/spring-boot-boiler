package com.rocketicg.app.domain.product.model;

import lombok.Value;
import jakarta.persistence.Embeddable;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Value
@Embeddable
public class Price {
    private static final int SCALE = 2;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    BigDecimal amount;

    public static final Price ZERO = new Price(BigDecimal.ZERO);

    public Price(BigDecimal amount) {
        validateAmount(amount);
        this.amount = amount.setScale(SCALE, ROUNDING_MODE);
    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException("Price amount cannot be null");
        }
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price amount cannot be negative");
        }
        if (amount.scale() > SCALE) {
            throw new IllegalArgumentException("Price amount cannot have more than " + SCALE + " decimal places");
        }
    }

    public Price add(Price other) {
        return new Price(this.amount.add(other.amount));
    }

    public Price subtract(Price other) {
        return new Price(this.amount.subtract(other.amount));
    }

    public Price multiply(int quantity) {
        return new Price(this.amount.multiply(BigDecimal.valueOf(quantity)));
    }

    public Price multiply(double factor) {
        return new Price(this.amount.multiply(BigDecimal.valueOf(factor)));
    }

    public Price divide(int divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return new Price(this.amount.divide(BigDecimal.valueOf(divisor), SCALE, ROUNDING_MODE));
    }
}
package com.rocketicg.app.domain.product.service;

import com.rocketicg.app.domain.product.model.Product;
import com.rocketicg.app.domain.product.model.Price;
import com.rocketicg.app.domain.product.model.ProductStatistics;
import com.rocketicg.app.domain.product.repository.ProductRepository;
import com.rocketicg.app.domain.product.specification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductDomainService {

    private final ProductRepository productRepository;

    /**
     * Calcula el valor total del inventario
     * Esta lógica no pertenece a Product ni a Price porque:
     * 1. Requiere acceder a todos los productos
     * 2. Realiza un cálculo agregado
     * 3. No es responsabilidad de una entidad individual
     */
    public Price calculateTotalInventoryValue() {
        return productRepository.findAll().stream()
                .map(product -> product.getPrice().multiply(product.getStock()))
                .reduce(Price.ZERO, Price::add);
    }

    /**
     * Encuentra productos que cumplen con múltiples especificaciones
     * Esta lógica no pertenece a Product porque:
     * 1. Coordina múltiples especificaciones
     * 2. Implementa reglas de búsqueda complejas
     * 3. No es responsabilidad de una entidad individual
     */
    public List<Product> findProductsBySpecifications(List<ProductSpecification> specifications) {
        return productRepository.findAll().stream()
                .filter(product -> specifications.stream()
                        .allMatch(spec -> spec.isSatisfiedBy(product)))
                .toList();
    }

    /**
     * Aplica reglas de negocio para determinar si un producto puede ser eliminado
     * Esta lógica no pertenece a Product porque:
     * 1. Implementa reglas de negocio complejas
     * 2. Puede involucrar validaciones externas
     * 3. No es responsabilidad de una entidad individual
     */
    public boolean canDeleteProduct(Product product) {
        // Ejemplo: No se puede eliminar si tiene stock
        if (product.getStock() > 0) {
            return false;
        }

        // Ejemplo: No se puede eliminar si es un producto premium
        if (product.isPremium()) {
            return false;
        }

        return true;
    }

    /**
     * Calcula estadísticas de productos
     * Esta lógica no pertenece a Product porque:
     * 1. Realiza cálculos agregados
     * 2. Procesa múltiples productos
     * 3. No es responsabilidad de una entidad individual
     */
    public ProductStatistics calculateProductStatistics() {
        List<Product> products = productRepository.findAll();

        return ProductStatistics.builder()
                .totalProducts(products.size())
                .totalValue(calculateTotalInventoryValue())
                .averagePrice(calculateAveragePrice(products))
                .build();
    }

    private Price calculateAveragePrice(List<Product> products) {
        if (products.isEmpty()) {
            return Price.ZERO;
        }

        Price total = products.stream()
                .map(Product::getPrice)
                .reduce(Price.ZERO, Price::add);

        return total.divide(products.size());
    }

    /**
     * Verifica si hay suficientes productos en stock para una orden
     */
    public boolean hasEnoughStockForOrder(Long productId, int quantity) {
        return productRepository.findById(productId)
                .map(product -> product.getStock() >= quantity)
                .orElse(false);
    }

    /**
     * Aplica un descuento a todos los productos de una categoría
     */
    public void applyDiscountToCategory(String category, double discountPercentage) {
        if (discountPercentage < 0 || discountPercentage > 100) {
            throw new IllegalArgumentException("Discount percentage must be between 0 and 100");
        }

        List<Product> products = productRepository.findByCategory(category);
        products.forEach(product -> {
            Price discountedPrice = product.getPrice().multiply(1 - (discountPercentage / 100));
            product.updatePrice(discountedPrice);
            productRepository.save(product);
        });
    }
}
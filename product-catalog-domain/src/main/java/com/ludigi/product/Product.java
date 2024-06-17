package com.ludigi.product;

import java.util.Objects;
import java.util.UUID;

public class Product {
    private ProductId id;
    private String name;
    private String description;

    private Product(ProductId id, String title, String description) {
        this.id = id;
        this.name = title;
        this.description = description;
    }

    public static Product withoutId(String title, String description) {
        return new Product(
                new ProductId(UUID.randomUUID().toString()),
                title,
                description
        );
    }

    public static Product withId(ProductId productId,
                                 String title,
                                 String description) {
        return new Product(productId, title, description);
    }

    public ProductId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

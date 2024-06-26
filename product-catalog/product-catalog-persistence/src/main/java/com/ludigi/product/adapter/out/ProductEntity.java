package com.ludigi.product.adapter.out;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "product")
class ProductEntity {
    @Id
    private UUID id;
    private String name;
    private String description;
    private UUID addedBy;

    public ProductEntity() {
    }

    public ProductEntity(UUID id, String name, String description, UUID addedBy) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.addedBy = addedBy;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(UUID addedBy) {
        this.addedBy = addedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(addedBy, that.addedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, addedBy);
    }
}

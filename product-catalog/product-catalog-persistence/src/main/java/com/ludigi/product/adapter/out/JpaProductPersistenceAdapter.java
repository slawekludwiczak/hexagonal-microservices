package com.ludigi.product.adapter.out;

import com.ludigi.product.Product;
import com.ludigi.product.port.out.ProductPersistencePort;

import java.util.Optional;
import java.util.UUID;

public class JpaProductPersistenceAdapter implements ProductPersistencePort {
    private final ProductEntityRepository productEntityRepository;

    public JpaProductPersistenceAdapter(ProductEntityRepository productEntityRepository) {
        this.productEntityRepository = productEntityRepository;
    }

    @Override
    public void save(Product product) {
        productEntityRepository.save(ProductEntityMapper.from(product));
    }

    @Override
    public Optional<Product> findById(String id) {
        return productEntityRepository.findById(UUID.fromString(id))
                .map(ProductEntityMapper::to);
    }
}

package com.ludigi.priceflow.product.adapter.out;

import com.ludigi.priceflow.product.Product;
import com.ludigi.priceflow.product.port.out.ProductPersistencePort;

import java.util.List;
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

    @Override
    public List<Product> findAll() {
        return productEntityRepository.findAll()
                .stream()
                .map(ProductEntityMapper::to)
                .toList();
    }
}

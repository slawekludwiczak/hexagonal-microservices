package com.ludigi.product.adapter.out;

import com.ludigi.product.Product;
import com.ludigi.product.port.out.ProductPersistPort;

public class JpaProductPersistenceAdapter implements ProductPersistPort {
    private final ProductEntityRepository productEntityRepository;

    public JpaProductPersistenceAdapter(ProductEntityRepository productEntityRepository) {
        this.productEntityRepository = productEntityRepository;
    }

    @Override
    public void save(Product product) {
        productEntityRepository.save(ProductEntityMapper.from(product));
    }
}

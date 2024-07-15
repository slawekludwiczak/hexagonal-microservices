package com.ludigi.priceflow.offer.crud.port.in;

import com.ludigi.priceflow.offer.common.vo.ProductId;
import com.ludigi.priceflow.offer.crud.port.out.ProductCrudPersistencePort;

public class AddProductUseCase {
    private final ProductCrudPersistencePort productCrudPersistencePort;

    public AddProductUseCase(ProductCrudPersistencePort productCrudPersistencePort) {
        this.productCrudPersistencePort = productCrudPersistencePort;
    }

    public void addProduct(ProductId product) {
        productCrudPersistencePort.save(product);
    }
}

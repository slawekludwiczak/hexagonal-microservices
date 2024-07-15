package com.ludigi.priceflow.offer.crud.port.out;

import com.ludigi.priceflow.offer.common.vo.ProductId;

public interface ProductCrudPersistencePort {
    void save(ProductId product);
}

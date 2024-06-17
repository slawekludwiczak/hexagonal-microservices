package com.ludigi.product.adapter.out;

import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface ProductEntityRepository extends CrudRepository<ProductEntity, UUID> {
}

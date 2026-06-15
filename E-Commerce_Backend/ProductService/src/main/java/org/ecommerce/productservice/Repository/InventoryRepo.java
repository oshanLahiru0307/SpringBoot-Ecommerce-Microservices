package org.ecommerce.productservice.Repository;

import org.ecommerce.productservice.Model.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepo extends JpaRepository<InventoryEntity, Integer> {
}

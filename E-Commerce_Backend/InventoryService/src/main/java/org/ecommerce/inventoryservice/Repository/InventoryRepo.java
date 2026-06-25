package org.ecommerce.inventoryservice.Repository;

import org.ecommerce.inventoryservice.Model.InventoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepo extends JpaRepository<InventoryModel, Integer> {
}

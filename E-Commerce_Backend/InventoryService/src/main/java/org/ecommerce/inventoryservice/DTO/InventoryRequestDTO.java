package org.ecommerce.inventoryservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryRequestDTO {

    private Integer productId;
    private String productName;
    private Integer quantity;
}

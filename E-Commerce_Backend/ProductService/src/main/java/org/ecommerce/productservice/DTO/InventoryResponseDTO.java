package org.ecommerce.productservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryResponseDTO {
    private Integer id;
    private String productName;
    private Integer quantity;
    private Float price;
}

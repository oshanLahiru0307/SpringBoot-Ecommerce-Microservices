package org.ecommerce.cartservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemRequestDTO {

    private Integer cartId;
    private String productId;
    private Integer quantity;
    private Double price;
}

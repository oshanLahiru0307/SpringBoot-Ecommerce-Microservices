package org.ecommerce.cartservice.Controller;

import org.ecommerce.cartservice.DTO.CartItemRequestDTO;
import org.ecommerce.cartservice.DTO.CartItemResponseDTO;
import org.ecommerce.cartservice.Service.CartItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/cart")
@CrossOrigin
public class CartController {

    private final CartItemService cartItemService;

    public CartController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    //get all cart items of particular user
    @GetMapping("/getCartItems/{userId}")
    public ResponseEntity<?> getAllCartItems(@PathVariable Integer userId){
        List<CartItemResponseDTO> cartItems = cartItemService.getAllCartItems(userId);
        if(cartItems != null){
            return ResponseEntity.ok().body(cartItems);
        }
        return ResponseEntity.notFound().build();
    }

    //api endpoint for add item into cart
    @PostMapping("/addItem/{userId}")
    public ResponseEntity<?> addItemIntoCart (@PathVariable Integer userId, @RequestBody CartItemRequestDTO cartItemRequestDTO) {
        CartItemResponseDTO response = cartItemService.addItemToCart(userId, cartItemRequestDTO);
        if(response == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(response);
    }

    //remove item from cart
    @DeleteMapping("/removeItem/{cartItemId}")
    public ResponseEntity<?> removeCartItemByItemId(@PathVariable Integer cartItemId) {
        String response = cartItemService.removeItem(cartItemId);
        if (response.contains("not found")) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(response);
    }
}

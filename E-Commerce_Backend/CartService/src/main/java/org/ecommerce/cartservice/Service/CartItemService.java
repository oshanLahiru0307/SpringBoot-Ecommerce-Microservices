package org.ecommerce.cartservice.Service;

import org.ecommerce.cartservice.DTO.CartItemRequestDTO;
import org.ecommerce.cartservice.DTO.CartItemResponseDTO;
import org.ecommerce.cartservice.Model.CartEntity;
import org.ecommerce.cartservice.Model.CartItemsEntity;
import org.ecommerce.cartservice.Repository.CartItemRepo;
import org.ecommerce.cartservice.Repository.CartRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {

    private final CartItemRepo cartItemRepo;
    private final CartRepo cartRepo;
    private final ModelMapper modelMapper;

    public CartItemService(CartItemRepo cartItemRepo, CartRepo cartRepo, ModelMapper modelMapper){
        this.cartItemRepo = cartItemRepo;
        this.cartRepo = cartRepo;
        this.modelMapper = modelMapper;
    }

    //add items to cart
    public CartItemResponseDTO addItemToCart(Integer userId, CartItemRequestDTO cartItemRequestDTO){

        CartEntity cart = cartRepo.findById(cartItemRequestDTO.getCartId()).orElse(null);
        if(cart == null){
            CartEntity newCart = new CartEntity();
            newCart.setCustomerId(userId);
            cartRepo.save(newCart);
            CartItemsEntity cartItem = new CartItemsEntity();
            cartItem.setCartId(newCart.getCartId());
            cartItem.setQuantity(cartItemRequestDTO.getQuantity());
            cartItem.setProductId(cartItemRequestDTO.getProductId());
            cartItem.setUnitPrice(cartItemRequestDTO.getUnitPrice());
            return modelMapper.map(cartItemRepo.save(cartItem), CartItemResponseDTO.class);
        }else{
            CartItemsEntity cartItem = modelMapper.map(cartItemRequestDTO, CartItemsEntity.class);
            return modelMapper.map(cartItemRepo.save(cartItem), CartItemResponseDTO.class);
        }



    }

}

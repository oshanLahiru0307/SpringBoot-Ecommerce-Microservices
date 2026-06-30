package org.ecommerce.cartservice.Service;

import org.ecommerce.cartservice.DTO.CartItemRequestDTO;
import org.ecommerce.cartservice.DTO.CartItemResponseDTO;
import org.ecommerce.cartservice.Model.CartEntity;
import org.ecommerce.cartservice.Model.CartItemsEntity;
import org.ecommerce.cartservice.Repository.CartItemRepo;
import org.ecommerce.cartservice.Repository.CartRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    //get all cart items of user
    public List<CartItemResponseDTO> getAllCartItems(Integer userId){
        try{
            CartEntity cart = cartRepo.findByUserId(userId).orElse(null);
            assert cart != null;
            Integer cartId = cart.getCartId();
            List<CartItemsEntity> cartItems = cartItemRepo.findByCartId(cartId);
            if(cartItems == null || cartItems.isEmpty()){
                System.out.println("CartItems is empty");
                return null;
            }
            return modelMapper.map(cartItems, new TypeToken<CartItemResponseDTO>(){}.getType());
        }catch(Exception e){
            System.out.println("Error while fetching cart items: " + e.getMessage());
            return null;
        }
    }

    //add items to cart
    public CartItemResponseDTO addItemToCart(Integer userId, CartItemRequestDTO cartItemRequestDTO){

        try{
            CartItemsEntity newItem = modelMapper.map(cartItemRequestDTO, CartItemsEntity.class);
            CartEntity cart = cartRepo.findById(cartItemRequestDTO.getCartId()).orElse(null);
            if(cart == null){
                CartEntity newCart = new CartEntity();
                newCart.setUserId(userId);
                cartRepo.save(newCart);
                newItem.setCartId(newCart.getCartId());
                CartItemsEntity cartItem = cartItemRepo.save(newItem);
                return modelMapper.map(cartItemRepo.save(cartItem), CartItemResponseDTO.class);
            }else{
                CartItemsEntity cartItem = modelMapper.map(cartItemRequestDTO, CartItemsEntity.class);
                return modelMapper.map(cartItemRepo.save(cartItem), CartItemResponseDTO.class);
            }
        }catch(Exception e){
            System.out.println("Failed to add item into cart: " + e.getMessage());
            return null;
        }

    }


    //remove item from cart
    public String removeItem(Integer cartItemId){
        try{
            CartItemsEntity cartItem = cartItemRepo.findById(cartItemId).orElse(null);
            if(cartItem != null){
                cartItemRepo.delete(cartItem);
                return "Item Removed";
            }
            return "Item Not Found";
        }catch(Exception e){
            System.out.println("Failed to remove item from cart: " + e.getMessage());
            return null;
        }
    }


}

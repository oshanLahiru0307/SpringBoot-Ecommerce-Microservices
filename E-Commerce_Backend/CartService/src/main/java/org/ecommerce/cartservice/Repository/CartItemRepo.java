package org.ecommerce.cartservice.Repository;

import org.ecommerce.cartservice.Model.CartEntity;
import org.ecommerce.cartservice.Model.CartItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepo extends JpaRepository<CartItemsEntity, Integer> {
    List<CartItemsEntity> findByCartId(Integer cartId);
}

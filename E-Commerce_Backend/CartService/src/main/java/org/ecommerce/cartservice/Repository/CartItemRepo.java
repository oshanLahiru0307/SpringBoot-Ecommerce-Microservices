package org.ecommerce.cartservice.Repository;

import org.ecommerce.cartservice.Model.CartItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepo extends JpaRepository<CartItemsEntity, Integer> {
}

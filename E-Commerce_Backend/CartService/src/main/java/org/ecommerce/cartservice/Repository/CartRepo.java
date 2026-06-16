package org.ecommerce.cartservice.Repository;

import org.ecommerce.cartservice.Model.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<CartEntity, Integer> {
}

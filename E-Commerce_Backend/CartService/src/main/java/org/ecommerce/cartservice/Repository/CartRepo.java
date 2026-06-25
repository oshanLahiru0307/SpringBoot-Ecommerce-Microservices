package org.ecommerce.cartservice.Repository;

import org.ecommerce.cartservice.Model.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<CartEntity, Integer> {
    Optional<CartEntity> findByUserId(Integer userId);
}

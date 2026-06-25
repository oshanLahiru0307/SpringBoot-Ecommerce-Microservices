package org.ecommerce.productservice.Repository;

import org.ecommerce.productservice.Model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<ProductEntity, Integer> {

}

package org.ecommerce.productservice.Repository;

import org.ecommerce.productservice.Model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<CategoryEntity, Integer> {
        CategoryEntity findByName(String name);
}

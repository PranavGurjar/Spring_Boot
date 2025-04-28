package com.multiple.db.postgresql.repo;

import com.multiple.db.postgresql.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Integer> {
    Product findByName(String name);
}

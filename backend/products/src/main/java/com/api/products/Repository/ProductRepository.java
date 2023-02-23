package com.api.products.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.products.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    
}

package com.api.products.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.products.Repository.ProductRepository;
import com.api.products.model.Product;
import com.api.products.model.ResponseProduct;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    @Autowired
    private ResponseProduct responseProduct;

    public Iterable<Product> findAllProducts() {
        return repository.findAll();
    }

    public ResponseEntity<?> insertOrUpdate(Product product, String action) {

        if (product.getName().isBlank()) {
            responseProduct.setMessage("Product name is mandatory!");
            return new ResponseEntity<ResponseProduct>(responseProduct, HttpStatus.BAD_REQUEST);
        } else if (product.getBrand().isBlank()) {
            responseProduct.setMessage("Brand of product is mandatory!");
            return new ResponseEntity<>(responseProduct, HttpStatus.BAD_REQUEST);
        } else {
            if (action.equals("insert")) {
                return new ResponseEntity<>(repository.save(product), HttpStatus.CREATED);
            } else {
                Optional<Product> productFound = repository.findById(product.getId());
                if (productFound.isEmpty() || product == null) {
                    responseProduct.setMessage("The product don't exist in the database!");
                    return new ResponseEntity<>(responseProduct, HttpStatus.NOT_FOUND);
                } else {
                    product.setId(product.getId());
                    return new ResponseEntity<>(repository.save(product), HttpStatus.OK);
                }

            }
        }
    }

    public ResponseEntity<?> delete(Long id) {
        Optional<Product> productFound = repository.findById(id);
        if (productFound.isEmpty()) {
            responseProduct.setMessage("The product don't exist in the database!");
            return new ResponseEntity<>(responseProduct, HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
        responseProduct.setMessage("Product was deleted");
        return new ResponseEntity<ResponseProduct>(responseProduct, HttpStatus.OK);
    }

    public Optional<Product> findById(long id) {
        return repository.findById(id);
    }
}

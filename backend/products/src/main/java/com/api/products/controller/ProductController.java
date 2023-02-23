package com.api.products.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.products.Service.ProductService;
import com.api.products.model.Product;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*" )
public class ProductController {
    
    @Autowired
    private ProductService service;

    @GetMapping
    public Iterable<Product> getAllProducts() {
        return service.findAllProducts();
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Product product) {
        return service.insertOrUpdate(product, "update");
    }

    @PostMapping("/register")
    public ResponseEntity<?> insert(@RequestBody Product product) {
        return service.insertOrUpdate(product, "insert");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        return service.delete(id);
    }

    @GetMapping("/")
    public String route() {
        return "API of products is working!";
    }
}

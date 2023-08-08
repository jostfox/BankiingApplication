package org.example.controller;

import org.example.entity.Product;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    List<Product> getAll() {
        return productService.getAll();
    }

    @PostMapping
    ResponseEntity<Product> newProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.newProduct(product));
    }
}

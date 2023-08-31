package org.example.controller;

import org.example.converter.Converter;
import org.example.dto.ProductDto;
import org.example.entity.Product;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private Converter<Product, ProductDto> productConverter;

    @GetMapping
    List<ProductDto> getAll() {
        return productService.getAll().stream()
                .map(product -> productConverter.toDto(product))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    ProductDto getById(@PathVariable("id") Long id){
        return productConverter.toDto(productService.getById(id));
    }

    @PostMapping
    ResponseEntity<ProductDto> add(@RequestBody ProductDto product) {
        return ResponseEntity.ok(productConverter.toDto(productService
                .create(productConverter.toEntity(product))));
    }
}

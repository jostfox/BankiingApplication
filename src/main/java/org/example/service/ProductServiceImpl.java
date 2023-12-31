package org.example.service;

import org.example.entity.Product;
import org.example.exceptions.ItemNotFoundException;
import org.example.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new ItemNotFoundException(String.format("Product with id %d not found", id)));
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }
}

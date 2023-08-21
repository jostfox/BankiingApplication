package org.example.service;

import org.example.entity.Client;
import org.example.entity.Product;
import org.example.exceptions.ItemNotFoundException;
import org.example.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null){
            throw new ItemNotFoundException(String.format("Product with id %d not found", id));
        }
        return productRepository.getReferenceById(id);
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }
}

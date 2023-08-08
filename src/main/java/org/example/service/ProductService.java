package org.example.service;

import org.example.entity.Product;

import java.util.List;

public interface ProductService {

    public List<Product> getAll();

    public Product newProduct(Product product);
}

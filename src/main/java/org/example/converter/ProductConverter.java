package org.example.converter;


import org.example.dto.ManagerDto;
import org.example.dto.ProductDto;
import org.example.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter implements Converter<Product, ProductDto> {
    @Override
    public ProductDto toDto(Product product) {
        return new ProductDto(product.getId(), product.getName(),
                new ManagerDto(product.getManager().getId(),
                        product.getManager().getFirstName(),
                        product.getManager().getLastName(), null,null, null, null, null));
    }

    @Override
    public Product toEntity(ProductDto product) {
        return null;
    }
}

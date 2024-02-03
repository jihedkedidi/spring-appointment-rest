package com.agil.admin.service;

import com.agil.admin.model.Product;
import com.agil.admin.repository.ProductRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id);
    }


    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + id));
    }

    // other methods as needed
}

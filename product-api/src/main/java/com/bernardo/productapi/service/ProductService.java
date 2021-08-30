package com.bernardo.productapi.service;

import com.bernardo.productapi.dto.ProductDTO;
import com.bernardo.productapi.model.Product;
import com.bernardo.productapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<ProductDTO> getAll(){
        List<Product> products = productRepository.findAll();

        return products.stream().map(ProductDTO::convert).collect(Collectors.toList());
    }

    public List<ProductDTO> getproductByCategoryId(Long categoryId) {
        List<Product> products = productRepository.getProductByCategory(categoryId);

        return products.stream().map(ProductDTO::convert).collect(Collectors.toList());
    }

    public ProductDTO findByproductIdentifier(String productIdentifier) {
        Product product = productRepository.findByProductIdentifier(productIdentifier);

        if (product != null) {
            return DTOConverter.convert(product);
        }
        return null;
    }
    public ProductDTO save(ProductDTO productDTO) {
        Product product = productRepository.save(Product.convert(productDTO));

        return ProductDTO.convert(product);
    }

    public ProductDTO delete(Long productId) throws Exception{
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()){
            productRepository.delete(product.get());
        }
        return null;
    }
}

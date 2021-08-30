package com.bernardo.productapi.controller;

import com.bernardo.productapi.dto.ProductDTO;
import com.bernardo.productapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(name = "/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public List<ProductDTO> getProducts(){
        List<ProductDTO> productDTO = productService.getAll();

        return productDTO;
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductDTO> getProductByCategory(@PathVariable Long categoryId) {
        List<ProductDTO> productDTO = productService.getproductByCategoryId(categoryId);

        return productDTO;
    }

    @GetMapping("/{productIdentifier}")
    public ProductDTO findById(@PathVariable String productIdentifier) {
        return productService.findByproductIdentifier(productIdentifier);
    }

    @PostMapping
    public ProductDTO newProduct(@Valid @RequestBody ProductDTO productDTO) {
        return productService.save(productDTO);
    }

    @DeleteMapping("/{id}")
    public ProductDTO delete(@PathVariable Long id) throws Exception {
        return productService.delete(id);
    }
}

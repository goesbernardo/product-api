package com.bernardo.productapi.controller;

import com.bernardo.productapi.dto.CategoryDTO;
import com.bernardo.productapi.dto.ProductDTO;
import com.bernardo.productapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping()
    public List<ProductDTO> getProducts(){

        List<ProductDTO> dtoList = productService.getAll();

        return ResponseEntity.ok().body(dtoList).getBody();
    }
    @GetMapping("/category/{categoryId}")
    public List<CategoryDTO> getProductByCategory(@PathVariable Long categoryId) {

        return productService.getproductByCategoryId(categoryId);
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

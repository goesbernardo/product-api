package com.bernardo.productapi.service;

import com.bernardo.productapi.dto.CategoryDTO;
import com.bernardo.productapi.dto.ProductDTO;
import com.bernardo.productapi.model.Category;
import com.bernardo.productapi.model.Product;
import com.bernardo.productapi.repository.CategoryRepository;
import com.bernardo.productapi.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private ProductService productServiceUnderTest;

    @BeforeEach
    void setUp() {
        productServiceUnderTest = new ProductService();
        productServiceUnderTest.productRepository = mock(ProductRepository.class);
        productServiceUnderTest.categoryRepository = mock(CategoryRepository.class);
    }

    @Test
    void testGetAll() {
        // Setup
        final ProductDTO productDTO = new ProductDTO();
        productDTO.setProductIdentifier("productIdentifier");
        productDTO.setNome("nome");
        productDTO.setPreco(0.0f);
        productDTO.setDescricao("descricao");
        final CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(0L);
        categoryDTO.setNome("nome");
        productDTO.setCategoryDTO(categoryDTO);
        final List<ProductDTO> expectedResult = List.of(productDTO);

        // Configure ProductRepository.findAll(...).
        final Product product = new Product();
        product.setNome("nome");
        product.setPreco(0.0f);
        product.setDescricao("descricao");
        product.setProductIdentifier("productIdentifier");
        final Category category = new Category();
        category.setId(0L);
        category.setNome("nome");
        product.setCategory(category);
        final List<Product> products = List.of(product);
        when(productServiceUnderTest.productRepository.findAll()).thenReturn(products);

        // Run the test
        final List<ProductDTO> result = productServiceUnderTest.getAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAll_ProductRepositoryReturnsNoItems() {
        // Setup
        when(productServiceUnderTest.productRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<ProductDTO> result = productServiceUnderTest.getAll();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetproductByCategoryId() {
        // Setup
        final List<CategoryDTO> expectedResult = List.of(new CategoryDTO(0L, "nome"));
        when(productServiceUnderTest.categoryRepository.findById(0L)).thenReturn(Optional.of(new Category(0L, "nome")));

        // Run the test
        final List<CategoryDTO> result = productServiceUnderTest.getproductByCategoryId(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetproductByCategoryId_CategoryRepositoryReturnsAbsent() {
        // Setup
        when(productServiceUnderTest.categoryRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final List<CategoryDTO> result = productServiceUnderTest.getproductByCategoryId(0L);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSave() {
        // Setup
        final ProductDTO productDTO = new ProductDTO();
        productDTO.setProductIdentifier("productIdentifier");
        productDTO.setNome("nome");
        productDTO.setPreco(0.0f);
        productDTO.setDescricao("descricao");
        final CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(0L);
        categoryDTO.setNome("nome");
        productDTO.setCategoryDTO(categoryDTO);

        final ProductDTO expectedResult = new ProductDTO();
        expectedResult.setProductIdentifier("productIdentifier");
        expectedResult.setNome("nome");
        expectedResult.setPreco(0.0f);
        expectedResult.setDescricao("descricao");
        final CategoryDTO categoryDTO1 = new CategoryDTO();
        categoryDTO1.setId(0L);
        categoryDTO1.setNome("nome");
        expectedResult.setCategoryDTO(categoryDTO1);

        // Configure ProductRepository.save(...).
        final Product product = new Product();
        product.setNome("nome");
        product.setPreco(0.0f);
        product.setDescricao("descricao");
        product.setProductIdentifier("productIdentifier");
        final Category category = new Category();
        category.setId(0L);
        category.setNome("nome");
        product.setCategory(category);
        final Product s = new Product();
        s.setNome("nome");
        s.setPreco(0.0f);
        s.setDescricao("descricao");
        s.setProductIdentifier("productIdentifier");
        final Category category1 = new Category();
        category1.setId(0L);
        category1.setNome("nome");
        s.setCategory(category1);
        when(productServiceUnderTest.productRepository.save(s)).thenReturn(product);

        // Run the test
        final ProductDTO result = productServiceUnderTest.save(productDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDelete() throws Exception {
        // Setup
        // Configure ProductRepository.findById(...).
        final Product product1 = new Product();
        product1.setNome("nome");
        product1.setPreco(0.0f);
        product1.setDescricao("descricao");
        product1.setProductIdentifier("productIdentifier");
        final Category category = new Category();
        category.setId(0L);
        category.setNome("nome");
        product1.setCategory(category);
        final Optional<Product> product = Optional.of(product1);
        when(productServiceUnderTest.productRepository.findById(0L)).thenReturn(product);

        // Run the test
        final ProductDTO result = productServiceUnderTest.delete(0L);

        // Verify the results
        assertThat(result).isNull();

        // Confirm ProductRepository.delete(...).
        final Product t = new Product();
        t.setNome("nome");
        t.setPreco(0.0f);
        t.setDescricao("descricao");
        t.setProductIdentifier("productIdentifier");
        final Category category1 = new Category();
        category1.setId(0L);
        category1.setNome("nome");
        t.setCategory(category1);
        verify(productServiceUnderTest.productRepository).delete(t);
    }

    @Test
    void testDelete_ProductRepositoryFindByIdReturnsAbsent() throws Exception {
        // Setup
        when(productServiceUnderTest.productRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final ProductDTO result = productServiceUnderTest.delete(0L);

        // Verify the results
        assertThat(result).isNull();
    }
}

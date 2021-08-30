package com.bernardo.productapi.model;

import com.bernardo.productapi.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private float preco;
    private String descricao;
    private String productIdentifier;

    @ManyToOne
    @JoinColumn
    private  Category category;

    public static Product convert(ProductDTO productDTO) {
        Product product = new Product();
        product.setNome(productDTO.getNome());
        product.setPreco(productDTO.getPreco());
        product.setDescricao(productDTO.getDescricao());
        product.setProductIdentifier(productDTO.getProductIdentifier());

        if (productDTO.getCategoryDTO() != null) {
            product.setCategory(Category.convert(productDTO.getCategoryDTO()));
        }
        return product;
    }


}

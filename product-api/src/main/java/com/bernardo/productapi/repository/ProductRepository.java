package com.bernardo.productapi.repository;

import com.bernardo.productapi.model.Category;
import com.bernardo.productapi.model.Product;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {


    List<Product> getProductByCategory(Long category);


}

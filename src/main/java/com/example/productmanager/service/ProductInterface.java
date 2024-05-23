package com.example.productmanager.service;

import com.example.productmanager.model.Product;

import java.util.List;

public interface ProductInterface {
List<Product> searchByName( String keyword);
    List<Product> getAllProduct();
   void saveProduct(Product product);
    void deleteProductById( Integer id);
    Product findProductById(Integer id);
}

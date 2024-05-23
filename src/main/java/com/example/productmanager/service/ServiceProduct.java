package com.example.productmanager.service;

import com.example.productmanager.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ServiceProduct implements ProductInterface {
    private static final List<Product> products = new ArrayList<>();

    static {
        Product product1 = new Product(1, "Iphone 12", 1000.0, 10, "New", "https://cdn.tgdd.vn/Products/Images/42/213031/iphone-12-xanh-duong-new-600x600-600x600.jpg");
        Product product2 = new Product(2, "Iphone 11", 900.0, 20, "New", "https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/5/_/5_158.jpg");
        Product product3 = new Product(3, "Iphone 10", 800.0, 30, "New", "https://cdn.tgdd.vn/Products/Images/42/114115/iphone-x-64gb-bac-org.png");
        products.add(product1);
        products.add(product2);
        products.add(product3);
    }

    @Override
    public List<Product> searchByName(String keyword) {
        return products.stream().filter(product -> product.getName().toLowerCase().contains(keyword.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public List<Product> getAllProduct() {
        return products;
    }

    @Override
    public void saveProduct(Product product) {
        if (product.getId() == null) {
            product.setId(getNewId());
            products.add(product);
        } else {
            products.set(products.indexOf(findProductById(product.getId())), product);
        }
    }

    @Override
    public void deleteProductById(Integer id) {
        products.remove(findProductById(id));
    }

    @Override
    public Product findProductById(Integer id) {
        return products.stream().filter(product -> Objects.equals(product.getId(), id)).findFirst().orElse(null);
    }

    private Integer getNewId() {
        return products.stream().map(Product::getId).max(Integer::compare).orElse(0) + 1;
    }
}

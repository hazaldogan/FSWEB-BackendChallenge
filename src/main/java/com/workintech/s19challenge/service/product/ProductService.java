package com.workintech.s19challenge.service.product;

import com.workintech.s19challenge.entity.product.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(long id);
    Product save(Product product);
    Product delete(long id);
}

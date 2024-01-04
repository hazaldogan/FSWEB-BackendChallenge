package com.workintech.s19challenge.repository.product;

import com.workintech.s19challenge.entity.product.Category;
import com.workintech.s19challenge.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
}

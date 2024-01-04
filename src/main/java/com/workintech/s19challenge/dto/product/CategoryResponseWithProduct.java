package com.workintech.s19challenge.dto.product;

import com.workintech.s19challenge.entity.product.Product;

import java.util.List;

public record CategoryResponseWithProduct(long id, String name, char gender, List<Product> productList) {
}

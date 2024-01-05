package com.workintech.s19challenge.dto.order;

import com.workintech.s19challenge.entity.product.Product;
import com.workintech.s19challenge.entity.user.Address;

import java.util.List;

public record OrderResponse(long id, Address address, List<Product> productList) {
}
